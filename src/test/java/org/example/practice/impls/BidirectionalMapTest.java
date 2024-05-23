package org.example.practice.impls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BidirectionalMapTest {

    @Test
    public void testBidirectionalMap() {
        BidirectionalMap<String, Integer> bidiMap = new BidirectionalMap<>();

        bidiMap.put("one", 1);
        bidiMap.put("two", 2);
        bidiMap.put("three", 3);
        bidiMap.put("four", 4);

        bidiMap.put("three", 33);
        bidiMap.put("four", 44);

        bidiMap.put("threethree", 3);
        bidiMap.put("fourfour", 4);

        Assertions.assertEquals(1, bidiMap.getByKey("one"));
        Assertions.assertEquals(2, bidiMap.getByKey("two"));
        Assertions.assertEquals("one", bidiMap.getByValue(1));
        Assertions.assertEquals("two", bidiMap.getByValue(2));

        Assertions.assertEquals(3, bidiMap.getByKey("three"));
        Assertions.assertEquals(4, bidiMap.getByKey("four"));
        Assertions.assertEquals("three", bidiMap.getByValue(3));
        Assertions.assertEquals("four", bidiMap.getByValue(4));

        bidiMap.removeByKey("one");
        Assertions.assertNull(bidiMap.getByValue(1));
        bidiMap.removeByValue(2);
        Assertions.assertNull(bidiMap.getByKey("two"));
    }
}
