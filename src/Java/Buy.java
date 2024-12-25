
package Java;

public class Buy{
    
    public static String buyNotification;
    
    // Tries to run update payments method
    public static boolean tryBuy(String months, String singlePayment)
    {
        if(DataStore.updatePayments(Integer.parseInt(months), Double.parseDouble(singlePayment)))
        {
            buyNotification = "The purchase completed succesfully!";
            return true;
        }
        else{
            buyNotification = "The purchase could not be completed due to unknown error!";
            return false;
        }
    }
}
