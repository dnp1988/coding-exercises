package org.example.practice.impls;

import java.io.FileWriter;
import java.io.IOException;

public class MyBufferedFileWriter {
    private int bufferSize;
    private FileWriter fileWriter;
    private char[] internalBuffer;
    private int head;

    public MyBufferedFileWriter(int bufferSize, FileWriter fileWriter) {
        this.fileWriter = fileWriter;
        this.bufferSize = bufferSize;
        this.internalBuffer = new char[bufferSize];
        this.head = 0;
    }

    public void write(char[] cbuf) throws IOException {
        write(cbuf, 0, cbuf.length);
    }

    public void write(char[] cbuf, int off, int len) throws IOException {
        int freeSpace = internalBuffer.length - head;

        if(len <= freeSpace) {
           System.arraycopy(cbuf, off, internalBuffer, head, len);
           head = head + len;
           if(head == internalBuffer.length) {
               flush();
           }
        } else {
           int remainder = len - freeSpace;
           System.arraycopy(cbuf, off, internalBuffer, head, freeSpace);
           head = head + freeSpace;
           flush();
           write(cbuf, off + freeSpace, remainder);
        }
    }

    public void flush() throws IOException {
        fileWriter.write(internalBuffer, 0, head);
        fileWriter.flush();
        head = 0;
    }

    public void close() throws IOException {
        this.flush();
        fileWriter.close();
    }
}
