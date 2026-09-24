import service.ATM;
import service.Bank;
import Frontend.LoginFrame;

import javax.swing.SwingUtilities;

public class Main {

    public static void main(String[] args) {

        Bank bank = new Bank();

        ATM atmService =
                new ATM(bank);

        SwingUtilities.invokeLater(() -> {

            LoginFrame loginFrame =
                    new LoginFrame(atmService);

            loginFrame.setVisible(true);
        });
    }
}