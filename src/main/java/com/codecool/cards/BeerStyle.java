package com.codecool.cards;

public class BeerStyle {
    private String primaryStyle;
    private String secondaryStyle;

    public BeerStyle(String primaryStyle, String secondaryStyle) {
        this.primaryStyle = primaryStyle;
        this.secondaryStyle = secondaryStyle;
    }

    public BeerStyle(String primaryStyle){
        this(primaryStyle, "Generic");
    }

    public String getPrimaryStyle() {
        return primaryStyle;
    }

    public String getSecondaryStyle() {
        return secondaryStyle;
    }
}
