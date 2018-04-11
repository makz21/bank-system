//import java.util.HashMap;
//import java.util.Scanner;
//
//
//public class Bank {
//    Bank() {
//    }
//
//    private int userID = 0;
//
//
//    Scanner input = new Scanner(System.in);
//    HashMap<Integer, Account> bankUsers = new HashMap<>();
//
//    private void startBankApplication() {
//        bankEntryMessage(); // printing some start informations
//        userChoiceMenu();
//        System.exit(0);
//    }
//
//    private void userChoiceMenu() {
//        // handle user commands
//        boolean quit = false;
//        String menuItem;
//        do {
//            System.out.println("\nEnter command...");
//            menuItem = input.nextLine();
//            switch (menuItem.toLowerCase()) {
//                case "help":                        // displays all available commands
//                    if (confirmOperation()) {
//                        displayHelpCommands();
//                    } else {
//                        System.out.println("Operation aborted\n");
//                        break;
//                    }
//                    break;
//                case "add":                         //adding new user user
//                    addNewUser();
//                    break;
//                case "all":                         //display all users
//                    if (confirmOperation()) {
//                        displayAllClients();
//                    } else {
//                        System.out.println("Operation aborted\n");
//                        break;
//                    }
//                    break;
//                case "delete":                      // delete chosen user
//                    deleteUser();
//                    break;
//                case "deposit":                     // deposit money
//                    makeDeposit();
//                    break;
//                case "withdraw":                    //withdraw money
//                    makeWithdraw();
//                    break;
//                case "transfer":
//                    makeTransfer(); //transfer money
//                    break;
//                case "exit":
//                    if (confirmOperation()) {
//                        quit = true;
//                    } else {
//                        System.out.println("Operation aborted\n");
//                        break;
//                    }
//                    break;
//                default:
//                    System.out.println("Invalid choice.");
//            }
//        } while (!quit);
//        System.out.println("Bye-bye!");
//    }
//
//    private boolean confirmOperation() {
//        String c;
//        do {
//            System.out.println("Do you confirm the operation? [Y]/[N]");
//            c = input.next();
//            input.nextLine();
//            if (c.equalsIgnoreCase("N")) {
//                return false;
//            }
//        } while (!c.equalsIgnoreCase("Y"));
//        return true;
//    }
//
//    private boolean confirmDisplayClients() {
//        String c;
//        do {
//            System.out.println("Do you wanna see client list? [Y]/[N]");
//            c = input.next();
//            input.nextLine();
//            if (c.equalsIgnoreCase("N")) {
//                return false;
//            }
//        } while (!c.equalsIgnoreCase("Y"));
//        return true;
//    }
//
//    private void bankEntryMessage() {
//        System.out.println("\nThis is bank management application\n" +
//                "=================================================================\n" +
//                "In this system you need to confirm all operations!\n" +
//                "[Y] = confirm || [N] = cancel\n" +
//                "=================================================================\n" +
//                "For more informations and options write: help\n" +
//                "For exit write: exit");
//    }
//
//    private void displayHelpCommands() {
//        System.out.println("All available commands:\n" +
//                "=================================================================\n" +
//                "help - displays all available commands\n" +
//                "add - add new user\n" +
//                "all - display all users\n" +
//                "delete - delete selected user\n" +
//                "deposit - deposit funds into an account\n" +
//                "withdraw - withdraw funds from an account \n" +
//                "transfer - transfer funds between two accounts\n" +
//                "exit - application closing command\n" +
//                "=================================================================");
//
//    }
//
//    private void addNewUser() {
//        String tmpNameFirst, tmpNameLast, tmpPesel, tmpAdress;
//        double tmpBalance;
//
//
//        System.out.println("\nEnter information about the new user\n" +
//                "=================================================================");
//        System.out.println("Account Holders First Name :: ");
//        tmpNameFirst = input.nextLine();
//        System.out.println("Account Holders Last Name :: ");
//        tmpNameLast = input.nextLine();
//        System.out.println("Account Holders PESEL :: ");
//        tmpPesel = input.nextLine();
//        System.out.println("Account Holders Adress :: ");
//        tmpAdress = input.nextLine();
//
//        System.out.println("Opening Balance :: ");
//        tmpBalance = input.nextDouble();
//        input.nextLine();
//
//        if (confirmOperation()) {
//            bankUsers.put(userID, (new Account(new Client(tmpNameFirst, tmpNameLast, tmpPesel, tmpAdress), userID, tmpBalance)));
//            System.out.println("New client successfully added!");
//            userID++;
//        } else {
//            System.out.println("Operation aborted\n");
//            return;
//        }
//    }
//
//    private void displayAllClients() {
//        if (bankUsers.isEmpty()) {
//            System.out.println("There is no clients in base!\n=================================================================");
//        } else {
//            bankUsers.forEach((key, value) -> System.out.println(key + ")" + value));
//        }
//    }
//
//    private Account getClientFromBase() {
//        System.out.println("=================================================================\n" +
//                "Enter clientID number you want to select");
//        int key = input.nextInt();
//        input.nextLine();
//        Account selectedUser = bankUsers.get(key);
////        if (selectedUser == null) {
////            return null;
////        }
//        return selectedUser;
//    }
//
//    private void deleteUser() {
//        if (bankUsers.isEmpty()) {
//            System.out.println("Nothing to delete! Base is empty\n");
//        } else {
//            if (confirmDisplayClients()) {
//                displayAllClients();
//            }
//            Account selectedUser = getClientFromBase();
//            if (selectedUser == null) {
//                System.out.println("Client is not existing in base");
//                return;
//            } else {
//
//                if (confirmOperation()) {
//                    int key = selectedUser.getClientID();
//                    bankUsers.remove(key);
//                    System.out.println("Delete operation completed successfully\n");
//                } else {
//                    System.out.println("Operation aborted\n");
//                }
//            }
//        }
//    }
//
//    private void makeDeposit() {
//        if (bankUsers.isEmpty()) {
//            System.out.println("You can not deposit funds, client base is empty!\n");
//        } else {
//            if (confirmDisplayClients()) {
//                displayAllClients();
//            }
//            Account selectedUser = getClientFromBase();
//            if (selectedUser == null) {
//                System.out.println("Client is not existing in base");
//                return;
//            }
//            System.out.println("Enter amount you want to deposit\n");
//            double tmpAmount = input.nextDouble();
//            input.nextLine();
//            if (confirmOperation()) {
//                selectedUser.depositFunds(tmpAmount);
//                System.out.println("Deposit operation completed successfully\n");
//            } else {
//                System.out.println("Operation aborted\n");
//            }
//        }
//    }
//
//    private void makeWithdraw() {
//        if (bankUsers.isEmpty()) {
//            System.out.println("You can not deposit funds, client base is empty!\n");
//        } else {
//            if (confirmDisplayClients()) {
//                displayAllClients();
//            }
//            Account selectedUser = getClientFromBase();
//            if (selectedUser == null) {
//                System.out.println("Client is not existing in base");
//                return;
//            }
//            System.out.println("Enter amount you want to withdraw\n");
//            double tmpAmount = input.nextDouble();
//            input.nextLine();
//            if (confirmOperation()) {
//                if (selectedUser.withdrawFunds(tmpAmount)) {
//                    System.out.println("Withdraw operation completed successfully\n");
//                } else {
//                    System.out.println("You can't withdraw: " + tmpAmount + "$ " +
//                            "You can withdraw max: " + selectedUser.getBalance() + "$\n");
//                }
//            } else {
//                System.out.println("Operation aborted\n");
//            }
//        }
//    }
//
//    private void makeTransfer() {
//        if (bankUsers.isEmpty() || bankUsers.size() < 2) {
//            System.out.println("You can not transfer funds, client base is empty or has less than two users!\n");
//        } else {
//            System.out.println("Select two userID (First-sender, second-recipient)\n");
//            if (confirmDisplayClients()) {
//                displayAllClients();
//            }
//            Account sender = getClientFromBase();
//
//            Account recipient = getClientFromBase();
//
//            if (sender == null || recipient == null) {
//                System.out.println("Invalid sender or recipient");
//            } else {
//                System.out.println("Enter the amount you want to transfer\n");
//                double tmpAmount = input.nextDouble();
//                input.nextLine();
//                if (sender.withdrawFunds(tmpAmount)) {
//                    recipient.depositFunds(tmpAmount);
//                    System.out.println("Transfer completed successfully");
//                } else {
//                    System.out.println("You can't transfer: " + tmpAmount + "$ " +
//                            "You can transfer max: " + sender.getBalance() + "$\n");
//                }
//            }
//        }
//    }
//
//
//    public static void main(String[] args) {
//        Bank myBank = new Bank();
//        myBank.startBankApplication();
//    }
//}
//
//
//
//
//
//
//
