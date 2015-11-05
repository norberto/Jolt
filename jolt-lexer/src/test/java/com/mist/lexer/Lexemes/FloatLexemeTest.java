package com.mist.lexer.Lexemes;

import org.junit.Test;

import java.util.regex.Pattern;

import static com.mist.lexer.Lexemes.FloatLexeme.FLOAT_START_REGEXP;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class FloatLexemeTest {

    @Test
    public void testBadFloats() throws Exception {
        assertThat(match("1"), is(false));
        assertThat(match("1."), is(false));
        assertThat(match("f"), is(false));
        assertThat(match(".f"), is(false));
        assertThat(match("a1f"), is(false));
    }

    @Test
    public void testGoodFloats() throws Exception {
        assertThat(match("12.0"), is(true));
        assertThat(match("1.1f"), is(true));
        assertThat(match("0f"), is(true));
        assertThat(match("1.0\n"), is(true));
        assertThat(match("1.0foo"), is(true));
    }

    public boolean match(String s) {
        return FLOAT_START_REGEXP.matcher(s).matches();
    }

}