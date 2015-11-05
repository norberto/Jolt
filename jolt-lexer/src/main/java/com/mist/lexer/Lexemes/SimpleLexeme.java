package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public class SimpleLexeme extends AbstractLexeme {

    private String match;

    public SimpleLexeme(String name, String match) {
        super(name);
        Preconditions.checkArgument(Strings.nullToEmpty(match).length() > 0);
        this.match = match;
    }

    @Override
    public boolean isApplicable(SmartInputStream in) throws IOException {
        String peek = in.peek(match.length());
        return peek.equals(match);
    }

    @Override
    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            in.read(match.length());
            return Optional.of(new Token(this, match));
        }
        return Optional.absent();
    }
}
