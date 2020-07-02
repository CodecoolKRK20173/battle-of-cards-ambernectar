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

    public void playRound(ComparisonOption ch) {
        int winner = -1;
        switch (ch) {
            case IBU:
                winner = findWinner(new ComparatorIbu());
            case PRICE:
                winner = findWinner(new ComparatorPrice());
            case PERCENTAGE:
                winner = findWinner(new ComparatorPercentage());
        }
        if (winner >= 0){
            moveCards(winner);
            iteratePlayers();
        }
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

    public List<Player> getPlayersList(){
        return playersList;
    }
    private Card getTopCardOfPlayer (int playerNumber){
        return playersList.get(playerNumber).getCardList().get(0);
    }

    public Card getCurrentCard(){
        return getTopCardOfPlayer(0);
    }

    public String getNameOfPlayer (int playerNumber){
        return playersList.get(playerNumber).getOwnerName();
    }

    public String getCurrentPlayerName(){
        return getNameOfPlayer(0);
    }

    public String getWinnerName(){
        int winner = 0;
        for (int i = 1; i < playersList.size(); i++) {
            if (numberOfPlayerCards(winner) < numberOfPlayerCards(i)){
                winner = i;
            }
        }
        return getNameOfPlayer(winner);
    }

    public int numberOfPlayerCards(int playerNumber){
        return playersList.get(playerNumber).getCardList().size();
    }
}
