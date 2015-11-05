package com.mist.lexer.exceptions;

public class JoltLexerException extends RuntimeException {

    public JoltLexerException() {
        super();
    }

    public JoltLexerException(String message) {
        super(message);
    }

    public JoltLexerException(String message, Throwable cause) {
        super(message, cause);
    }

    public JoltLexerException(Throwable cause) {
        super(cause);
    }


}
