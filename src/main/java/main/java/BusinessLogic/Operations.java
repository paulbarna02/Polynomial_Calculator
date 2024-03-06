package main.java.BusinessLogic;

import main.java.DataModels.Polynomial;
import main.java.WrongPolynomialExeption;

public class Operations {

    public static Polynomial addPolynomial(Polynomial p, Polynomial q)
    {
        Polynomial x = new Polynomial();
        for(Double i: p.getTerms().keySet())
        {
            if(q.getTerms().containsKey(i)) {
                try {
                        x.newTerm(i, p.getTerms().get(i).getCoeficient() + q.getTerms().get(i).getCoeficient());
                } catch (WrongPolynomialExeption e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    x.newTerm(i, p.getTerms().get(i).getCoeficient());
                } catch (WrongPolynomialExeption e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for(Double i: q.getTerms().keySet())
            if(!x.getTerms().containsKey(i)) {
                try {
                    x.newTerm(i, q.getTerms().get(i).getCoeficient());
                } catch (WrongPolynomialExeption e) {
                    throw new RuntimeException(e);
                }
            }
        x.removeZero();
        return x;
    }

    public static Polynomial subPolynomial(Polynomial p, Polynomial q)
    {
        Polynomial x = new Polynomial();
        for(Double i: p.getTerms().keySet())
        {
            if(q.getTerms().containsKey(i)) {
                try {
                        x.newTerm(i, p.getTerms().get(i).getCoeficient() - q.getTerms().get(i).getCoeficient());
                } catch (WrongPolynomialExeption e) {
                    throw new RuntimeException(e);
                }
            }
            else {
                try {
                    x.newTerm(i, p.getTerms().get(i).getCoeficient());
                } catch (WrongPolynomialExeption e) {
                    throw new RuntimeException(e);
                }
            }
        }
        for(Double i: q.getTerms().keySet())
            if(!x.getTerms().containsKey(i)) {
                try {
                    x.newTerm(i, -q.getTerms().get(i).getCoeficient());
                } catch (WrongPolynomialExeption e) {
                    throw new RuntimeException(e);
                }
            }
        x.removeZero();
        return x;
    }

    public static Polynomial mulPolynomial(Polynomial p, Polynomial q)
    {
        Polynomial x = new Polynomial();
        for(Double i: p.getTerms().keySet())
            for(Double j: q.getTerms().keySet())
            {
                if(x.getTerms().containsKey(i + j))
                    x.getTerms().get(i + j).setCoeficient(x.getTerms().get(i + j).getCoeficient() + p.getTerms().get(i).getCoeficient() * q.getTerms().get(j).getCoeficient());
                else {
                    try {
                        x.newTerm(i + j, p.getTerms().get(i).getCoeficient() * q.getTerms().get(j).getCoeficient());
                    } catch (WrongPolynomialExeption e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        x.removeZero();
        return x;
    }

    private static Double[] getMax(Polynomial p, Polynomial q)
    {
        Double maxKeyP = 0.0;
        Double maxKeyQ = 0.0;

        for(Double i: p.getTerms().keySet())
            if(i > maxKeyP)
                maxKeyP = i;
        for(Double i: q.getTerms().keySet())
            if(i > maxKeyQ)
                maxKeyQ = i;
        if(maxKeyQ > maxKeyP)
        {
            Polynomial aux = p;
            p = q;
            q = aux;
            Double a = maxKeyP;
            maxKeyP = maxKeyQ;
            maxKeyQ = a;
        }

        if(p.getTerms().get(maxKeyP).getCoeficient() == 0.0)
            throw new ArithmeticException();

        Double[] d = new Double[2];
        d[0] = maxKeyP;
        d[1] = maxKeyQ;
        return d;
    }

    public static Polynomial[] divPolynomial(Polynomial p, Polynomial q)
    {
        Polynomial[] x = new Polynomial[2];
        x[0] = new Polynomial();
        x[1] = new Polynomial();
        Double[] maxKey = getMax(p, q);
        Double maxKeyP = maxKey[0];
        Double maxKeyQ = maxKey[1];
        while(!p.toString().equals("0.0") && maxKeyP >= maxKeyQ) {
            try {
                x[0].newTerm(maxKeyP - maxKeyQ, p.getTerms().get(maxKeyP).getCoeficient() / q.getTerms().get(maxKeyQ).getCoeficient());
            } catch (WrongPolynomialExeption e) {
                throw new RuntimeException(e);
            }
            Polynomial y = new Polynomial();
            try {
                y.newTerm(maxKeyP - maxKeyQ, p.getTerms().get(maxKeyP).getCoeficient() / q.getTerms().get(maxKeyQ).getCoeficient());
                p = subPolynomial(p, mulPolynomial(y, q));
            } catch (WrongPolynomialExeption e) {
                throw new RuntimeException(e);
            }
            maxKeyP = 0.0;
            for (Double i : p.getTerms().keySet())
                if (i > maxKeyP)
                    maxKeyP = i;
        }
        x[1] = p;
        x[0].removeZero();
        //x[1].removeZero();
        return x;
    }

    public static Polynomial derivePolynomial(Polynomial p)
    {
        Polynomial q = new Polynomial();
        for(Double i: p.getTerms().keySet())
        {
            try {
                q.newTerm(i - 1.0, p.getTerms().get(i).getCoeficient() * i);
            } catch (WrongPolynomialExeption e) {
                throw new RuntimeException(e);
            }
        }
        q.removeZero();
        return q;
    }

    public static Polynomial integratePolynomial(Polynomial p)
    {
        Polynomial q = new Polynomial();
        for(Double i: p.getTerms().keySet())
        {
            try{
                q.newTerm(i + 1.0, p.getTerms().get(i).getCoeficient() / (i + 1.0));
            } catch (WrongPolynomialExeption e){
                throw  new RuntimeException(e);
            }
        }
        q.setConstant();
        q.removeZero();
        return q;
    }
}
