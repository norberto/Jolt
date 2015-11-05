package com.mist.lexer;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.mist.lexer.Lexemes.AbstractLexeme;
import com.mist.lexer.exceptions.JoltLexerException;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;
import java.util.List;

public class Lexerizer {

    public static List<Token> lexerize(SmartInputStream in) throws IOException {
        List<Token> result = Lists.newArrayList();
        StringBuilder exceptionSb = new StringBuilder();

        while (in.available() > 0) {
            Optional<AbstractLexeme> optLexeme = Lexeme.getApplicableLexeme(in);
            if (optLexeme.isPresent()) {
                if (exceptionSb.length() > 0) {
                    result.add(generateExceptionToken(
                            String.format("Undefined lexeme \"%s\"", exceptionSb.toString())));
                    exceptionSb = new StringBuilder();
                }
                result.add(retrieveToken(optLexeme.get(), in));
            } else {
                String nextChar = in.read(1);
                if (!Character.isWhitespace(nextChar.charAt(0))) {
                    exceptionSb.append(nextChar);
                }
            }
        }

        return result;
    }

    private static Token retrieveToken(AbstractLexeme lexeme, SmartInputStream in) throws IOException {
        try {
            return lexeme.getToken(in).get();
        } catch (JoltLexerException e) {
            return generateExceptionToken(e.getMessage());
        }
    }

    private static Token generateExceptionToken(String message) {
        return new Token(Lexeme.EXCEPTION.getLexeme(), message);
    }
}
