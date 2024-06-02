package org.example.practice.impls.url;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Base64IdKeyMapperTest {

    private Base64IdKeyMapper mapper = new Base64IdKeyMapper();

    @Test
    public void testMapIdToKey() {
        Assertions.assertEquals("0", mapper.toKey(0));
        Assertions.assertEquals("1", mapper.toKey(1));
        Assertions.assertEquals("1C", mapper.toKey(100));
        Assertions.assertEquals("g8", mapper.toKey(1000));
    }

    @Test
    public void testMapKeyToId() {
        Assertions.assertEquals(0, mapper.toId("0"));
        Assertions.assertEquals(1, mapper.toId("1"));
        Assertions.assertEquals(100, mapper.toId("1C"));
        Assertions.assertEquals(1000, mapper.toId("g8"));
    }
}
