package com.codecool.comparator;

import com.codecool.cards.Card;

import java.util.Comparator;

public class ComparatorPrice implements Comparator<Card> {
    @Override
    public int compare(Card firstCard, Card secondCard) {
        if (firstCard.getPrice() < secondCard.getPrice()) {
            return 0;
        } else if (firstCard.getPrice() > secondCard.getPrice()) {
            return 1;
        }
        return -1;
    }
}
