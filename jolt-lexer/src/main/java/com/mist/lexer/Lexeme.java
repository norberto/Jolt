package com.mist.lexer;

import com.google.common.base.Optional;
import com.mist.lexer.Lexemes.*;
import com.mist.lexer.utils.SmartInputStream;

import java.io.IOException;

import static com.mist.lexer.ReservedLexemesRegistry.getReservedKeyWords;

public enum Lexeme {

    TITLE(new TitleLexeme()),
    COMMENT(new CommentLexeme()),
    MULTILINE_COMMENT(new MultilineCommentLexeme()),
    DIVISION(new SimpleLexeme("DIVISION", "/")),

    OPERATOR_AND(new SimpleLexeme("AND", "&&")),
    OPERATOR_BIT_AND(new SimpleLexeme("BIT AND", "&")),

    OPERATOR_OR(new SimpleLexeme("OR", "||")),
    OPERATOR_BIT_OR(new SimpleLexeme("BIT OR", "|")),

    INCREMENT(new SimpleLexeme("INCREMENT", "++")),
    DECREMENT(new SimpleLexeme("DECREMENT", "--")),

    ADDITION(new SimpleLexeme("ADDITION", "+")),
    SUBTRACTION(new SimpleLexeme("SUBTRACTION", "-")),
    MULTIPLICATION(new SimpleLexeme("MULTIPLICATION", "*")),
    MODULATION(new SimpleLexeme("MODULATION", "%")),

    FLOAT(new FloatLexeme()),
    INTEGER(new IntegerLexeme()),

    ASSIGN(new SimpleLexeme("ASSIGN", "<=-")),
    ASSIGN_ADD(new SimpleLexeme("ASSIGN AND ADD", "<+-")),
    ASSIGN_SUB(new SimpleLexeme("ASSIGN AND SUBTRACT", "<--")),
    ASSIGN_MULT(new SimpleLexeme("ASSIGN AND MULTIPLY", "<*-")),
    ASSIGN_DIV(new SimpleLexeme("ASSIGN AND DIVIDE", "</-")),
    ASSIGN_MOD(new SimpleLexeme("ASSIGN AND MODULUS", "<%-")),

    COMPARE_EQ(new SimpleLexeme("COMPARE IF EQUAL", "==")),
    COMPARE_NOT_EQ(new SimpleLexeme("COMPARE IF NOT EQUAL", "!=")),
    COMPARE_LESS_EQ(new SimpleLexeme("COMPARE IF LESS OR EQUAL", "<=")),
    COMPARE_MORE_EQ(new SimpleLexeme("COMPARE IF MORE OR EQUAL", ">=")),
    COMPARE_LESS(new SimpleLexeme("COMPARE IF LESS", "<")),
    COMPARE_MORE(new SimpleLexeme("COMPARE IF MORE", ">")),

    STRING(new StringLexeme()),

    PAREN_L(new SimpleLexeme("PAREN L", "(")),
    PAREN_R(new SimpleLexeme("PAREN R", ")")),

    BRACKET_L(new SimpleLexeme("BRACKET L", "[")),
    BRACKET_R(new SimpleLexeme("BRACKET R", "]")),

    CURLY_L(new SimpleLexeme("CURLY L", "{")),
    CURLY_R(new SimpleLexeme("CURLY R", "}")),

    SEMICOLON(new SimpleLexeme("SEMICOLON", ";")),
    COMMA(new SimpleLexeme("COMMA", ",")),
    PERIOD(new SimpleLexeme("PERIOD", ".")),

    QUESTION_MARK(new SimpleLexeme("QUESTION MARK", "?")),
    EXCLAMATION_POINT(new SimpleLexeme("EXCLAMATION POINT", "!")),
    COLON(new SimpleLexeme("COLON", ":")),
    UNDERSCORE(new SimpleLexeme("UNDERSCORE", "_")),

    RESERVED_KEY_WORD(new ListLexeme("RESERVED KEY WORD", getReservedKeyWords())),

    ATOM(new AtomLexeme()),

    EXCEPTION(new ExceptionLexeme())
    ;

    private AbstractLexeme lexeme;
    Lexeme(AbstractLexeme lexeme) {
        this.lexeme = lexeme;
    }

    public AbstractLexeme getLexeme() {
        return lexeme;
    }

    public static Optional<AbstractLexeme> getApplicableLexeme(SmartInputStream in) throws IOException {
        for (Lexeme lexeme: values()) {
            if (lexeme.getLexeme().isApplicable(in)) {
                return Optional.of(lexeme.getLexeme());
            }
        }
        return Optional.absent();
    }
}
