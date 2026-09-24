package Frontend;

import service.Account;
import service.ATM;

import javax.swing.*;
import java.awt.*;

public class DepositDialog extends JDialog {

    private final ATM atm;
    private final Account account;
    private final DashboardFrame dashboard;

    private JTextField amountField;

    public DepositDialog(
            DashboardFrame parent,
            ATM atm,
            Account account,
            DashboardFrame dashboard
    ) {

        super(parent, "Deposit Money", true);

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
                new JLabel("Deposit Amount");

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

        JButton deposit =
                new JButton("DEPOSIT");

        deposit.addActionListener(
                e -> depositMoney()
        );

        panel.add(title);

        panel.add(
                Box.createVerticalStrut(25)
        );

        panel.add(amountField);

        panel.add(
                Box.createVerticalStrut(20)
        );

        panel.add(deposit);

        add(panel);
    }

    private void depositMoney() {

        try {

            double amount =
                    Double.parseDouble(
                            amountField.getText()
                    );

            if (atm.deposit(
                    account,
                    amount
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Money deposited successfully."
                );

                dashboard.refreshBalance();

                dispose();

            } else {

                showError(
                        "Enter a valid amount."
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