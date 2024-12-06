
package Java;

import java.util.Map;

public class Log {
    public static String signUpNotification;
    
    public static boolean trySignIn(String username, String password)
    {
        Map<String, String> userData = DataStore.getUserData();
        
        return userData.containsKey(username) && userData.get(username).equals(password);
    }
    
    public static boolean trySignUp(String username, String password, String password2)
    {
        Map<String, String> userData = DataStore.getUserData();
        
        if(validUsernamePassword(username, password, password2))
        {
            if(userData.containsKey(username))
            {
                signUpNotification = "This username is already in use!";
            }
            else{
                DataStore.addUser(username, password);
                return true;
            }
        }
        
        return false;
    }
    
    public static boolean validUsernamePassword(String username, String password, String password2)
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
}
