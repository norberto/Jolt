package com.mist.lexer.exceptions;

public class TitleNotClosedException extends JoltLexerException {

    public TitleNotClosedException() {
        super();
    }

    public TitleNotClosedException(String message) {
        super(message);
    }

    public TitleNotClosedException(String message, Throwable cause) {
        super(message, cause);
    }

    public TitleNotClosedException(Throwable cause) {
        super(cause);
    }

}
