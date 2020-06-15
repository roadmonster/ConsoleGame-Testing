package com.company.cards;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeckFactionTest {

    DeckFaction elf = DeckFaction.getFromString("ELVES");
    DeckFaction pirates = DeckFaction.getFromString("PIRATES");
    DeckFaction kd = DeckFaction.getFromString("KINGDOM");
    String d1 = "A group of forest elves that advocate peace above all else.\n - Strong Resiliance\n - Weak power\n - Average Infliction";
    String d2 =  "Ahoy! No, not that kind of pirates. These guys are more of the robin hood kind of group... except they have ships.. and curved swords.\n - Weak Resiliance\n - Strong Power\n - Average Infliction";
    String d3 = "Knights, sourcerers, kings and queens.. what you expect from any fantasy kingdom.\n - Average Resiliance\n - Weak Power\n - Average Infliction";

    @Test
    void getName() {
        assertEquals("Elves", elf.getName());
        assertEquals("Pirates", pirates.getName());
        assertEquals("Kingdom", kd.getName());
    }

    @Test
    void getDescription() {
        assertEquals(d1, elf.getDescription());
        assertEquals(d2, pirates.getDescription());
        assertEquals(d3, kd.getDescription());
    }

    @Test
    void getFromString() {
        assertNotEquals(elf, pirates);
        assertNotEquals(elf, kd);
        assertNotEquals(kd, pirates);
    }
}