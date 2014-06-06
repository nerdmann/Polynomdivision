package oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeSet;

public final class Polynomial implements Cloneable {

    private TreeSet<Monomial> monomials = new TreeSet<>(Collections.reverseOrder());

    public void addMonomial(Monomial monomial) {
        monomials.add(monomial);
    }

    public Monomial getLeadingMonomial() {
        return monomials.first();
    }

    public Polynomial divide(Polynomial divisor) {
        checkParams(divisor);
        Polynomial q = (Polynomial) divisor.clone();
        return divideWithRemainder(q)[0];
    }

    public Polynomial modulo(Polynomial divisor) {
        checkParams(divisor);
        Polynomial q = (Polynomial) divisor.clone();
        return divideWithRemainder(q)[1];
    }

    private Polynomial[] divideWithRemainder(Polynomial q) {
        Polynomial p = (Polynomial) this.clone();

        Polynomial result = new Polynomial();
        while (divisionIsPossible(p, q)) {
            Monomial leadingMonomialOfP = p.getLeadingMonomial();
            Monomial leadingMonomialOfQ = q.getLeadingMonomial();

            Monomial resultMonomial = new Monomial();
            resultMonomial.setExponent(leadingMonomialOfP.getExponent() - leadingMonomialOfQ.getExponent());
            resultMonomial.setCoefficient(leadingMonomialOfP.getCoefficient() / leadingMonomialOfQ.getCoefficient());
            result.addMonomial(resultMonomial);

            Polynomial o = (Polynomial) q.clone();
            o.multiply(resultMonomial);
            p.subtract(o);
        }
        return new Polynomial[] {result, p};
    }

    private static boolean divisionByZero(Polynomial divisor) {
        Monomial leadingMonomial = divisor.getLeadingMonomial();
        return leadingMonomial.getCoefficient() == 0 && leadingMonomial.getExponent() == 0;
    }

    /*
     * Eine Division ist dann möglich, wenn beide Polynome nicht leer sind und
     * der Grad des Dividenden größer oder gleich dem des Divisors ist
     */
    private boolean divisionIsPossible(Polynomial p, Polynomial q) {
        return !p.isEmpty() && !q.isEmpty() && p.getLeadingMonomial().getExponent() >= q.getLeadingMonomial().getExponent();
    }

    private void checkParams(Polynomial divisor) {
        if (emptyPolynomial(divisor) || divisionByZero(divisor)) {
            throw new IllegalArgumentException("Dividend und Divisor müssen jeweils mindestens ein Monom enthalten. Division durch 0 ist nicht erlaubt.");
        }
    }

    private boolean emptyPolynomial(Polynomial divisor) {
        return divisor == null || divisor.isEmpty();
    }

    public void multiply(Monomial resultMonomial) {
        for (Monomial monomial : monomials) {
            monomial.multiply(resultMonomial);
        }
    }

    public void subtract(Polynomial subtrahend) {
        List<Monomial> monomialsToBeRemoved = new ArrayList<>();

        for (Monomial otherMonomial : subtrahend.monomials) {
            Monomial monomial = findByExponent(otherMonomial.getExponent());
            if (monomial == null) {
                Monomial clone = (Monomial) otherMonomial.clone();
                clone.setCoefficient(-(clone.getCoefficient()));
                monomials.add(clone);
            } else {
                monomial.subtract(otherMonomial);
                if (monomial.getCoefficient() == 0) {
                    monomialsToBeRemoved.add(monomial);
                }
            }
        }
        monomials.removeAll(monomialsToBeRemoved);
    }

    public boolean isEmpty() {
        return monomials.size() == 0;
    }

    private Monomial findByExponent(int exponent) {
        for (Monomial monomial : monomials) {
            if (monomial.getExponent() == exponent) {
                return monomial;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        StringBuilder prettyPolynom = new StringBuilder();
        if (monomials.size() > 0) {
            for (Monomial monomial : monomials) {
                prettyPolynom.append(monomial.toString());
            }
        } else {
            prettyPolynom.append(new Monomial().toString());
        }
        removeStartingPlus(prettyPolynom);
        return prettyPolynom.toString();
    }

    private void removeStartingPlus(StringBuilder prettyPolynom) {
        if (prettyPolynom.charAt(0) == '+') {
            prettyPolynom.replace(0, 1, "");
        }
    }

    @Override
    protected Object clone() {
        Polynomial clone = new Polynomial();
        for (Monomial monomial : monomials) {
            clone.addMonomial((Monomial) monomial.clone());
        }
        return clone;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((monomials == null) ? 0 : monomials.hashCode());
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
        Polynomial other = (Polynomial) obj;
        if (monomials == null) {
            if (other.monomials != null)
                return false;
        } else if (!monomials.equals(other.monomials)) {
            return false;
        }
        return true;
    }

}
