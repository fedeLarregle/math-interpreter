package com.larregle.core;

import org.junit.Assert;
import org.junit.Test;

import java.nio.charset.MalformedInputException;
import java.util.List;

public class TokenizerTest {

    @Test
    public void parseSimpleExpression() throws Exception {
        List<Token> parse = new Tokenizer().parse("4 * 4");

        Assert.assertEquals(parse.size(), 3);
        Assert.assertEquals(parse.get(0).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(1).getTokenType(), Token.TokenType.MULTIPLY);
        Assert.assertEquals(parse.get(2).getTokenType(), Token.TokenType.NUMBER);
    }

    @Test
    public void parseComplexExpression() throws Exception {
        List<Token> parse = new Tokenizer().parse("(sqrt(3 * 3) + sqrt(3 * 3)) * 2");

        Assert.assertEquals(parse.size(), 17);
        Assert.assertEquals(parse.get(0).getTokenType(), Token.TokenType.OPEN_BRACE);
        Assert.assertEquals(parse.get(1).getTokenType(), Token.TokenType.FUNCTION);
        Assert.assertEquals(parse.get(2).getTokenType(), Token.TokenType.OPEN_BRACE);
        Assert.assertEquals(parse.get(3).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(4).getTokenType(), Token.TokenType.MULTIPLY);
        Assert.assertEquals(parse.get(5).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(6).getTokenType(), Token.TokenType.CLOSE_BRACE);
        Assert.assertEquals(parse.get(7).getTokenType(), Token.TokenType.PLUS);
        Assert.assertEquals(parse.get(8).getTokenType(), Token.TokenType.FUNCTION);
        Assert.assertEquals(parse.get(9).getTokenType(), Token.TokenType.OPEN_BRACE);
        Assert.assertEquals(parse.get(10).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(11).getTokenType(), Token.TokenType.MULTIPLY);
        Assert.assertEquals(parse.get(12).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(13).getTokenType(), Token.TokenType.CLOSE_BRACE);
        Assert.assertEquals(parse.get(14).getTokenType(), Token.TokenType.CLOSE_BRACE);
        Assert.assertEquals(parse.get(15).getTokenType(), Token.TokenType.MULTIPLY);
        Assert.assertEquals(parse.get(16).getTokenType(), Token.TokenType.NUMBER);
    }

    @Test
    public void parseExpressionAvoidWhiteSpaces() throws Exception {
        List<Token> parse = new Tokenizer().parse("4      * 4 + sqrt   (3 * 3)");

        Assert.assertEquals(parse.size(), 10);
        Assert.assertEquals(parse.get(0).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(1).getTokenType(), Token.TokenType.MULTIPLY);
        Assert.assertEquals(parse.get(2).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(3).getTokenType(), Token.TokenType.PLUS);
        Assert.assertEquals(parse.get(4).getTokenType(), Token.TokenType.FUNCTION);
        Assert.assertEquals(parse.get(5).getTokenType(), Token.TokenType.OPEN_BRACE);
        Assert.assertEquals(parse.get(6).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(7).getTokenType(), Token.TokenType.MULTIPLY);
        Assert.assertEquals(parse.get(8).getTokenType(), Token.TokenType.NUMBER);
        Assert.assertEquals(parse.get(9).getTokenType(), Token.TokenType.CLOSE_BRACE);
    }

    @Test(expected = MalformedInputException.class)
    public void parseIncorrectDecimalNumber() throws Exception {
        new Tokenizer().parse("123.123.123");
    }

    @Test(expected = MalformedInputException.class)
    public void parseIncorrectDecimalNumberCommas() throws Exception {
        new Tokenizer().parse("123,123,123");
    }

}