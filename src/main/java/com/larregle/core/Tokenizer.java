package com.larregle.core;

import java.nio.charset.MalformedInputException;
import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    private int index;

    public Tokenizer() { this.index = 0; }

    public List<Token> parse(String expression) throws MalformedInputException {
        List<Token> result = new ArrayList<>();

        while (index < expression.length()) {
            if (Character.isWhitespace(expression.charAt(index))) { index++; continue; }
            result.add(getToken(expression));
        }

        return result;
    }

    private Token getToken(String expression) throws MalformedInputException {
        int startPosition = index;
        char c = expression.charAt(startPosition);
        Token token = new Token();
        token.setIndex(startPosition);
        token.setText(new String(new char[]{c}));

        switch (c) {
            case '(': {
                token.setTokenType(Token.TokenType.OPEN_BRACE); index++;
                break;
            }
            case ')': {
                token.setTokenType(Token.TokenType.CLOSE_BRACE); index++;
                break;
            }
            case '+': {
                token.setTokenType(Token.TokenType.PLUS); index++;
                break;
            }
            case '-': {
                token.setTokenType(Token.TokenType.MINUS); index++;
                break;
            }
            case '/': {
                token.setTokenType(Token.TokenType.DIVIDE); index++;
                break;
            }
            case '*': {
                token.setTokenType(Token.TokenType.MULTIPLY); index++;
                break;
            }
            case '^': {
                token.setTokenType(Token.TokenType.POW); index++;
                break;
            }
            default:
                if (Character.isDigit(c)) {
                    boolean decimal = false;
                    while (expression.length() > index) {
                        if (expression.charAt(index) == '.') {
                            if (decimal) { throw new MalformedInputException(index); }
                            decimal = true;
                            index++;
                        }
                        if(Character.isDigit(expression.charAt(index))) {
                            index++;
                        } else { break; }
                    }

                    token.setTokenType(Token.TokenType.NUMBER);
                    token.setText(expression.substring(startPosition, index));
                    break;
                }
                else if (Character.isAlphabetic(c)) {
                    while (expression.length() > index) {
                        if (Character.isAlphabetic(expression.charAt(index))) {
                            index++;
                        } else { break; }
                    }
                    token.setText(expression.substring(startPosition, index));
                    token.setTokenType(Token.TokenType.FUNCTION);
                    break;
                } else {
                    throw new MalformedInputException(index);
                }
        }

        return token;
    }
}
