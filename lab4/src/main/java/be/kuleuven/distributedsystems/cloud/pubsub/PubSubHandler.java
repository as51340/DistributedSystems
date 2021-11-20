package be.kuleuven.distributedsystems.cloud.pubsub;

import com.google.api.gax.core.CredentialsProvider;
import com.google.api.gax.core.NoCredentialsProvider;
import com.google.api.gax.grpc.GrpcTransportChannel;
import com.google.api.gax.rpc.AlreadyExistsException;
import com.google.api.gax.rpc.FixedTransportChannelProvider;
import com.google.api.gax.rpc.TransportChannelProvider;
import com.google.cloud.pubsub.v1.TopicAdminClient;
import com.google.cloud.pubsub.v1.TopicAdminSettings;
import com.google.pubsub.v1.*;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.eclipse.jetty.util.IO;

import java.io.IOException;

public class PubSubHandler {

    private String projectId = null;
    private Encoding encoding = Encoding.JSON;
    private TopicAdminClient topicAdminClient = null;

    public PubSubHandler(String projectId, Encoding encoding) throws IOException {
        this.projectId = projectId;
        this.encoding = encoding;
        this.setLocalPubSubTesting();
        // this.setDeployedPubSubTesting();
    }

    /**
     * When it should be deployed normally, there shoudn't be any problem
     * @throws IOException
     */
    private void setDeployedPubSubTesting() throws IOException {
        this.topicAdminClient = TopicAdminClient.create();
    }

    /**
     * Sets environmental variable and channel for local pub sub testing
     * @throws IOException
     */
    private void setLocalPubSubTesting() throws IOException{
        String hostport = System.getenv("PUBSUB_EMULATOR_HOST");
        ManagedChannel channel = ManagedChannelBuilder.forTarget(hostport).usePlaintext().build();
        try {
            TransportChannelProvider channelProvider =
                    FixedTransportChannelProvider.create(GrpcTransportChannel.create(channel));
            CredentialsProvider credentialsProvider = NoCredentialsProvider.create();

            // Set the channel and credentials provider when creating a `TopicAdminClient`.
            // Similarly for SubscriptionAdminClient
            this.topicAdminClient = TopicAdminClient.create(TopicAdminSettings.newBuilder()
                                    .setTransportChannelProvider(channelProvider)
                                    .setCredentialsProvider(credentialsProvider)
                                    .build());

        } catch(Exception e) {
            System.out.println(e.toString());
        }
    }

    /**
     * Creates topic with schema.
     * @param topicId id of the topic
     * @param schemaId id of the schema
     */
    public void createTopicWithSchema(String topicId, String schemaId) throws IOException {
        TopicName topicName = TopicName.of(projectId, topicId);
        SchemaName schemaName = SchemaName.of(projectId, schemaId);

        SchemaSettings schemaSettings =
                SchemaSettings.newBuilder().setSchema(schemaName.toString()).setEncoding(encoding).build();

        try{
            Topic topic =
                    this.topicAdminClient.createTopic(
                            Topic.newBuilder()
                                    .setName(topicName.toString())
                                    .setSchemaSettings(schemaSettings)
                                    .build());

            System.out.println("Created topic with schema: " + topic.getName());
        } catch (AlreadyExistsException e) {
            System.out.println(schemaName + "already exists.");
        }
    }

}
