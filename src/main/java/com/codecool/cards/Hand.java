package com.codecool.cards;

import java.util.ArrayList;
import java.util.List;

public class Hand {
    private List<Card> cardList;
    private String ownerName;

    public Hand () {
//        this.cardList = cardlist;
//        this.ownerName = ownerName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public List<Card> getCardList() {
        return cardList;
    }

    public void addCard (Card nextCard) {
        cardList.add(nextCard);
    }

    // TODO no behavior when card not found or multiple cards, possibility of returning null!!
    public Card getCardByName(String wantedName) {
        Card resultCard = null;
        for (Card nextCard : cardList) {
            if (nextCard.getName().equals(wantedName)) {
                resultCard = nextCard;
            }
        }
        return resultCard;
    }
}
