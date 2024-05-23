package org.example.practice.impls;

public class CircularBuffer<T> {

    private T[] buffer;
    private Integer tail;
    private Integer head;
    private Integer size;
    private Integer capacity;

    public CircularBuffer(Integer capacity) {
        this.buffer = (T[]) new Object[capacity];
        this.head = 0;
        this.tail = -1;
        this.capacity = capacity;
        this.size = 0;
    }

    public void add(T element) {
        if(size >= capacity) {
            throw new IllegalStateException("buffer is full");
        }
        Integer index = (tail + 1) % capacity;
        buffer[index] = element;
        tail++;
        size++;
    }

    public T get() {
        if(size == 0) {
            throw new IllegalStateException("buffer is empty");
        }
        Integer index = head % capacity;
        head++;
        size--;
        return buffer[index];
    }

    public T peek() {
        if(size == 0) {
            throw new IllegalStateException("buffer is empty");
        }
        Integer index = head % capacity;
        return buffer[index];
    }

    public Integer size() {
        return size;
    }

    public Boolean isEmpty() {
        return size == 0;
    }
}
