package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.mist.lexer.Parser;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public abstract class AbstractStartEndLexeme extends AbstractStartLexeme {

    protected String end;

    public AbstractStartEndLexeme(String name, String start, String end) {
        super(name, start);
        Preconditions.checkArgument(Strings.nullToEmpty(end).length() > 0);
        this.end = end;
    }

    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            in.read(start.length());
            String content = start + Parser.readUntilWithEnd(in, end);
            if (content.endsWith(end)) {
                return Optional.of(new Token(this, stripStartAndEnd(content)));
            }
        }
        return Optional.absent();
    }

    protected String stripStartAndEnd(String s) {
        return s.substring(start.length(), s.length() - end.length());
    }
}
