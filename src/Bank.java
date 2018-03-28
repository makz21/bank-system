import java.util.Scanner;

public class Bank {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        bankEntryMessage(); // printing informations

        // handle user commands
        boolean quit = false;
        String menuItem;

        do {
            System.out.print("Choose menu item: ");
            menuItem = in.nextLine();

            switch (menuItem.toLowerCase()) {
                case "help":
                    helpCommand();
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


    private static void bankEntryMessage() {
        System.out.println("This is bank management application\n" +
                "For more informations and options write: help\n" +
                "For exit write: exit\n");
    }

    private static void helpCommand() {
        System.out.println("All available commands:\n" +
                "help - displays all available commands\n" +
                "exit- application closing command");
    }
}
