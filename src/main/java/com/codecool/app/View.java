package com.codecool.app;

import com.codecool.cards.Card;

import java.util.List;

public class View {

    void printMessage(String message) {
        System.out.println(message);
    }

    public void printOptions(List<String> optionsList, String message) {
        printMessage(message);
        try {
            for (int index = 0; index < optionsList.size(); index++) {
                printMessage("(" + index + ") " + optionsList.get(index));
            }
        }catch (IndexOutOfBoundsException e) {
//            TODO
        }
    }

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

}
