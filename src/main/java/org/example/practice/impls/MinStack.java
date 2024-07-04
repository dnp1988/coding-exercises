package org.example.practice.impls;

import java.util.Stack;

/**
 * Implement a stack with common operations (push, pop, peek), plus a get_min operation.
 * Get_min is similar to peek in that it is an immutable operation,
 * except instead of returning the value of the HEAD, it returns the minimum value in the stack.
 */
public class MinStack {
    private Stack<NumberAndMin> mainStack = new Stack<>();

    public void push(Integer number) {
        Integer min = null;
        if(mainStack.isEmpty()) {
            min = number;
        } else {
            min = Math.min(number, mainStack.peek().min);
        }

        mainStack.push(new NumberAndMin(number, min));
    }

    public Integer pop() {
        NumberAndMin node = mainStack.pop();
        return node.number;
    }

    public Integer peek() {
        NumberAndMin node = mainStack.peek();
        return node.number;
    }

    public Integer getMin() {
        NumberAndMin node = mainStack.peek();
        return node.min;
    }

    private static class NumberAndMin {
        Integer number;
        Integer min;

        public NumberAndMin(Integer number, Integer min) {
            this.number = number;
            this.min = min;
        }
    }
}
