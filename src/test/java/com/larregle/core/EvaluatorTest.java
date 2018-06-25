package com.larregle.core;

import org.junit.Assert;
import org.junit.Test;

public class EvaluatorTest {

    private static final double DELTA = 1e-15;

    @Test
    public void evaluateAdd() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("9 + 9"), 18D, DELTA);
    }

    @Test
    public void evaluateMinus() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("9 - 9"), 0D, DELTA);
    }

    @Test
    public void evaluateMultiply() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("9 * 9"), 81D, DELTA);
    }

    @Test
    public void evaluateDivide() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("9 / 9"), 1D, DELTA);
    }

    @Test
    public void evaluatePow() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("5 ^ 2"), 25D, DELTA);
    }

    @Test
    public void evaluateSqrt() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("sqrt(9)"), 3D, DELTA);
    }
    @Test
    public void evaluateCos() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("cos(0)"), 1D, DELTA);
    }

    @Test
    public void evaluateMultipleOperations() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("sqrt(3 * 3) + sqrt(3 * 3)"), 6D, DELTA);
    }

    @Test
    public void evaluateMultipleGroupedOperations() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("(sqrt(3 * 3) + sqrt(3 * 3)) * 2"), 12D, DELTA);
    }

    @Test
    public void evaluateMultipleGroupedOperationsWithUnaryOperator() throws Exception {
        Assert.assertEquals(new Evaluator().evaluate("((4 - 2^3 + 1) * -sqrt(3*3 + (sqrt(3*3)+13))) + 2"), 17D, DELTA);
    }


}