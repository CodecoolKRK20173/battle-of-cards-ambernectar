package com.codecool.cards;

public class BeerStyle {
    String primaryStyle;
    String secondaryStyle;

    public BeerStyle(String primaryStyle, String secondaryStyle) {
        this.primaryStyle = primaryStyle;
        this.secondaryStyle = secondaryStyle;
    }

    public BeerStyle(String primaryStyle){
        new BeerStyle(primaryStyle, "Generic");
    }
}
