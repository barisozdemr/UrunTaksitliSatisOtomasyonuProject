
package Java;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class DataStore {
    
    private static final String USERDATA_FILE_PATH = "src/Data/userData.txt";
    private static HashMap<String, String> userData = new HashMap<>();
    //Example: Username -> Password
    //Example Text File: baris;baris123
    
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
                    userData.put(data[0], data[1]);
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
                write.write(username + ";" + userData.get(username));
                write.newLine();
                
                System.out.println(username + ";" + userData.get(username));
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        loadUserData();
    }
    
    public static Map<String, String> getUserData()
    {
        Map<String, String> unmodifiableUserData = Collections.unmodifiableMap(userData);
        
        return unmodifiableUserData;
    }
    
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
                
                System.out.println(productID + ";" + productName + ":" + productImagePath + ":" + productPrice);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        loadProductData();
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
                
                System.out.println(line);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        
        loadBankData();
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
