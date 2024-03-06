package test.java;
import main.java.BusinessLogic.Operations;
import main.java.DataModels.Polynomial;
import main.java.WrongPolynomialExeption;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationsTest {
    Polynomial p = new Polynomial();
    Polynomial q = new Polynomial();
    Polynomial x = new Polynomial();
    @BeforeEach
    public void init()
    {
        try {
            p.newTerm(5.0, 4.0);
            p.newTerm(4.0, -3.0);
            p.newTerm(2.0, 1.0);
            p.newTerm(1.0, -8.0);
            p.newTerm(0.0, 1.0);
            q.newTerm(4.0, 3.0);
            q.newTerm(3.0, -1.0);
            q.newTerm(2.0, 1.0);
            q.newTerm(1.0, 2.0);
            q.newTerm(0.0, -1.0);
        } catch (WrongPolynomialExeption e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void addTest(){
        try {
            x.newTerm(5.0, 4.0);
            x.newTerm(3.0, -1.0);
            x.newTerm(2.0, 2.0);
            x.newTerm(1.0, -6.0);
        }catch (WrongPolynomialExeption e){
            throw  new RuntimeException();
        }
        assertEquals(x, Operations.addPolynomial(p, q));
    }

    @Test
    public void subTest(){
        try {
            x.newTerm(5.0, 4.0);
            x.newTerm(4.0, -6.0);
            x.newTerm(3.0, 1.0);
            x.newTerm(1.0, -10.0);
            x.newTerm(0.0, 2.0);
        }catch (WrongPolynomialExeption e){
            throw  new RuntimeException();
        }
        assertEquals(x, Operations.subPolynomial(p, q));
    }

    @Test
    public void mulTest(){
        try {
            x.newTerm(9.0, 12.0);
            x.newTerm(8.0, -13.0);
            x.newTerm(7.0, 7.0);
            x.newTerm(6.0, 8.0);
            x.newTerm(5.0, -35.0);
            x.newTerm(4.0, 15.0);
            x.newTerm(3.0, -7.0);
            x.newTerm(2.0, -16.0);
            x.newTerm(1.0, 10.0);
            x.newTerm(0.0, -1.0);
        }catch (WrongPolynomialExeption e){
            throw  new RuntimeException();
        }
        assertEquals(x, Operations.mulPolynomial(p, q));
    }

    @Test
    public void divTest(){
        try {
            x.newTerm(1.0, 4.0 / 3.0);
            x.newTerm(0.0, -5.0 / 9.0);
        }catch (WrongPolynomialExeption e){
            throw  new RuntimeException();
        }
        assertEquals(x, Operations.divPolynomial(p, q)[0]);
    }

    @Test
    public void derTest(){
        try {
            x.newTerm(4.0, 20.0);
            x.newTerm(3.0, -12.0);
            x.newTerm(1.0, 2.0);
            x.newTerm(0.0, -8.0);
        }catch (WrongPolynomialExeption e){
            throw  new RuntimeException();
        }
        assertEquals(x, Operations.derivePolynomial(p));
    }

    @Test
    public void integTest(){
        try {
            x.newTerm(6.0, 4.0 / 6.0);
            x.newTerm(5.0, -3.0 / 5.0);
            x.newTerm(3.0, 1.0 / 3.0);
            x.newTerm(2.0, -8.0 / 2.0);
            x.newTerm(1.0, 1.0);
            x.setConstant();
        }catch (WrongPolynomialExeption e){
            throw  new RuntimeException();
        }
        assertEquals(x, Operations.integratePolynomial(p));
    }
}
