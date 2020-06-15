/**
 * This is the test class for Util.java
 * @Author Hao Li
 * Seattle University
 * CPSC5210
 */
package com.company.utility;

import org.junit.Test;
import java.io.*;
import java.util.Scanner;
import static org.junit.Assert.*;

/**
 * Tested all the methods except the ClearConsole Method and ClearConsoleConfirm due to lack of
 * strategy testing functions related to testing commandline execution.
 * All the tests below has been passed by the Util.java
 */
public class UtilTest {

    /**
     * Test the print function by capture the outputstream and compare
     * Test cases: string, blank, integer, character
     */
    @Test
    public void test_print() {
        String name = "John";
        String blank = "";
        int testNumber = 999;
        char specialChar = '!';
        String res1 = null;
        String res2 = null;
        String res3 = null;
        String res4 = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);

            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            Util.print("happy birthday %s..", name);
            capture.flush();
            res1 = os.toString();

            os.reset();
            Util.print("testing print blank %s..", blank);
            capture.flush();
            res2 = os.toString();

            os.reset();
            Util.print("testing print number %s..", testNumber);
            capture.flush();
            res3 = os.toString();

            os.reset();
            Util.print("testing print number %s..", specialChar);
            capture.flush();
            res4 = os.toString();


        } finally {
            System.setOut(originalOut);
        }
        String expectedString = "[CARD GAME] happy birthday John..\n";
        String expectedRes = "[CARD GAME] testing print blank ..\n";
        String expectedRes2 = "[CARD GAME] testing print number 999..\n";
        String expectedRes3 = "[CARD GAME] testing print number !..\n";
        assertEquals(expectedString, res1);
        assertEquals(expectedRes, res2);
        assertEquals(expectedRes2, res3);
        assertEquals(expectedRes3, res4);

    }

    /**
     * Same strategy as previous
     */
    @Test
    public void printDebug() {
        String name = "print debug";
        String res = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            Util.printDebug("Testing %s..", name);
            capture.flush();
            res = os.toString();
        } finally {
            System.setOut(originalOut);
        }
        String expectedString = "[DEBUG] Testing print debug..\n";
        assertEquals(expectedString, res);
    }

    /**
     * Same strategy as previous
     */
    @Test
    public void printError() {
        String name = "print error";
        String res = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            Util.printError("Testing %s..", name);
            capture.flush();
            res = os.toString();
        } finally {
            System.setOut(originalOut);
        }
        String expectedString = "[ERROR] Testing print error..\n";
        assertEquals(expectedString, res);
    }
    /**
     * Same strategy as previous
     */
    @Test
    public void printEmptyMessage() {
        String msg = "this is the msg";
        String res = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            Util.printEmptyMessage(msg);
            capture.flush();
            res = os.toString();
        } finally {
            System.setOut(originalOut);
        }
        String expectedString = "<<< [this is the msg] >>>\n";
        assertEquals(expectedString, res);
    }
    /**
     * Same strategy as previous
     */
    @Test
    public void printSeparator() {
        String msg = "this is the msg";
        String res = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            Util.printSeparator(msg);
            capture.flush();
            res = os.toString();
        } finally {
            System.setOut(originalOut);
        }
        String expectedString = "==================[this is the msg]==================\n";
        assertEquals(expectedString, res);
    }
    /**
     * Same strategy as previous
     */
    @Test
    public void printSeparator2() {
        String msg = "this is the msg";
        String res = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            Util.printSeparator2(msg);
            capture.flush();
            res = os.toString();
        } finally {
            System.setOut(originalOut);
        }
        String expectedString = ">>>>>>>>>>>>>>> [this is the msg] <<<<<<<<<<<<<<<<\n";
        assertEquals(expectedString, res);
    }
    /**
     * Same strategy as previous
     */
    @Test
    public void printInBox() {

        String res = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            Util.printInBox("The winner of the game is: %s with %d round wins!", "McQueen", 5);
            capture.flush();
            res = os.toString();
        } finally {
            System.setOut(originalOut);
        }

        String expected_display = "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n" +
                     "||| The winner of the game is: McQueen with 5 round wins! |||\n" +
                     "|||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||\n";
        assertEquals(expected_display, res);


    }

    /**
     * Create a scanner reading the test.txt to invoke actions related with scanner
     * Testing recursive functions of the test subject and assert the return value equals to the expected value
     * @throws FileNotFoundException
     */
    @Test
    public void promptInputValidationByValue() throws FileNotFoundException {

        Scanner scanner = new Scanner(new FileReader("test.txt"));
        Object[]arr = {"maddog","computer", "nerd"};
        Object returnedVal = Util.promptInputValidationByValue("hello", scanner, arr);
        Object testObject = "computer";
        assertEquals(testObject, returnedVal);
        scanner.close();
    }

    /**
     * Same as above
     * @throws FileNotFoundException
     */
    @Test
    public void promptInputValidationByClass() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("test2.txt"));
        Object returnedVal = Util.promptInputValidationByClass("testing", scanner, Integer.class);
        Object testObject = 99;
        assertEquals(testObject, returnedVal);

        Object returnedVal2 = Util.promptInputValidationByClass("testing", scanner, Boolean.class);
        Object testObject2 = true;
        assertEquals(testObject2, returnedVal2);

        scanner.close();

    }

    /**
     * Same as above
     * @throws FileNotFoundException
     */
    @Test
    public void promptInputValidationByRange() throws FileNotFoundException {
        Scanner scanner = new Scanner(new FileReader("test2.txt"));
        Object returnedVal = Util.promptInputValidationByRange("testing", scanner, 1, 1000);
        Object testObject = 99;
        assertEquals(testObject, returnedVal);

        Object returnedVal2 = Util.promptInputValidationByRange("testing", scanner, -501, -1);
        Object testObject2 = -500;
        assertEquals(testObject2, returnedVal2);

        scanner.close();
    }
}