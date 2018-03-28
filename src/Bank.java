import java.util.Scanner;

public class Bank {
    Bank() {
    };

    Scanner in = new Scanner(System.in);

    public void startBankApplication() {
        bankEntryMessage(); // printing some start informations
        userChoiceMenu();
    }

    private void userChoiceMenu() {
        // handle user commands
        boolean quit = false;
        String menuItem;

        do {
            menuItem = in.nextLine();

            switch (menuItem.toLowerCase()) {
                case "help":
                    helpCommand();
                    break;
                case "add":
                    //adding user
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
        System.out.println("\nThis is bank management application\n" +
                "For more informations and options write: help\n" +
                "For exit write: exit\n");
    }

    private void helpCommand() {
        System.out.println("All available commands:\n\n" +
                "help - displays all available commands\n" +
                "add - add new user\n" +
                "delete - delete selected user\n" +
                "exit - application closing command");
    }


    public static void main(String[] args) {
        Bank myBank = new Bank();
        myBank.startBankApplication();

    }
}
