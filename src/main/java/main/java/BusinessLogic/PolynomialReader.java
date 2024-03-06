package main.java.BusinessLogic;
import main.java.DataModels.Polynomial;
import main.java.WrongPolynomialExeption;

import java.util.regex.*;

public class PolynomialReader {
    public static Polynomial read(String s) {
        Polynomial p = new Polynomial();
        Pattern pattern = Pattern.compile("([+-]?\\d*\\.?\\d*)\\*?[xX]?\\^?(\\d\\.?\\d*)*");
        Matcher matcher = pattern.matcher(s);
        while(matcher.find()) {
            Double coef = 0.0;
            Double exponent = 0.0;
            try {
                if (matcher.group(1) != null && !matcher.group(1).equals(""))
                    if(matcher.group(1).equals("+"))
                        coef = 1.0;
                    else
                        if(matcher.group(1).trim().equals("-"))
                            coef = -1.0;
                        else
                            coef = Double.parseDouble(matcher.group(1));
                if (matcher.group(2) != null && !matcher.group(2).equals(""))
                    exponent = Double.parseDouble(matcher.group(2));
                if(matcher.group().contains("X"))
                {
                    if(matcher.group(2) == null || matcher.group(2).equals(""))
                            exponent = 1.0;
                    if(matcher.group(1) == null || matcher.group(1).equals(""))
                            coef = 1.0;
                }
                if(coef != 0.0 || exponent != 0.0) {
                    p.newTerm(exponent, coef);
                }
            } catch (WrongPolynomialExeption e) {
                throw new RuntimeException(e);
            }
        }
        return p;
    }
}
