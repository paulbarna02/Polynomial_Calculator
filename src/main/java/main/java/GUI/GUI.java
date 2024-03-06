package main.java.GUI;

import main.java.DataModels.Polynomial;
import main.java.BusinessLogic.Operations;
import main.java.BusinessLogic.PolynomialReader;
import main.java.WrongPolynomialExeption;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class GUI {
    JTextField activeTF;
    public GUI()
    {
        prepareGUI();
    }

    private void prepareGUI()
    {
        JFrame mainFrame = new JFrame("Polynomial calculator");
        mainFrame.setBounds(100, 100, 1000, 500);
        mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainFrame.setResizable(false);

        JPanel panel = new JPanel(null);
        mainFrame.add(panel);

        mainFrame.setVisible(true);

        JLabel label1 = new JLabel("First polynomial:");
        panel.add(label1);
        label1.setBounds(30, 20, 300, 40);
        label1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JLabel label2 = new JLabel("Second polynomial:");
        panel.add(label2);
        label2.setBounds(30, 70, 300, 40);
        label2.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JTextField textField1 = new JTextField();
        panel.add(textField1);
        textField1.setBounds(200, 20, 750, 40);
        textField1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JTextField textField2 = new JTextField();
        panel.add(textField2);
        textField2.setBounds(200, 70, 750, 40);
        textField2.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        activeTF = textField1;

        JLabel res = new JLabel("Result:");
        panel.add(res);
        res.setBounds(30, 120, 300, 40);
        res.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JLabel resField = new JLabel();
        panel.add(resField);
        resField.setBounds(200, 120, 750, 40);
        resField.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        JLabel rest = new JLabel("Remainder:");
        panel.add(rest);
        rest.setBounds(30, 150, 750, 40);
        rest.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        rest.setVisible(false);

        JLabel restLabel = new JLabel();
        panel.add(restLabel);
        restLabel.setBounds(200, 150, 750, 40);
        restLabel.setFont(new Font(Font.SERIF, Font.PLAIN, 20));
        restLabel.setVisible(false);

        JButton add = new JButton("Add");
        panel.add(add);
        add.setBounds(30, 200, 210, 40);
        add.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        add.addActionListener(e -> {
            rest.setVisible(false);
            restLabel.setVisible(false);
            Polynomial p = new Polynomial();
            Polynomial q = new Polynomial();
            if(textField1.getText().equals("") || textField2.getText().equals(""))
                JOptionPane.showMessageDialog(mainFrame, "Introduceti datele.", "Lipsa Date", JOptionPane.WARNING_MESSAGE);
            else {
                p = PolynomialReader.read(textField1.getText().toUpperCase());
                q = PolynomialReader.read(textField2.getText().toUpperCase());
                resField.setText(Operations.addPolynomial(p, q).toString());
            }
        });

        JButton sub = new JButton("Subtract");
        panel.add(sub);
        sub.setBounds(250, 200, 210, 40);
        sub.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        sub.addActionListener(e -> {
            rest.setVisible(false);
            restLabel.setVisible(false);
            Polynomial p = new Polynomial();
            Polynomial q = new Polynomial();
            if(textField1.getText().equals("") || textField2.getText().equals(""))
                JOptionPane.showMessageDialog(mainFrame, "Introduceti datele.", "Lipsa Date", JOptionPane.WARNING_MESSAGE);
            else {
                p = PolynomialReader.read(textField1.getText().toUpperCase());
                q = PolynomialReader.read(textField2.getText().toUpperCase());
                resField.setText(Operations.subPolynomial(p, q).toString());
            }
        });

        JButton mul = new JButton("Multiply");
        panel.add(mul);
        mul.setBounds(30, 250, 210, 40);
        mul.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        mul.addActionListener(e -> {
            rest.setVisible(false);
            restLabel.setVisible(false);
            Polynomial p = new Polynomial();
            Polynomial q = new Polynomial();
            if(textField1.getText().equals("") || textField2.getText().equals(""))
                JOptionPane.showMessageDialog(mainFrame, "Introduceti datele.", "Lipsa Date", JOptionPane.WARNING_MESSAGE);
            else {
                p = PolynomialReader.read(textField1.getText().toUpperCase());
                q = PolynomialReader.read(textField2.getText().toUpperCase());
                resField.setText(Operations.mulPolynomial(p, q).toString());
            }
        });

        JButton div = new JButton("Divide");
        panel.add(div);
        div.setBounds(250, 250, 210, 40);
        div.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        div.addActionListener(e -> {
            Polynomial p = new Polynomial();
            Polynomial q = new Polynomial();
            Polynomial zero = new Polynomial();
            try {
                zero.newTerm(0.0, 0.0);
            } catch (WrongPolynomialExeption ex) {
                throw new RuntimeException(ex);
            }
            if(textField1.getText().equals("") || textField2.getText().equals(""))
                JOptionPane.showMessageDialog(mainFrame, "Introduceți datele.", "Lipsă Date", JOptionPane.WARNING_MESSAGE);
            else if (PolynomialReader.read(textField2.getText()).equals(zero)) {
                JOptionPane.showMessageDialog(mainFrame, "Nu se poate realiza împărțirea la 0.", "Eroare", JOptionPane.ERROR_MESSAGE);
            } else {
                p = PolynomialReader.read(textField1.getText().toUpperCase());
                q = PolynomialReader.read(textField2.getText().toUpperCase());
                resField.setText(Operations.divPolynomial(p, q)[0].toString());
                restLabel.setText(Operations.divPolynomial(p, q)[1].toString());
                rest.setVisible(true);
                restLabel.setVisible(true);
            }
        });

        JButton der = new JButton("Derive");
        panel.add(der);
        der.setBounds(30, 300, 210, 40);
        der.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        der.addActionListener(e -> {
            rest.setVisible(false);
            restLabel.setVisible(false);
            Polynomial p = new Polynomial();
            Polynomial q = new Polynomial();
            if(textField1.getText().equals(""))
                JOptionPane.showMessageDialog(mainFrame, "Introduceti datele.", "Lipsa Date", JOptionPane.WARNING_MESSAGE);
            else {
                p = PolynomialReader.read(textField1.getText().toUpperCase());
                resField.setText(Operations.derivePolynomial(p).toString());
            }
        });

        JButton integ = new JButton("Integrate");
        panel.add(integ);
        integ.setBounds(250, 300, 210, 40);
        integ.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        integ.addActionListener(e -> {
            rest.setVisible(false);
            restLabel.setVisible(false);
            Polynomial p = new Polynomial();
            Polynomial q = new Polynomial();
            if(textField1.getText().equals(""))
                JOptionPane.showMessageDialog(mainFrame, "Introduceti datele.", "Lipsa Date", JOptionPane.WARNING_MESSAGE);
            else {
                p = PolynomialReader.read(textField1.getText().toUpperCase());
                resField.setText(Operations.integratePolynomial(p).toString());
            }
        });

        JButton btn1 = new JButton("1");
        panel.add(btn1);
        btn1.setBounds(480, 200, 50, 40);
        btn1.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn1.addActionListener(e -> {
                text(activeTF, "1");
        });

        JButton btn2 = new JButton("2");
        panel.add(btn2);
        btn2.setBounds(540, 200, 50, 40);
        btn2.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn2.addActionListener(e -> {
                text(activeTF, "2");
        });

        JButton btn3 = new JButton("3");
        panel.add(btn3);
        btn3.setBounds(600, 200, 50, 40);
        btn3.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn3.addActionListener(e -> {
                text(activeTF, "3");
        });

        JButton btn4 = new JButton("4");
        panel.add(btn4);
        btn4.setBounds(480, 250, 50, 40);
        btn4.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn4.addActionListener(e -> {
                text(activeTF, "4");
        });

        JButton btn5 = new JButton("5");
        panel.add(btn5);
        btn5.setBounds(540, 250, 50, 40);
        btn5.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn5.addActionListener(e -> {
                text(activeTF, "5");
        });

        JButton btn6 = new JButton("6");
        panel.add(btn6);
        btn6.setBounds(600, 250, 50, 40);
        btn6.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn6.addActionListener(e -> {
                text(activeTF, "6");
        });

        JButton btn7 = new JButton("7");
        panel.add(btn7);
        btn7.setBounds(480, 300, 50, 40);
        btn7.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn7.addActionListener(e -> {
                text(activeTF, "7");
        });

        JButton btn8 = new JButton("8");
        panel.add(btn8);
        btn8.setBounds(540, 300, 50, 40);
        btn8.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn8.addActionListener(e -> {
                text(activeTF, "8");
        });

        JButton btn9 = new JButton("9");
        panel.add(btn9);
        btn9.setBounds(600, 300, 50, 40);
        btn9.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn9.addActionListener(e -> {
                text(activeTF, "9");
        });

        JButton btn0 = new JButton("0");
        panel.add(btn0);
        btn0.setBounds(540, 350, 50, 40);
        btn0.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btn0.addActionListener(e -> {
                text(activeTF, "0");
        });

        JButton btnPoint = new JButton(".");
        panel.add(btnPoint);
        btnPoint.setBounds(480, 350, 50, 40);
        btnPoint.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btnPoint.addActionListener(e -> {
                text(activeTF, ".");
        });

        JButton btnXor = new JButton("^");
        panel.add(btnXor);
        btnXor.setBounds(660, 350, 50, 40);
        btnXor.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btnXor.addActionListener(e -> {
                text(activeTF, "^");
        });

        JButton btnChange = new JButton("Change Field");
        panel.add(btnChange);
        btnChange.setBounds(30, 350, 210, 40);
        btnChange.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btnChange.addActionListener(e -> {
            if(activeTF == textField2)
                activeTF = textField1;
            else
                activeTF = textField2;
        });

        JButton btnClear = new JButton("Clear");
        panel.add(btnClear);
        btnClear.setBounds(250, 350, 210, 40);
        btnClear.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btnClear.addActionListener(e -> {
            textField2.setText("");
            textField1.setText("");
        });

        JButton btnPlus = new JButton("+");
        panel.add(btnPlus);
        btnPlus.setBounds(660, 200, 50, 40);
        btnPlus.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btnPlus.addActionListener(e -> {
            text(activeTF, "+");
        });

        JButton btnMinus = new JButton("-");
        panel.add(btnMinus);
        btnMinus.setBounds(660, 250, 50, 40);
        btnMinus.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btnMinus.addActionListener(e -> {
            text(activeTF, "-");
        });

        JButton btnMul = new JButton("*");
        panel.add(btnMul);
        btnMul.setBounds(660, 300, 50, 40);
        btnMul.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btnMul.addActionListener(e -> {
            text(activeTF, "*");
        });

        JButton btnX = new JButton("X");
        panel.add(btnX);
        btnX.setBounds(600, 350, 50, 40);
        btnX.setFont(new Font(Font.SERIF, Font.PLAIN, 20));

        btnX.addActionListener(e -> {
            text(activeTF, "X");
        });

        String chars = "+-xX*^1234567890.";

        textField1.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!chars.contains(new String(String.valueOf(c)))) {
                    e.consume();
                }
            }
        });

        textField2.addKeyListener(new KeyAdapter() {
            public void keyTyped(KeyEvent e) {
                char c = e.getKeyChar();
                if(!chars.contains(new String(String.valueOf(c)))) {
                    e.consume();
                }
            }
        });
    }




    private void text(JTextField field, String s)
    {
        field.setText(field.getText() + s);
    }
}
