package com.mist.lexer;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class LexerizerTest {

    @Test
    public void shouldLexerizeComments() throws Exception {
        String code = "//hello guys\nunknown\n/*multi\nline\ncomment*/";

        String result = lexerize(code);

        assertThat(result, is(
                "Lexeme[SINGLE COMMENT] contents[hello guys]\n" +
                "Lexeme[ATOM] contents[unknown]\n" +
                "Lexeme[MULTI LINE COMMENT] contents[multi\\nline\\ncomment]\n"
        ));
    }

    @Test
    public void shouldLexerizeTitle() throws Exception {
        String code = "### Jolt ###";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Lexer lexer = new Lexer(new StringReader(code))
                            .withOutputStream(baos);

        List<Token> tokens = lexer.lexerize();

        assertThat(tokens.get(0).getContents(), is(" Jolt "));
    }

    @Test
    public void shouldLexerizeInteger() throws Exception {
        String code = "1234";

        List<Token> tokens = lexerizeToTokens(code);

        assertThat(tokens.get(0).toString(), is("Lexeme[INTEGER] contents[1234]"));
    }

    @Test
    public void shouldLexerizeFloat() throws Exception {
        String code = "1234f";

        List<Token> tokens = lexerizeToTokens(code);

        assertThat(tokens.get(0).toString(), is("Lexeme[FLOAT] contents[1234f]"));
    }

    @Test
    public void shouldLexerizeFloat3() throws Exception {
        String code = "12.0";

        List<Token> tokens = lexerizeToTokens(code);

        assertThat(tokens.get(0).toString(), is("Lexeme[FLOAT] contents[12.0]"));
    }

    @Test
    public void shouldLexerizeFloat4() throws Exception {
        String code = "12.0f";

        List<Token> tokens = lexerizeToTokens(code);

        assertThat(tokens.get(0).toString(), is("Lexeme[FLOAT] contents[12.0f]"));
    }

    @Test
    public void shouldLexerizeMultipleFloats() throws Exception {
        String code = "1.0f 2.0 -3f";

        String result = lexerize(code);

        assertThat(result, is(
                "Lexeme[FLOAT] contents[1.0f]\n" +
                "Lexeme[FLOAT] contents[2.0]\n" +
                "Lexeme[SUBTRACTION] contents[-]\n" +
                "Lexeme[FLOAT] contents[3f]\n"));
    }

    public static List<Token> lexerizeToTokens(String contents) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Lexer lexer = new Lexer(new StringReader(contents));
        lexer.withOutputStream(baos);

        return lexer.lexerize();
    }

    public static String lexerize(String contents) throws Exception {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        Lexer lexer = new Lexer(new StringReader(contents));
        lexer.withOutputStream(baos);

        lexer.lexerize();
        return baos.toString();
    }
}