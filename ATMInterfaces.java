import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class BankAccount {
    private String name;
    private String userName;
    private String password;
    private String accountNo;
    private float balance = 20000f;
    private int transactions = 0;
    private String transactionHistory = "";
    private JLabel balanceLabel;

    public void register() {
        JFrame frame = new JFrame("Registration");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setLayout(null);

        JLabel nameLabel = new JLabel("Name:");
        nameLabel.setBounds(20, 20, 80, 25);
        JTextField nameField = new JTextField();
        nameField.setBounds(100, 20, 165, 25);

        JLabel userNameLabel = new JLabel("Username:");
        userNameLabel.setBounds(20, 50, 80, 25);
        JTextField userNameField = new JTextField();
        userNameField.setBounds(100, 50, 165, 25);

        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(20, 80, 80, 25);
        JPasswordField passwordField = new JPasswordField();
        passwordField.setBounds(100, 80, 165, 25);

        JLabel accountNoLabel = new JLabel("Account Number:");
        accountNoLabel.setBounds(20, 110, 110, 25);
        JTextField accountNoField = new JTextField();
        accountNoField.setBounds(130, 110, 135, 25);

        frame.add(nameLabel);
        frame.add(nameField);
        frame.add(userNameLabel);
        frame.add(userNameField);
        frame.add(passwordLabel);
        frame.add(passwordField);
        frame.add(accountNoLabel);
        frame.add(accountNoField);

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 140, 100, 25);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                name = nameField.getText();
                userName = userNameField.getText();
                password = new String(passwordField.getPassword());
                accountNo = accountNoField.getText();
                JOptionPane.showMessageDialog(null, "Registration Successful. Please Log in to your Bank Account");
                frame.dispose();
            }
        });

        frame.add(registerButton);
        frame.setVisible(true);
    }

    public boolean login(String enteredUserName, String enteredPassword) {
        if (enteredUserName.equals(userName) && enteredPassword.equals(password)) {
            JOptionPane.showMessageDialog(null, "Login Successful");
            return true;
        } else {
            JOptionPane.showMessageDialog(null, "Incorrect Username or Password");
            return false;
        }
    }

    private void updateBalanceLabel() {
        balanceLabel.setText("Current Balance: $" + balance);
    }

    public void showATMWindow() {
        JFrame atmFrame = new JFrame("Welcome to SBC Bank ATM");
        atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atmFrame.setSize(400, 400);
        atmFrame.setLayout(new BorderLayout());

        JPanel atmPanel = new JPanel(new BorderLayout());
        atmPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JLabel welcomeLabel = new JLabel("Welcome to SBC Bank ATM \uD83D\uDE4F", JLabel.CENTER);
        welcomeLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        atmPanel.add(welcomeLabel, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton historyButton = new JButton("Transaction History");
        historyButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, transactionHistory.isEmpty() ? "No transaction history." : transactionHistory);
            }
        });

        JButton transferButton = new JButton("Transfer");
        transferButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String transferAmount = JOptionPane.showInputDialog("Enter transfer amount:");
                if (transferAmount != null && !transferAmount.isEmpty()) {
                    float amount = Float.parseFloat(transferAmount);
                    if (amount > 0 && amount <= balance) {
                        String recipientAccount = JOptionPane.showInputDialog("Enter recipient's account number:");
                        if (recipientAccount != null && !recipientAccount.isEmpty()) {
                            balance -= amount;
                            transactions++;
                            transactionHistory += "Transfer of $" + amount + " to account " + recipientAccount + "\n";
                            JOptionPane.showMessageDialog(null, "Transfer successful!");
                            updateBalanceLabel();
                        } else {
                            JOptionPane.showMessageDialog(null, "Recipient's account number cannot be empty.");
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid amount entered or insufficient funds.");
                    }
                }
            }
        });

        JButton depositButton = new JButton("Deposit");
        depositButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String depositAmount = JOptionPane.showInputDialog("Enter deposit amount:");
                if (depositAmount != null && !depositAmount.isEmpty()) {
                    float amount = Float.parseFloat(depositAmount);
                    if (amount > 0) {
                        balance += amount;
                        transactions++;
                        transactionHistory += "Deposit of $" + amount + "\n";
                        JOptionPane.showMessageDialog(null, "Deposit successful!");
                        updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid amount entered.");
                    }
                }
            }
        });

        JButton withdrawButton = new JButton("Withdraw");
        withdrawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String withdrawAmount = JOptionPane.showInputDialog("Enter withdraw amount:");
                if (withdrawAmount != null && !withdrawAmount.isEmpty()) {
                    float amount = Float.parseFloat(withdrawAmount);
                    if (amount > 0 && amount <= balance) {
                        balance -= amount;
                        transactions++;
                        transactionHistory += "Withdrawal of $" + amount + "\n";
                        JOptionPane.showMessageDialog(null, "Withdrawal successful!");
                        updateBalanceLabel();
                    } else {
                        JOptionPane.showMessageDialog(null, "Invalid amount entered or insufficient funds.");
                    }
                }
            }
        });

        buttonPanel.add(historyButton);
        buttonPanel.add(transferButton);
        buttonPanel.add(depositButton);
        buttonPanel.add(withdrawButton);

        atmPanel.add(buttonPanel, BorderLayout.CENTER);

        JPanel balancePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        balanceLabel = new JLabel("Current Balance: $" + balance);
        balanceLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        balancePanel.add(balanceLabel);

        atmPanel.add(balancePanel, BorderLayout.SOUTH);

        atmFrame.add(atmPanel, BorderLayout.CENTER);
        atmFrame.setVisible(true);
    }
}

public class ATMInterfaces {
    public static void main(String[] args) {
        BankAccount b = new BankAccount();

        JFrame frame = new JFrame("Welcome to SBC Bank ATM");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(350, 250);
        frame.setLayout(null);

        JPanel welcomePanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("Welcome to SBC Bank ATM \uD83D\uDE4F");
        welcomeLabel.setFont(new Font("Segoe UI Emoji", Font.PLAIN, 16));
        welcomePanel.add(welcomeLabel);
        welcomePanel.setBounds(0, 0, 300, 40);
        frame.add(welcomePanel);

        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JLabel userNameLabel = new JLabel("Username:");
        JTextField userNameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField();

        loginPanel.add(userNameLabel);
        loginPanel.add(userNameField);
        loginPanel.add(passwordLabel);
        loginPanel.add(passwordField);
        loginPanel.setBounds(0, 40, 300, 100);
        frame.add(loginPanel);

        JButton loginButton = new JButton("Login");
        loginButton.setBounds(10, 140, 80, 25);
        loginButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String enteredUserName = userNameField.getText();
                String enteredPassword = new String(passwordField.getPassword());
                if (b.login(enteredUserName, enteredPassword)) {
                    b.showATMWindow();
                }
            }
        });

        JButton registerButton = new JButton("Register");
        registerButton.setBounds(100, 140, 100, 25);
        registerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                b.register();
            }
        });

        JButton quitButton = new JButton("Quit");
        quitButton.setBounds(210, 140, 80, 25);
        quitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        frame.add(loginButton);
        frame.add(registerButton);
        frame.add(quitButton);
        frame.setVisible(true);
    }
}
