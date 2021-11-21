package be.kuleuven.distributedsystems.cloud.pubsub;

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
import com.google.cloud.pubsub.v1.Publisher;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.common.util.concurrent.MoreExecutors;
import com.google.protobuf.ByteString;
import com.google.pubsub.v1.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.eclipse.jetty.util.security.CredentialProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;
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

    private Encoding encoding = Encoding.JSON;


    public PubSubHandler() {

    }

    /*@PostConstruct
    public void init() {
        this.localPubSubTesting();
    }

    private TopicAdminClient localPubSubTesting() {
        String hostport = System.getenv("PUBSUB_EMULATOR_HOST");
        //String hostport = "localhost:8085";
        System.out.println("Hostport: " + hostport);

        ManagedChannel channel = ManagedChannelBuilder.forTarget(hostport).usePlaintext().build();
        try {
            TransportChannelProvider channelProvider =
                    FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel));
            CredentialsProvider credentialsProvider = NoCredentialsProvider.create();

            // Set the channel and credentials provider when creating a `TopicAdminClient`.
            // Similarly for SubscriptionAdminClient
            return TopicAdminClient.create(TopicAdminSettings.newBuilder()
                    .setTransportChannelProvider(channelProvider)
                    .setCredentialsProvider(credentialsProvider)
                    .build());
        } catch(Exception e) {
            System.out.println(e.toString());
        }
        return null;
    }*/

    /**
     * Sets environmental variable and channel for local pub sub testing
     * @throws IOException
     */

    /**
     * Creates topic with schema.
     * @param topicId id of the topic
     * @param schemaId id of the schema
     */
    public void createTopicWithSchema(String topicId, String schemaId) throws IOException {

        TopicAdminClient topicAdminClient = TopicAdminClient.create(TopicAdminSettings.newBuilder()
                .setTransportChannelProvider(channelProvider)
                .setCredentialsProvider(pubSubCredentialsProvider)
                .build());

        TopicName topicName = TopicName.of(projectId, topicId);
        SchemaName schemaName = SchemaName.of(projectId, schemaId);

        SchemaSettings schemaSettings =
                SchemaSettings.newBuilder().setSchema(schemaName.toString()).setEncoding(encoding).build();

        try{
            Topic topic =
                    topicAdminClient.createTopic(
                            Topic.newBuilder()
                                    .setName(topicName.toString())
                                    .setSchemaSettings(schemaSettings)
                                    .build());

            System.out.println("Created topic with schema: " + topic.getName());
        } catch (AlreadyExistsException e) {
            System.out.println(schemaName + " already exists.");
        }
    }

    /**
     * Method for publishing messages
     * @param topicId
     * @throws IOException
     * @throws InterruptedException
     */
    public void publishWithErrorHandlerExample(String topicId)
            throws IOException, InterruptedException {
        TopicName topicName = TopicName.of(projectId, topicId);
        Publisher publisher = null;
        try {
            // Create a publisher instance with default settings bound to the topic
            CredentialsProvider credentialProvider = NoCredentialsProvider.create();
            publisher = Publisher.newBuilder(topicName).setChannelProvider(channelProvider).setCredentialsProvider(pubSubCredentialsProvider).build();

            List<String> messages = Arrays.asList("first message", "second message");

            for (final String message : messages) {
                ByteString data = ByteString.copyFromUtf8(message);
                PubsubMessage pubsubMessage = PubsubMessage.newBuilder().setData(data).build();

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
                                    System.out.println(apiException.getStatusCode().getCode());
                                    System.out.println(apiException.isRetryable());
                                }
                                System.out.println("Error publishing message : " + message);
                            }

                            @Override
                            public void onSuccess(String messageId) {
                                // Once published, returns server-assigned message ids (unique within the topic)
                                System.out.println("Published message ID: " + messageId);
                            }
                        },
                        MoreExecutors.directExecutor());
            }
        } finally {
            if (publisher != null) {
                // When finished with the publisher, shutdown to free up resources.
                publisher.shutdown();
                publisher.awaitTermination(1, TimeUnit.MINUTES);
            }
        }
    }


}
