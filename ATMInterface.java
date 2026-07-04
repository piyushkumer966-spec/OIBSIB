import java.util.ArrayList;
import java.util.Scanner;

// Class 1: Main System Driver
public class ATMInterface {
    public static void main(String[] args) {
        UserSession session = new UserSession();
        session.initializeSystem();
    }
}

// Class 2: Handles the running session and login verification
class UserSession {
    public void initializeSystem() {
        Scanner scanner = new Scanner(System.in);
        // Default credentials for testing: User ID = 12345, PIN = 9999
        Account userAccount = new Account("12345", "9999", 5000.0);

        System.out.println("=== Console-Based ATM System ===");
        System.out.print("Enter User ID: ");
        String idInput = scanner.nextLine();
        System.out.print("Enter User PIN: ");
        String pinInput = scanner.nextLine();

        if (userAccount.authenticate(idInput, pinInput)) {
            System.out.println("\nAuthentication Successful!");
            ATMOperations ops = new ATMOperations(userAccount, scanner);
            ops.showMenu();
        } else {
            System.out.println("Invalid ID or PIN. System locked.");
        }
    }
}

// Class 3: Holds data variables & verification profile records
class Account {
    private final String userId;
    private final String userPin;
    private double balance;
    private final ArrayList<String> transactionHistory;

    public Account(String userId, String userPin, double initialBalance) {
        this.userId = userId;
        this.userPin = userPin;
        this.balance = initialBalance;
        this.transactionHistory = new ArrayList<>();
        addLog("Account opened with balance: ₹" + initialBalance);
    }

    public boolean authenticate(String id, String pin) {
        return this.userId.equals(id) && this.userPin.equals(pin);
    }

    public double getBalance() { 
        return balance; 
    }
    
    public void adjustBalance(double amount) { 
        this.balance += amount; 
    }

    public void addLog(String note) { 
        transactionHistory.add(note); 
    }

    public void printHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No historical data found.");
        } else {
            System.out.println("\n--- Transaction Logs ---");
            for (String log : transactionHistory) {
                System.out.println(log);
            }
        }
    }
}

// Class 4: Execution operations engine for banking functionalities
class ATMOperations {
    private final Account account;
    private final Scanner scanner;

    public ATMOperations(Account account, Scanner scanner) {
        this.account = account;
        this.scanner = scanner;
    }

    public void showMenu() {
        while (true) {
            System.out.println("\n1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose operation: ");
            int option = scanner.nextInt();

            switch (option) {
                case 1:
                    account.printHistory();
                    break;
                case 2:
                    performWithdraw();
                    break;
                case 3:
                    performDeposit();
                    break;
                case 4:
                    performTransfer();
                    break;
                case 5:
                    System.out.println("Session terminated. Goodbye!");
                    return;
                default:
                    System.out.println("Invalid selection.");
            }
        }
    }

    private void performWithdraw() {
        System.out.print("Enter withdrawal amount: ₹");
        double amt = scanner.nextDouble();
        if (amt > 0 && amt <= account.getBalance()) {
            account.adjustBalance(-amt);
            account.addLog("Withdrew: ₹" + amt);
            System.out.println("Withdrawal successful. Balance remaining: ₹" + account.getBalance());
        } else {
            System.out.println("Insufficient funds or invalid structural balance constraint.");
        }
    }

    private void performDeposit() {
        System.out.print("Enter deposit amount: ₹");
        double amt = scanner.nextDouble();
        if (amt > 0) {
            account.adjustBalance(amt);
            account.addLog("Deposited: ₹" + amt);
            System.out.println("Deposit added. Updated Balance: ₹" + account.getBalance());
        } else {
            System.out.println("Invalid transaction payload.");
        }
    }

    private void performTransfer() {
        System.out.print("Enter target recipient account id: ");
        String recipientId = scanner.next(); 
        System.out.print("Enter transfer quantum amount: ₹");
        double amt = scanner.nextDouble();
        TransferEngine.process(account, amt, recipientId);
    }
}

// Class 5: Dedicated tracking engine class mapping transfers
class TransferEngine {
    public static void process(Account source, double quantum, String recipientId) {
        if (quantum > 0 && quantum <= source.getBalance()) {
            source.adjustBalance(-quantum);
            source.addLog("Transferred out: ₹" + quantum + " to Account " + recipientId);
            System.out.println("Transfer complete. Balance: ₹" + source.getBalance());
        } else {
            System.out.println("Transaction cancelled due to data logic invalidation rules.");
        }
    }
}
