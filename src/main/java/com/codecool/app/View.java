package com.codecool.app;

import com.codecool.cards.Card;
import com.codecool.cards.Hand;

public class View {

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
        for (String line : card.getCardLineList()) {
            System.out.print("\033[" + y++ + ";" + x + "H");
            System.out.print(line);
        }
        System.out.println();
    }
    void clear(){
        System.out.print("\033[H\033[2J");
    }

    void printLine(String text){ System.out.println(text); }

    void displayScreen(Hand currentPlayer){
        clear();
        printLine("   " + currentPlayer.getOwnerName());
        printCard(currentPlayer.getCardList().get(0), 0, 1);
    }
}
