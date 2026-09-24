package Frontend;


import service.Account;
import service.ATM;

import javax.swing.*;
import java.awt.*;

public class DashboardFrame extends JFrame {

    private final ATM atm;
    private final Account account;

    private JLabel balanceLabel;

    public DashboardFrame(
            ATM atm,
            Account account
    ) {

        this.atm = atm;
        this.account = account;

        setTitle("New Bank - Dashboard");
        setSize(950, 650);
        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );
        setLocationRelativeTo(null);
        setResizable(false);

        createUI();
    }

    private void createUI() {

        JPanel mainPanel =
                new JPanel(new BorderLayout());

        mainPanel.setBackground(
                new Color(245, 247, 250)
        );

        // ================= HEADER =================

        JPanel header =
                new JPanel(new BorderLayout());

        header.setBackground(
                new Color(20, 30, 50)
        );

        header.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 30, 20, 30
                )
        );

        JLabel title =
                new JLabel("NEW BANK");

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        25
                )
        );

        title.setForeground(Color.WHITE);

        JLabel user =
                new JLabel(
                        "Welcome, " +
                                account.getName()
                );

        user.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        15
                )
        );

        user.setForeground(Color.WHITE);

        header.add(
                title,
                BorderLayout.WEST
        );

        header.add(
                user,
                BorderLayout.EAST
        );


        JPanel center =
                new JPanel();

        center.setBackground(
                new Color(245, 247, 250)
        );

        center.setLayout(
                new BoxLayout(
                        center,
                        BoxLayout.Y_AXIS
                )
        );

        center.setBorder(
                BorderFactory.createEmptyBorder(
                        30, 60, 30, 60
                )
        );

        JLabel balanceTitle =
                new JLabel("AVAILABLE BALANCE");

        balanceTitle.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        15
                )
        );

        balanceTitle.setForeground(
                Color.GRAY
        );

        balanceTitle.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        balanceLabel =
                new JLabel(
                        formatBalance()
                );

        balanceLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        42
                )
        );

        balanceLabel.setForeground(
                new Color(20, 30, 50)
        );

        balanceLabel.setAlignmentX(
                Component.CENTER_ALIGNMENT
        );

        center.add(balanceTitle);

        center.add(
                Box.createVerticalStrut(5)
        );

        center.add(balanceLabel);

        center.add(
                Box.createVerticalStrut(35)
        );

        // ================= BUTTON GRID =================

        JPanel buttonPanel =
                new JPanel(
                        new GridLayout(
                                2,
                                2,
                                20,
                                20
                        )
                );

        buttonPanel.setBackground(
                new Color(245, 247, 250)
        );

        JButton deposit =
                createMenuButton(
                        "DEPOSIT"
                );

        JButton withdraw =
                createMenuButton(
                        "WITHDRAW"
                );

        JButton transfer =
                createMenuButton(
                        "TRANSFER"
                );

        JButton history =
                createMenuButton(
                        "TRANSACTION HISTORY"
                );

        deposit.addActionListener(
                e -> openDeposit()
        );

        withdraw.addActionListener(
                e -> openWithdraw()
        );

        transfer.addActionListener(
                e -> openTransfer()
        );

        history.addActionListener(
                e -> openHistory()
        );

        buttonPanel.add(deposit);
        buttonPanel.add(withdraw);
        buttonPanel.add(transfer);
        buttonPanel.add(history);

        center.add(buttonPanel);

        // ================= FOOTER =================

        JButton logout =
                new JButton("LOGOUT");

        logout.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        14
                )
        );

        logout.setForeground(Color.WHITE);

        logout.setBackground(
                new Color(180, 50, 50)
        );

        logout.setFocusPainted(false);

        logout.addActionListener(e -> logout());

        JPanel footer =
                new JPanel();

        footer.setBackground(
                new Color(245, 247, 250)
        );

        footer.add(logout);

        mainPanel.add(
                header,
                BorderLayout.NORTH
        );

        mainPanel.add(
                center,
                BorderLayout.CENTER
        );

        mainPanel.add(
                footer,
                BorderLayout.SOUTH
        );

        add(mainPanel);
    }

    private JButton createMenuButton(
            String text
    ) {

        JButton button =
                new JButton(text);

        button.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        16
                )
        );

        button.setForeground(Color.WHITE);

        button.setBackground(
                new Color(20, 30, 50)
        );

        button.setFocusPainted(false);

        return button;
    }

    private String formatBalance() {

        return String.format(
                "₹ %.2f",
                account.getBalance()
        );
    }

    public void refreshBalance() {

        balanceLabel.setText(
                formatBalance()
        );
    }

    private void openDeposit() {

        new DepositDialog(
                this,
                atm,
                account,
                this
        ).setVisible(true);
    }

    private void openWithdraw() {

        new WithdrawDialog(
                this,
                atm,
                account,
                this
        ).setVisible(true);
    }

    private void openTransfer() {

        new TransferDialog(
                this,
                atm,
                account,
                this
        ).setVisible(true);
    }

    private void openHistory() {

        new TransactionFrame(
                this,
                account
        ).setVisible(true);
    }

    private void logout() {

        int result =
                JOptionPane.showConfirmDialog(
                        this,
                        "Do you want to logout?",
                        "Logout",
                        JOptionPane.YES_NO_OPTION
                );

        if (result ==
                JOptionPane.YES_OPTION) {

            dispose();

            new LoginFrame(
                    atm
            ).setVisible(true);
        }
    }
}