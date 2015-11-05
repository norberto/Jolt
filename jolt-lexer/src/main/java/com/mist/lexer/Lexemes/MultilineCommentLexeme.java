package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.mist.lexer.exceptions.CommentNotClosedException;
import com.mist.lexer.Parser;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

public class MultilineCommentLexeme extends AbstractStartEndLexeme {

    private static final String NAME = "MULTI LINE COMMENT";
    private static final String START = "/*";
    private static final String END = "*/";

    public MultilineCommentLexeme() {
        super(NAME, START, END);
    }

    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            String comment = Parser.readMultilineComment(in);
            if (!comment.endsWith(END)) {
                throw new CommentNotClosedException(String.format("Comment not closed: [%s]", comment));
            }
            return Optional.of(new Token(this, stripStartAndEnd(comment)));
        }
        return Optional.absent();
    }

    @Override
    public boolean isVisible() {
        return false;
    }
}
