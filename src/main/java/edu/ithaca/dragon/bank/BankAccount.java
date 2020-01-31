package edu.ithaca.dragon.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount)  {
        if (!(balance < amount) && amount > 0){
            balance -= amount;
        }
    }


    public static boolean isEmailValid(String email){
        boolean isValid = true;
        if (email.indexOf('@') == -1) {
            isValid = false;
        }
        else if (!email.contains(".com")){
            isValid = false;
        }
        else if(email.indexOf('@') == 0){
            isValid = false;
        }
        else if (email.charAt(0) == '.') {
            isValid = false;
        }
        else if (email.charAt(1) == '-') {
            isValid = false;
        }
        else if (email.indexOf('#') != -1) {
            isValid = false;
        }
        else if (email.charAt(email.indexOf('.') + 1) == '.') {
            isValid=false;
        }
        else if (email.charAt(email.indexOf('.') + 2) == '.') {
            if (email.indexOf('.') < email.indexOf('@')) {
                isValid=false;
            }
        }
        else if (email.length() <= email.indexOf('@') + 4){
            isValid=false;
        }
        else if (email.charAt(email.indexOf('@') + 2) == '_'){
            isValid=false;
        }
        else if (email.contains("c.d.")){
            isValid = false;
        }
        return isValid;
    }

    /**
     * checks if the amount entered is a valid double amount greater than 0 and no more than two decimal points
     * @param amount
     * @return isValid
     */

    public static boolean isAmountValid(double amount){
        if(amount <= 0){
            return false;
        }
        else {
            String amountString = Double.toString(amount);
            String decimalPlace = amountString.substring(amountString.indexOf("."));
            System.out.print(decimalPlace + "\n");
            if(decimalPlace.length() > 3){
                return false;
            }
            return true;
        }
    }

}

