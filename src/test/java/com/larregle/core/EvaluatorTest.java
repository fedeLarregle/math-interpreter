package com.larregle.core;

import org.junit.Assert;
import static org.junit.Assert.*;
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

}