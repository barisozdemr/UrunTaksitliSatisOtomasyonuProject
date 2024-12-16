
package Java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import javafx.scene.image.Image;

public class DataStore {
    
    public static String chosenProductsID;
    
    public static String loggedUsersName;
    
    public static final Image programLogo = new Image("/Images/icons-logos/programLogo.jpg");
    
    private static final String USERDATA_FILE_PATH = "src/Data/userData.txt";
    private static HashMap<String, ArrayList<String>> userData = new HashMap<>();
    //Example: Username -> {Password, 1st Month Payment, 2nd Month Payment, ... , 12nd Month Payment}
    //Example: baris -> {baris123, 1500, 1500, 1500, 600, 600, 600, 0, 0, 0, 0, 0, 0}
    //Example Text File: baris;baris123:1500:1500:1500:600:600:600:0:0:0:0:0:0
    
    private static final String PRODUCTDATA_FILE_PATH = "src/Data/productData.txt";
    private static HashMap<String, ArrayList<String>> productData = new HashMap<>();
    //Example: ProductID -> {ProductName, ProductImagePath, ProductPrice}
    //Example: 1001 -> {Dağ Bisikleti, /Images/products/dagBisikleti.jpg, 15000}
    //Example Text File: 1001;Dağ Bisikleti:/Images/products/dagBisikleti.jpg:15000
    
    private static final String BANKDATA_FILE_PATH = "src/Data/bankData.txt";
    private static HashMap<String, ArrayList<String>> bankData = new HashMap<>();
    //Example: BankID -> {BankName, BankImagePath, 3MonthInstallmentInterest, 6MonthInstallmentInterest, 9MonthInstallmentInterest, 12MonthInstallmentInterest}
    //Installment Insterests are in percentage form
    //Example: 101 -> {Yapı Kredi, /Images/icons-logos/yapiKredi.jpg, 1.5, 3, 4, 5}
    //Example Text File: 101;Yapı Kredi:/Images/icons-logos/yapiKredi.jpg:1,5:3:4:5
    
    //-------------------------------------------------------------------------------------------------------- User Methods
    
