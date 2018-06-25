package com.larregle.core;

import java.util.ArrayList;
import java.util.List;

public class Evaluator {

    private List<Token> tokens;
    private int index;

    public Evaluator() {
        this.tokens = new ArrayList<>();
        this.index = 0;
    }

    public double evaluate(String expression) {
        this.tokens = new Tokenizer().parse(expression);
        return evalExpression();
    }

    private double evalExpression() {
        double x = evalTerm();
        while(true) {
            if (index < tokens.size()) {
                if (tokens.get(index).getTokenType().equals(Token.TokenType.PLUS)) { index++; x += evalTerm(); }
                else if (tokens.get(index).getTokenType().equals(Token.TokenType.MINUS)) { index++; x += -evalTerm(); }
            }
            return x;
        }
    }

    private double evalTerm() {
        double x = evalFactor();
        while(index < tokens.size()) {
            if (tokens.get(index).getTokenType().equals(Token.TokenType.MULTIPLY)) { index++; x *= evalFactor(); }
            else if (tokens.get(index).getTokenType().equals(Token.TokenType.DIVIDE)) { index++; x /= evalFactor(); }
            else if (tokens.get(index).getTokenType().equals(Token.TokenType.POW)) { index++; x = Math.pow(x, evalFactor()); }
            else { return x; }
        }
        return x;
    }

    private double evalFactor() {
        double x;
        // Checking for unary operators.
        if (tokens.get(index).getTokenType().equals(Token.TokenType.PLUS)) { index++; return evalFactor(); }
        else if (tokens.get(index).getTokenType().equals(Token.TokenType.MINUS)) { index++; return -evalFactor(); }

        if (tokens.get(index).getTokenType().equals(Token.TokenType.OPEN_BRACE)) {
            index++;
            x = evalExpression();
            while (tokens.get(index).getTokenType().equals(Token.TokenType.CLOSE_BRACE)) {
                index++;
            }
        } else if (tokens.get(index).getTokenType().equals(Token.TokenType.NUMBER)) {
            x = toDouble(tokens.get(index)); if (tokens.size() > index + 1) {index++;}
        } else if (tokens.get(index).getTokenType().equals(Token.TokenType.FUNCTION)) {
            String function = tokens.get(index).getText(); index++;
            x = evalFactor();
            x = evalFunction(function, x);
        } else {
            throw new RuntimeException("Unexpected token: " + tokens.get(index));
        }
        return x;
    }

    /**
     * Helper function that maps the given string with one of the available functions and returns the result of
     * applying that function to x.
     * @param function string representation of a function (sqrt, sin, cos, tan)
     * @param x value to apply function
     * @return result
     */
    private double evalFunction(String function, double x) {
        if (function.equals("sqrt")) { return Math.sqrt(x); }
        else if (function.equals("sin")) { return Math.sin(x); }
        else if (function.equals("cos")) { return Math.cos(x); }
        else if (function.equals("tan")) { return Math.tan(x); }
        else {
            throw new RuntimeException("Unsupported function: " + function);
        }
    }

    private double toDouble(Token a) { return Double.valueOf(a.getText()); }
}
