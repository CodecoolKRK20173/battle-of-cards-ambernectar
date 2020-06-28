package com.codecool.cards;

import java.lang.Comparable;
import java.util.ArrayList;
import java.util.List;

public class Card implements Comparable<Card>{
    private final String name;
    private final Integer ibu;
    private final int price;
    private final int percentage;
    private final BeerStyle style;

    private String activeCriteria;


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

//    private String getValue (String criteriaName) {
//        criteriaName.toLowerCase();
//        if (criteriaName == "name") {
//            return getName();
//        } else {
//            // TODO throw some exception ?
//        }
//    }

    private int getValue (String criteriaName) {
        criteriaName.toLowerCase();
        if (criteriaName == "ibu") {
            return ibu;
        } else if (criteriaName == "price") {
            return price;
        } else if (criteriaName == "percentage") {
            return percentage;
        }
        //in case name not found
        return 0;
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
        this.cardLineList[6] = getCardLineString("vol. " + percentageToString());
        this.cardLineList[7] = getCardLineString("Price: " + priceToString());

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
    public int compareTo(Card opponentCard) {
        if (ibu == opponentCard.getIbu()) {
            return 0;
        } else if (ibu > getIbu()) {
            return 1;
        }
        return 0;
    }

    public String getName() {
        return name;
    }

    public int getIbu() {
        return ibu;
    }

    public String priceToString(){
        return String.join("",
                String.valueOf(Math.floorDiv(getPrice(), 100)),
                          ",",
                          String.valueOf(Math.floorMod(getPrice(), 100)));
    }

    public String percentageToString(){
        return String.join("",
                String.valueOf(Math.floorDiv(getPercentage(), 10)),
                ",",
                String.valueOf(Math.floorMod(getPercentage(), 10)),
                "%");
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
