package org.example.practice.impls.balancer;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Timer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LoadBalancer {
    private final int initialCapacity;
    private final PriorityQueue<ServerMetadata> pq;
    private final Map<Integer, ServerMetadata> map;
    private final ReentrantLock lock;
    private final Condition notEmpty;
    private final Condition notFull;
    private final Timer timer;

    public LoadBalancer(int initialCapacity) {
        this.initialCapacity = initialCapacity;
        this.pq = new PriorityQueue<>(initialCapacity, Comparator.comparingInt(s -> -(s.capacity - s.load)));
        this.map = new HashMap<>();
        this.lock = new ReentrantLock();
        this.notEmpty = this.lock.newCondition();
        this.notFull = this.lock.newCondition();
        this.timer = new Timer();
    }

    public void addServer(int id, int capacity) {
        lock.lock();
        try {
            while (pq.size() == initialCapacity) {
                notFull.await();
            }
            System.out.println("add server " + id + " with initial capacity " + capacity);
            ServerMetadata metadata = new ServerMetadata(id, 0, capacity);
            pq.add(metadata);
            map.put(id, metadata);
            notEmpty.signal();
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
    }

    public int routeRequest(int weight, int ttl) {
        lock.lock();
        try {
            while (pq.isEmpty()) {
                notEmpty.await();
            }
            ServerMetadata metadata = pq.peek();
            if (weight > metadata.capacity - metadata.load) return -1;

            metadata = pq.poll();
            metadata.load += weight;

            pq.add(metadata);
            notFull.signal();

            System.out.println("schedule task to finish after " + ttl + "ms on server " + metadata.id + " with load " + weight + " (" + metadata.load + "/" + metadata.capacity + ")");
            timer.schedule(new DelayedTask(metadata.id, weight, pq, map, lock), ttl);
            return metadata.id;
        } catch (InterruptedException ignored) {
        } finally {
            lock.unlock();
        }
        return -1;
    }

    public void end() {
        System.out.println("timer cancelled");
        timer.cancel();
    }
}
