package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.mist.lexer.Token;
import com.mist.lexer.exceptions.TitleNotClosedException;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public class TitleLexeme extends AbstractStartEndLexeme {

    private static final String NAME = "TITLE";
    private static final String START = "###";
    private static final String END = "###";

    public TitleLexeme() {
        super(NAME, START, END);
    }

    @Override
    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            Optional<Token> token = super.getToken(in);
            if (!token.isPresent()) {
                throw new TitleNotClosedException("Title is not closed");
            }
            return token;
        }
        return Optional.absent();
    }
}
