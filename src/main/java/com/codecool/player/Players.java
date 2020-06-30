package com.codecool.player;

import com.codecool.cards.Card;
import com.codecool.comparator.ComparatorIbu;
import com.codecool.comparator.ComparatorPercentage;
import com.codecool.comparator.ComparatorPrice;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Players {
    private List<Player> playersList;
    private int currentPlayer;

    public Players() {
        this.playersList = new ArrayList<>();
        this.currentPlayer = 0;
    }

    public void iteratePlayers() {
        if (currentPlayer < playersList.size() - 1){
            currentPlayer++;
        }else currentPlayer = 0;
    }

    public void addPlayer(Player player){
        this.playersList.add(player);
    }

    public void addCard(Card card, int playerNumber){
        this.playersList.get(playerNumber).addCard(card);
    }

    public int playRound(String ch) {
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
        for (int i = 1; i < playersList.size(); i++) {
            int result = comparator.compare(getTopCardOfPlayer(winner),
                                    getTopCardOfPlayer(i));
            switch (result) {
                case 1:
                    winner = i;
                    // TODO implement draws somehow
            }
        }
        return winner;
    }

    private Card getTopCardOfPlayer (int playerNumber){
        return playersList.get(playerNumber).getCardList().get(0);
    }

    public boolean isGameOver(){
        for (Player player : playersList) {
            if (player.getCardList().size() == 0){
                return true;
            }
        }
        return false;
    }

    public void moveCards(int winner) {
        List<Card> tempCardList = new ArrayList<>();
        for (Player player : playersList) {
            tempCardList.add(player.getCardList().get(0));
            player.getCardList().remove(0);
        }
        for (Card card : tempCardList) {
            playersList.get(winner).addCard(card);
        }
    }

    public Card getCurrentCard(){
        return playersList.get(currentPlayer).getCardList().get(0);
    }
}
