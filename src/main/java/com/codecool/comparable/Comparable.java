package com.codecool.comparable;

import com.codecool.cards.Card;

import java.util.List;

public interface Comparable {
    //return card list, winner on first field of list and so on
    public List<Card> compare(List<Card> cardsList);
}
