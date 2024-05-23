package org.example.practice.impls;

import org.example.practice.impls.CircularBuffer;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CircularBufferTest {

    @Test
    public void testCircularBuffer() {
        CircularBuffer<String> buffer = new CircularBuffer<String>(10);
        buffer.add("1");
        buffer.add("2");
        buffer.add("3");
        buffer.add("4");
        buffer.add("5");
        buffer.add("6");
        buffer.add("7");
        buffer.add("8");
        buffer.add("9");
        buffer.add("10");
        Assertions.assertEquals(buffer.peek(), "1");
        Assertions.assertEquals(buffer.get(), "1");
        buffer.add("11");
        Assertions.assertEquals(buffer.get(), "2");
        Assertions.assertEquals(buffer.get(), "3");
        Assertions.assertEquals(buffer.get(), "4");
        Assertions.assertEquals(buffer.get(), "5");
        Assertions.assertEquals(buffer.get(), "6");
        Assertions.assertEquals(buffer.get(), "7");
        Assertions.assertEquals(buffer.get(), "8");
        Assertions.assertEquals(buffer.get(), "9");
        Assertions.assertEquals(buffer.get(), "10");
        Assertions.assertEquals(buffer.get(), "11");
        Assertions.assertTrue(buffer.isEmpty());
    }
}
