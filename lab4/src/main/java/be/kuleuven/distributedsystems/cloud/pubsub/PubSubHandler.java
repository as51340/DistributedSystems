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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

@Component
public class PubSubHandler {

    @Autowired
    private String projectId = null;
    private Encoding encoding = Encoding.JSON;

    public PubSubHandler() {

    }

    /**
     * Sets environmental variable and channel for local pub sub testing
     * @throws IOException
     */


    /**
     * Creates topic with schema.
     * @param topicId id of the topic
     * @param schemaId id of the schema
     */
    public void createTopicWithSchema(TopicAdminClient topicAdminClient, String topicId, String schemaId) throws IOException {
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

}
