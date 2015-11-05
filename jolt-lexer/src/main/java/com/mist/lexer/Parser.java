package com.mist.lexer;

import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;
import java.util.function.Function;

public class Parser {

    private Parser(){}

    // Reads already opened string
    public static String readString(SmartInputStream in) throws IOException {
        String read = readUntilWithEnd(in, "\"");
        while(in.available() > 0 && read.charAt(read.length() - 2) == '\\') {
            read += readUntilWithEnd(in, "\"");
        }
        return read;
    }

    public static String readComment(SmartInputStream in) throws IOException {
        return readUntil(in, "\n");
    }

    public static String readMultilineComment(SmartInputStream in) throws IOException {
        return readUntilWithEnd(in, "*/");
    }

    public static String readUntil(SmartInputStream in, String end) throws IOException {
        String result = readUntilWithEnd(in, end);
        if (result.length() >= end.length()) {
            return result.substring(0, result.length() - end.length());
        }
        return "";
    }

    public static String readUntil(SmartInputStream in, Function<Character, Boolean> fn) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (in.available() > 0) {
            String peek = in.peek(1);
            if (fn.apply(peek.charAt(0))) {
                break;
            } else {
                in.read(1);
                sb.append(peek);
            }
        }
        return sb.toString();
    }

    public static String readUntilWithEnd(SmartInputStream in, String end) throws IOException {
        StringBuilder sb = new StringBuilder();
        while (in.available() > 0) {
            String peek = in.peek(end.length());
            if (peek.equals(end)) {
                in.read(end.length());
                sb.append(end);
                break;
            } else {
                sb.append((char) in.read());
            }
        }
        return sb.toString();
    }

}
