package org.example.AlmaOnline.provided.server;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import org.example.AlmaOnline.required.AlmaOnlineServerGrpcAdapter;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class AlmaOnlineServer {
    private static final Logger logger = Logger.getLogger(AlmaOnlineServer.class.getName());

    private final int port;
    private final Server server;

    public AlmaOnlineServer(int port, AlmaOnlineServerGrpcAdapter adapter) {
        this.port = port;
        ServerBuilder<?> builder = ServerBuilder.forPort(port);
        builder.addService(adapter);
        server = builder.build();
    }

    public void start() throws IOException {
        server.start();
        logger.info("server started, listening on " + port);
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                logger.info("*** shutting down gRPC server since JVM is shutting down");
                try {
                    AlmaOnlineServer.this.stop();
                } catch (InterruptedException e) {
                    e.printStackTrace(System.err);
                }
                logger.info("*** server shut down");
            }
        });
    }

    /* Stop serving requests and shutdown resources. */
    public void stop() throws InterruptedException {
        if (server != null) {
            server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
        }
    }

    /* Await termination on the main thread since the grpc library uses daemon threads. */
    private void blockUntilShutdown() throws InterruptedException {
        if (server != null) {
            server.awaitTermination();
        }
    }

    public static void main(String[] args) throws Exception {
        AlmaOnlineServerGrpcAdapter adapter = new AlmaOnlineServerGrpcAdapter();
        AlmaOnlineServer server = new AlmaOnlineServer(8980, adapter);

        server.start();
        server.blockUntilShutdown();
    }
}
