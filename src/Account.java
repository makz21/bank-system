public class Account
{
    // variables contained within class account //
    // accessible only via methods/functions as //
    // they are private and subsequently can    //
    // only be changed via member functions.    //

    private Client person;            // account holders client
    private double balance;           // current account balance
    private long accountNumber;      // account number

    // Constuctor for Objects of type account //
    Account( Client c_client, long c_num, double c_balance)
    {
        person = c_client;
        balance = c_balance;
        accountNumber = c_num;
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


    public String getName() // returns account holders name //
    {
        // Access our client object (person)
        // then access getName() function
        // of Client class
        return person.getName();
    }

    public long getAccountNumber() // returns account number //
    {
        return accountNumber;
    }

    public Client getClient()
    {
        return person;
    }

    @Override
    public String toString() {
        return person +
                " Balance: " + balance + '$' +
                " Account Number: " + accountNumber;
    }
}
