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
        assertFalse( BankAccount.isEmailValid("a-@c.com")); //no symbol before @ (border, only 1 symbol)
        assertFalse(BankAccount.isEmailValid("a..b@c.com")); //double periods (border only 1 following period)
        assertFalse( BankAccount.isEmailValid(".a@c.com")); //can't start with symbol (border only 1 symbol at start)
        assertFalse(BankAccount.isEmailValid("a#b@c.com")); //can't use # (border because only 1 #)

        // missing borders:
        //no symbols before @, multiple symbols before @
        //single period followed by multiple characters then period, single period one character single period
        //starting with letter char instead of symbol
        //multiple #, zero #

        //testing incorrect domain
        //letters, numbers and dashes are ok
        //the last portion of the domain must be at least two characters
        assertFalse( BankAccount.isEmailValid("a@c.c")); //domain needs 2 or more character (border only 1 charcter)
        assertFalse(BankAccount.isEmailValid("a@c#c.com")); //no # in domain (border testing only 1 #)
        assertFalse( BankAccount.isEmailValid("a@c")); //domain has 0 periods
        assertFalse(BankAccount.isEmailValid("a@c..com")); //can't have double periods (border only 1 following period)

        //missing borders:
        //domain having multiple characters
        //testing 0 #'s, testing multiple #'s
        // testing more than 2 consecutive periods

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