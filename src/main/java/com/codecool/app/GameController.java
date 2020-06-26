package com.codecool.app;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;

public class GameController {
    View view;

    public GameController() {
        this.view = new View();
    }

    void run(){
        Card card1 = new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager"));
        Card card2 = new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA"));
        Card card3 = new Card("Guiness",22, 820, 72, new BeerStyle("Stout", "Coffee"));
        view.clear();
        view.printCardSimply(card1);
        view.printCardSimply(card2);
        view.printCard(card3, 40, 20);
    }
}
