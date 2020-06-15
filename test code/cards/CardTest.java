package com.company.cards;

import com.company.players.Human;
import com.company.players.Player;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class CardTest {
    Attribute a1 = new Attribute("attribute1", null, 0, 0, false, 0, null),
            a2 = new Attribute("attribute2", null, 0, 0, false, 0, null),
            a3 = new Attribute("attribute3", null, 0, 0, false, 0, null);
    Card test1;
    Card test2;
    Player p1;
    Player p2;

    @BeforeEach
    public void setUp() throws Exception {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
    }

    @Test
    public void testCard() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertNotNull(test1);
    }

    @Test
    public void testGetName() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals("CardName1", test1.getName());
        assertNotEquals("CardName1", test2.getName());
    }

    @Test
    public void testGetPower() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(10, test1.getPower());
        assertNotEquals(10, test2.getPower());
    }

    @Test
    public void testGetDescription() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals("This is test 1 description", test1.getDescription());
        assertNotEquals("This is test 1 description", test2.getDescription());
    }

    @Test
    public void testSetPower() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(10, test1.getPower());
        test1.setPower(20);
        assertEquals(20, test1.getPower());

    }

    @Test
    public void testGetResiliance() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(0, test1.getResiliance());
        assertEquals(0, test2.getResiliance());
    }

    @Test
    public void testSetResiliance() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(0, test1.getResiliance());
        test1.setResiliance(100);
        assertEquals(100, test1.getResiliance());
    }

    @Test
    public void testGetAttributes() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals("attribute1", test1.getAttributes()[0].getName());
        assertEquals("attribute2", test1.getAttributes()[1].getName());
        assertEquals("attribute3", test1.getAttributes()[2].getName());
    }

    @Test
    public void testGetActiveAttribute() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(null, test1.getActiveAttribute());
        assertEquals(null, test2.getActiveAttribute());
    }

    @Test
    public void testSetActiveAttribute() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(null, test1.getActiveAttribute());
        test1.setActiveAttribute(a1);
        assertEquals(a1.getName(), test1.getActiveAttribute().getName());
        test1.setActiveAttribute(a2);
        assertEquals(a2.getName(), test1.getActiveAttribute().getName());
    }

    @Test
    public void testGetOwner() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(null, test1.getOwner());
        assertNotEquals(p2, test2.getOwner());
    }

    @Test
    public void testSetOwner() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(null, test1.getOwner());
        test1.setOwner(p1);
        assertEquals(p1, test1.getOwner());
    }

    @Test
    public void testInGraveyard() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(false,test1.inGraveyard());
        assertEquals(false,test2.inGraveyard());
    }

    @Test
    public void testSetInGraveyard() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(false,test1.inGraveyard());
        test1.setInGraveyard(true);
        assertEquals(true, test1.inGraveyard());
    }

    @Test
    public void testGetBoardId() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(-1, test1.getBoardId());
        assertEquals(-1, test2.getBoardId());
    }

    @Test
    public void testSetBoardId() {
        test1 = new Card("CardName1", "This is test 1 description", 10, new Attribute[] {a1,a2,a3});
        test2 = new Card("CardName2", "This is test 2 descrpition", 0, new Attribute[] {a1,a2,a3});
        p1 = new Human(1, "player 1");
        p2 = new Human(1, "player 2");
        assertEquals(-1, test1.getBoardId());
        test1.setBoardId(99);
        assertEquals(99, test1.getBoardId());
    }

}