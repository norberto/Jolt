package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.mist.lexer.Parser;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public class IntegerLexeme extends AbstractLexeme {

    private static final String NAME = "INTEGER";

    public IntegerLexeme() {
        super(NAME);
    }

    @Override
    public boolean isApplicable(SmartInputStream in) throws IOException {
        return Character.isDigit(in.peek(1).charAt(0));
    }

    @Override
    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            String integer = Parser.readUntil(in, (input) -> !Character.isDigit(input));
            return Optional.of(new Token(this, integer));
        }
        return Optional.absent();
    }
}