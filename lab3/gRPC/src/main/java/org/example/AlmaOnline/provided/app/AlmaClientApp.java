package org.example.AlmaOnline.provided.app;

import io.grpc.Channel;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.example.AlmaOnline.provided.client.AlmaOnlineClientAdapter;
import org.example.AlmaOnline.required.AlmaOnlineClientGrpcAdapter;
import org.example.AlmaOnline.server.AlmaOnlineGrpc;

import java.util.logging.Logger;

public class AlmaClientApp {
    private Logger logger = Logger.getLogger(AlmaClientApp.class.getName());

    private final AlmaOnlineGrpc.AlmaOnlineBlockingStub blocking;
    private final AlmaOnlineGrpc.AlmaOnlineFutureStub future;

    private final AlmaOnlineClientAdapter adapter;

    AlmaClientApp(Channel channel, AlmaOnlineClientAdapter adapter) {
        this.blocking = AlmaOnlineGrpc.newBlockingStub(channel);
        this.future = AlmaOnlineGrpc.newFutureStub(channel);
        this.adapter = adapter;
    }


    public static void main(String[] args) {
        String target = "localhost:8980";
        ManagedChannel channel = ManagedChannelBuilder.forTarget(target).usePlaintext().build();

        var adapter = new AlmaOnlineClientGrpcAdapter();

        AlmaClientApp client = new AlmaClientApp(channel, adapter);
        client.Execute();
        System.out.println("All tests successful");

        System.exit(0);
    }

    void Execute() {
        var script = adapter.getScript();
        try {
            script.Execute(
                adapter,
                blocking,
                future
            );
        } catch (Exception e) {
          e.printStackTrace();
        }

        System.out.println("Done");
    }
}
