/**
 * Test class for PresetDeck.java
 * @Author Hao Li
 * CPSC 5210 Seattle University
 * This is a free software released to the public
 */
package com.company.utility;
import com.company.cards.Card;
import com.company.cards.Deck;
import com.company.cards.DeckFaction;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * PresetDecks.java has passed all the tests below
 */
public class PresetDecksTest {

    String[] elevesNames = {"Dol Blathanna Protector", "Elf Infantry", "Elf Commander", "Elf King", "Elf Queen"};
    String[] pirateNames ={"Sailor", "Quarter Master", "Captain", "Black Beard", "Pirate King"};
    String[] kingdomNames = {"Peasent", "Assasin", "Knight", "Commander", "Prince"};

    int testSize = 5;
    int positiveCount = 0;
    int negativeCount = 0;
    ArrayList<Integer> cardPowerList = new ArrayList<>();

    /**
     *
     */
    @Test
    public void loadElvenDeck() {
        Deck deck_elf = new Deck( DeckFaction.ELVES.getName(), testSize );
        deck_elf = PresetDecks.loadElvenDeck(deck_elf);
        assertNull(deck_elf.getOwner());
        assertEquals(deck_elf.getCardsLeft(), 5);

        Card deqCard = null;
        while(deck_elf.getCardsLeft() >0){
            deqCard = deck_elf.removeCard();
            for(String cardName: elevesNames){
                if(cardName == deqCard.getName()){
                    positiveCount++;
                    cardPowerList.add(deqCard.getPower());
                }
            }
        }
        assertEquals(testSize, positiveCount);

        deck_elf = PresetDecks.loadElvenDeck(deck_elf);
        positiveCount = 0;
        while(deck_elf.getCardsLeft() >0){
            deqCard = deck_elf.removeCard();
            for(String cardName: pirateNames){
                if(cardName == deqCard.getName()){
                    negativeCount++;
                }
            }
            if(cardPowerList.contains(deqCard.getPower()))
                positiveCount++;
        }

        assertEquals(0,negativeCount);
        assertEquals(testSize, positiveCount);
    }
    /**
     *
     */
    @Test
    public void loadPirateDeck() {
        Deck deck_pirate = new Deck(DeckFaction.PIRATES.getName(), 5);
        deck_pirate = PresetDecks.loadPirateDeck(deck_pirate);

        assertNull(deck_pirate.getOwner());
        assertEquals(deck_pirate.getCardsLeft(), 5);

        cardPowerList.clear();
        positiveCount = 0;
        Card deqCard = null;
        while(deck_pirate.getCardsLeft() >0){
            deqCard = deck_pirate.removeCard();
            for(String cardName: pirateNames){
                if(cardName == deqCard.getName()){
                    positiveCount++;
                    cardPowerList.add(deqCard.getPower());
                }
            }
        }
        assertEquals(testSize, positiveCount);

        positiveCount = 0;
        deck_pirate = PresetDecks.loadPirateDeck(deck_pirate);
        while(deck_pirate.getCardsLeft() >0){
            deqCard = deck_pirate.removeCard();
            for(String cardName: elevesNames){
                if(cardName == deqCard.getName()){
                    negativeCount++;
                }
            }
            if(cardPowerList.contains(deqCard.getPower()))
                positiveCount++;
        }
        assertEquals(0,negativeCount);
        assertEquals(testSize, positiveCount);



    }

    @Test
    public void loadKingdomDeck() {
        Deck deck_kingdom = new Deck(DeckFaction.KINGDOM.getName(), 5);
        deck_kingdom = PresetDecks.loadKingdomDeck(deck_kingdom);

        assertNull(deck_kingdom.getOwner());
        assertEquals(deck_kingdom.getCardsLeft(), 5);

        cardPowerList.clear();
        positiveCount = 0;
        Card deqCard = null;
        while(deck_kingdom.getCardsLeft() >0){
            deqCard = deck_kingdom.removeCard();
            for(String cardName: kingdomNames){
                if(cardName == deqCard.getName()){
                    positiveCount++;
                    cardPowerList.add(deqCard.getPower());
                }
            }
        }
        assertEquals(testSize, positiveCount);

        positiveCount = 0;

        deck_kingdom = PresetDecks.loadKingdomDeck(deck_kingdom);
        while(deck_kingdom.getCardsLeft() >0){
            deqCard = deck_kingdom.removeCard();
            for(String cardName: pirateNames){
                if(cardName == deqCard.getName()){
                    negativeCount++;
                }
            }
            if(cardPowerList.contains(deqCard.getPower()))
                positiveCount++;
        }
        assertEquals(0,negativeCount);
        assertEquals(testSize, positiveCount);
    }
}