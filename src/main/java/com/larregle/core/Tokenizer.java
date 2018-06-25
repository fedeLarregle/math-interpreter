package com.larregle.core;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private int index;

    public Tokenizer() { this.index = 0; }

    public List<Token> parse(String expression) {
        List<Token> result = new ArrayList<>();

        while (index < expression.length()) {
            if (Character.isWhitespace(expression.charAt(index))) { index++; continue; }
            result.add(getToken(expression));
            index++;
        }

        return result;
    }

    private Token getToken(String expression) {
        int startPosition = index;
        char c = expression.charAt(startPosition);
        Token token = new Token();
        token.setIndex(startPosition);
        token.setText(new String(new char[]{c}));

        switch (c) {
            case '(': {
                token.setTokenType(Token.TokenType.OPEN_BRACE);
                break;
            }
            case ')': {
                token.setTokenType(Token.TokenType.CLOSE_BRACE);
                break;
            }
            case '+': {
                token.setTokenType(Token.TokenType.PLUS);
                break;
            }
            case '-': {
                token.setTokenType(Token.TokenType.MINUS);
                break;
            }
            case '/': {
                token.setTokenType(Token.TokenType.DIVIDE);
                break;
            }
            case '*': {
                token.setTokenType(Token.TokenType.MULTIPLY);
                break;
            }
            case '^': {
                token.setTokenType(Token.TokenType.POW);
                break;
            }
            default:
                if (Character.isDigit(c)) {
                    if (expression.length() > (index + 1)) {
                        while (Character.isDigit(expression.charAt(index + 1)) || expression.charAt(index + 1) == '.') {index++;}
                    }
                    token.setTokenType(Token.TokenType.NUMBER);
                    token.setText(expression.substring(startPosition, index + 1));
                    break;
                }
                else if (Character.isAlphabetic(c)) {
                    if (expression.length() > (index + 1)) {
                        while (Character.isAlphabetic(expression.charAt(index + 1))) {index++;}
                    }
                    token.setText(expression.substring(startPosition, index + 1));
                    token.setTokenType(Token.TokenType.FUNCTION);
                    break;
                }
        }

        return token;
    }
}
