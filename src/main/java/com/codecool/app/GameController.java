package com.codecool.app;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import com.codecool.cards.Hand;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class GameController {
    View view;
    List<Hand> players;
    int currentPlayer;
    Scanner scan;

    public GameController() {
        this.view = new View();
        this.players = new ArrayList<>();
        this.currentPlayer = 0;
        scan = new Scanner(System.in);
    }

    void run(){
        setupGame();
        while (!isGameOver()){
            view.printCard(getCurrentCard(), 0, 1);
            scan.nextLine();
            iteratePlayers();
        }
    }

    private void iteratePlayers() {
        if (currentPlayer < players.size() - 1){
            currentPlayer++;
        }else currentPlayer = 0;
    }

    private void setupGame(){
        view.clear();

        players.add(new Hand("Player1"));
        players.add(new Hand("Player2"));
        players.add(new Hand("Player3"));

        players.get(0).addCard(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")));
        players.get(1).addCard(new Card("Druga Pomoc",10, 510, 40, new BeerStyle("Lager")));
        players.get(2).addCard(new Card("Trzecia Pomoc",13, 500, 41, new BeerStyle("Lager")));

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
}
