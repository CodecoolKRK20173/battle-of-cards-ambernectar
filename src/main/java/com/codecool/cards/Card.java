package com.codecool.cards;

import java.lang.Comparable;

public class Card implements Comparable<Card>{
    String name;
    int ibu;
    int price;
    int percentage;
    BeerStyle style;

    public Card(String name, int ibu, int price, int percentage, BeerStyle style) {
        this.name = name;
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
