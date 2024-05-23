package org.example.practice.impls.balancer;

import org.junit.jupiter.api.Test;

public class LoadBalancerTest {

    @Test
    public void testLoadBalancer() {
        try {
            LoadBalancer lb = new LoadBalancer(10);

            Thread thread1 = new Thread(() -> lb.routeRequest(4, 1000));
            thread1.start();

            Thread thread2 = new Thread(() -> lb.addServer(1, 5));
            thread2.start();

            lb.addServer(2, 10);
            lb.routeRequest(4, 1000);
            lb.routeRequest(5, 500);

            Thread.sleep(600);

            lb.routeRequest(6, 500);

            Thread.sleep(3000);
            lb.end();
        } catch (InterruptedException ignored) {
        }
    }
}
