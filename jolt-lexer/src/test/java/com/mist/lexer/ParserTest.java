package com.mist.lexer;

import com.mist.lexer.utils.SmartInputStream;
import org.junit.Test;

import static com.mist.lexer.utils.SmartInputStream.fromString;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class ParserTest {

    @Test
    public void testGetComment() throws Exception {
        String comment = "//Test test\nFoo";
        SmartInputStream in = fromString(comment);

        String result = Parser.readComment(in);

        assertThat(result, is("//Test test"));
    }

    @Test
    public void testGetMultilineComment() throws Exception {
        String comment = "/*Test test\n" +
                "Bar foo foo*/asds\n" +
                "eqwewqewq";
        SmartInputStream in = fromString(comment);

        String result = Parser.readMultilineComment(in);

        assertThat(result, is("/*Test test\nBar foo foo*/"));
    }

    @Test
    public void testReadUntilWithOneChar() throws Exception {
        String text = "hello\nhi";
        SmartInputStream in = fromString(text);

        String result = Parser.readUntil(in, "\n");

        assertThat(result, is("hello"));
    }

    @Test
    public void testReadUntilWithMultipleChars() throws Exception {
        String text = "/*Test String*/foooooo";
        SmartInputStream in = fromString(text);

        String result = Parser.readUntilWithEnd(in, "*/");

        assertThat(result, is("/*Test String*/"));
    }
}