package com.mist.lexer;

import com.google.common.collect.Lists;

import java.util.List;

public class ReservedLexemesRegistry {

    private ReservedLexemesRegistry() {}

    public static List<String> getReservedKeyWords() {
        return Lists.newArrayList(
                "const", "fn", "group", "let", "spit",
                "if", "then", "else",
                "ala", "ditto", "again", "bye", "@->",
                "this",
                "true", "false",
                "string", "int", "boolean",
                "$argc", "$args"
        );
    }
}
