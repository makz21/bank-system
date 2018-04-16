import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.function.Predicate;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.stream.Collectors;

public class BankTest {
    BankTest() {
    }

    private int userID = 0;

    Scanner input = new Scanner(System.in);
    HashMap<Integer, Account> bankUsers = new HashMap<>();
    Gson gson = new GsonBuilder().setPrettyPrinting().create();


    private void startBankApplication() {
        loadBankUsersFromJsontoHashMap();       // loads users from json to HashMap
        setUserID();                            // protects overwriting userID
        bankEntryMessage();                     // printing some start informations
        userChoiceMenu();                       // handle user commands
        saveBankUsersToJson();                  // saves users to from HashMap to json
    }

    private void userChoiceMenu() {
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
                case "search":
                    searchMenu();
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

    private void searchMenu() {
        boolean quit = false;
        String menuItem;
        do {
            System.out.println("Enter the chosen criterion\n" +
                    "firstname || lastname || pesel || clientid || address\n");
            menuItem = input.nextLine();
            switch (menuItem.toLowerCase()) {
                case "firstname":
                    searchByFirstName();        //search by first name
                    quit = true;
                    break;
                case "lastname":
                    searchByLastName();          //search by last name
                    quit = true;
                    break;
                case "pesel":
                    searchByPesel();             //search by pesel
                    quit = true;
                    break;
                case "clientid":
                    searchByClientID();         //search by ClientID
                    quit = true;
                    break;
                case "address":
                    searchByAddress();          //search by address
                    quit = true;
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
                    System.out.println("Invalid choice.\n");
            }
        } while (!quit);
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

    private boolean confirmDisplayClients() {
        String c;
        do {
            System.out.println("Do you wanna see client list? [Y]/[N]");
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
                "search - search clients after a given criterion \n" +
                "transfer - transfer funds between two accounts\n" +
                "exit - application closing command\n" +
                "=================================================================");

    }

    private void addNewUser() {
        String tmpNameFirst, tmpNameLast, tmpPesel, tmpAdress;
        double tmpBalance;


        System.out.println("\nEnter information about the new user\n" +
                "=================================================================");
        System.out.println("Account Holders First Name :: ");
        tmpNameFirst = input.nextLine();
        System.out.println("Account Holders Last Name :: ");
        tmpNameLast = input.nextLine();
        System.out.println("Account Holders PESEL :: ");
        tmpPesel = input.nextLine();
        while (tmpPesel.length() != 11) {
            System.out.println("You have to enter an correct length!");
            tmpPesel = input.nextLine();
        }
        System.out.println("Account Holders address :: ");
        tmpAdress = input.nextLine();

        System.out.println("Opening Balance :: ");
        while (!input.hasNextDouble()) {
            System.out.println("You have to enter an correct amount!");
            input.next();
        }
        tmpBalance = input.nextDouble();
        input.nextLine();

        if (confirmOperation()) {
            Account newUser = new Account(new Client(tmpNameFirst, tmpNameLast, tmpPesel, tmpAdress, userID), tmpBalance);
            bankUsers.put(userID, newUser);
            System.out.println("New client successfully added!");
            userID++;
        } else {
            System.out.println("Operation aborted\n");
            return;
        }
    }

    private void displayAllClients() {
        if (bankUsers.isEmpty()) {
            System.out.println("There is no clients in base!\n=================================================================");
        } else {
            bankUsers.forEach((key, value) -> System.out.println(key + ")" + value));
        }
    }

    private Account getClientFromBase() {
        System.out.println("=================================================================\n" +
                "Enter clientID number you want to select");
        int key = input.nextInt();
        input.nextLine();
        Account selectedUser = bankUsers.get(key);
        return selectedUser;
    }

    private void deleteUser() {
        if (bankUsers.isEmpty()) {
            System.out.println("Nothing to delete! Base is empty\n");
        } else {
            if (confirmDisplayClients()) {
                displayAllClients();
            }
            Account selectedUser = getClientFromBase();
            if (selectedUser == null) {
                System.out.println("Client is not existing in base");
                return;
            } else {

                if (confirmOperation()) {
                    int key = selectedUser.getClientID();
                    bankUsers.remove(key);
                    System.out.println("Delete operation completed successfully\n");
                } else {
                    System.out.println("Operation aborted\n");
                }
            }
        }
    }

    private void makeDeposit() {
        if (bankUsers.isEmpty()) {
            System.out.println("You can not deposit funds, client base is empty!\n");
        } else {
            if (confirmDisplayClients()) {
                displayAllClients();
            }
            Account selectedUser = getClientFromBase();
            if (selectedUser == null) {
                System.out.println("Client is not existing in base");
                return;
            }
            System.out.println("Enter amount you want to deposit\n");
            double tmpAmount = input.nextDouble();
            input.nextLine();
            if (confirmOperation()) {
                selectedUser.depositFunds(tmpAmount);
                System.out.println("Deposit operation completed successfully\n");
            } else {
                System.out.println("Operation aborted\n");
            }
        }
    }

    private void makeWithdraw() {
        if (bankUsers.isEmpty()) {
            System.out.println("You can not deposit funds, client base is empty!\n");
        } else {
            if (confirmDisplayClients()) {
                displayAllClients();
            }
            Account selectedUser = getClientFromBase();
            if (selectedUser == null) {
                System.out.println("Client is not existing in base");
                return;
            }
            System.out.println("Enter amount you want to withdraw\n");
            double tmpAmount = input.nextDouble();
            input.nextLine();
            if (confirmOperation()) {
                if (selectedUser.withdrawFunds(tmpAmount)) {
                    System.out.println("Withdraw operation completed successfully\n");
                } else {
                    System.out.println("You can't withdraw: " + tmpAmount + "$ " +
                            "You can withdraw max: " + selectedUser.getBalance() + "$\n");
                }
            } else {
                System.out.println("Operation aborted\n");
            }
        }
    }

    private void makeTransfer() {
        if (bankUsers.isEmpty() || bankUsers.size() < 2) {
            System.out.println("You can not transfer funds, client base is empty or has less than two users!\n");
        } else {
            System.out.println("Select two userID (First-sender, second-recipient)\n");
            if (confirmDisplayClients()) {
                displayAllClients();
            }
            Account sender = getClientFromBase();

            Account recipient = getClientFromBase();

            if (sender == null || recipient == null) {
                System.out.println("Invalid sender or recipient");
            } else {
                System.out.println("Enter the amount you want to transfer\n");
                double tmpAmount = input.nextDouble();
                input.nextLine();
                if (sender.withdrawFunds(tmpAmount)) {
                    recipient.depositFunds(tmpAmount);
                    System.out.println("Transfer completed successfully");
                } else {
                    System.out.println("You can't transfer: " + tmpAmount + "$ " +
                            "You can transfer max: " + sender.getBalance() + "$\n");
                }
            }
        }
    }

    private void saveBankUsersToJson() {
        try (FileWriter writer = new FileWriter("bank_users.json")) {
            gson.toJson(bankUsers, writer);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadBankUsersFromJsontoHashMap() {
        try (Reader reader = new FileReader("bank_users.json")) {
            bankUsers = gson.fromJson(reader, new TypeToken<HashMap<Integer, Account>>() {
            }.getType());
        } catch (NoSuchElementException e) {

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void setUserID() {
        try {
            this.userID = bankUsers.entrySet().stream()
                    .map(x -> x.getValue())
                    .map(x -> x.getClientID())
                    .max((x1, x2) -> Integer.compare(x1, x2))
                    .get() + 1;
        } catch (NoSuchElementException e) {
        }
    }

    private void searchByFirstName(){
        String tmpNameFirst;
        System.out.println("Account Holders First Name: ");
        tmpNameFirst = input.nextLine();

        Map<Integer, Account> collect = bankUsers.entrySet()
                .stream()
                .filter(map -> tmpNameFirst.toUpperCase().equals(map.getValue().getNameFirst().toUpperCase()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        if(collect.isEmpty()){
            System.out.println("Client not found");
        }else{
            System.out.println(collect);
        }
    }

    private void searchByLastName(){
        String tmpNameLast;
        System.out.println("Account Holders Last Name: ");
        tmpNameLast = input.nextLine();

        Map<Integer, Account> collect = bankUsers.entrySet()
                .stream()
                .filter(map -> tmpNameLast.toUpperCase().equals(map.getValue().getNameLast().toUpperCase()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        if(collect.isEmpty()){
            System.out.println("Client not found");
        }else{
            System.out.println(collect);
        }

    }

    private void searchByPesel(){
        String tmpPesel;
        System.out.println("Account Holders PESEL: ");
        tmpPesel = input.nextLine();
        if(tmpPesel.length() != 11){
            System.out.println("Incorrect PESEL length");
        }

        Map<Integer, Account> collect = bankUsers.entrySet()
                .stream()
                .filter(map -> tmpPesel.toUpperCase().equals(map.getValue().getNumberPesel().toUpperCase()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        if(collect.isEmpty()){
            System.out.println("Client not found");
        }else{
            System.out.println(collect);
        }
    }

    public void searchByAddress(){
        String tmpAdress;
        System.out.println("Account Holders address: ");
        tmpAdress = input.nextLine();

        Map<Integer, Account> collect = bankUsers.entrySet()
                .stream()
                .filter(map -> tmpAdress.toUpperCase().equals(map.getValue().getClientAdress().toUpperCase()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        if(collect.isEmpty()){
            System.out.println("Client not found");
        }else{
            System.out.println(collect);
        }
    }

    public void searchByClientID(){
        Integer tmpClientID;
        System.out.println("Account Holders ClientID: ");
        while (!input.hasNextInt()) {
            System.out.println("You have to enter an correct amount!");
            input.next();
        }
        tmpClientID = input.nextInt();
        input.nextLine();

        Map<Integer, Account> collect = bankUsers.entrySet()
                .stream()
                .filter(map -> tmpClientID.equals(map.getValue().getClientID()))
                .collect(Collectors.toMap(map -> map.getKey(), map -> map.getValue()));
        if(collect.isEmpty()){
            System.out.println("Client not found");
        }else{
            System.out.println(collect);
        }
    }

    public static void main(String[] args) {
        BankTest myBank = new BankTest();
        myBank.startBankApplication();
    }
}







