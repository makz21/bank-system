public class Account
{
    // variables contained within class account //
    // accessible only via methods/functions as //
    // they are private and subsequently can    //
    // only be changed via member functions.    //

    private Client person;            // account holders client
    private double balance;           // current account balance

    // Constuctor for Objects of type account //
    Account( Client c_client, double c_balance)
    {
        person = c_client;
        balance = c_balance;

    }

    // Function to deposit funds into an account //
    public void depositFunds( double p_amount)
    {
        balance += p_amount;
    }

    // function to withdraw funds from an account //
    // checks to see whether funds are available. //
    public boolean withdrawFunds( double p_amount)
    {
        // check if enough funds
        if ( p_amount > balance )
            return false;
        else
            balance -= p_amount;
        return true;
    }


    public double getBalance() // returns account balance //
    {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getFullName() // returns account holders name //
    {
        // Access our client object (person)
        // then access getName() function
        // of Client class
        return person.getFullName();
    }

    public String getNameFirst(){
        return person.getNameFirst();
    }

    public String getNameLast() {
        return person.getNameLast();
    }

    public int getClientID() // returns account number //
    {
        return person.getClientID();
    }

    public String getNumberPesel() {
        return person.getNumberPesel();
    }

    public String getClientAdress(){
        return person.getClientAdress();
    }

    public Client getClient()
    {
        return person;
    }

    @Override
    public String toString() {
        return person +
                " Balance: " + balance + '$';
    }
}
