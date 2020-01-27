package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance());

        //test for 0
        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);
        assertEquals(0, bankAccount2.getBalance());

        //test for negatives this should throw an error
        //this should also be caught within the constructor
        BankAccount bankAccount3 = new BankAccount("a@b.com", -200);
        assertEquals(-200, bankAccount3.getBalance());
        BankAccount bankAccount4 = new BankAccount("a@b.com", -15);
        assertEquals(-15, bankAccount4.getBalance());
        BankAccount bankAccount5 = new BankAccount("a@b.com", -1050);
        assertEquals(-1050, bankAccount5.getBalance());
        BankAccount bankAccount6 = new BankAccount("a@b.com", -1050.00);
        assertEquals(-1050, bankAccount6.getBalance());

        //test for larger positives
        BankAccount bankAccount7 = new BankAccount("a@b.com", 1000);
        assertEquals(1000, bankAccount7.getBalance());
        BankAccount bankAccount8 = new BankAccount("a@b.com", 1000000);
        assertEquals(1000000, bankAccount8.getBalance());
        BankAccount bankAccount9 = new BankAccount("a@b.com", 15682);
        assertEquals(15682, bankAccount9.getBalance());
        BankAccount bankAccount10 = new BankAccount("a@b.com", 76543.00);
        assertEquals(76543.00, bankAccount10.getBalance());
    }

    @Test
    void withdrawTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);
        assertEquals(100, bankAccount.getBalance());
        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());

        //test zero and small amounts
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        bankAccount2.withdraw(0);
        assertEquals(200, bankAccount2.getBalance());
        bankAccount2.withdraw(5);
        assertEquals(195, bankAccount2.getBalance());
        bankAccount2.withdraw(.10);
        assertEquals(194.90, bankAccount2.getBalance());
        bankAccount2.withdraw(8.16);
        assertEquals(186.74, bankAccount2.getBalance());

        //tests greater than amount in account and the exact amount
        BankAccount bankAccount3 = new BankAccount("a@b.com", 200);
        bankAccount3.withdraw(1000);
        assertEquals(200, bankAccount3.getBalance());
        bankAccount3.withdraw(200.00);
        assertEquals(0, bankAccount3.getBalance());

        BankAccount bankAccount4 = new BankAccount("a@b.com", 200);
        bankAccount4.withdraw(-5);
        assertEquals(200, bankAccount4.getBalance());
        bankAccount4.withdraw(-150);
        assertEquals(200, bankAccount4.getBalance());
        bankAccount4.withdraw(-1000);
        assertEquals(200, bankAccount4.getBalance());
        bankAccount4.withdraw(-1500.00);
        assertEquals(200, bankAccount4.getBalance());

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
        // testing more than 2 consecutive periods
        assertFalse( BankAccount.isEmailValid("a....@c.com"));
        assertFalse( BankAccount.isEmailValid("@c.com"));

        //single period followed by multiple characters then period, single period one character single period
        assertFalse( BankAccount.isEmailValid(".ab.@c.com"));
        assertFalse( BankAccount.isEmailValid("a.b.@c.com"));

        //starting with letter char instead of symbol

        //multiple #, zero #
        assertFalse( BankAccount.isEmailValid("a#@c.com"));
        assertFalse( BankAccount.isEmailValid("a##b@c.com"));


        //testing incorrect domain
        //letters, numbers and dashes are ok
        //the last portion of the domain must be at least two characters
        assertFalse( BankAccount.isEmailValid("a@c.c")); //domain needs 2 or more character (border only 1 charcter)
        assertFalse(BankAccount.isEmailValid("a@c#c.com")); //no # in domain (border testing only 1 #)
        assertFalse( BankAccount.isEmailValid("a@c")); //domain has 0 periods
        assertFalse(BankAccount.isEmailValid("a@c..com")); //can't have double periods (border only 1 following period)

        //missing borders:
        //domain having multiple characters
        assertFalse( BankAccount.isEmailValid("a.b@cdf.com"));
        assertFalse( BankAccount.isEmailValid("a.b@c_f.com"));
        assertFalse( BankAccount.isEmailValid("a.b@c.d.com"));

        //testing 0 #'s, testing multiple #'s
        assertTrue( BankAccount.isEmailValid("a.b123@cdf.com"));
        assertTrue( BankAccount.isEmailValid("a2.b@c_f.com"));
        assertTrue( BankAccount.isEmailValid("a.b000@c.d.com"));
        assertTrue( BankAccount.isEmailValid("a.b@cdf1234.com"));
        assertTrue( BankAccount.isEmailValid("a.b@c0000000000f.com"));
        assertTrue( BankAccount.isEmailValid("a.b@c-123434986.com"));

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