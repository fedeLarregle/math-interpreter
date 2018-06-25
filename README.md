# math-interpreter

Well as the title sais, this is a simple math interpreter.
For now, you can use:
* Decimal numbers and integer numbers
* Functions:
    * tan
    * sin
    * cos
    * sqrt
* Unary Operators:
    * plus (+)
    * minus (-)
* Binary Operators:
    * multiply (*)
    * divide (/)
    * minus (-)
    * plus (+)
    * pow (^)
* Group by parentheses: ()

A few examples:

```java
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
```
