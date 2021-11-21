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
import io.grpc.StatusRuntimeException;
import org.eclipse.jetty.util.security.CredentialProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;


@Component
public class PubSubHandler {

    @Autowired
    private String projectId = null;

    @Autowired
    private CredentialsProvider pubSubCredentialsProvider;

    @Autowired
    private TransportChannelProvider channelProvider;

    @Autowired
    private PushConfig pushConfig;

    public PubSubHandler() {

    }

    /**
     * Sets environmental variable and channel for local pub sub testing
     * @throws IOException
     */

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

    /**
     * Method for publishing messages
     * @param topicId
     * @throws IOException
     * @throws InterruptedException
     */
    public void publishWithErrorHandlerExample(String topicId, PubSubRequest message)
            throws IOException, InterruptedException {
        TopicName topicName = TopicName.of(projectId, topicId);
        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            publisher = Publisher.newBuilder(topicName).setChannelProvider(channelProvider).setCredentialsProvider(pubSubCredentialsProvider).build();

            PubsubMessage pubsubMessage = null;
            if(message == null) {
                pubsubMessage = PubsubMessage.newBuilder().putAllAttributes(
                        ImmutableMap.of("Empty", "attribute")).build(); // put some random attributes if it is empty.

            } else {
                String jsonInString = new Gson().toJson(message);
                //ByteString data = message.toByteString();
                ByteString data = ByteString.copyFrom(jsonInString, StandardCharsets.UTF_8);


                pubsubMessage = PubsubMessage.newBuilder().setData(data).build();
            }

            // Once published, returns a server-assigned message id (unique within the topic)
            ApiFuture<String> future = publisher.publish(pubsubMessage);
            // Add an asynchronous callback to handle success / failure
            ApiFutures.addCallback(
                    future,
                    new ApiFutureCallback<String>() {
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
                            System.out.println("Successfully published message with ID: " + messageId  + " for topic: " + topicName.getTopic());
                        }},
                    MoreExecutors.directExecutor());

        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }

    /**
     * Creates push subscription
     * @param subscriptionId
     * @param topicId
     * @throws IOException
     */
    public void createPushSubscriptionExample(String subscriptionId, String topicId) throws IOException{

        SubscriptionAdminSettings subscriptionAdminSettings = SubscriptionAdminSettings.newBuilder()
                .setTransportChannelProvider(channelProvider).setCredentialsProvider(pubSubCredentialsProvider)
                .build();

        try (SubscriptionAdminClient subscriptionAdminClient = SubscriptionAdminClient.create(subscriptionAdminSettings)) {
            TopicName topicName = TopicName.of(projectId, topicId);
            ProjectSubscriptionName subscriptionName =
                    ProjectSubscriptionName.of(projectId, subscriptionId);


            // Create a push subscription with default acknowledgement deadline of 10 seconds.
            // Messages not successfully acknowledged within 10 seconds will get resent by the server.
            Subscription subscription =
                    subscriptionAdminClient.createSubscription(subscriptionName, topicName, pushConfig, 10);

            System.out.println("Created push subscription: " + subscription.getName() + " for topic " + topicName.getTopic() +
                    " push endpoint " + pushConfig.getPushEndpoint());
        } catch(Exception ex) {
            System.out.println(ex.toString());
        }
    }
 }
