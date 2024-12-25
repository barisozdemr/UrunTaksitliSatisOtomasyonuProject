
package Java;

import java.util.ArrayList;
import java.util.Map;

public class Log {
    public static String signUpNotification;
    
    public static String changeUsernameNotification;
    
    public static String changePasswordNotification;
    
    // Checks if username and password is correct
    public static boolean trySignIn(String username, String password)
    {
        Map<String, ArrayList<String>> userData = DataStore.getUserData();
        
        return userData.containsKey(username) && userData.get(username).get(0).equals(password);
    }
    
    // Checks if username, password and password again are valid and username is not already in use
    public static boolean trySignUp(String username, String password, String password2)
    {
        Map<String, ArrayList<String>> userData = DataStore.getUserData();
        
        if(validUsernamePasswordControl(username, password, password2)) //--- valid control
        {
            if(userData.containsKey(username)) //---------------------------- username not already in use control
            {
                signUpNotification = "This username is already in use!";
            }
            else{
                return DataStore.addUser(username, password);
            }
        }
        
        return false;
    }
    
    // Checks if username, password and password again are valid
    public static boolean validUsernamePasswordControl(String username, String password, String password2)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specCharacters = ",._-*+/=&%$#";
        
        if(username.isBlank() || password.isBlank() || password2.isBlank())
        {
            signUpNotification = "You can not leave blank lines!";
            return false;
        }
        
        if(!password.equals(password2))
        {
            signUpNotification = "Your passwords don't match!";
            return false;
        }
        
        if(username.length() < 5)
        {
            signUpNotification = "Username can not be shorter than 5 characters!";
            return false;
        }
        
        if(password.length() < 8)
        {
            signUpNotification = "Password can not be shorter than 8 characters!";
            return false;
        }
        
        for(int i=0 ; i<username.length() ; i++)
        {
            if(alphabet.indexOf(username.charAt(i)) == -1 && numbers.indexOf(username.charAt(i)) == -1)
            {
                signUpNotification = "Username can only contain characters of alphabet and numbers!";
                return false;
            }
        }
        
        for(int i=0 ; i<password.length() ; i++)
        {
            if(alphabet.indexOf(password.charAt(i)) == -1 && numbers.indexOf(password.charAt(i)) == -1 && specCharacters.indexOf(password.charAt(i)) == -1)
            {
                signUpNotification = "Your password contains invalid characters!";
                return false;
            }
        }
        
        return true;
    }
    
    //------------------------------------------------------------------------------------ User Information Change Methods
    
    public static boolean tryChangeUsername(String username, String password)
    {
        Map<String, ArrayList<String>> userData = DataStore.getUserData();
        
        if(userData.get(DataStore.loggedUsersName).get(0).equals(password)) //- correct password control
        {
            if(validUsernameControl(username)) //------------------------------ valid username control
            {
                if(!DataStore.loggedUsersName.equals(username)) //------------- not same username control
                {
                    if(!userData.containsKey(username)) //--------------------- username not already in use control
                    {
                        DataStore.changeUsername(username); //----------------- change username
                        return true;
                    }
                    else{
                        changeUsernameNotification = "This username is already in use!";
                    }
                }
                else{
                    changeUsernameNotification = "You already have the same username!";
                }
            }
        }
        else{
            changeUsernameNotification = "Your password is incorrect!";
        }
        
        
        return false;
    }
    
    public static boolean tryChangePassword(String oldPassword, String newPassword, String newPasswordAgain)
    {
        Map<String, ArrayList<String>> userData = DataStore.getUserData();
        
        if(userData.get(DataStore.loggedUsersName).get(0).equals(oldPassword)) //---------- correct password control
        {
            if(validPasswordControl(newPassword, newPasswordAgain)) //--------------------- valid new password control
            {
                if(!userData.get(DataStore.loggedUsersName).get(0).equals(newPassword)) //- not same password control
                {
                    DataStore.changePassword(newPassword); //------------------------------ change password
                    return true;
                }
                else{
                    changePasswordNotification = "You already have the same password!";
                }
            }
        }
        else{
            changePasswordNotification = "Your password is incorrect!";
        }
        
        return false;
    }
    
    public static boolean validUsernameControl(String username)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        
        if(username.isBlank())
        {
            changeUsernameNotification = "You can not leave blank lines!";
            return false;
        }
        
        if(username.length() < 5)
        {
            changeUsernameNotification = "Username can not be shorter than 5 characters!";
            return false;
        }
        
        for(int i=0 ; i<username.length() ; i++)
        {
            if(alphabet.indexOf(username.charAt(i)) == -1 && numbers.indexOf(username.charAt(i)) == -1)
            {
                changeUsernameNotification = "Username can only contain characters of alphabet and numbers!";
                return false;
            }
        }
        
        return true;
    }
    
    public static boolean validPasswordControl(String newPassword, String newPasswordAgain)
    {
        String alphabet = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String numbers = "0123456789";
        String specCharacters = ",._-*+/=&%$#";
        
        if(newPassword.isBlank() || newPasswordAgain.isBlank())
        {
            changePasswordNotification = "You can not leave blank lines!";
            return false;
        }
        
        if(!newPassword.equals(newPasswordAgain))
        {
            changePasswordNotification = "Your passwords don't match!";
            return false;
        }
        
        if(newPassword.length() < 8)
        {
            changePasswordNotification = "Password can not be shorter than 8 characters!";
            return false;
        }
        
        for(int i=0 ; i<newPassword.length() ; i++)
        {
            if(alphabet.indexOf(newPassword.charAt(i)) == -1 && numbers.indexOf(newPassword.charAt(i)) == -1 && specCharacters.indexOf(newPassword.charAt(i)) == -1)
            {
                changePasswordNotification = "Your password contains invalid characters!";
                return false;
            }
        }
        
        return true;
    }
}
