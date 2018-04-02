public class Account
{
    // variables contained within class account //
    // accessible only via methods/functions as //
    // they are private and subsequently can    //
    // only be changed via member functions.    //
    Account(){

    }

    private Client person;            // account holders client
    private double balance;           // current account balance
    private int clientID;      // account number

    // Constuctor for Objects of type account //
    Account( Client c_client, int c_num, double c_balance)
    {
        person = c_client;
        balance = c_balance;
        clientID = c_num;
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

    public String getName() // returns account holders name //
    {
        // Access our client object (person)
        // then access getName() function
        // of Client class
        return person.getName();
    }

    public int getClientID() // returns account number //
    {
        return clientID;
    }

    public Client getClient()
    {
        return person;
    }

    @Override
    public String toString() {
        return person +
                " Balance: " + balance + '$' +
                " ClientID: " + clientID;
    }
}
