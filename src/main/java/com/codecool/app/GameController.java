package com.codecool.app;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import com.codecool.cards.Hand;
import com.codecool.comparator.ComparatorIbu;
import com.codecool.comparator.ComparatorPercentage;
import com.codecool.comparator.ComparatorPrice;

import java.util.*;

public class GameController {
    View view;
    Util util;
    List<Hand> players;
    int currentPlayer;
    Scanner scan;

    public GameController() {
        this.view = new View();
        this.util = new Util();
        this.players = new ArrayList<>();
        this.currentPlayer = 0;
        scan = new Scanner(System.in);
    }

    void run(){
        setupGame2();
        while (!isGameOver()){
            view.clear();
            view.printCard(getCurrentCard(), 0, 1);
            int winner = playRound(scan.nextLine());
            moveCards(winner);
            scan.nextLine();
            iteratePlayers();
        }
    }

    private int playRound(String ch) {
        switch (ch) {
            case "a":
                return findWinner(new ComparatorIbu());
            case "s":
                return findWinner(new ComparatorPrice());
            case "d":
                return findWinner(new ComparatorPercentage());
        }
        return -1;
    }

    private int findWinner(Comparator<Card> comparator) {
        int winner = 0;
        for (int i = 1; i < players.size(); i++) {
            int result = comparator.compare(players.get(winner).getCardList().get(0),
                                            players.get(i).getCardList().get(0));
            switch (result) {
                case 1:
                    winner = i;
                // TODO implement draws somehow
            }
        }
        return winner;
    }

    private void iteratePlayers() {
        if (currentPlayer < players.size() - 1){
            currentPlayer++;
        }else currentPlayer = 0;
    }


    private void setupGame2(){
        view.printMessage("MENU:");
        List<String> optionsList = new ArrayList<String>();
        optionsList.add("Play 2 players game.");
        optionsList.add("Play 3 players game.");
        optionsList.add("Play 4 players game.");
//        int playerChoice = util.getChoice(optionsList, "Chose game mode");
        int playerChoice = 1;

        if (playerChoice == 0) {
            setupGame3Players();
        }   else if (playerChoice == 1) {
            setupGame3Players();
        } else if (playerChoice == 2) {
            setupGame3Players();
        }
    }



    private void setupGame3Players(){
//    private void setupGame(){
        view.clear();

        players.add(new Hand("Player1"));
        players.add(new Hand("Player2"));
        players.add(new Hand("Player3"));

        players.get(0).addCard(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")));
        players.get(1).addCard(new Card("Druga Pomoc",10, 510, 45, new BeerStyle("Lager")));
        players.get(2).addCard(new Card("Trzecia Pomoc",13, 500, 43, new BeerStyle("Lager")));

        players.get(0).addCard(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")));
        players.get(1).addCard(new Card("Żywe IPA",32, 700, 70, new BeerStyle("IPA")));
        players.get(2).addCard(new Card("Żywieckie IPA",29, 690, 60, new BeerStyle("IPA")));

        players.get(0).addCard(new Card("Guiness",32, 1000, 80, new BeerStyle("Stout", "Coffee")));
        players.get(1).addCard(new Card("Guines",30, 800, 75, new BeerStyle("Stout", "Coffee")));
        players.get(2).addCard(new Card("Ginuess",22, 820, 72, new BeerStyle("Stout", "Coffee")));
    }

    private boolean isGameOver(){
        for (Hand player : players) {
            if (player.getCardList().size() == 0){
                return true;
            }
        }
        return false;
    }

    private Card getCurrentCard(){
        return players.get(currentPlayer).getCardList().get(0);
    }

    private void moveCards(int winner) {
        List<Card> tempCardList = new ArrayList<>();
        for (Hand player : players) {
            tempCardList.add(player.getCardList().get(0));
            player.getCardList().remove(0);
        }
        for (Card card : tempCardList) {
            players.get(winner).getCardList().add(card);
        }
    }
}
