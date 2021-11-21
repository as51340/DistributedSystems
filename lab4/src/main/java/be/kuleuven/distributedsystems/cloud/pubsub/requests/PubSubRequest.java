package be.kuleuven.distributedsystems.cloud.pubsub.requests;

import com.google.protobuf.ByteString;

import java.io.IOException;

/**
 * All requests that will be sent over pub/sub network should follow this structure.
 */
public interface PubSubRequest {

    ByteString toByteString() throws IOException;

    void fromByteString(ByteString bs) throws IOException;

}
