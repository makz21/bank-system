import java.util.ArrayList;
import java.util.Scanner;

public class Bank {
    Bank() {
    };

    int usersCounter;

    Scanner input = new Scanner(System.in);
    ArrayList<Account> bankUsers = new ArrayList<Account>();

    public void startBankApplication() {
        bankEntryMessage(); // printing some start informations
        userChoiceMenu();
        System.exit(0);
    }

    private void userChoiceMenu() {
        // handle user commands
        boolean quit = false;
        String menuItem;

        do {
            menuItem = input.nextLine();

            switch (menuItem.toLowerCase()) {
                case "help":
                    helpCommand();
                    break;
                case "add":
                    addNewUser();       //adding new user user
                    break;
                case "all":
                    displayAllClients(); //display all users
                    break;
                case "delete":
                    //delete user
                        break;
                case "exit":
                    quit = true;
                    break;

                default:
                    System.out.println("Invalid choice.");
            }

        } while (!quit);

        System.out.println("Bye-bye!");

    }

    private void bankEntryMessage() {
        System.out.println("\nThis is bank management application\n==========================================\n" +
                "For more informations and options write: help\n" +
                "For exit write: exit\n");
    }

    private void helpCommand() {
        System.out.println("All available commands:\n==========================================\n" +
                "help - displays all available commands\n" +
                "add - add new user\n" +
                "all - display all users\n" +
                "delete - delete selected user\n" +
                "exit - application closing command");
    }

    private void addNewUser() {
        String tmpNameFirst, tmpNameLast, tmpPesel, tmpAdress;
        double tmpBalance;
        long tmpAccNumber;


        System.out.println("\nEnter information about the new user\n==========================================");
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

        bankUsers.add(new Account(new Client(tmpNameFirst, tmpNameLast, tmpPesel, tmpAdress), tmpAccNumber, tmpBalance));
        System.out.println("New client successfully added!");

    }

    private void displayAllClients(){
        if(bankUsers.isEmpty()){
            System.out.println("There is no clients in base!\n==========================================");
        }else {
            for (Account str : bankUsers) {
                System.out.println(str);
            }
        }
    }


    public static void main(String[] args) {
        Bank myBank = new Bank();
        myBank.startBankApplication();

    }
}
