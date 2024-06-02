package org.example.practice.impls.registry;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Random;
import java.util.UUID;

public class ServiceRegistryTest {

    private ServiceRegistry serviceRegistry;

    @BeforeEach
    public void setUp() {
        serviceRegistry = new ServiceRegistry(10, DistributionStrategy.ROUND_ROBIN);
    }

    @Test
    public void testAddService() {
        ServiceInstance instance = new ServiceInstance(UUID.randomUUID().toString());

        serviceRegistry.addService(instance);

        Assertions.assertTrue(serviceRegistry.containsService(instance.getId()));
        Assertions.assertEquals(1, serviceRegistry.serviceCount());
    }

    @Test
    public void testAddServiceTwiceError() {
        serviceRegistry.addService(new ServiceInstance("ID"));

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> serviceRegistry.addService(new ServiceInstance("ID")));
        Assertions.assertEquals(1, serviceRegistry.serviceCount());
    }

    @Test
    public void testAddServiceLimitExceededError() {
        addServiceInstances(serviceRegistry, 10);

        Assertions.assertThrows(IllegalArgumentException.class,
                () -> serviceRegistry.addService(new ServiceInstance(Integer.toString(11))));
        Assertions.assertEquals(10, serviceRegistry.serviceCount());
    }

    @Test
    public void testRoundRobinDistribution() {
        addServiceInstances(serviceRegistry, 4);

        for(Integer i = 0; i < 8; i++) {
            serviceRegistry.distributeRequest("message" + i);
        }

        for(Integer i = 0; i < 4; i++) {
            Assertions.assertEquals(2, serviceRegistry.getService(Integer.toString(i)).getRequestCount());
        }
    }

    private void addServiceInstances(ServiceRegistry serviceRegistry, int x) {
        for (Integer i = 0; i < x; i++) {
            ServiceInstance instance = new ServiceInstance(Integer.toString(i));
            serviceRegistry.addService(instance);
        }
    }

    @Test
    public void testRandomDistribution() {
        Random randomMock = Mockito.mock(Random.class);
        Mockito.when(randomMock.nextInt(10))
                .thenReturn(2)
                .thenReturn(6)
                .thenReturn(2)
                .thenReturn(5)
                .thenReturn(7)
                .thenReturn(5)
                .thenReturn(4)
                .thenReturn(7);

        ServiceRegistry serviceRegistryRandom = new ServiceRegistry(10,
                DistributionStrategy.RANDOM,
                randomMock);

        addServiceInstances(serviceRegistryRandom, 10);

        for(Integer i = 0; i < 8; i++) {
            serviceRegistryRandom.distributeRequest("message" + i);
        }

        Mockito.verify(randomMock, Mockito.times(8)).nextInt(10);
        Assertions.assertEquals(2, serviceRegistryRandom.getService("2").getRequestCount());
        Assertions.assertEquals(1, serviceRegistryRandom.getService("4").getRequestCount());
        Assertions.assertEquals(2, serviceRegistryRandom.getService("5").getRequestCount());
        Assertions.assertEquals(1, serviceRegistryRandom.getService("6").getRequestCount());
        Assertions.assertEquals(2, serviceRegistryRandom.getService("7").getRequestCount());
    }
}
