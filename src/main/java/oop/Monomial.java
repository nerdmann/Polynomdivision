package oop;

public final class Monomial implements Comparable<Monomial>, Cloneable {

    private double coefficient;
    private int exponent;

    public Monomial withCoefficient(double coefficient) {
        this.coefficient = coefficient;
        return this;
    }

    public Monomial withExponent(int exponent) {
        this.exponent = exponent;
        return this;
    }

    public void setCoefficient(double coefficient) {
        this.coefficient = coefficient;
    }

    public void setExponent(int exponent) {
        this.exponent = exponent;
    }

    public double getCoefficient() {
        return coefficient;
    }

    public int getExponent() {
        return exponent;
    }

    public void multiply(Monomial factor) {
        this.coefficient *= factor.coefficient;
        this.exponent += factor.exponent;
    }

    public void subtract(Monomial minuend) {
        if (exponent != minuend.exponent) {
            throw new IllegalArgumentException("Die Potenzen der beiden Monome m�ssen �bereinstimmen!");
        }
        this.coefficient -= minuend.coefficient;
    }

    @Override
    public int compareTo(Monomial other) {
        return Integer.compare(exponent, other.exponent);
    }

    @Override
    protected Object clone() {
        Monomial clone = new Monomial();
        clone.exponent = this.exponent;
        clone.coefficient = this.coefficient;
        return clone;
    }

    @Override
    public String toString() {
        if (coefficient == 0 && exponent != 0) {
            return "";
        } else if (coefficient == 0 && exponent == 0) {
            return String.valueOf(0);
        }

        if (exponent > 1) {
            return (coefficient > 0 ? "+" : "") + getCoefficientString() + "x^" + exponent;
        } else if (exponent == 1) {
            return (coefficient > 0 ? "+" : "") + getCoefficientString() + "x";
        } else {
            return (coefficient > 0 ? "+" : "") + getCoefficientString();
        }
    }

    private String getCoefficientString() {
        if (isCoefficientInteger() && exponent != 0) {
            return String.valueOf(coefficient == 1 ? "" : (int) coefficient);
        } else if (isCoefficientInteger()) {
            return String.valueOf((int) coefficient);
        } else {
            return String.valueOf(coefficient);
        }
    }

    private boolean isCoefficientInteger() {
        return coefficient == Math.rint(coefficient);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        long temp;
        temp = Double.doubleToLongBits(coefficient);
        result = prime * result + (int) (temp ^ (temp >>> 32));
        result = prime * result + exponent;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Monomial other = (Monomial) obj;
        if (Double.doubleToLongBits(coefficient) != Double.doubleToLongBits(other.coefficient))
            return false;
        if (exponent != other.exponent)
            return false;
        return true;
    }

}
