package com.codecool.app;

import junit.framework.TestCase;

public class LocalUtilTest extends TestCase {

    public void testGetChoice() {
    }

    public void testValidateAsIntIntInput() {
        String inputValue = "13";
        LocalUtil util = new LocalUtil();
        assertEquals(util.validateAsInt(inputValue), 13);
    }

    public void testValidateAsIntStringInput() {
        String inputValue = "13qwd";
        LocalUtil util = new LocalUtil();
        assertEquals(util.validateAsInt(inputValue), 999);
    }
}