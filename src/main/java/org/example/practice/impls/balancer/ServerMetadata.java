package org.example.practice.impls.balancer;

public class ServerMetadata {
    int id;
    int load;
    int capacity;

    public ServerMetadata(int id, int load, int capacity) {
        this.id = id;
        this.load = load;
        this.capacity = capacity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
