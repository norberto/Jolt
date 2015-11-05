package com.mist.lexer.exceptions;

public class CommentNotClosedException extends JoltLexerException {

    public CommentNotClosedException() {
        super();
    }

    public CommentNotClosedException(String message) {
        super(message);
    }

    public CommentNotClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public CommentNotClosedException(Throwable cause) {
        super(cause);
    }

}
