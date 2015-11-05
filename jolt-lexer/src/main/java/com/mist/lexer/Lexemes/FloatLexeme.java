package com.mist.lexer.Lexemes;

import com.google.common.base.Optional;
import com.mist.lexer.Token;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;
import java.util.regex.Pattern;

public class FloatLexeme extends AbstractLexeme {

    private static final String NAME = "FLOAT";
    private static final int FLOAT_PEEK_SIZE = 30;
    public static final Pattern FLOAT_START_REGEXP = Pattern.compile("^((\\d+f)|(\\d+\\.\\d+f?))(.)*", Pattern.DOTALL);

    public FloatLexeme() {
        super(NAME);
    }

    @Override
    public boolean isApplicable(SmartInputStream in) throws IOException {
        return FLOAT_START_REGEXP.matcher(in.peek(FLOAT_PEEK_SIZE)).matches();
    }

    @Override
    public Optional<Token> getToken(SmartInputStream in) throws IOException {
        if (isApplicable(in)) {
            StringBuilder sb = new StringBuilder();

            while (nextIsDigit(in)) {
                sb.append(in.read(1));
            }

            String read = in.read(1);
            sb.append(read);
            if (!read.equals("f")) {
                while (nextIsDigit(in)) {
                    sb.append(in.read(1));
                }
                if (in.peek(1).equals("f")) {
                    sb.append(in.read(1));
                }
            }

            return Optional.of(new Token(this, "" + sb.toString()));
        }
        return Optional.absent();
    }

    private boolean nextIsDigit(SmartInputStream in) throws IOException {
        return in.available() > 0 && Character.isDigit(in.peek(1).charAt(0));
    }
}