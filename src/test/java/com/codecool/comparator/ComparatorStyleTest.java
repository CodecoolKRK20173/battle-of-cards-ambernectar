package com.codecool.comparator;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import org.junit.Test;

import static org.junit.Assert.*;

public class ComparatorStyleTest {

    @Test
    public void compare() {
        Card test1 = new Card("Pierwsza Pomoc",10, 520, 42, new BeerStyle("Lager"));
        Card test2 = new Card("Druga Pomoc",10, 510, 45, new BeerStyle("Lager"));
        // TODO
    }
}