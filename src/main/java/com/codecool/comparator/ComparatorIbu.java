package com.codecool.comparator;

import com.codecool.cards.Card;

import java.util.Comparator;

public class ComparatorIbu implements Comparator<Card> {

    @Override
    public int compare(Card firstCard, Card secondCard) {
        if (firstCard.getIbu() > secondCard.getIbu()) {
            return 0;
        } else if (firstCard.getIbu() < secondCard.getIbu()) {
            return 1;
        }
        return -1;
    }
}
