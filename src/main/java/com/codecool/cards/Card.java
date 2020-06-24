package com.codecool.cards;

import java.lang.Comparable;

public class Card implements Comparable<Card>{
    private String name;
    private Integer ibu;
    private int price;
    private int percentage;
    private BeerStyle style;

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

    public String getName() {
        return name;
    }

    public int getIbu() {
        return ibu;
    }

    public int getPrice() {
        return price;
    }

    public int getPercentage() {
        return percentage;
    }

    public String getPrimaryStyle() {
        return this.style.getPrimaryStyle();
    }

    public String getSecondaryStyle() {
        return this.style.getSecondaryStyle();
    }
}
