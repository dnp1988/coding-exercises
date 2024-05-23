package org.example.practice.impls;

import org.junit.jupiter.api.Test;

import java.io.FileWriter;
import java.io.IOException;

public class MyBufferedFileWriterTest {

    @Test
    public void testMyBuffer() throws IOException {
        FileWriter fileWriter = new FileWriter("fileWriterTesting.txt");
        MyBufferedFileWriter myBufferedFileWriter = new MyBufferedFileWriter(40, fileWriter);

        myBufferedFileWriter.write("0000000000".toCharArray());
        myBufferedFileWriter.write("1111111111".toCharArray());
        myBufferedFileWriter.write("2222222222".toCharArray());
        myBufferedFileWriter.close();
    }
}
