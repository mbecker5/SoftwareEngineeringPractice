package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
    }

    @Test
    void isEmailValidTest(){

        assertTrue(BankAccount.isEmailValid( "a@c.com"));

        //testing prefix
        //numbers, letters, underscore, periods and dashes are ok
        //an underscore, period or dash must be followed by one or more numbers or letters
        assertFalse( BankAccount.isEmailValid("a-@c.com"));
        assertFalse(BankAccount.isEmailValid("a..b@c.com"));
        assertFalse( BankAccount.isEmailValid(".a@c.com"));
        assertFalse(BankAccount.isEmailValid("a#b@c.com"));

        //testing incorrect domain
        //letters, numbers and dashes are ok
        //the last portion of the domain must be at least two characters
        assertFalse( BankAccount.isEmailValid("a@c.c"));
        assertFalse(BankAccount.isEmailValid("a@c#c.com"));
        assertFalse( BankAccount.isEmailValid("a@c"));
        assertFalse(BankAccount.isEmailValid("a@c..com"));

        //no email at all
        assertFalse( BankAccount.isEmailValid(""));


    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}