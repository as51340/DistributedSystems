package org.example.AlmaOnline.provided.server;

import org.example.AlmaOnline.defaults.Initializer;
import org.example.AlmaOnline.provided.service.ServiceInitializer;

public interface AlmaOnlineServerAdapter {
    default ServiceInitializer getInitializer() {
        return new Initializer();
    }
}
