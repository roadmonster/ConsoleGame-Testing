package com.company.cards;

import com.company.utility.Util;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

class GraveyardTest {

    Graveyard grave = new Graveyard();

    Attribute a1 = new Attribute("attribute1", null, 0, 0, false, 0, null),
            a2 = new Attribute("attribute2", null, 0, 0, false, 0, null),
            a3 = new Attribute("attribute3", null, 0, 0, false, 0, null);

    @Test
    void addCard() {

        for(int i  = 0; i < 50; i++){
            String cardname = "Sample card " + i;
            Card card = new Card(cardname, "Sample card", 10, new Attribute[] {a1,a2,a3});
            grave.addCard(card);
            assertTrue(card.inGraveyard());

        }
        assertNotEquals(0, grave.numCardsGraveyard());
        assertNotEquals(51, grave.numCardsGraveyard());
        assertEquals(50, grave.numCardsGraveyard());

    }



    @Test
    void removeCardTest() {
        for(int i  = 0; i < 50; i++) {
            String cardname = "Sample card " + i;
            Card card = new Card(cardname, "Sample card", 10, new Attribute[]{a1, a2, a3});
            grave.addCard(card);
        }

        for(int i  = 49; i >= 0; i--){
            Card card = grave.removeCard(i);
            assertFalse(card.inGraveyard());
        }
        assertNotEquals(50, grave.numCardsGraveyard());
        assertEquals(0, grave.numCardsGraveyard());

    }

    @Test
    void containsCard() {
        for(int i  = 0; i < 50; i++) {
            String cardname = "Sample card " + i;
            Card card = new Card(cardname, "Sample card", 10, new Attribute[]{a1, a2, a3});
            grave.addCard(card);
            assertTrue(grave.containsCard(card));
        }
        for(int i  = 49; i >= 0; i--){
            Card card = grave.removeCard(i);
            assertFalse(grave.containsCard(card));
        }


    }

    @Test
    void numCardsGraveyard() {
        String cardname = "Sample card one";
        grave.addCard(new Card(cardname, "Sample card", 10, new Attribute[]{a1, a2, a3}));
        assertNotEquals(0, grave.numCardsGraveyard());
        assertEquals(1, grave.numCardsGraveyard());

    }

    @Test
    void printGraveyard() {


        String res = null;
        PrintStream originalOut = System.out;

        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            grave.printGraveyard();
            capture.flush();
            res = os.toString();
        } finally {
            System.setOut(originalOut);
        }
        String expectedString = "<<< [GRAVEYARD IS EMPTY] >>>\n";
        assertEquals(expectedString, res);

        String cardname = "Sample card one";
        grave.addCard(new Card(cardname, "Sample card", 10, new Attribute[]{a1, a2, a3}));
        grave.addCard(new Card("sample card2", "Sample card", 10, new Attribute[]{a1, a2, a3}));
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream(100);
            PrintStream capture = new PrintStream(os);
            System.setOut(capture);
            grave.printGraveyard();
            capture.flush();
            res = os.toString();
        } finally {
            System.setOut(originalOut);
        }

        String expectedString2 = "[CARD GAME] Card Index on graveyard: 0\n" +
                "|-------------------------------|\n" +
                "| Name: Sample card one         |\n" +
                "| Power: 10                     |\n" +
                "| Resiliance: 0                 |\n" +
                "|                               |\n" +
                "| A1: attribute1                |\n" +
                "| A2: attribute2                |\n" +
                "| A3: attribute3                |\n" +
                "|-------------------------------|\n" +
                "[CARD GAME] Card Index on graveyard: 1\n" +
                "|-------------------------------|\n" +
                "| Name: sample card2            |\n" +
                "| Power: 10                     |\n" +
                "| Resiliance: 0                 |\n" +
                "|                               |\n" +
                "| A1: attribute1                |\n" +
                "| A2: attribute2                |\n" +
                "| A3: attribute3                |\n" +
                "|-------------------------------|\n";
        assertEquals(expectedString2, res);

    }
}