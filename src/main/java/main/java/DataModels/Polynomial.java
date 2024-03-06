package main.java.DataModels;

import main.java.WrongPolynomialExeption;

import java.util.HashMap;
import java.util.Objects;
import java.util.TreeMap;

public class Polynomial {
    private HashMap<Double, Monomial> terms;
    private static int index;
    private static final int SIZE = 50;
    private boolean constant;

    public Polynomial(){
        terms = new HashMap<>(SIZE);
        constant = false;
        index = 0;
    }

    public void setConstant()
    {
        constant = true;
    }

    public HashMap<Double, Monomial> getTerms(){
        return this.terms;
    }

    public void newTerm(Double exponent, Double coeficient) throws WrongPolynomialExeption {
        if(index >= SIZE)
            throw new WrongPolynomialExeption();
        terms.put(exponent, new Monomial(exponent, coeficient));
    }

    public void removeZero()
    {
        Double[] a = new Double[50];
        int index = 0;
        for(Double i: terms.keySet())
            if(this.getTerms().get(i).getCoeficient() == 0.0) {
                a[index] = i;
                index++;
            }
        for(int j = 0; j < index; j++)
            terms.remove(a[j]);

        if(this.getTerms().isEmpty()) {
            try {
                this.newTerm(0.0, 0.0);
            } catch (WrongPolynomialExeption e) {
                throw new RuntimeException(e);
            }
        }
    }

    private TreeMap<Double, Monomial> order()
    {
        TreeMap<Double, Monomial> treeMap = new TreeMap<Double, Monomial>();
        for(Double i: this.getTerms().keySet())
            treeMap.put(i, this.getTerms().get(i));
        return treeMap;
    }

    private String removePlus(String s)
    {
        if(s.length() >= 4 && !constant)
            return s.substring(0, s.length() - 3);
        else if (constant) {
            return s + "C";
        } else
            return s;
    }

    private String nullExponent(String s)
    {
        if(this.getTerms().get(0.0).getCoeficient() >= 0.0)
            s += this.getTerms().get(0.0).getCoeficient() + " + ";
        else {
            if (s.length() > 3)
                s = s.substring(0, s.length() - 3);
            s += " - " + (-this.getTerms().get(0.0).getCoeficient()) + " + ";
        }
        return s;
    }

    private String positiveCoeficient(String s, Double i)
    {
        if(this.getTerms().get(i).getCoeficient() == 1.0)
            if(i != 1.0)
                s += "X^" + i.shortValue() + " + ";
            else
                s += "X + ";
        else
        if(i != 1.0)
            s += this.getTerms().get(i).getCoeficient() + "*X^" + i.shortValue() + " + ";
        else
            s += this.getTerms().get(i).getCoeficient() + "*X + ";
        return s;
    }

    private String negativeCoeficient(String s, Double i)
    {
        if (s.length() > 3)
            s = s.substring(0, s.length() - 3);
        if(this.getTerms().get(i).getCoeficient() == -1.0)
            if(i != 1.0)
                s += " - " + "X^" + i.shortValue() + " + ";
            else
                s += " - " + "X + ";
        else
        if(i != 1.0)
            s += " - " + (-this.getTerms().get(i).getCoeficient()) + "*X^" + i.shortValue() + " + ";
        else
            s += " - " + (-this.getTerms().get(i).getCoeficient()) + "*X + ";
        return s;
    }

    public String toString()
    {
        String s = "";
        TreeMap<Double, Monomial> treeMap = order();
        for(Double i: treeMap.descendingMap().keySet())
        {
                if(i == 0.0)
                    s = nullExponent(s);
                else
                        if(this.getTerms().get(i).getCoeficient() >= 0.0)
                            s = positiveCoeficient(s, i);
                        else
                            s = negativeCoeficient(s, i);
        }
        return removePlus(s);
    }

    @Override
    public boolean equals(Object o) {
        if(!(o instanceof Polynomial))
            return false;
        if(this.getTerms().size() != ((Polynomial) o).getTerms().size())
            return false;
        if(this.constant != ((Polynomial) o).constant)
            return false;
        for(Double i: this.getTerms().keySet())
            if(!((Polynomial) o).getTerms().containsKey(i))
                return false;
            else
                if(!((Polynomial) o).getTerms().get(i).getCoeficient().equals(this.getTerms().get(i).getCoeficient()))
                    return false;
        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hash(terms, constant);
    }
}
