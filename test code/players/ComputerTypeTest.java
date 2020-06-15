package com.company.players;
/**
 * This is the test class for ComputerType
 * @Author Hao Li
 * Seattle University
 * cpsc5210 Software Testing and Debugging
 * This is free software release to the public domain
 */

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * The test below has been passed by ComputerType.java
 */
public class ComputerTypeTest {

    String desp1 = "This computer tries to play in a 'smart' way.";
    String desp2 = "This player will play randomly.";
    String desp3 = "This player will play in a tunnel vision manner.";

    @Test
    public void getFromString() {
        assertEquals(ComputerType.LOGICAL, ComputerType.getFromString("LOGICAL"));
        assertNotEquals(ComputerType.LOGICAL, ComputerType.getFromString("RANDOM"));
        assertNotEquals(ComputerType.RANDOM, ComputerType.getFromString("DUMB"));
        assertEquals(ComputerType.RANDOM, ComputerType.getFromString("RANDOM"));
    }

    @Test
    public void testGetFromStringErr(){
        try {
            ComputerType.getFromString("abc");
        } catch (Error e) {
            assertEquals("Invalid conversion from string abc to Difficulty enum!",e.getMessage());;
        }
    }

    @Test
    public void getDescription() {
        assertEquals(desp1, ComputerType.LOGICAL.getDescription());
        assertNotEquals(desp1, ComputerType.RANDOM.getDescription());
        assertNotEquals(desp2, ComputerType.DUMB.getDescription());
        assertEquals(desp3, ComputerType.DUMB.getDescription());
    }

    @Test
    public void getName() {
        assertNotEquals(ComputerType.LOGICAL.getName(), ComputerType.getFromString("DUMB").getName());
        assertEquals(ComputerType.LOGICAL.getName(), ComputerType.getFromString("LOGICAL").getName());
        assertNotEquals(ComputerType.RANDOM.getName(), ComputerType.getFromString("DUMB").getName());
    }

    @Test
    public void printDescription() {
        assertNotEquals(ComputerType.LOGICAL.getDescription(), ComputerType.getFromString("DUMB").getDescription());
        assertNotEquals(ComputerType.RANDOM.getDescription(), ComputerType.getFromString("LOGICAL").getDescription());
        assertEquals(ComputerType.RANDOM.getDescription(), ComputerType.getFromString("RANDOM").getDescription());
    }

    @Test
    public void getTypesAsStrings() {
        boolean matched = true;
        ArrayList<String> list = new ArrayList<>();
        String str1 = "logical";
        String str2 ="random";
        String str3 = "dumb";
        list.add(str1);
        list.add(str1.toUpperCase());
        list.add(str2);
        list.add(str2.toUpperCase());
        list.add(str3);
        list.add(str3.toUpperCase());

        String[] res = ComputerType.getTypesAsStrings();
        for(String each: res){
            if(!list.contains(each))
                matched = false;
        }

        assertTrue(matched);


    }
}