package com.codecool.player;

import com.codecool.app.ComparisonOption;
import com.codecool.cards.Card;
import com.codecool.comparator.ComparatorIbu;
import com.codecool.comparator.ComparatorPercentage;
import com.codecool.comparator.ComparatorPrice;
import com.codecool.dao.DAO;
import com.codecool.dao.DAOCsv;

import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import com.codecool.comparator.ComparatorStyle;

import java.util.*;

public class Players {
    private List<Player> playersList;
    private int currentPlayer;
    private List<Card> allCards;
    private String databaseName;
    private List<Card> drawCardStack;
    private Map<String, Integer> styleRates;

    public Players(String databaseName){
        this.playersList = new ArrayList<>();
        this.currentPlayer = 0;
        this.databaseName = databaseName;
        DAO dao = new DAOCsv();
        try {
            this.allCards = dao.loadDatabase(databaseName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        this.drawCardStack = new ArrayList<>();
        this.styleRates = new HashMap<>();
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
            case STYLE:
                winner = findWinner(new ComparatorStyle(this.styleRates));
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

    public void setAllCards(List<Card> allCards) {
        this.allCards = allCards;
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

    public int getNumberOfDrawnCards(){
        return drawCardStack.size();
    }

    public List<Card> getAllCards() {
        return allCards;
    }

    public void setupStyleRates(){
        if (allCards != null){
            for (Card card : allCards) {
                incrementStyle(card.getPrimaryStyle());
                incrementStyle(card.getSecondaryStyle());
            }
        }
    }

    public double[] useCheat(){
        double [] percentageChances = {0,0,0,0};
        Card currentCard =  getCurrentCard();
        int denominator = 0;
        List<Card> tempCardList = new ArrayList<>();
        for (Player player : playersList) {
            if (this.playersList.get(currentPlayer) != player) {
                tempCardList = player.getCardList();
                denominator += tempCardList.size();
                for (Card card : tempCardList) {
                    int ibu = new ComparatorIbu().compare(card, currentCard);
                    int percentage = new ComparatorPercentage().compare(card, currentCard);
                    int price = new ComparatorPrice().compare(card, currentCard);
                    if(ibu<0) ibu = 0;
                    if(percentage<0) percentage=0;
                    if(price<0) price = 0;
                    percentageChances[0] += ibu;
                    percentageChances[1] += percentage;
                    percentageChances[2] += price;
                }

            }
        }
        percentageChances[3] = denominator;
        return percentageChances;


    }

    private void incrementStyle(String key){
        styleRates.putIfAbsent(key, 1);
        styleRates.put(key, styleRates.get(key) + 1);
    }
}
