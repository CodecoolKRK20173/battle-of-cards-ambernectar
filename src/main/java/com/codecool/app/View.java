package com.codecool.app;

import com.codecool.cards.Card;

public class View {
    private final static int CARD_WIDTH = 20;

    void printCard(Card card, int x, int y){
        System.out.print("\033[<" + y + ">;<" + x + ">H");
        System.out.print("/--------------\\");
        System.out.print("\033[<" + y+1 + ">;<" + x + ">H");
        System.out.print(getCardLineString(card.getName()));
        System.out.print("\033[<" + y+2 + ">;<" + x + ">H");
        System.out.print(getCardLineString());
        System.out.print("\033[<" + y+3 + ">;<" + x + ">H");
        System.out.print(getCardLineString(card.getPrimaryStyle()));
        System.out.print("\033[<" + y+4 + ">;<" + x + ">H");
        System.out.print(getCardLineString(card.getSecondaryStyle()));
        System.out.print("\033[<" + y+5 + ">;<" + x + ">H");
        System.out.print(getCardLineString(card.getIbu()));
        System.out.print("\033[<" + y+6 + ">;<" + x + ">H");
        System.out.print(getCardLineString(card.getPercentage()));
        System.out.print("\033[<" + y+7 + ">;<" + x + ">H");
        System.out.print(getCardLineString(card.getPrice()));
        System.out.print("\033[<" + y+8 + ">;<" + x + ">H");
        System.out.print("\\ -------------/");
    }

    void clear(){
        System.out.print("\033[2J");
    }

    String getCardLineString(String text){
        int leftIndent = Math.floorDiv(CARD_WIDTH - text.length(), 2);
        int rightIndent = Math.floorDiv(CARD_WIDTH - text.length(), 2) +
                Math.floorMod(CARD_WIDTH - text.length(), 2);
        String value = String.format("%-"+ leftIndent + "s", text);
        String rightSide = String.format("%-"+ rightIndent + "s", "|");
        String line = String.join("", "|", value, rightSide);
        return line;
    }

    String getCardLineString(int value){
        return getCardLineString(String.valueOf(value));
    }

    String getCardLineString(){
        return String.format("%-"+ CARD_WIDTH + "s", "");
    }
}
