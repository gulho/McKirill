package ee.sda.mackirill.util;

public class Validation {

    public int isEmailValid(String email) {

        int result = 0; // user exists, proceed

        if (email.matches("^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$") == false){
           result = 1; // email format is invalid
           return result;
        } /*else if (*//* check DB for existing user & if user doesn't exist*//*){
            result = 2;
            return result;
        }   */
        return result;
    }

    public boolean isPasswordValid(String password) {
/*
        if (*//* password matches with DB = true *//*){
           return true;
        } */
        return false;
    }
}
