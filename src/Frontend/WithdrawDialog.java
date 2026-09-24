package Frontend;


import service.Account;
import service.ATM;

import javax.swing.*;
import java.awt.*;

public class WithdrawDialog extends JDialog {

    private final ATM atm;
    private final Account account;
    private final DashboardFrame dashboard;

    private JTextField amountField;

    public WithdrawDialog(
            DashboardFrame parent,
            ATM atm,
            Account account,
            DashboardFrame dashboard
    ) {

        super(parent, "Withdraw Money", true);

        this.atm = atm;
        this.account = account;
        this.dashboard = dashboard;

        setSize(400, 250);
        setLocationRelativeTo(parent);

        createUI();
    }

    private void createUI() {

        JPanel panel = new JPanel();

        panel.setLayout(
                new BoxLayout(
                        panel,
                        BoxLayout.Y_AXIS
                )
        );

        panel.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 40, 30, 40
                )
        );

        JLabel title =
                new JLabel("Withdraw Amount");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        22
                )
        );

        title.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        amountField =
                new JTextField();

        amountField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        JButton withdraw =
                new JButton("WITHDRAW");

        withdraw.addActionListener(
                e -> withdrawMoney()
        );

        panel.add(title);

        panel.add(
                Box.createVerticalStrut(25)
        );

        panel.add(amountField);

        panel.add(
                Box.createVerticalStrut(20)
        );

        panel.add(withdraw);

        add(panel);
    }

    private void withdrawMoney() {

        try {

            double amount =
                    Double.parseDouble(
                            amountField.getText()
                    );

            if (atm.withdraw(
                    account,
                    amount
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Please collect your cash."
                );

                dashboard.refreshBalance();

                dispose();

            } else {

                showError(
                        "Insufficient balance or invalid amount."
                );
            }

        } catch (NumberFormatException e) {

            showError(
                    "Please enter a valid number."
            );
        }
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