    public static void loadUserData()
    {
        try(BufferedReader read = new BufferedReader(new FileReader(USERDATA_FILE_PATH)))
        {
            String line;
            while ((line = read.readLine()) != null)
            {
                String[] data = line.split(";");
                
                if(data.length==2)
                {
                    String[] data2 = data[1].split(":");
                    
                    if(data2.length==13)
                    {
                        ArrayList<String> data2List = new ArrayList<>();
                        data2List.add(data2[0]);
                        data2List.add(data2[1]);
                        data2List.add(data2[2]);
                        data2List.add(data2[3]);
                        data2List.add(data2[4]);
                        data2List.add(data2[5]);
                        data2List.add(data2[6]);
                        data2List.add(data2[7]);
                        data2List.add(data2[8]);
                        data2List.add(data2[9]);
                        data2List.add(data2[10]);
                        data2List.add(data2[11]);
                        data2List.add(data2[12]);
                        
                        userData.put(data[0], data2List);
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void saveUserData()
    {
        try(BufferedWriter write = new BufferedWriter(new FileWriter(USERDATA_FILE_PATH)))
        {
            for(String username : userData.keySet())
            {
                String password = userData.get(username).get(0);
                String firstMonthPayment = userData.get(username).get(1);
                String secondMonthPayment = userData.get(username).get(2);
                String thirdMonthPayment = userData.get(username).get(3);
                String fourthMonthPayment = userData.get(username).get(4);
                String fifthMonthPayment = userData.get(username).get(5);
                String sixthMonthPayment = userData.get(username).get(6);
                String seventhMonthPayment = userData.get(username).get(7);
                String eighthMonthPayment = userData.get(username).get(8);
                String ninthMonthPayment = userData.get(username).get(9);
                String tenthMonthPayment = userData.get(username).get(10);
                String eleventhMonthPayment = userData.get(username).get(11);
                String twelfthMonthPayment = userData.get(username).get(12);
                
                write.write(username+";"+password+":"+firstMonthPayment+":"+secondMonthPayment+":"+thirdMonthPayment+":");
                write.write(fourthMonthPayment+":"+fifthMonthPayment+":"+sixthMonthPayment+":"+seventhMonthPayment+":"+eighthMonthPayment+":");
                write.write(ninthMonthPayment+":"+tenthMonthPayment+":"+eleventhMonthPayment+":"+twelfthMonthPayment);
                write.newLine();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Map<String, ArrayList<String>> getUserData()
    {
        Map<String, ArrayList<String>> unmodifiableUserData = new HashMap<>();
        
        for (Map.Entry<String, ArrayList<String>> entry : userData.entrySet())
        {
            unmodifiableUserData.put(entry.getKey(), new ArrayList<>(Collections.unmodifiableList(entry.getValue())));
        }
        
        return Collections.unmodifiableMap(unmodifiableUserData);
    }
    
    public static boolean addUser(String username, String password)
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        
        if (stackTrace.length > 2 && stackTrace[2].getClassName().equals("Java.Log")) 
        {
            ArrayList<String> theUsersData = new ArrayList<>();
            
            theUsersData.add(password);
            
            for(int i=0 ; i<12 ; i++)
            {
                theUsersData.add("0");
            }
            
            userData.put(username, theUsersData);
            
            return true;
        }
        
        return false;
    }
    
    public static void changeUsername(String newUsername)
    {
        ArrayList<String> theUsersData = userData.get(loggedUsersName);
        
        userData.remove(loggedUsersName);
        
        userData.put(newUsername, theUsersData);
        
        loggedUsersName = null;
        
        saveUserData();
    }
    
    public static void changePassword(String newPassword)
    {
        userData.get(loggedUsersName).set(0, newPassword);
        
        loggedUsersName = null;
        
        saveUserData();
    }
    
    public static boolean updatePayments(int months, double singlePayment)
    {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();
        
        if (stackTrace.length > 2 && stackTrace[2].getClassName().equals("Java.Buy")) 
        {
            for(int i=1 ; i<months+1 ; i++)
            {
                double oldMonthsPayment = Double.parseDouble(userData.get(loggedUsersName).get(i));
            
                String newMonthsPayment = String.valueOf(oldMonthsPayment + singlePayment);
            
                userData.get(loggedUsersName).set(i, newMonthsPayment);
            }
            
            return true;
        }
        
        return false;
    }
    
    //-------------------------------------------------------------------------------------------------------- Product Methods
    
    public static void loadProductData()
    {
        try(BufferedReader read = new BufferedReader(new FileReader(PRODUCTDATA_FILE_PATH)))
        {
            String line;
            while ((line = read.readLine()) != null)
            {
                String[] data = line.split(";");
                
                if(data.length==2)
                {
                    String[] data2 = data[1].split(":");
                    
                    if(data2.length==3)
                    {
                        ArrayList<String> data2List = new ArrayList<>();
                        data2List.add(data2[0]);
                        data2List.add(data2[1]);
                        data2List.add(data2[2]);
                        
                        productData.put(data[0], data2List);
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void saveProductData() //Not needed for this project
    {
        try(BufferedWriter write = new BufferedWriter(new FileWriter(PRODUCTDATA_FILE_PATH)))
        {
            for(String productID : productData.keySet())
            {
                String productName = productData.get(productID).get(0);
                String productImagePath = productData.get(productID).get(1);
                String productPrice = productData.get(productID).get(2);
                
                String line = ( productID + ";" + productName + ":" + productImagePath + ":" + productPrice );
                write.write(line);
                write.newLine();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Map<String, ArrayList<String>> getProductData()
    {
        Map<String, ArrayList<String>> unmodifiableProductData = new HashMap<>();
        
        for (Map.Entry<String, ArrayList<String>> entry : productData.entrySet())
        {
            unmodifiableProductData.put(entry.getKey(), new ArrayList<>(Collections.unmodifiableList(entry.getValue())));
        }
        
        return Collections.unmodifiableMap(unmodifiableProductData);
    }
    
    //-------------------------------------------------------------------------------------------------------- Bank Methods
    
    public static void loadBankData()
    {
        try(BufferedReader read = new BufferedReader(new FileReader(BANKDATA_FILE_PATH)))
        {
            String line;
            while ((line = read.readLine()) != null)
            {
                String[] data = line.split(";");
                
                if(data.length==2)
                {
                    String[] data2 = data[1].split(":");
                    
                    if(data2.length==6)
                    {
                        ArrayList<String> data2List = new ArrayList<>();
                        data2List.add(data2[0]);
                        data2List.add(data2[1]);
                        data2List.add(data2[2]);
                        data2List.add(data2[3]);
                        data2List.add(data2[4]);
                        data2List.add(data2[5]);
                        
                        bankData.put(data[0], data2List);
                    }
                }
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static void saveBankData() //Not needed for this project
    {
        try(BufferedWriter write = new BufferedWriter(new FileWriter(BANKDATA_FILE_PATH)))
        {
            for(String bankID : bankData.keySet())
            {
                String bankName = bankData.get(bankID).get(0);
                String bankImagePath = bankData.get(bankID).get(1);
                String Month3InstallmentInterest = bankData.get(bankID).get(2);
                String Month6InstallmentInterest = bankData.get(bankID).get(3);
                String Month9InstallmentInterest = bankData.get(bankID).get(4);
                String Month12InstallmentInterest = bankData.get(bankID).get(5);
                
                String line = ( bankID + ";" + bankName + ":" + bankImagePath + ":" + Month3InstallmentInterest + ":");
                line += ( Month6InstallmentInterest + ":" + Month9InstallmentInterest + ":" + Month12InstallmentInterest );
                write.write(line);
                write.newLine();
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Map<String, ArrayList<String>> getBankData()
    {
        Map<String, ArrayList<String>> unmodifiableBankData = new HashMap<>();
        
        for (Map.Entry<String, ArrayList<String>> entry : bankData.entrySet())
        {
            unmodifiableBankData.put(entry.getKey(), new ArrayList<>(Collections.unmodifiableList(entry.getValue())));
        }
        
        return Collections.unmodifiableMap(unmodifiableBankData);
    }
}
