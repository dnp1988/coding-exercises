package org.example.practice.impls;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Implement an unbounded set where each entry has an expiration date and disappears once it has expired.
 * @param <T>
 */
public class UnboundedSet<T> {

    private final Long timeoutInMillis;
    private final Map<T, Long> entryExpirationMap;

    public UnboundedSet(Long timeoutInMillis) {
        this.timeoutInMillis = timeoutInMillis;
        this.entryExpirationMap = new LinkedHashMap<>();
    }

    public void add(T element) {
        entryExpirationMap.put(element, System.currentTimeMillis() + timeoutInMillis);
    }

    public boolean contains(T element) {
        Long expirationTime = entryExpirationMap.get(element);
        if(Objects.isNull(expirationTime)) {
            return false;
        }
        return System.currentTimeMillis() < expirationTime;
    }

    public int size() {
        cleanUp();
        return entryExpirationMap.size();
    }

    private void cleanUp() {
        Iterator<Map.Entry<T, Long>> iterator = entryExpirationMap.entrySet().iterator();

        while(iterator.hasNext()) {
           Map.Entry<T, Long> entry = iterator.next();
            if(System.currentTimeMillis() >= entry.getValue()) {
                iterator.remove();
            }
        }
    }
}
