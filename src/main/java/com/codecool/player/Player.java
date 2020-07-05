package com.codecool.player;

import com.codecool.cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Player {
    private List<Card> cardList;
    private String ownerName;

    public Player(String ownerName) {
        this.cardList = new ArrayList<Card>();
        this.ownerName = ownerName;
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

    public Card getTopCard(){
        return cardList.get(0);
    }

    public String getOption(Players players) {
        Scanner scan = new Scanner(System.in);
        String playerInput = scan.nextLine();
//        scan2.close();
        return playerInput;
    }
}
