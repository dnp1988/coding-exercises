package org.example.practice.impls;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class MinStackTest {
    private MinStack minStack = new MinStack();

    @Test
    public void testMinStack() {
        minStack.push(2);
        minStack.push(3);
        minStack.push(1);

        Assertions.assertEquals(1, minStack.getMin());
        Assertions.assertEquals(1, minStack.pop());
        Assertions.assertEquals(2, minStack.getMin());
        Assertions.assertEquals(3, minStack.peek());

        minStack.push(0);

        Assertions.assertEquals(0, minStack.getMin());
    }
}
