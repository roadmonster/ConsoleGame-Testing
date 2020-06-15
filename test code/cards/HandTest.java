package com.company.cards;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class HandTest {
    Attribute a1 = new Attribute("attribute1", null, 0, 0, false, 0, null),
            a2 = new Attribute("attribute2", null, 0, 0, false, 0, null),
            a3 = new Attribute("attribute3", null, 0, 0, false, 0, null);
    Card test1;
    Card test2;
    Hand hand1;
    Hand hand2;

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;


    @BeforeEach
    void setUp() {
        test1 = new Card("CardName1", "This is test 1 description", 1, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 4, new Attribute[] {a1,a2,a3});
        hand1 = new Hand();
        hand2 = new Hand();
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void addCardToHand() {
        hand1.addCardToHand(test1);
        assertEquals(true, hand1.hasCardOnIndex(0));
    }

    @Test
    void addCardToHandFullHandException() {
        String expectedMessage = "Hand is full!";

        hand1.addCardToHand(test1);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            hand1.addCardToHand(test1);
            hand1.addCardToHand(test2);
            hand1.addCardToHand(test1);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void removeCardFromHand() {
        hand1.addCardToHand(test1);
        assertEquals(true, hand1.hasCardOnIndex(0));
        hand1.removeCardFromHand(0);
        assertEquals(false, hand1.hasCardOnIndex(0));
    }

    @Test
    void removeCardFromHandEmptyHandException() {
        String expectedMessage = "Hand is empty";

        hand1.addCardToHand(test1);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            hand1.removeCardFromHand(0);
            hand1.removeCardFromHand(0);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void removeCardFromHandNoCardAtIndexException() {
        String expectedMessage = "There is no card on the hand at this index!";

        hand1.addCardToHand(test1);
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            hand1.removeCardFromHand(1);
        });

        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void hasCardOnIndex() {
        assertEquals(false, hand1.hasCardOnIndex(0));
        hand1.addCardToHand(test1);
        assertEquals(true, hand1.hasCardOnIndex(0));
    }

    @Test
    void getCardsInHand() {
        assertEquals(0, hand1.getCardsInHand());
        hand1.addCardToHand(test1);
        assertEquals(1, hand1.getCardsInHand());
        hand1.addCardToHand(test1);
        assertEquals(2, hand1.getCardsInHand());
    }

    @Test
    void getCardFromHand() {
        hand1.addCardToHand(test1);
        assertEquals(test1, hand1.getCardFromHand(0));
    }

    @Test
    void getCardFromHandNoCardAtIndexException() {
        String expectedMessage = "There is no card on the hand at this index!";

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            hand1.addCardToHand(test1);
            hand1.getCardFromHand(1);
        });
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getFirstCardIndexFromHand() {
        hand1.addCardToHand(test1);
        assertEquals(0, hand1.getFirstCardIndexFromHand());
    }

    @Test
    void printCards() {
        String expectedPrint = "[CARD GAME] Card Index on hand: 0\n";
        System.setOut(new PrintStream(outContent));

        test1.printCard();
        expectedPrint += outContent.toString();

        hand1.addCardToHand(test1);
        outContent.reset();

        hand1.printCards();
        String actualPrint = outContent.toString();
        assertEquals(expectedPrint, actualPrint);

        System.setOut(originalOut);
    }

    @Test
    void printCardsEmptyHand() {
        String expectedPrint = "<<< [HAND IS EMPTY] >>>\n";
        System.setOut(new PrintStream(outContent));

        hand1.printCards();
        String actualPrint = outContent.toString();
        assertEquals(expectedPrint, actualPrint);

        System.setOut(originalOut);
    }

    @Test
    void printCardsHidden() {
        System.setOut(new PrintStream(outContent));

        test1.printCardHidden();
        String expectedPrint = outContent.toString();

        hand1.addCardToHand(test1);
        outContent.reset();

        hand1.printCardsHidden();
        String actualPrint = outContent.toString();
        assertEquals(expectedPrint, actualPrint);

        System.setOut(originalOut);
    }

    @Test
    void printCardsHiddenEmptyHand() {
        String expectedPrint = "<<< [HAND IS EMPTY] >>>\n";
        System.setOut(new PrintStream(outContent));

        hand1.printCardsHidden();
        String actualPrint = outContent.toString();
        assertEquals(expectedPrint, actualPrint);

        System.setOut(originalOut);
    }
}