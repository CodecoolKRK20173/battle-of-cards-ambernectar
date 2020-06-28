package com.codecool.comparator;

import com.codecool.cards.Card;

import java.util.Comparator;

public class ComparatorStyle implements Comparator<Card> {
    @Override
    public int compare(Card firstCard, Card secondCard) {
        return firstCard.getPrimaryStyle().compareTo(secondCard.getPrimaryStyle());
    }
}
