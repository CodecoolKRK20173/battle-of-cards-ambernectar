package com.codecool.app;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;

public class GameController {
    View view;

    public GameController(View view) {
        this.view = view;
    }

    void run(){
        Card card1 = new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager"));
        Card card2 = new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA"));
        Card card3 = new Card("Guiness",22, 820, 72, new BeerStyle("Stout", "Coffee"));
        view.clear();

    }
}
