package oop;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class MonomialTest {

    @Test
    public void monomialToStringWithExponent0() {
        Monomial monomial = new Monomial().withCoefficient(1);
        assertEquals("+1", monomial.toString());
    }

    @Test
    public void monomialToStringWithExponent1() {
        Monomial monomial = new Monomial().withCoefficient(2.3).withExponent(1);
        assertEquals("+2.3x", monomial.toString());
    }

    @Test
    public void monomialToStringWithExponent2() {
        Monomial monomial = new Monomial().withCoefficient(2.3).withExponent(2);
        assertEquals("+2.3x^2", monomial.toString());
    }

    @Test
    public void monomialToStringWithCoefficient0() {
        Monomial monomial = new Monomial().withExponent(3);
        assertEquals("", monomial.toString());
    }

    @Test
    public void monomialToStringWithCoefficient0AndExponent0() {
        Monomial monomial = new Monomial();
        assertEquals("0", monomial.toString());
    }

    @Test
    public void monomialToStringWithCoefficient1() {
        Monomial monomial = new Monomial().withCoefficient(1).withExponent(3);
        assertEquals("+x^3", monomial.toString());
    }

    @Test
    public void multiply() {
        Monomial monomial = new Monomial().withCoefficient(3).withExponent(2);
        Monomial factor = new Monomial().withCoefficient(4).withExponent(2);
        monomial.multiply(factor);

        Monomial expectedResult = new Monomial().withCoefficient(12).withExponent(4);

        assertEquals(expectedResult, monomial);
    }

    @Test
    public void subtractPositiveCoefficient() {
        Monomial subtrahend = new Monomial().withCoefficient(5).withExponent(2);
        Monomial minuend = new Monomial().withCoefficient(3).withExponent(2);

        Monomial expectedResult = new Monomial().withCoefficient(2).withExponent(2);

        subtrahend.subtract(minuend);

        assertEquals(expectedResult, subtrahend);
    }

    @Test
    public void subtractWithTwoNegativeCoefficients() {
        Monomial subtrahend = new Monomial().withCoefficient(-6).withExponent(2);
        Monomial minuend = new Monomial().withCoefficient(-30).withExponent(2);

        Monomial expectedResult = new Monomial().withCoefficient(24).withExponent(2);

        subtrahend.subtract(minuend);

        assertEquals(expectedResult, subtrahend);
    }

    @Test
    public void subtractNegativeCoefficient() {
        Monomial subtrahend = new Monomial().withCoefficient(2).withExponent(2);
        Monomial minuend = new Monomial().withCoefficient(-15).withExponent(2);

        Monomial expectedResult = new Monomial().withCoefficient(17).withExponent(2);

        subtrahend.subtract(minuend);

        assertEquals(expectedResult, subtrahend);
    }

    @Test(expected = IllegalArgumentException.class)
    public void subtractWithDifferentExponent() {
        Monomial subtrahend = new Monomial().withCoefficient(2).withExponent(2);
        Monomial minuend = new Monomial().withCoefficient(2).withExponent(3);
        subtrahend.subtract(minuend);
    }
}
