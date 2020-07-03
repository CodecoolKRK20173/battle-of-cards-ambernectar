package com.codecool.app;

import com.codecool.cards.Card;
import com.codecool.player.Player;
import com.codecool.player.Players;

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

    void displayGameScreen(Players players){
        clear();
        printLine("Current player: " + players.getCurrentPlayerName() + "\n");
        printCard(players.getCurrentCard(), 4, 3);
        printLine("a: IBU, s: Price, d: Percentage");
        for (Player player : players.getPlayersList()) {
            System.out.print(player.getOwnerName() + ": " +
                    player.getCardList().size() + "   ");
        }
        System.out.println();
        if (players.getNumberOfDrawnCards() > 0){
            System.out.println("Number of pooled cards: " + players.getNumberOfDrawnCards());
        }
        System.out.print("Your choice: ");
    }

    void displayEndScreen(Players players){
        clear();
        printLine("The winner is " + players.getWinnerName());
        for (Player player : players.getPlayersList()) {
            printLine(player.getOwnerName() + ": " + player.getCardList().size() + " cards");
        }
        printLine("Press enter to finish");
    }
}
