package org.example.practice.impls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class UnboundedSetTest {

    public static final Long TIMEOUT = 5000l;
    private final UnboundedSet<String> unboundedSet = new UnboundedSet<>(TIMEOUT);

    @Test
    public void testUnboundedSet() throws InterruptedException {
        unboundedSet.add("value1");
        Assertions.assertTrue(unboundedSet.contains("value1"));
        Assertions.assertEquals(1, unboundedSet.size());
        Thread.sleep(6000l);
        Assertions.assertFalse(unboundedSet.contains("value1"));
        Assertions.assertEquals(0, unboundedSet.size());
    }

    @Test
    public void testUnboundedSetMultipleValues() throws InterruptedException {
        unboundedSet.add("value1");
        unboundedSet.add("value2");
        Assertions.assertTrue(unboundedSet.contains("value1"));
        Assertions.assertTrue(unboundedSet.contains("value2"));
        Assertions.assertEquals(2, unboundedSet.size());
        Thread.sleep(6000l);
        Assertions.assertFalse(unboundedSet.contains("value1"));
        Assertions.assertFalse(unboundedSet.contains("value2"));
        Assertions.assertEquals(0, unboundedSet.size());
    }
}
