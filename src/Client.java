public class Client {

    private String nameFirst;
    private String nameLast;
    private String numberPesel;
    private String clientAdress;
    private int clientID;

    Client(String name_f, String name_lst, String pesel, String adress, int c_num) {
        nameFirst = name_f;
        nameLast = name_lst;
        numberPesel = pesel;
        clientAdress = adress;
        clientID = c_num;
    }

    // method to return full name
    public String getFullName() {
        return (nameFirst + " " + nameLast);
    }

    public String getNameFirst() {
        return nameFirst;
    }

    public String getNameLast() {
        return nameLast;
    }

    void setFirstName(String n_new) {
        nameFirst = n_new;
    }

    void setLastName(String n_new) {
        nameLast = n_new;
    }

    public String getClientAdress() {
        return clientAdress;
    }

    public int getClientID()
    {
        return clientID;
    }

    @Override
    public String toString() {
        return "Client: " + nameFirst +
                " " + nameLast +
                " PESEL: " + numberPesel +
                " Adress: " + clientAdress +
                " ClientID: " + clientID;

    }
}
