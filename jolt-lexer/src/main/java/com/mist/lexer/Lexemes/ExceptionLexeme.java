package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public class ExceptionLexeme extends AbstractLexeme {

    private static final String NAME = "EXCEPTION";

    public ExceptionLexeme() {
        super(NAME);
    }

    @Override
    public boolean isApplicable(SmartInputStream in) throws IOException {
        return false;
    }

    @Override
    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        throw new RuntimeException("Should not get here");
    }
}
