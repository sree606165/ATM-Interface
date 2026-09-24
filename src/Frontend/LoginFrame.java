package Frontend;

import service.ATM;
import service.Account;

import javax.swing.*;
import java.awt.*;

public class LoginFrame extends JFrame {

    private final ATM atm;

    private JTextField accountField;
    private JPasswordField pinField;

    public LoginFrame(ATM atm) {

        this.atm = atm;

        setTitle("Java Bank - ATM");
        setSize(900, 550);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        createUI();
    }

    private void createUI() {

        JPanel mainPanel = new JPanel(new GridLayout(1, 2));

        // ================= LEFT SIDE =================

        JPanel leftPanel = new JPanel();
        leftPanel.setBackground(new Color(20, 30, 50));
        leftPanel.setLayout(
                new BoxLayout(leftPanel, BoxLayout.Y_AXIS)
        );

        leftPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        100, 50, 100, 50
                )
        );

        JLabel bankName = new JLabel("JAVA BANK");

        bankName.setFont(
                new Font("Arial", Font.BOLD, 38)
        );

        bankName.setForeground(Color.WHITE);
        bankName.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel atmText = new JLabel("ATM INTERFACE");

        atmText.setFont(
                new Font("Arial", Font.PLAIN, 20)
        );

        atmText.setForeground(
                new Color(180, 190, 210)
        );

        atmText.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel secureText = new JLabel(
                "<html><center>Secure Banking<br>" +
                        "Made Simple</center></html>"
        );

        secureText.setFont(
                new Font("Arial", Font.PLAIN, 17)
        );

        secureText.setForeground(Color.WHITE);
        secureText.setAlignmentX(Component.CENTER_ALIGNMENT);

        leftPanel.add(bankName);
        leftPanel.add(Box.createVerticalStrut(10));
        leftPanel.add(atmText);
        leftPanel.add(Box.createVerticalStrut(60));
        leftPanel.add(secureText);

        // ================= RIGHT SIDE =================

        JPanel rightPanel = new JPanel();

        rightPanel.setBackground(Color.WHITE);

        rightPanel.setLayout(
                new BoxLayout(
                        rightPanel,
                        BoxLayout.Y_AXIS
                )
        );

        rightPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        70, 70, 70, 70
                )
        );

        JLabel title = new JLabel("Welcome Back");

        title.setFont(
                new Font("Arial", Font.BOLD, 30)
        );

        title.setForeground(
                new Color(20, 30, 50)
        );

        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel subtitle = new JLabel(
                "Login to your bank account"
        );

        subtitle.setFont(
                new Font("Arial", Font.PLAIN, 14)
        );

        subtitle.setForeground(Color.GRAY);

        subtitle.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Account label

        JLabel accountLabel =
                new JLabel("Account Number");

        accountLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        accountLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        accountField = new JTextField();

        accountField.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        accountField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );

        JLabel pinLabel =
                new JLabel("PIN");

        pinLabel.setFont(
                new Font("Arial", Font.BOLD, 14)
        );

        pinLabel.setAlignmentX(
                Component.LEFT_ALIGNMENT
        );

        pinField = new JPasswordField();

        pinField.setFont(
                new Font("Arial", Font.PLAIN, 16)
        );

        pinField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        42
                )
        );


        JButton loginButton =
                createButton("LOGIN");

        loginButton.addActionListener(e ->
                login()
        );


        rightPanel.add(title);
        rightPanel.add(
                Box.createVerticalStrut(8)
        );

        rightPanel.add(subtitle);

        rightPanel.add(
                Box.createVerticalStrut(40)
        );

        rightPanel.add(accountLabel);

        rightPanel.add(
                Box.createVerticalStrut(7)
        );

        rightPanel.add(accountField);

        rightPanel.add(
                Box.createVerticalStrut(20)
        );

        rightPanel.add(pinLabel);

        rightPanel.add(
                Box.createVerticalStrut(7)
        );

        rightPanel.add(pinField);

        rightPanel.add(
                Box.createVerticalStrut(30)
        );

        rightPanel.add(loginButton);

        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        add(mainPanel);
    }

    private void login()  {

        String accountNumber =
                accountField.getText().trim();

        String pin =
                new String(
                        pinField.getPassword()
                );

        if (accountNumber.isEmpty()
                || pin.isEmpty()) {

            showError(
                    "Please enter account number and PIN."
            );

            return;
        }

        Account account =
                atm.login(
                        accountNumber,
                        pin
                );

        if (account != null) {

            dispose();

            new DashboardFrame(
                    atm,
                    account
            ).setVisible(true);

        } else {

            showError(
                    "Invalid account number or PIN."
            );
        }
    }

    private JButton createButton(String text) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(20, 30, 50)
        );

        button.setFocusPainted(false);

        button.setBorderPainted(false);

        button.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        45
                )
        );

        return button;
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}