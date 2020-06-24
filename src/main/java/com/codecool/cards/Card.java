package com.codecool.cards;

import java.lang.Comparable;

public class Card implements Comparable<Card>{
    int ibu;
    int price;
    int percentage;
    BeerStyle style;

    public Card(int ibu, int price, int percentage, BeerStyle style) {
        this.ibu = ibu;
        this.price = price;
        this.percentage = percentage;
        this.style = style;
    }

    @Override
    public int compareTo(Card opponent) {
        // TODO implement comparison
        return 0;
    }
}
