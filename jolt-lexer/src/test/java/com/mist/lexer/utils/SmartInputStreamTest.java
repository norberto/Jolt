package com.mist.lexer.utils;

import org.junit.Test;

import static com.mist.lexer.utils.SmartInputStream.fromString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SmartInputStreamTest {

    @Test
    public void testPeek() throws Exception {
        SmartInputStream in = fromString("Hello");

        assertThat(in.peek(3), is("Hel"));
    }

    @Test
    public void peekShouldNotChangeInputStreamPosition() throws Exception {
        SmartInputStream in = fromString("Hello");

        in.peek(2);
        assertThat(in.peek(3), is("Hel"));
    }

    @Test
    public void testSmartRead() throws Exception {
        SmartInputStream in = fromString("This is a sentence.");

        String readString = new String(in.smartRead(5));

        assertThat(readString, is("This "));
    }

    @Test
    public void shouldReturnSmallerBufferIfCountIsMoreThanStreamSize() throws Exception {
        SmartInputStream in = fromString("Hello!");

        String readString = new String(in.smartRead(100));

        assertThat(readString, is("Hello!"));
    }
}