package com.larregle.core;

public class Token {

    private int index;
    private int precedence;
    private TokenType tokenType;
    private String text;


    public int getPrecedence() {
        return precedence;
    }

    public void setPrecedence(int precedence) {
        this.precedence = precedence;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public TokenType getTokenType() {
        return tokenType;
    }

    public void setTokenType(TokenType tokenType) {
        this.tokenType = tokenType;
    }

    public static enum TokenType {
        NUMBER, FUNCTION, PLUS, MINUS, MULTIPLY, DIVIDE, POW, OPEN_BRACE, CLOSE_BRACE
    }
}
