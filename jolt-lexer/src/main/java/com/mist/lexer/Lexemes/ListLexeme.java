package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;
import java.util.List;

public class ListLexeme extends AbstractLexeme {

    private List<String> identifiers;

    public ListLexeme(String name, List<String> identifiers) {
        super(name);
        this.identifiers = Preconditions.checkNotNull(identifiers);
    }

    @Override
    public boolean isApplicable(SmartInputStream in) throws IOException {
        return !findKeyWord(in).isEmpty();
    }

    @Override
    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        String keyword = findKeyWord(in);
        if (!keyword.isEmpty()) {
            in.read(keyword.length());
            return Optional.of(new Token(this, keyword));
        }
        return Optional.absent();
    }

    private String findKeyWord(SmartInputStream in) throws IOException {
        for (String keyword : identifiers) {
            String peek = in.peek(keyword.length() + 1);
            Character afterPeek = peek.charAt(peek.length() - 1);
            peek = peek.substring(0, peek.length() - 1);
            if (peek.equals(keyword) && !Character.isLetterOrDigit(afterPeek)) {
                return keyword;
            }
        }
        return "";
    }
}
