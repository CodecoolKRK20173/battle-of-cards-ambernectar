package com.codecool.app;

import com.codecool.cards.Card;

public class AnimatedCard {
    private Card card;
    private int x;
    private int y;
    private String owner;
    private int place;

    public AnimatedCard(Card card, String owner) {
        this.card = card;
        this.owner = owner;
        this.x = 5;
        this.y = 3;
        this.place = 0;
    }

    public int getPlace() {
        return place;
    }

    public void setPlace(int place) {
        this.place = place;
    }

    public Card getCard() {
        return card;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getOwner() {
        return owner;
    }

    public void incrementPlace(){
        this.place++;
    }
}
