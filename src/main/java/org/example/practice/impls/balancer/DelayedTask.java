package org.example.practice.impls.balancer;

import java.util.Map;
import java.util.PriorityQueue;
import java.util.TimerTask;
import java.util.concurrent.locks.ReentrantLock;

public class DelayedTask extends TimerTask {
    private final int serverId;
    private final int load;
    private final PriorityQueue<ServerMetadata> pq;
    private final Map<Integer, ServerMetadata> map;
    private final ReentrantLock lock;

    public DelayedTask(int serverId, int load, PriorityQueue<ServerMetadata> pq, Map<Integer, ServerMetadata> map, ReentrantLock lock) {
        this.serverId = serverId;
        this.load = load;
        this.pq = pq;
        this.map = map;
        this.lock = lock;
    }

    @Override
    public void run() {
        lock.lock();
        ServerMetadata metadata = map.get(serverId);
        metadata.load -= load;
        pq.remove(metadata);
        pq.add(metadata);
        System.out.println("free server " + metadata.id + " with load " + load + " (" + metadata.load + "/" + metadata.capacity + ")");
        lock.unlock();
    }
}
