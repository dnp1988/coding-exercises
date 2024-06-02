package org.example.practice.impls.registry;

import java.util.Objects;
import java.util.Optional;
import java.util.Random;
import java.util.concurrent.ConcurrentSkipListMap;
import java.util.concurrent.atomic.AtomicLong;

public class ServiceRegistry {

    private final ConcurrentSkipListMap<String, ServiceInstance> serviceMap;
    private final Integer serviceCountLimit;
    private final AtomicLong requestNumber;
    private final DistributionStrategy distributionStrategy;
    private final Random random;

    public ServiceRegistry(Integer serviceCountLimit, DistributionStrategy distributionStrategy) {
        this(serviceCountLimit, distributionStrategy, null);
    }

    public ServiceRegistry(Integer serviceCountLimit, DistributionStrategy distributionStrategy, Random random) {
        this.serviceCountLimit = serviceCountLimit;
        this.serviceMap = new ConcurrentSkipListMap<>();
        this.requestNumber = new AtomicLong(0L);
        this.distributionStrategy = distributionStrategy;
        this.random = Optional.ofNullable(random).orElse(new Random());
    }

    public void addService(ServiceInstance serviceInstance) {
        if(serviceMap.size() < serviceCountLimit) {
            ServiceInstance result = serviceMap.putIfAbsent(serviceInstance.getId(), serviceInstance);
            if(Objects.nonNull(result)) {
                throw new IllegalArgumentException(String.format("Service Instance provided is already in registry"));
            }
        } else {
            throw new IllegalArgumentException(String.format("Service count limit (%s) exceeded", serviceCountLimit));
        }
    }

    public Integer serviceCount() {
        return serviceMap.size();
    }

    public Boolean containsService(String serviceId) {
        return serviceMap.containsKey(serviceId);
    }

    public ServiceInstance getService(String serviceId) {
        return serviceMap.get(serviceId);
    }

    public void distributeRequest(String request) {
        if(DistributionStrategy.ROUND_ROBIN.equals(distributionStrategy)) {
            distributeRequestRoundRobin(request);
        } else if(DistributionStrategy.RANDOM.equals(distributionStrategy)) {
            distributeRequestRandom(request);
        }
    }

    private void distributeRequestRandom(String request) {
        Integer position = random.nextInt(serviceMap.size());
        distributeRequestToPosition(request, position);
    }

    private void distributeRequestRoundRobin(String request) {
        Long value = requestNumber.incrementAndGet();
        Integer position = value.intValue() % serviceMap.size();
        distributeRequestToPosition(request, position);
    }

    private void distributeRequestToPosition(String request, Integer position) {
        String[] ids = serviceMap.navigableKeySet().toArray(new String[position + 1]);
        serviceMap.get(ids[position]).receiveRequest(request);
    }
}
