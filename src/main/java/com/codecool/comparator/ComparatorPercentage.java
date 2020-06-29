package com.codecool.comparator;

import com.codecool.cards.Card;

import java.util.Comparator;

public class ComparatorPercentage implements Comparator<Card> {
    @Override
    public int compare(Card firstCard, Card secondCard) {
        if (firstCard.getPercentage() > secondCard.getPercentage()) {
            return 0;
        } else if (firstCard.getPercentage() < secondCard.getPercentage()) {
            return 1;
        }
        return -1;
    }

}
