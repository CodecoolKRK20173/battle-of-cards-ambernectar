package com.codecool.cards;

import java.lang.Comparable;

public class Card implements Comparable<Card>{
    private final String name;
    private final Integer ibu;
    private final int price;
    private final int percentage;
    private final BeerStyle style;

    public String[] getCardLineList() {
        return cardLineList;
    }

    private String[] cardLineList;
    private final static int CARD_WIDTH = 20;

    public Card(String name, int ibu, int price, int percentage, BeerStyle style) {
        this.name = name;
        this.ibu = ibu;
        this.price = price;
        this.percentage = percentage;
        this.style = style;
        createCardLines();
    }

    private void createCardLines() {
        this.cardLineList = new String[9];
        // Top line
        StringBuilder top = new StringBuilder();
        top.append("/");
        top.append(createDashLine(CARD_WIDTH));
        top.append("\\");
        this.cardLineList[0] = top.toString();

        this.cardLineList[1] = getCardLineString(getName());
        this.cardLineList[2] = getCardLineString(" ");
        this.cardLineList[3] = getCardLineString(getSecondaryStyle());
        this.cardLineList[4] = getCardLineString(getPrimaryStyle());
        this.cardLineList[5] = getCardLineString("IBU: " + getIbu());
        this.cardLineList[6] = getCardLineString("vol. " + getPercentage());
        this.cardLineList[7] = getCardLineString("Price: " + getPrice());

        // Bottom line
        StringBuilder bottom = new StringBuilder();
        bottom.append("\\");
        bottom.append(createDashLine(CARD_WIDTH));
        bottom.append("/");
        this.cardLineList[8] = bottom.toString();
    }

    private String getCardLineString(String text){
        StringBuilder line = new StringBuilder();
        int leftIndent = Math.floorDiv(CARD_WIDTH - text.length(), 2);
        int rightIndent = CARD_WIDTH - text.length() - leftIndent;
        line.append("|");
        for (int i = 0; i < leftIndent; i++) {
            line.append(" ");
        }
        line.append(text);
        for (int i = 0; i < rightIndent; i++) {
            line.append(" ");
        }
        line.append("|");
        return line.toString();
    }

    private String createDashLine(int length){
         StringBuilder line = new StringBuilder();
        for (int i = 0; i < length; i++) {
            line.append("-");
        }
        return line.toString();
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
