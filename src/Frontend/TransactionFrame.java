package Frontend;

import service.Account;
import service.Transaction;

import javax.swing.*;
import java.awt.*;

public class TransactionFrame extends JFrame {

    private final Account account;

    public TransactionFrame(
            JFrame parent,
            Account account
    ) {

        this.account = account;

        setTitle("Transaction History");
        setSize(800, 500);
        setLocationRelativeTo(parent);

        createUI();
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );

        JLabel title =
                new JLabel("Transaction History");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        mainPanel.add(
                title,
                BorderLayout.NORTH
        );

        String[] columns = {
                "Date",
                "Type",
                "Amount",
                "Balance",
                "Description"
        };

        Object[][] data =
                createTransactionData();

        JTable table =
                new JTable(
                        data,
                        columns
                );

        table.setRowHeight(30);

        table.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        13
                )
        );

        JScrollPane scrollPane =
                new JScrollPane(table);

        mainPanel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        add(mainPanel);
    }

    private Object[][] createTransactionData() {

        var transactions =
                account.getTransactions();

        Object[][] data =
                new Object[transactions.size()][5];

        for (int i = 0;
             i < transactions.size();
             i++) {

            Transaction transaction =
                    transactions.get(i);

            data[i][0] =
                    transaction.getDateTime();

            data[i][1] =
                    transaction.getType();

            data[i][2] =
                    "₹ " +
                            String.format(
                                    "%.2f",
                                    transaction.getAmount()
                            );

            data[i][3] =
                    "₹ " +
                            String.format(
                                    "%.2f",
                                    transaction.getBalance()
                            );

            data[i][4] =
                    transaction.getDescription();
        }

        return data;
    }
}