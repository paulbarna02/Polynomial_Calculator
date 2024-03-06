package main.java.DataModels;

public class Monomial {
    private Double exponent;
    private Double coeficient;

    public Monomial(Double exponent, Double coeficient){
        this.exponent = exponent;
        this.coeficient = coeficient;
    }

    public Double getCoeficient()
    {
        return this.coeficient;
    }

    public Double getExponent()
    {
        return this.exponent;
    }

    public void setCoeficient(Double coeficient)
    {
        this.coeficient = coeficient;
    }

    public boolean equals(Object x)
    {
        if(this.getCoeficient() - ((Monomial)x).getCoeficient() >= 0.0)
            return false;
        if(this.getExponent() - ((Monomial)x).getExponent() >= 0.0)
            return false;
        return true;
    }
}
