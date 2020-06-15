package com.company.cards;

import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

public class AttributeTest {

    @BeforeEach
    public void setUp() throws Exception {
    }

    @Test
    public void testAttribute() {
        //fail("Not yet implemented");
        Attribute test = new Attribute(null, null, 0, 0, false, 0, null);
        assertNotNull(test);
    }

    @Test
    public void testGetName() {
        String testname = "ABC";
        Attribute test1 = new Attribute("ABC", null, 0, 0, false, 0, null);
        Attribute test2 = new Attribute("DEF", null, 0, 0, false, 0, null);
        assertEquals(testname, test1.getName());
        assertNotEquals(testname, test2.getName());
    }

    @Test
    public void testGetDescription() {
        String testDesc = "Description";
        Attribute test1 = new Attribute(null, "Description", 0, 0, false, 0, null);
        Attribute test2 = new Attribute(null, "Not Description", 0, 0, false, 0, null);
        assertEquals(testDesc, test1.getDescription());
        assertNotEquals(testDesc, test2.getDescription());
    }

    @Test
    public void testGetValue() {
        int testValue = 999;
        Attribute test1 = new Attribute(null, null, 999, 0, false, 0, null);
        Attribute test2 = new Attribute(null, null, -1, 0, false, 0, null);
        assertEquals(testValue, test1.getValue());
        assertNotEquals(testValue, test2.getValue());
    }

    @Test
    public void testGetType() {
        AttributeType testType = AttributeType.BUFF;
        Attribute test1 = new Attribute(null, null, 0, 0, false, 0, AttributeType.BUFF);
        Attribute test2 = new Attribute(null, null, 0, 0, false, 0, AttributeType.INFLICTING);
        assertEquals(testType, test1.getType());
        assertNotEquals(testType, test2.getType());
    }

    @Test
    public void testGetNumTargets() {
        int testNumTar = 999;
        Attribute test1 = new Attribute(null, null, 0, 999, false, 0, null);
        Attribute test2 = new Attribute(null, null, 0, 1, false, 0, null);
        assertEquals(testNumTar, test1.getNumTargets());
        assertNotEquals(testNumTar, test2.getNumTargets());
    }

    @Test
    public void testIsTimed() {
        boolean testIsTimed = true;
        Attribute test1 = new Attribute(null, null, 0, 0, true, 0, null);
        Attribute test2 = new Attribute(null, null, 0, 0, false, 0, null);
        assertEquals(testIsTimed, test1.isTimed());
        assertNotEquals(testIsTimed, test2.isTimed());
    }

    @Test
    public void testGetTurns() {
        int testTurns = 100;
        Attribute test1 = new Attribute(null, null, 0, 0, false, 100, null);
        Attribute test2 = new Attribute(null, null, 0, 0, false, 1, null);
        assertEquals(testTurns, test1.getTurns());
        assertNotEquals(testTurns, test2.getTurns());
    }

    @Test
    public void testActivate() {
        //fail("not implemented");

        //need to set attribute array to avoid throwing exception from card class
        //activator attributes
        Attribute actInflicting = new Attribute(null, null, 10, 0, false, 0, AttributeType.INFLICTING);
        Attribute actBuff = new Attribute(null, null, 777, 0, false, 0, AttributeType.BUFF);
        Attribute actResiliance = new Attribute(null, null, 100, 0, false, 0, AttributeType.RESILIANCE);
        Attribute[] activatorAttributes = new Attribute[3];
        activatorAttributes[0] = actInflicting;
        activatorAttributes[1] = actBuff;
        activatorAttributes[2] = actResiliance;

        //target attributes, set values null and 0 for test independence
        Attribute tarInflicting = new Attribute(null, null, 0, 0, false, 0, null);
        Attribute tarBuff = new Attribute(null, null, 0, 0, false, 0, null);
        Attribute tarResiliance = new Attribute(null, null, 0, 0, false, 0, null);
        Attribute[] tarAttributes = new Attribute[3];
        tarAttributes[0] = tarInflicting;
        tarAttributes[1] = tarBuff;
        tarAttributes[2] = tarResiliance;

        //set cards
        Card activator = new Card("activator", null, 0, activatorAttributes);
        Card target = new Card("target", null, 100, tarAttributes);

        //check Inflicting --> target power 100 - 10 damage = 90 target power remain
        activatorAttributes[0].activate(activator, target);
        assertEquals(90,target.getPower());

        //check Buff
        //now target power is 90, adding actBuff value 777 = 867
        activatorAttributes[1].activate(activator, target);
        assertEquals(867, target.getPower());

        //check resiliance
        //initially target card has 0 resiliance, so 0 + 100 = 100
        activatorAttributes[2].activate(activator, target);
        assertEquals(100, target.getResiliance());

        //test target with lower resiliance than activator inflicting
        //set target power back to 100 with resiliance 9 for easier testing
        target.setResiliance(9);
        target.setPower(100);
        activatorAttributes[0].activate(activator, target);
        assertEquals(99, target.getPower());

        //test target with lower resiliance than activator inflicting
        //set target power back to 100 with resiliance 9 for easier testing
        target.setResiliance(11);
        target.setPower(100);
        activatorAttributes[0].activate(activator, target);
        assertEquals(100, target.getPower());
        assertEquals(1, target.getResiliance());

    }

}