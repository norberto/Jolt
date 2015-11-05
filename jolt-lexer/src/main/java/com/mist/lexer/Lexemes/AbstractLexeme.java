package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public abstract class AbstractLexeme {
    private String name;

    public AbstractLexeme(String name) {
        Preconditions.checkArgument(Strings.nullToEmpty(name).length() > 0);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract boolean isApplicable(SmartInputStream in) throws IOException;
    public abstract Optional<Token> getToken(SmartInputStream in) throws IOException;
    public boolean isVisible() {
        return true;
    }
}
