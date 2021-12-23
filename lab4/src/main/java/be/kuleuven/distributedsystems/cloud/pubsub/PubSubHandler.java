package be.kuleuven.distributedsystems.cloud.pubsub;

import be.kuleuven.distributedsystems.cloud.pubsub.requests.PubSubRequest;
import com.google.api.core.ApiFuture;
import com.google.api.core.ApiFutureCallback;
import com.google.api.core.ApiFutures;
import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.api.gax.rpc.ApiException;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.pubsub.v1.*;
import com.google.common.collect.ImmutableMap;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.gson.Gson;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;


@Component
public class PubSubHandler {

    @Autowired
    private String projectId;

    @Autowired
    private String pubSubEndpoint;

    @Autowired
    private boolean isProduction;

    private CredentialsProvider pubSubCredentialsProvider = null;

    private TransportChannelProvider channelProvider = null;

    public PubSubHandler() {
    }

    @PostConstruct
    public void init() {
        System.out.println("Project id: " + projectId);
        System.out.println("PubSubEndpoint: " + pubSubEndpoint);
        System.out.println("Is production: " + isProduction);
        if(!isProduction) {
            this.channelProvider = getChannelProvider();
            this.pubSubCredentialsProvider = NoCredentialsProvider.create();
        }
    }

    /**
     * Creates topic.
     * @param topicId id of the topic
     */
    public void createTopic(String topicId) throws IOException {
        TopicAdminClient topicAdminClient = TopicAdminClient.create(TopicAdminSettings.newBuilder()
                .setTransportChannelProvider(channelProvider)
                .setCredentialsProvider(pubSubCredentialsProvider)
                .build());

        TopicName topicName = TopicName.of(projectId, topicId);

        try{
            Topic topic =
                    topicAdminClient.createTopic(
                            Topic.newBuilder()
                                    .setName(topicName.toString())
                                    .build());

            System.out.println("Created topic with name: " + topicName.getTopic());
        } catch (AlreadyExistsException e) {
            System.out.println("Topic " + topicName.getTopic() + " already exists.");
        }
    }

    public TransportChannelProvider getChannelProvider() {
        String hostport = System.getenv("PUBSUB_EMULATOR_HOST");
        System.out.println("Hostport: " + hostport);
        ManagedChannel channel = ManagedChannelBuilder.forTarget(hostport).usePlaintext().build();
        TransportChannelProvider channelProvider =
                FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel));
        return channelProvider;
    }

    public void publishWithErrorHandlerExample(String topicId, PubSubRequest message)
            throws IOException, InterruptedException {
        TopicName topicName = TopicName.of(projectId, topicId);
        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            if(!isProduction) {
                publisher = Publisher.newBuilder(topicName).setChannelProvider(channelProvider).setCredentialsProvider(pubSubCredentialsProvider).build();
            } else {
                publisher = Publisher.newBuilder(topicName).build();
            }

            PubsubMessage pubsubMessage = null;
            if(message == null) {
                pubsubMessage = PubsubMessage.newBuilder().putAllAttributes(
                        ImmutableMap.of("Empty", "attribute")).build(); // put some random attributes if it is empty.

            } else {
                String jsonInString = new Gson().toJson(message);
                //ByteString data = message.toByteString();
                ByteString data = ByteString.copyFrom(jsonInString, StandardCharsets.UTF_8);
                // System.out.println("Number of bytes sent: " + data.toByteArray().length);
                pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
            }

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> future = publisher.publish(pubsubMessage);
            // Add an asynchronous callback to handle success / failure
            ApiFutures.addCallback(
                    future,
                new ApiFutureCallback<>() {
                  @Override
                  public void onFailure(Throwable throwable) {
                    if (throwable instanceof ApiException) {
                      ApiException apiException = ((ApiException) throwable);
                      // details on the API exception
                      System.out.println("Publisher API Exception code: " + apiException.getStatusCode().getCode());
                      System.out.println("Publisher API exception retryable: " + apiException.isRetryable());
                    }
                    System.out.println("Error publishing message for topic : " + topicName.getTopic());
                  }

                  @Override
                  public void onSuccess(String messageId) {
                    // Once published, returns server-assigned message ids (unique within the topic)
                    System.out.println("Successfully published message with ID: " + messageId + " for topic: " + topicName.getTopic());
                  }
                },
                    MoreExecutors.directExecutor());

        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }

    public void createPushSubscriptionExample(String subscriptionId, String topicId) throws IOException{

        SubscriptionAdminClient subscriptionAdminClient = null;
        // LOCAL DEVELOPMENT
        if(!isProduction) {
            SubscriptionAdminSettings subscriptionAdminSettings = SubscriptionAdminSettings.newBuilder()
                    .setTransportChannelProvider(channelProvider).setCredentialsProvider(pubSubCredentialsProvider)
                    .build();
            subscriptionAdminClient = SubscriptionAdminClient.create(subscriptionAdminSettings);
        } else {
            subscriptionAdminClient = SubscriptionAdminClient.create();
        }
        try {
            TopicName topicName = TopicName.of(projectId, topicId);
            ProjectSubscriptionName subscriptionName =
                    ProjectSubscriptionName.of(projectId, subscriptionId);


            // Create a push subscription with default acknowledgement deadline of 10 seconds.
            // Messages not successfully acknowledged within 10 seconds will get resent by the server.
            PushConfig pushConfig = PushConfig.newBuilder().setPushEndpoint(pubSubEndpoint).build();
            Subscription subscription =
                    subscriptionAdminClient.createSubscription(subscriptionName, topicName, pushConfig, 60);

            System.out.println("Created push subscription: " + subscription.getName() + " for topic " + topicName.getTopic() +
                    " push endpoint " + pushConfig.getPushEndpoint());
        } catch(Exception ex) {
            System.out.println(ex);
        }
    }
 }
