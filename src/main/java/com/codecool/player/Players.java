package com.codecool.player;

import com.codecool.app.ComparisonOption;
import com.codecool.cards.Card;
import com.codecool.comparator.ComparatorIbu;
import com.codecool.comparator.ComparatorPercentage;
import com.codecool.comparator.ComparatorPrice;
import com.codecool.dao.BeerDAO;
import com.codecool.dao.BeerDAOCsv;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Players {
    private List<Player> playersList;
    private int currentPlayer;
    private List<Card> allCards;
    private String databaseName;
    private List<Card> drawCardStack;

    public Players(String databaseName) throws FileNotFoundException {
        this.playersList = new ArrayList<>();
        this.currentPlayer = 0;
        this.databaseName = databaseName;
        BeerDAO dao = new BeerDAOCsv();
        this.allCards = dao.loadDatabase(databaseName);
        this.drawCardStack = new ArrayList<>();
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
        List<Integer> winner = new ArrayList<>();
        switch (ch) {
            case IBU:
                winner = findWinner(new ComparatorIbu());
                break;
            case PRICE:
                winner = findWinner(new ComparatorPrice());
                break;
            case PERCENTAGE:
                winner = findWinner(new ComparatorPercentage());
                break;
        }
        // One winner
        if (winner.size() == 1){
            moveCards(winner.get(0));

        // Multiple winners
        } else if (winner.size() > 1){
            for (Player player : playersList) {
                drawCardStack.add(player.getTopCard());
                player.getCardList().remove(0);
            }
        }
        iteratePlayers();
    }

    private List<Integer> findWinner(Comparator<Card> comparator) {
        List<Integer> winner = new ArrayList<>();
        winner.add(0);
        for (int i = 1; i < playersList.size(); i++) {
            int result = comparator.compare(getTopCardOfPlayer(winner.get(0)),
                                    getTopCardOfPlayer(i));
            switch (result) {
                case 1:
                    winner.clear();
                case -1:
                    winner.add(i);
                    break;
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
        for (Card card : drawCardStack) {
            playersList.get(winner).addCard(card);
        }
        drawCardStack.clear();
    }

    public List<Player> getPlayersList(){
        return playersList;
    }

    private Card getTopCardOfPlayer (int playerNumber){
        return playersList.get(playerNumber).getCardList().get(0);
    }

    public Card getCurrentCard(){
        return getTopCardOfPlayer(currentPlayer);
    }

    public String getNameOfPlayer (int playerNumber){
        return playersList.get(playerNumber).getOwnerName();
    }

    public String getCurrentPlayerName(){
        return getNameOfPlayer(currentPlayer);
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

    public List<Card> getAllCards() {
        return allCards;
    }

    public int getNumberOfDrawnCards(){
        return drawCardStack.size();
        }
}
