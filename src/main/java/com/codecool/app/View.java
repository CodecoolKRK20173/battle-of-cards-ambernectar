package com.codecool.app;

import com.codecool.cards.Card;

public class View {
    private final static int CARD_WIDTH = 20;

    void printCardSimply(Card card){
        System.out.println(card.getName());
        System.out.println("================");
        System.out.println(card.getSecondaryStyle());
        System.out.println(card.getPrimaryStyle());
        System.out.println("IBU: " + card.getIbu());
        System.out.println("vol. " + card.getPercentage());
        System.out.println("Price " + card.getPrice() + "\n");
    }

    void printCard(Card card, int x, int y){
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print("/--------------------\\");
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print(getCardLineString(card.getName()));
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print(getCardLineString());
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print(getCardLineString(card.getSecondaryStyle()));
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print(getCardLineString(card.getPrimaryStyle()));
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print(getCardLineString("IBU: " + card.getIbu()));
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print(getCardLineString("vol. " + card.getPercentage()));
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print(getCardLineString("Price: " + card.getPrice()));
        System.out.print("\033[" + y + ";" + x + "H");
        System.out.print("\\--------------------/\n");
    }

    void clear(){
        System.out.print("\033[H\033[2J");
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

    String getCardLineString(int value){
        return getCardLineString(String.valueOf(value));
    }

    String getCardLineString(){
        String space = String.format("%1$"+ CARD_WIDTH + "s", "");
        return String.join("", "|", space, "|");
    }
}
