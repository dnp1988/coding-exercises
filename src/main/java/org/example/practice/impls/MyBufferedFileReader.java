package org.example.practice.impls;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class MyBufferedFileReader {
    private int bufferSize;
    private FileReader fileReader;
    private char[] internalBuffer;
    private int head;
    private int tail;

    public MyBufferedFileReader(int bufferSize, FileReader fileReader) {
        this.fileReader = fileReader;
        this.bufferSize = bufferSize;
        this.internalBuffer = new char[bufferSize];
        this.head = bufferSize;
        this.tail = bufferSize;
    }

    public int read(char[] cbuf) throws IOException {
        return read(cbuf, 0, cbuf.length);
    }

    public int read(char[] cbuf, int off, int len) throws IOException {
        if(len < 0) {
            throw new IllegalArgumentException("len is lower than 0");
        }
        if(len > cbuf.length - off) {
            throw new IndexOutOfBoundsException("len is higher than (cbuf.length - off)");
        }

        int lenRead = 0;

        if(tail == -1) {
            return lenRead;
        }

        int remainingInBuffer = tail - head;
        if(len <= remainingInBuffer) {
            System.arraycopy(internalBuffer, head, cbuf, off, len);
            lenRead = lenRead + len;
            head = head + len;
            if(head == tail && tail == bufferSize) {
                fillBuffer();
            }
        } else {
            int remainder = len - remainingInBuffer;
            System.arraycopy(internalBuffer, head, cbuf, off, remainingInBuffer);
            head = head + remainingInBuffer;
            if(tail == bufferSize) {
                fillBuffer();
            }
            lenRead = lenRead + remainingInBuffer + read(cbuf, off + remainingInBuffer, remainder);
        }
        return lenRead;
    }

    private void fillBuffer() throws IOException {
        tail = fileReader.read(internalBuffer, 0, bufferSize);
        head = 0;
    }

    public void close() throws IOException {
        fileReader.close();
    }
}
