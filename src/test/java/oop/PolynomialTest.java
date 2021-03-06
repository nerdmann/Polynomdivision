package oop;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class PolynomialTest {

    @Test
    public void test1() {
        // x^4-3x^2+5x-1 : x^2-3x+1 = x^2+3x+5 Rest: 17x-6
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial().withCoefficient(-1).withExponent(0));
        p.addMonomial(new Monomial().withCoefficient(5).withExponent(1));
        p.addMonomial(new Monomial().withCoefficient(-3).withExponent(2));
        p.addMonomial(new Monomial().withCoefficient(1).withExponent(4));

        Polynomial h = new Polynomial();
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(0));
        h.addMonomial(new Monomial().withCoefficient(-3).withExponent(1));
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(2));

        Polynomial result = p.divide(h);
        Polynomial remainder = p.modulo(h);

        assertEquals("x^2+3x+5", result.toString());
        assertEquals("17x-6", remainder.toString());
    }

    @Test
    public void test2() {
        // x^4-3x : x^2-3x+1 = x^2+3x+8 Rest: 18x-8
        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial().withCoefficient(1).withExponent(4));
        q.addMonomial(new Monomial().withCoefficient(-3).withExponent(1));

        Polynomial h = new Polynomial();
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(2));
        h.addMonomial(new Monomial().withCoefficient(-3).withExponent(1));
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(0));

        Polynomial result = q.divide(h);
        Polynomial remainder = q.modulo(h);

        assertEquals("x^2+3x+8", result.toString());
        assertEquals("18x-8", remainder.toString());
    }

    @Test
    public void test3() {
        // 2x^4-3x^2+2x-1 : x^2-3x+1 = 2x^2+6x+13 Rest: 35x-14
        Polynomial o = new Polynomial();
        o.addMonomial(new Monomial().withCoefficient(2).withExponent(4));
        o.addMonomial(new Monomial().withCoefficient(-3).withExponent(2));
        o.addMonomial(new Monomial().withCoefficient(2).withExponent(1));
        o.addMonomial(new Monomial().withCoefficient(-1).withExponent(0));

        Polynomial h = new Polynomial();
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(2));
        h.addMonomial(new Monomial().withCoefficient(-3).withExponent(1));
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(0));

        Polynomial result = o.divide(h);
        Polynomial remainder = o.modulo(h);

        assertEquals("2x^2+6x+13", result.toString());
        assertEquals("35x-14", remainder.toString());
    }

    @Test
    public void test4() {
        // 1 : 1 = 1 Rest 0
        Polynomial o = new Polynomial();
        o.addMonomial(new Monomial().withCoefficient(1).withExponent(0));

        Polynomial h = new Polynomial();
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(0));

        Polynomial result = o.divide(h);
        Polynomial remainder = o.modulo(h);

        assertEquals("1", result.toString());
        assertEquals("0", remainder.toString());
    }

    @Test
    public void test5() {
        // x-1 : x-1 = 1 Rest 0
        Polynomial o = new Polynomial();
        o.addMonomial(new Monomial().withCoefficient(1).withExponent(1));
        o.addMonomial(new Monomial().withCoefficient(-1).withExponent(0));

        Polynomial h = new Polynomial();
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(1));
        h.addMonomial(new Monomial().withCoefficient(-1).withExponent(0));

        Polynomial result = o.divide(h);
        Polynomial remainder = o.modulo(h);

        assertEquals("1", result.toString());
        assertEquals("0", remainder.toString());
    }

    @Test
    public void test6() {
        // 0 : x-1 = 0 Rest 0
        Polynomial o = new Polynomial();
        o.addMonomial(new Monomial().withCoefficient(0).withExponent(0));

        Polynomial h = new Polynomial();
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(1));
        h.addMonomial(new Monomial().withCoefficient(-1).withExponent(0));

        Polynomial result = o.divide(h);
        Polynomial remainder = o.modulo(h);

        assertEquals("0", result.toString());
        assertEquals("0", remainder.toString());
    }

    @Test
    public void test7() {
        // 2x-2 : x-1 = 2 Rest 0
        Polynomial o = new Polynomial();
        o.addMonomial(new Monomial().withCoefficient(-2).withExponent(0));
        o.addMonomial(new Monomial().withCoefficient(2).withExponent(1));

        Polynomial h = new Polynomial();
        h.addMonomial(new Monomial().withCoefficient(1).withExponent(1));
        h.addMonomial(new Monomial().withCoefficient(-1).withExponent(0));

        Polynomial result = o.divide(h);
        Polynomial remainder = o.modulo(h);

        assertEquals("2", result.toString());
        assertEquals("0", remainder.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void divideWithEmptyDividend() {
        Polynomial p = new Polynomial();
        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial());

        p.divide(q);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divideWithEmptyDivisor() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial());
        Polynomial q = new Polynomial();

        p.divide(q);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divideByZero() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial().withCoefficient(1));
        Polynomial q = new Polynomial();
        q.addMonomial(new Monomial());

        p.divide(q);
    }

    @Test(expected = IllegalArgumentException.class)
    public void divideWithNullDivisor() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial());

        p.divide(null);
    }

    @Test
    public void getMonomialWithHighestExponent() {
        Polynomial polynom = new Polynomial();
        polynom.addMonomial(new Monomial().withCoefficient(1.2).withExponent(2));
        polynom.addMonomial(new Monomial().withCoefficient(2).withExponent(4));
        polynom.addMonomial(new Monomial().withCoefficient(3).withExponent(1));

        assertEquals(4, polynom.getLeadingMonomial().getExponent());
    }

    @Test
    public void polynomToString() {
        Polynomial polynom = new Polynomial();
        polynom.addMonomial(new Monomial().withCoefficient(1.2).withExponent(2));
        polynom.addMonomial(new Monomial().withCoefficient(2).withExponent(4));
        polynom.addMonomial(new Monomial().withCoefficient(-3).withExponent(1));
        polynom.addMonomial(new Monomial().withCoefficient(1).withExponent(3));
        polynom.addMonomial(new Monomial().withCoefficient(1));

        assertEquals("2x^4+x^3+1.2x^2-3x+1", polynom.toString());
    }

    @Test
    public void zeroPolynomToString() {
        Polynomial polynom = new Polynomial();
        polynom.addMonomial(new Monomial());

        assertEquals("0", polynom.toString());
    }

    @Test
    public void emptyPolynomToString() {
        Polynomial polynom = new Polynomial();
        assertEquals("0", polynom.toString());
    }

    @Test
    public void multiply() {
        Polynomial polynom = new Polynomial();
        polynom.addMonomial(new Monomial().withCoefficient(5).withExponent(2));
        polynom.addMonomial(new Monomial().withCoefficient(2).withExponent(4));
        polynom.addMonomial(new Monomial().withCoefficient(-3).withExponent(1));

        Monomial factor = new Monomial().withCoefficient(2).withExponent(4);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addMonomial(new Monomial().withCoefficient(4).withExponent(8));
        expectedResult.addMonomial(new Monomial().withCoefficient(10).withExponent(6));
        expectedResult.addMonomial(new Monomial().withCoefficient(-6).withExponent(5));

        polynom.multiply(factor);
        assertEquals(expectedResult, polynom);
    }

    @Test
    public void subtractWithSameExponent() {
        Polynomial minuend = new Polynomial();
        minuend.addMonomial(new Monomial().withCoefficient(5).withExponent(2));
        minuend.addMonomial(new Monomial().withCoefficient(2).withExponent(4));
        Polynomial subtrahend = new Polynomial();
        subtrahend.addMonomial(new Monomial().withCoefficient(5).withExponent(2));
        subtrahend.addMonomial(new Monomial().withCoefficient(2).withExponent(4));

        minuend.subtract(subtrahend);

        Polynomial expectedResult = new Polynomial();
        assertEquals(expectedResult, minuend);
    }

    @Test
    public void subtractWithDifferentExponent() {
        Polynomial minuend = new Polynomial();
        minuend.addMonomial(new Monomial().withCoefficient(1).withExponent(4));
        minuend.addMonomial(new Monomial().withCoefficient(-3).withExponent(2));
        minuend.addMonomial(new Monomial().withCoefficient(5).withExponent(1));
        minuend.addMonomial(new Monomial().withCoefficient(-1));

        Polynomial subtrahend = new Polynomial();
        subtrahend.addMonomial(new Monomial().withCoefficient(1).withExponent(4));
        subtrahend.addMonomial(new Monomial().withCoefficient(-3).withExponent(3));
        subtrahend.addMonomial(new Monomial().withCoefficient(1).withExponent(2));

        minuend.subtract(subtrahend);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addMonomial(new Monomial().withCoefficient(3).withExponent(3));
        expectedResult.addMonomial(new Monomial().withCoefficient(-4).withExponent(2));
        expectedResult.addMonomial(new Monomial().withCoefficient(5).withExponent(1));
        expectedResult.addMonomial(new Monomial().withCoefficient(-1));

        assertEquals(expectedResult, minuend);
    }

    @Test
    public void subtractWithDifferentExponent2() {
        Polynomial minuend = new Polynomial();
        minuend.addMonomial(new Monomial().withCoefficient(8).withExponent(2));
        minuend.addMonomial(new Monomial().withCoefficient(-6).withExponent(1));

        Polynomial subtrahend = new Polynomial();
        subtrahend.addMonomial(new Monomial().withCoefficient(8).withExponent(2));
        subtrahend.addMonomial(new Monomial().withCoefficient(-24).withExponent(1));
        subtrahend.addMonomial(new Monomial().withCoefficient(8));

        minuend.subtract(subtrahend);

        Polynomial expectedResult = new Polynomial();
        expectedResult.addMonomial(new Monomial().withCoefficient(18).withExponent(1));
        expectedResult.addMonomial(new Monomial().withCoefficient(-8));

        assertEquals(expectedResult, minuend);
    }

    @Test
    public void isEmpty() {
        Polynomial p = new Polynomial();
        assertTrue(p.isEmpty());
    }

    @Test
    public void isNotEmpty() {
        Polynomial p = new Polynomial();
        p.addMonomial(new Monomial());
        assertFalse(p.isEmpty());
    }
}
