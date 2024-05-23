package org.example.practice.impls;

import org.junit.jupiter.api.Test;

import java.io.FileReader;
import java.io.IOException;

public class MyBufferedFileReaderTest {

    @Test
    public void testMyBuffer() throws IOException {
        FileReader fileReader = new FileReader("fileReaderTesting.txt");
        MyBufferedFileReader myBufferedFileReader = new MyBufferedFileReader(6, fileReader);

        char[] readArray = new char[60];
        int charsRead = myBufferedFileReader.read(readArray);
    }
}
