public class Client {

    private String nameFirst, nameLast, numberPesel, clientAdress;

    // default constructor
    Client() {
    }

    // Overload Constructor
    Client(String name_f, String name_lst, String pesel, String adress) {
        nameFirst = name_f;
        nameLast = name_lst;
        numberPesel = pesel;
        clientAdress = adress;
    }

    // method to return full name
    String getName() {
        return (nameFirst + " " + nameLast);
    }

    void changeFirstName(String n_new) {
        nameFirst = n_new;
    }

    void changeLastName(String n_new) {
        nameLast = n_new;
    }

    String getClientAdress() {
        return clientAdress;
    }

    @Override
    public String toString() {
        return "Client: " + nameFirst +
                " " + nameLast +
                " PESEL: " + numberPesel +
                " Adress: " + clientAdress;

    }
}
