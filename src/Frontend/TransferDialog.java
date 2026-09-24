package Frontend;


import service.Account;
import service.ATM;

import javax.swing.*;
import java.awt.*;

public class TransferDialog extends JDialog {

    private final ATM atmService;
    private final Account sender;
    private final DashboardFrame dashboard;

    private JTextField receiverField;
    private JTextField amountField;

    public TransferDialog(
            DashboardFrame parent,
            ATM atmService,
            Account sender,
            DashboardFrame dashboard
    ) {

        super(parent, "Transfer Money", true);

        this.atmService = atmService;
        this.sender = sender;
        this.dashboard = dashboard;

        setSize(450, 320);
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
                new JLabel("Transfer Money");

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

        JLabel receiverLabel =
                new JLabel("Receiver Account Number");

        receiverField =
                new JTextField();

        receiverField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        JLabel amountLabel =
                new JLabel("Amount");

        amountField =
                new JTextField();

        amountField.setMaximumSize(
                new Dimension(
                        Integer.MAX_VALUE,
                        40
                )
        );

        JButton transfer =
                new JButton("TRANSFER");

        transfer.addActionListener(
                e -> transferMoney()
        );

        panel.add(title);

        panel.add(
                Box.createVerticalStrut(20)
        );

        panel.add(receiverLabel);

        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(receiverField);

        panel.add(
                Box.createVerticalStrut(15)
        );

        panel.add(amountLabel);

        panel.add(
                Box.createVerticalStrut(5)
        );

        panel.add(amountField);

        panel.add(
                Box.createVerticalStrut(20)
        );

        panel.add(transfer);

        add(panel);
    }

    private void transferMoney() {

        String receiver =
                receiverField.getText().trim();

        try {

            double amount =
                    Double.parseDouble(
                            amountField.getText()
                    );

            if (atmService.transfer(
                    sender,
                    receiver,
                    amount
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Transfer successful."
                );

                dashboard.refreshBalance();

                dispose();

            } else {

                showError(
                        "Transfer failed. Check account or balance."
                );
            }

        } catch (NumberFormatException e) {

            showError(
                    "Please enter a valid amount."
            );
        }
    }

    private void showError(String message) {

        JOptionPane.showMessageDialog(
                this,
                message,
                "Transfer Error",
                JOptionPane.ERROR_MESSAGE
        );
    }
}