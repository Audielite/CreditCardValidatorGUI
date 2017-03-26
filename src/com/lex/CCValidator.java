package com.lex;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Created by alexishennings on 3/25/17.
 */
public class CCValidator extends JFrame {
    private JPanel rootPanel;
    private JTextField creditCardNumbertextField;
    private JButton validateButton;
    private JButton quitButton;
    private JLabel validMessageLabel;

    private boolean resetMessageOnKeyPress = false;

    private final String VISA = "Visa";

    protected CCValidator() {

        super("Credit Card Validator");
        setContentPane(rootPanel);

        setPreferredSize(new Dimension(500, 200));   //Set preferred size before call to pack()

        pack();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setVisible(true);

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String ccNumber = creditCardNumbertextField.getText();

                boolean valid = false;

               if (valid) {
                    validMessageLabel.setText(" Credit Card number is valid");
                } else {
                    validMessageLabel.setText(" Credit Card number is invalid");
                }

                resetMessageOnKeyPress = true;
            }
        });

        // Listener for user typing in JTextField.
        // Used to reset the valid or not valid message when user enters a new card.
        creditCardNumbertextField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (resetMessageOnKeyPress) {
                    validMessageLabel.setText("~ Valid or not valid displayed here ~");
                    resetMessageOnKeyPress = false;
                }
            }
        });

        validateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int quit = JOptionPane.showConfirmDialog(CCValidator.this, "Are you Sure?", "Quit", JOptionPane.OK_CANCEL_OPTION);
                if (quit == JOptionPane.OK_OPTION) {
                    System.exit(0);
                }
            }
        });
    }

    private static boolean isVisaCreditCardNumberValid(String cc) {

        if (!cc.startsWith("4") || (cc.length() != 16)) {
            return false;
        }
        int sum = 0;
        for (int i = 0; i < 16; i++) {
            int thisDigit = Integer.parseInt((cc.substring(i, i + 1)));
            if (i % 2 == 1) {
                sum = sum + thisDigit;
            } else {
                int doubled = thisDigit * 2;
                if (doubled > 9) {
                    int toAdd = 1 + (doubled % 10);
                    sum = sum + toAdd;
                } else {
                    sum = sum + (thisDigit * 2);
                }
            }
        }
        if (sum % 10 == 0) {
            return true;
        }
        return false;
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}