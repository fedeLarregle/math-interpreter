# math-interpreter

Well as the title sais, this is a simple math interpreter.
For now, you can use:
* Decimal numbers and integer numbers
* Functions:
...* tan, sin, cos and sqrt
* Binary Operators:
...* * / - +
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
```
