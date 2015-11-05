package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.mist.lexer.Parser;
import com.mist.lexer.Token;
import com.mist.lexer.exceptions.StringNotClosedException;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public class StringLexeme extends AbstractStartEndLexeme {

    private static final String NAME = "STRING";
    private static final String START = "\"";
    private static final String END = "\"";

    public StringLexeme() {
        super(NAME, START, END);
    }

    @Override
    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            in.read(1);
            String string = "\"" + Parser.readString(in);
            if ((string.length() < 2 || (string.charAt(string.length() - 2) == '\\')) || !string.endsWith("\"")) {
                throw new StringNotClosedException(String.format("String was not closed: [%s]", string));
            }
            return Optional.of(new Token(this, stripStartAndEnd(string)));
        }
        return Optional.absent();
    }
}
