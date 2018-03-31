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
                        break;
                    }
                    break;
                case "delete":                      // delete chosen user
                    deleteUser();
                    break;
                case "deposit":
                    //deposit money
                    break;
                case "withdraw":
                    //withdraw money
                    break;
                case "transfer":
                    //transfer money
                    break;
                case "exit":
                    if (confirmOperation()) {
                        quit = true;
                    } else {
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
            return;
        }
    }

    private void displayAllClients() {
        int k = 0;
        if (bankUsers.isEmpty()) {
            System.out.println("There is no clients in base!\n==========================================");
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
                return;
            }
        }
    }


    public static void main(String[] args) {
        Bank myBank = new Bank();
        myBank.startBankApplication();

    }
}
