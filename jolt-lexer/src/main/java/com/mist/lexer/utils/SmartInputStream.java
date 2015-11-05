package com.mist.lexer.utils;

import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

public class SmartInputStream extends BufferedInputStream {
    public SmartInputStream(InputStream in, int size) {
        super(in, size);
    }

    public SmartInputStream(InputStream in) {
        super(in);
    }

    public static SmartInputStream fromString(String s) throws IOException {
        return new SmartInputStream(
                new ByteArrayInputStream(
                        s.getBytes(StandardCharsets.UTF_8)));
    }

    public String peek(int count) throws IOException {
        // save for restoring old values
        int _marklimit = marklimit;
        int _markpos = markpos;

        mark(count);
        byte[] bytes = smartRead(count);
        reset();

        // restore old values
        marklimit = _marklimit;
        markpos = _markpos;

        return new String(bytes);
    }

    public String read(int count) throws IOException {
        return new String(smartRead(count));
    }

    public byte[] smartRead(int count) throws IOException {
        byte[] buffer = new byte[count];
        int readBytes = 0;
        while (readBytes < count) {
            int amount = read(buffer, readBytes, count - readBytes);
            if (amount > 0) {
                readBytes += amount;
            } else {
                byte[] result = new byte[readBytes];
                System.arraycopy(buffer, 0, result, 0, readBytes);
                return result;
            }
        }
        return buffer;
    }
}
