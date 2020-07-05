package com.codecool.app;

import com.codecool.cards.Card;
import com.codecool.player.Player;
import com.codecool.player.Players;

import java.util.List;

public class View {
    private static final long ANIMATION_PERIOD = 15;
    private static final int CARD_INDENT = 6;
    private static final int CARD_INDENT_VERT = 13;

    void printMessage(String message) {
        System.out.println(message);
    }

    public void printOptions(List<String> optionsList, String message) {
        printMessage(message);
        int changeIndex = 2;
        try {
            for (int index = 0; index < optionsList.size(); index++) {
                printMessage("(" + (index+changeIndex) + ") " + optionsList.get(index));
            }
        }catch (IndexOutOfBoundsException e) {
//            TODO
        }
    }

    void printCard(Card card, int x, int y){
        for (String line : card.getCardLineList()) {
            System.out.print("\033[" + y++ + ";" + x + "H");
            System.out.print(line);
        }
        System.out.println();
    }

    void printCard(Card card, int x, int y, String owner){
        System.out.print("\033[" + y++ + ";" + x + "H");
        System.out.print(owner);
        printCard(card, x, y);
    }

    void clear(){
        System.out.print("\033[H\033[2J");
    }

    void printLine(String text){ System.out.println(text); }

    void displayGameScreen(Players players){
        clear();
        printLine("Current player: " + players.getCurrentPlayerName() + "\n");
        printCard(players.getCurrentCard(), 4, 3);
        printLine("a: IBU, s: Price, d: Percentage, f: Style, h: Cheats");
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


    void displayCheatScreen(Players players) {

        double[] values = players.useCheat();
        printLine("With your current card percentage chance of winning are \n " +
                "Ibu = " + Math.round(100 * values[0] / values[4])
                + "%   Percentage = " + Math.round(100 * values[1] / values[4]) +
                "%   Price = " + Math.round(100 * values[2] / values[4]) +
                "%  Style = " + Math.round(100 * values[3] / values[4]) + "%");
    }

    public void displayAnimation(List<AnimatedCard> playingCards){
        displayFrame(playingCards);
        for (AnimatedCard playingCard : playingCards) {
            moveHorizontally(playingCard, playingCards);
        }

        for (AnimatedCard playingCard : playingCards) {
            moveVertically(playingCard, playingCards);
        }
    }

    private void moveHorizontally(AnimatedCard card, List<AnimatedCard> playingCards) {
        int newX = card.getX() + card.getPlace() * (Card.getCardWidth() + CARD_INDENT);
        for (int i = card.getX(); i < newX; i++) {
            card.setX(i);
            displayFrame(playingCards);
            try {
                Thread.sleep(ANIMATION_PERIOD);
            } catch (InterruptedException e) {
                // TODO
                e.printStackTrace();
            }
        }
    }

    private void moveVertically(AnimatedCard card, List<AnimatedCard> playingCards) {
        int cardsOnOneSpot;
        do {
            // Check if occupied
            cardsOnOneSpot = 0;
            for (AnimatedCard playingCard : playingCards) {
                if (card.getX() == playingCard.getX() && card.getY() == playingCard.getY()){
                    cardsOnOneSpot++;
                }
            }

            if (cardsOnOneSpot > 1) {
                int newY = card.getY() + (CARD_INDENT_VERT);
                for (int i = card.getY(); i < newY; i++) {
                    card.setY(i);
                    displayFrame(playingCards);
                    try {
                        Thread.sleep(ANIMATION_PERIOD);
                    } catch (InterruptedException e) {
                        // TODO
                        e.printStackTrace();
                    }
                }
            }
        }while (cardsOnOneSpot > 1);
    }

    void displayFrame(List<AnimatedCard> playingCards){
        clear();
        for (AnimatedCard playingCard : playingCards) {
            printCard(playingCard.getCard(),
                    playingCard.getX(),
                    playingCard.getY(),
                    playingCard.getOwner());
        }

    }
}
