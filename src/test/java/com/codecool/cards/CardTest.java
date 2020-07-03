package com.codecool.cards;

import junit.framework.TestCase;

public class CardTest extends TestCase {

    public void testGetCardLineList() {
    // TODO
    }

    public void testCompareToGreater() {
        BeerStyle testStyle = new BeerStyle("TestStyle");
        Card testCard = new Card("TestBeer", 100, 200, 200, testStyle);
        Card opponentCard = new Card("OpponentCard", 120, 200, 200, testStyle);
        assertEquals( -1, testCard.compareTo(opponentCard));
    }

    public void testCompareToEqual() {
        BeerStyle testStyle = new BeerStyle("TestStyle");
        Card testCard = new Card("TestBeer", 100, 200, 200, testStyle);
        Card opponentCard = new Card("OpponentCard", 100, 200, 200, testStyle);
        assertEquals(0, testCard.compareTo(opponentCard));
    }

    public void testCompareToSmaller() {
        BeerStyle testStyle = new BeerStyle("TestStyle");
        Card testCard = new Card("TestBeer", 100, 200, 200, testStyle);
        Card opponentCard = new Card("OpponentCard", 80, 200, 200, testStyle);
        assertEquals(1, testCard.compareTo(opponentCard));
    }

    public void testPriceToString() {
        Card testCard = new Card("Pierwsza Pomoc",10, 520, 42, new BeerStyle("Lager"));
        assertEquals("5,20", testCard.priceToString());
    }

    public void testPercentageToString() {
        Card testCard = new Card("Pierwsza Pomoc",10, 520, 42, new BeerStyle("Lager"));
        assertEquals("4,2%", testCard.percentageToString());
    }
}