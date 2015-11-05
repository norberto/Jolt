package com.mist.lexer.exceptions;

public class StringNotClosedException extends JoltLexerException {

    public StringNotClosedException() {
    }

    public StringNotClosedException(String message) {
        super(message);
    }

    public StringNotClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public StringNotClosedException(Throwable cause) {
        super(cause);
    }
}
