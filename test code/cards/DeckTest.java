package com.company.cards;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.company.players.Player;
import com.company.utility.PresetDecks;

import java.util.Iterator;
import java.util.NoSuchElementException;

import static com.company.cards.DeckFaction.*;
import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Attribute a1 = new Attribute("attribute1", null, 0, 0, false, 0, null),
            a2 = new Attribute("attribute2", null, 0, 0, false, 0, null),
            a3 = new Attribute("attribute3", null, 0, 0, false, 0, null);
    Card test1;
    Card test2;
    Player p1;
    Player p2;
    Deck deck1;
    Deck deck2;

    @BeforeEach
    void setUp() {
        test1 = new Card("CardName1", "This is test 1 description", 1, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 4, new Attribute[] {a1,a2,a3});
        deck1 = new Deck("DeckName1", 5);
        deck2 = new Deck("DeckName2", 5);
        deck1.addCard(test1);
        deck1.addCard(test2);
        deck2.addCard(test1);
        deck2.addCard(test2);
    }

    @Test
    void removeCard() {
        assertEquals(2, deck1.getCardsLeft());
        deck1.removeCard();
        assertEquals(1, deck1.getCardsLeft());
    }

    @Test
    void removeCardExceptionDeckIsEmpty() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            deck1.removeCard();
            deck1.removeCard();
            deck1.removeCard();
        });

        String expectedMessage = "Queue for deck is empty!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void addCard() {
        assertEquals(2, deck1.getCardsLeft());
        deck1.addCard(test1);
        assertEquals(3, deck1.getCardsLeft());
    }

    @Test
    void addCardExceptionDeckIsFull() {
        Exception exception = assertThrows(IllegalStateException.class, () -> {
            deck1.addCard(test1);
            deck1.addCard(test2);
            deck1.addCard(test1);
            deck1.addCard(test2);
        });

        String expectedMessage = "Queue for deck is full!";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    void getCardsLeft() {
        int expected = 2;
        assertEquals(expected, deck1.getCardsLeft());

        expected++;
        deck1.addCard(test1);
        assertEquals(expected, deck1.getCardsLeft());

        expected -= 2;
        deck1.removeCard();
        deck1.removeCard();
        assertEquals(expected, deck1.getCardsLeft());
    }

    @Test
    void getOwner() {
        deck1.setOwner(p1);
        assertEquals(p1, deck1.getOwner());
    }

    @Test
    void setOwner() {
        assertEquals(null, deck1.getOwner());
        deck1.setOwner(p1);
        assertEquals(p1, deck1.getOwner());
    }

    @Test
    void shuffle() {
        // Chances of this test failing are low, but still possible due to random nature of shuffling
        Card test3 = new Card("CardName3", "This is test 3 descrpition", 8, new Attribute[] {a1,a2,a3});
        Card test4 = new Card("CardName4", "This is test 4 descrpition", 12, new Attribute[] {a1,a2,a3});
        Card test5 = new Card("CardName5", "This is test 5 descrpition", 16, new Attribute[] {a1,a2,a3});
        deck1.addCard(test3);
        deck2.addCard(test3);
        deck1.addCard(test4);
        deck2.addCard(test4);
        deck1.addCard(test5);
        deck2.addCard(test5);
        deck1.shuffle();
        deck2.shuffle();
        String deck1Pulls = "";
        while(deck1.getCardsLeft() > 0) {
            deck1Pulls += Integer.toString(deck1.removeCard().getPower());
        }
        String deck2Pulls = "";
        while(deck2.getCardsLeft() > 0) {
            deck2Pulls += Integer.toString(deck2.removeCard().getPower());
        }
        assertNotEquals(deck1Pulls, deck2Pulls);
    }

    @Test
    void loadPresetElvenDeck() {
        deck1 = Deck.loadPresetDeck(ELVES);
        Deck elvenDeck = new Deck("Elven Deck", 99);
        PresetDecks.loadElvenDeck(elvenDeck);
        assertEquals(elvenDeck.removeCard().getName(), deck1.removeCard().getName());
    }
    @Test
    void loadPresetPirateDeck() {
        deck1 = Deck.loadPresetDeck(PIRATES);
        Deck pirateDeck = new Deck("Pirate Deck", 99);
        PresetDecks.loadPirateDeck(pirateDeck);
        assertEquals(pirateDeck.removeCard().getName(), deck1.removeCard().getName());
    }
    @Test
    void loadPresetKingdomDeck() {
        deck1 = Deck.loadPresetDeck(KINGDOM);
        Deck kingdomDeck = new Deck("Kingdom Deck", 99);
        PresetDecks.loadKingdomDeck(kingdomDeck);
        assertEquals(kingdomDeck.removeCard().getName(), deck1.removeCard().getName());
    }

    @Test
    void iteratorNextExceptionNoMoreCards() {
        Iterator<Card> deck1Iterator = deck1.iterator();
        assertEquals(false, deck1Iterator.hasNext());
        deck1.addCard(test1);
        Exception exception = assertThrows(NoSuchElementException.class, () -> {
            deck1Iterator.next();
        });
    }
}