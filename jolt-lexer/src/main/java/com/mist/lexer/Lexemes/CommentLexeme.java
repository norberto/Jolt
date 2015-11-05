package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.mist.lexer.Parser;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public class CommentLexeme extends AbstractStartLexeme {

    private static final String NAME = "SINGLE COMMENT";
    private static final String IDENTIFIER = "//";

    public CommentLexeme() {
        super(NAME, IDENTIFIER);
    }

    @Override
    public boolean isApplicable(SmartInputStream in) throws IOException {
        String s = in.peek(IDENTIFIER.length());
        return s.equals(IDENTIFIER);
    }

    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            String comment = Parser.readComment(in);
            return Optional.of(new Token(this, getContents(comment)));
        } else {
            return Optional.absent();
        }
    }

    @Override
    public boolean isVisible() {
        return false;
    }

    private String getContents(String s) {
        return s.substring(IDENTIFIER.length());
    }
}
