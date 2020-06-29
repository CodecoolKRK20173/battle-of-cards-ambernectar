package com.codecool.app;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import com.codecool.cards.Hand;

import java.util.ArrayList;
import java.util.List;

public class GameController {
    View view;
    List<Hand> players;

    public GameController() {
        this.view = new View();
        this.players = new ArrayList<Hand>();
    }

    void run(){
        setupGame();
    }

    private void setupGame(){
        players.add(new Hand("Player1"));
        players.add(new Hand("Player2"));
        players.add(new Hand("Player3"));

        players.get(0).addCard(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")));
        players.get(1).addCard(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")));
        players.get(2).addCard(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")));

        players.get(0).addCard(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")));
        players.get(1).addCard(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")));
        players.get(2).addCard(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")));

        players.get(0).addCard(new Card("Guiness",22, 820, 72, new BeerStyle("Stout", "Coffee")));
        players.get(1).addCard(new Card("Guiness",22, 820, 72, new BeerStyle("Stout", "Coffee")));
        players.get(2).addCard(new Card("Guiness",22, 820, 72, new BeerStyle("Stout", "Coffee")));

    }
}
