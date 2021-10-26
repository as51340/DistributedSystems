package org.example.AlmaOnline.provided.client;

import org.example.AlmaOnline.server.AlmaOnlineGrpc;

public interface AppScript {
    void Execute(
        AlmaOnlineClientAdapter adapter,
        AlmaOnlineGrpc.AlmaOnlineBlockingStub blocking,
        AlmaOnlineGrpc.AlmaOnlineFutureStub future) throws Exception;
}
