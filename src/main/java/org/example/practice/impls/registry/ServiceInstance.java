package org.example.practice.impls.registry;

import java.util.ArrayList;
import java.util.List;

public class ServiceInstance {

    private final String id;
    private final List<String> requests;

    public ServiceInstance(String id) {
        this.id = id;
        this.requests = new ArrayList<>();
    }

    public String getId() {
        return id;
    }

    public void receiveRequest(String request) {
        requests.add(request);
    }

    public Integer getRequestCount() {
        return requests.size();
    }
}
