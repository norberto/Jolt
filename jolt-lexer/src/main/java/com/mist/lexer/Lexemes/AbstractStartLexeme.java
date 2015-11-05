package com.mist.lexer.Lexemes;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public abstract class AbstractStartLexeme extends AbstractLexeme {

    protected String start;

    public AbstractStartLexeme(String name, String start) {
        super(name);
        Preconditions.checkArgument(Strings.nullToEmpty(start).length() > 0);
        this.start = start;
    }

    @Override
    public boolean isApplicable(SmartInputStream in) throws IOException {
        String s = in.peek(start.length());
        return s.equals(start);
    }
}
