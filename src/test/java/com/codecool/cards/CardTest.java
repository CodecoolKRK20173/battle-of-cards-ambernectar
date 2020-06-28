package com.codecool.cards;

import junit.framework.TestCase;

public class CardTest extends TestCase {

    public void testGetCardLineList() {
    }

    public void testCompareToGreater() {
        BeerStyle testStyle = new BeerStyle("TestStyle");
        Card testCard = new Card("TestBeer", 100, 200, 200, testStyle);
        Card opponentCard = new Card("OpponentCard", 120, 200, 200, testStyle);
        assertEquals(1, testCard.compareTo(opponentCard));
    }

    public  void testCompareToEqual() {
        BeerStyle testStyle = new BeerStyle("TestStyle");
        Card testCard = new Card("TestBeer", 100, 200, 200, testStyle);
        Card opponentCard = new Card("OpponentCard", 100, 200, 200, testStyle);
        assertEquals(0, testCard.compareTo(opponentCard));
    }

    public void testCompareToSmaller() {
        BeerStyle testStyle = new BeerStyle("TestStyle");
        Card testCard = new Card("TestBeer", 100, 200, 200, testStyle);
        Card opponentCard = new Card("OpponentCard", 80, 200, 200, testStyle);
        assertEquals(-1, testCard.compareTo(opponentCard));
    }

    public void testTestGetName() {
    }

    public void testGetIbu() {
    }

    public void testPriceToString() {
    }

    public void testPercentageToString() {
    }

    public void testGetPrice() {
    }

    public void testGetPercentage() {
    }

    public void testGetPrimaryStyle() {
    }

    public void testGetSecondaryStyle() {
    }
}