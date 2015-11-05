package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public class AtomLexeme extends AbstractLexeme {

    private static final String NAME = "ATOM";

    public AtomLexeme() {
        super(NAME);
    }

    @Override
    public boolean isApplicable(SmartInputStream in) throws IOException {
        return Character.isJavaIdentifierStart(
                in.peek(1).charAt(0));
    }

    @Override
    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            StringBuilder sb = new StringBuilder();

            sb.append(in.read(1)); // isApplicable tells us, that next character is an applicable variable identifier
            while(in.available() > 0 && Character.isJavaIdentifierPart(in.peek(1).charAt(0))) {
                sb.append(in.read(1));
            }

            return Optional.of(new Token(this, sb.toString()));
        }
        return Optional.absent();
    }
}
