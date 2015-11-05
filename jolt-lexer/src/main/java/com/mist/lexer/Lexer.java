package com.mist.lexer;

import com.google.common.base.Verify;
import com.google.common.collect.Lists;
import com.mist.lexer.utils.SmartInputStream;

import java.io.*;
import java.util.List;

public class Lexer {

    private final Reader reader;
    private Writer writer;

    public Lexer(Reader reader) {
        Verify.verifyNotNull(reader);
        this.reader = reader;
    }

    public Lexer withOutputStream(OutputStream os) {
        this.writer = new OutputStreamWriter(
                os != null ? os : System.out
        );
        return this;
    }

    public void write(Token t) throws IOException {
        if (t.getLexeme().isVisible()) {
            writer.append(t.toString()).append("\n");
        }
    }

    public List<Token> lexerize() throws IOException {
        try(BufferedReader br = new BufferedReader(reader)) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine())!= null) {
                sb.append(line).append("\n");
            }

            SmartInputStream in = SmartInputStream.fromString(sb.toString());
            List<Token> tokens = Lexerizer.lexerize(in);
            for (Token t: tokens) {
                write(t);
            }
            writer.flush();
            return tokens;
        }
    }
}
