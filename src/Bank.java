import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    Bank() {
    }


    Scanner input = new Scanner(System.in);
    ArrayList<Account> bankUsers = new ArrayList<>();

    private void startBankApplication() {
        bankEntryMessage(); // printing some start informations
        userChoiceMenu();
        System.exit(0);
    }

    private void userChoiceMenu() {
        // handle user commands
        boolean quit = false;
        String menuItem;
        do {
            System.out.println("\nEnter command...");
            menuItem = input.nextLine();
            switch (menuItem.toLowerCase()) {
                case "help":                        // displays all available commands
                    if (confirmOperation()) {
                        displayHelpCommands();
                    } else {
                        System.out.println("Operation aborted\n");
                        break;
                    }
                    break;
                case "add":                         //adding new user user
                    addNewUser();
                    break;
                case "all":                         //display all users
                    if (confirmOperation()) {
                        displayAllClients();
                    } else {
                        System.out.println("Operation aborted\n");
                        break;
                    }
                    break;
                case "delete":                      // delete chosen user
                    deleteUser();
                    break;
                case "deposit":                     // deposit money
                    makeDeposit();
                    break;
                case "withdraw":                    //withdraw money
                    makeWithdraw();
                    break;
                case "transfer":
                    makeTransfer(); //transfer money
                    break;
                case "exit":
                    if (confirmOperation()) {
                        quit = true;
                    } else {
                        System.out.println("Operation aborted\n");
                        break;
                    }
                    break;
                default:
                    System.out.println("Invalid choice.");
            }
        } while (!quit);
        System.out.println("Bye-bye!");
    }

    private boolean confirmOperation() {
        String c;
        do {
            System.out.println("Do you confirm the operation? [Y]/[N]");
            c = input.next();
            input.nextLine();
            if (c.equalsIgnoreCase("N")) {
                return false;
            }
        } while (!c.equalsIgnoreCase("Y"));
        return true;
    }

    private void bankEntryMessage() {
        System.out.println("\nThis is bank management application\n" +
                "=================================================================\n" +
                "In this system you need to confirm all operations!\n" +
                "[Y] = confirm || [N] = cancel\n" +
                "=================================================================\n" +
                "For more informations and options write: help\n" +
                "For exit write: exit");
    }

    private void displayHelpCommands() {
        System.out.println("All available commands:\n" +
                "=================================================================\n" +
                "help - displays all available commands\n" +
                "add - add new user\n" +
                "all - display all users\n" +
                "delete - delete selected user\n" +
                "deposit - deposit funds into an account\n" +
                "withdraw - withdraw funds from an account \n" +
                "transfer - transfer funds between two accounts\n" +
                "exit - application closing command\n" +
                "=================================================================");

    }

    private void addNewUser() {
        String tmpNameFirst, tmpNameLast, tmpPesel, tmpAdress;
        double tmpBalance;
        long tmpAccNumber;


        System.out.println("\nEnter information about the new user\n" +
                "=================================================================");
        System.out.println("Account Holders First Name :: ");
        tmpNameFirst = input.nextLine();
        System.out.println("Account Holders Last Name :: ");
        tmpNameLast = input.nextLine();
        System.out.println("Account Holders PESEL :: ");
        tmpPesel = input.nextLine();
        System.out.println("Account Holders Adress :: ");
        tmpAdress = input.nextLine();


        System.out.println("AccountNumber :: ");
        tmpAccNumber = input.nextLong();
        System.out.println("Opening Balance :: ");
        tmpBalance = input.nextDouble();
        input.nextLine();

        if (confirmOperation()) {
            bankUsers.add(new Account(new Client(tmpNameFirst, tmpNameLast, tmpPesel, tmpAdress), tmpAccNumber, tmpBalance));
            System.out.println("New client successfully added!");
        } else {
            System.out.println("Operation aborted\n");
            return;
        }
    }

    private void displayAllClients() {
        int k = 0;
        if (bankUsers.isEmpty()) {
            System.out.println("There is no clients in base!\n=================================================================");
        } else {
            for (Account str : bankUsers) {
                System.out.println("ClientID: " + k + " || " + str);
                k++;
            }
        }
    }

    private void deleteUser() {
        if (bankUsers.isEmpty()) {
            System.out.println("Nothing to delete! Base is empty\n");
        } else {
            displayAllClients();
            System.out.println("=================================================================\n" +
                    "Enter clientID number you want to delete");
            int userToDelete = input.nextInt();
            input.nextLine();
            if (confirmOperation()) {
                bankUsers.remove(userToDelete);
                System.out.println("Client account deleted\n");
            } else {
                System.out.println("Operation aborted\n");
                return;
            }
        }
    }

    private void makeDeposit() {
        if (bankUsers.isEmpty()) {
            System.out.println("You can not deposit funds, client base is empty!\n");
        } else {
            displayAllClients();

            System.out.println("=================================================================\n" +
                    "Enter client ID number you want to deposit funds\n");
            int userToDeposit = input.nextInt();
            input.nextLine();

            System.out.println("Enter amount you want to deposit\n");
            double tmpAmount = input.nextDouble();
            input.nextLine();
            if (confirmOperation()) {
                bankUsers.get(userToDeposit).depositFunds(tmpAmount);
                System.out.println("Deposit operation completed successfully\n");
            } else {
                System.out.println("Operation aborted\n");
                return;
            }

        }
    }

    private void makeWithdraw() {
        if (bankUsers.isEmpty()) {
            System.out.println("You can not withdraw funds, client base is empty!\n");
        } else {
            displayAllClients();
            System.out.println("=================================================================\n" +
                    "Enter client ID number you want to withdraw funds\n");
            int userToWithdraw = input.nextInt();
            input.nextLine();

            System.out.println("Enter amount you want to withdraw\n");
            double tmpAmount = input.nextDouble();

            if (confirmOperation()) {
                if (bankUsers.get(userToWithdraw).withdrawFunds(tmpAmount)) {
                    System.out.println("Withdraw operation completed successfully\n");
                } else {
                    System.out.println("You can't withdraw: " + tmpAmount + "$ " +
                            "You can withdraw max: " + bankUsers.get(userToWithdraw).getBalance() + "$\n");
                }

            } else {
                System.out.println("Operation aborted\n");
                return;
            }

        }
    }

    private void makeTransfer() {

        System.out.println("Enter sender's account number\n");
        long senderAccNumber = input.nextLong();
        input.nextLine();
        System.out.println("Enter recipient's account number\n");
        long recipientAccNumber = input.nextLong();
        input.nextLine();
        System.out.println("Enter the amount you want to transfer\n");
        double tmpAmount = input.nextDouble();
        input.nextLine();

        boolean transfer = true;

        for (int i = 0; i < bankUsers.size(); i++) {
            if (bankUsers.get(i).getAccountNumber() == senderAccNumber) {
                if (bankUsers.get(i).getBalance() < tmpAmount) {
                    transfer = false;
                    System.out.println("You can't transfer more than current saldo("
                            + bankUsers.get(i).getBalance() + "$)\n");
                } else {
                    bankUsers.get(i).setBalance(bankUsers.get(i).getBalance() - tmpAmount);
                    break;
                }

            }
        }
        if (transfer) {
            for (int i = 0; i < bankUsers.size(); i++) {
                if (bankUsers.get(i).getAccountNumber() == recipientAccNumber) {
                    bankUsers.get(i).setBalance(bankUsers.get(i).getBalance() + tmpAmount);
                    System.out.println("Transfer completed successfully");
                    break;
                }

            }

        }else{
            System.out.println("Transfer failed");
        }
    }


    public static void main(String[] args) {
        Bank myBank = new Bank();
        myBank.startBankApplication();

    }
}

