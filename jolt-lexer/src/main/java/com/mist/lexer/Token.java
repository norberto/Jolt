package com.mist.lexer;

import com.mist.lexer.Lexemes.AbstractLexeme;

import static com.google.common.base.Strings.isNullOrEmpty;

public class Token {
    private AbstractLexeme lexeme;
    private String contents;

    public Token(AbstractLexeme lexeme) {
        this.lexeme = lexeme;
    }

    public Token(AbstractLexeme lexeme, String contents) {
        this.lexeme = lexeme;
        this.contents = contents;
    }

    public AbstractLexeme getLexeme() {
        return lexeme;
    }

    public void setLexeme(AbstractLexeme lexeme) {
        this.lexeme = lexeme;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    @Override
    public String toString() {
        if (!isNullOrEmpty(contents)) {
            contents = contents.replace("\n", "\\n").replace("\r", "\\r").replace("\t", "\\t");
            return "Lexeme[" + lexeme.getName() + "] contents[" + contents + "]";
        } else {
            return "Lexeme[" + lexeme.getName() + "]";
        }
    }
}
