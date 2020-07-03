package com.codecool.comparator;

import com.codecool.cards.Card;

import java.util.Comparator;
import java.util.Map;

public class ComparatorStyle implements Comparator<Card> {
    Map<String, Integer> styleRates;

    public ComparatorStyle(Map<String, Integer> styleRates) {
        this.styleRates = styleRates;
    }

    @Override
    public int compare(Card firstCard, Card secondCard) {
        if (getPrimStyleRating(firstCard) < getPrimStyleRating(secondCard)){
            return 0;
        }else if (getPrimStyleRating(firstCard) > getPrimStyleRating(secondCard)){
            return 1;
        }else if (getSecondStyleRating(firstCard) < getSecondStyleRating(secondCard)){
            return 0;
        }else if (getSecondStyleRating(firstCard) > getSecondStyleRating(secondCard)){
            return 1;
        }
        return -1;
    }

    private int getPrimStyleRating(Card card){
        return styleRates.get(card.getPrimaryStyle());
    }

    private int getSecondStyleRating(Card card){
        return styleRates.get(card.getSecondaryStyle());
    }
}
