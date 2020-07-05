package com.codecool.app;

import com.codecool.cards.Card;
import com.codecool.player.Player;
import com.codecool.player.Players;

import java.util.List;
import java.util.regex.Pattern;

public class View {
    private static final long ANIMATION_PERIOD = 15;
    private static final int CARD_INDENT = 6;
    private static final int CARD_INDENT_VERT = 13;

    void printMessage(String message) {
        System.out.println(message);
    }

    public void printOptions(List<String> optionsList, String message) {

        String logo ="\u001B[33m" + "  _   _   _   _   _     _   _   _   _   _   _     _   _   _   _   _   _  \n" +
                " / xx / xx / xx / xx / xx   / xx / xx / xx / xx / xx / xx   / xx / xx / xx / xx / xx / xx \n" +
                "( A | m | b | e | r ) ( N | e | c | t | a | r ) ( B | a | t | t | l | e )\n" +
                " xx_/ xx_/ xx_/ xx_/ xx_/   xx_/ xx_/ xx_/ xx_/ xx_/ xx_/   xx_/ xx_/ xx_/ xx_/ xx_/ xx_/\n " + "\u001B[0m" ;
        System.out.print(logo.replaceAll("xx","\\\\"));
        printMessage(message);
        int changeIndex = 2;
        try {
            for (int index = 0; index < optionsList.size(); index++) {
                printMessage("\033[4;33m" + "(" + (index+changeIndex) + ") " + optionsList.get(index) + "\u001B[0m");
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
        printLine("\033[0;33m" + "Current player: " + players.getCurrentPlayerName() + "\n" + "\033[0m");
        printCard(players.getCurrentCard(), 4, 3);
        printLine( "\033[0;32m" + "a: IBU, s: Price, d: Percentage, f: Style, h: Cheats\n" + "\033[0m");
        for (Player player : players.getPlayersList()) {
            System.out.print("\033[0;96m" + player.getOwnerName() + ": " +
                    player.getCardList().size() + "   " + "\033[0m");
        }
        System.out.println();
        if (players.getNumberOfDrawnCards() > 0){
            System.out.println("\033[0;96m" + "Number of pooled cards: " + players.getNumberOfDrawnCards() + "\033[0m");
        }
        System.out.print("\nYour choice: ");
    }

    void displayEndScreen(Players players){
        clear();
        printLine("\033[1;31m" + "The winner is " + players.getWinnerName() + "\033[0m" );
        for (Player player : players.getPlayersList()) {
            printLine("\033[1;31m" + player.getOwnerName() + ": " + player.getCardList().size() + " cards" + "\033[0m");
        }
        printLine("Press enter to finish");
    }


    void displayCheatScreen(Players players) {

        double[] values = players.useCheat();
        printLine("\n" + "\033[1;96m" + "With your current card percentage chance of winning are \n " +
                "Ibu = " + Math.round(100 * values[0] / values[4])
                + "%   Percentage = " + Math.round(100 * values[1] / values[4]) +
                "%   Price = " + Math.round(100 * values[2] / values[4]) +
                "%  Style = " + Math.round(100 * values[3] / values[4]) + "%" + "\033[0m" +
                "\n Press enter to close cheats information");
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
