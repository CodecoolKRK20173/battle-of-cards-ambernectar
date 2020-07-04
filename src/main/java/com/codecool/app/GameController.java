package com.codecool.app;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import com.codecool.dao.DAO;
import com.codecool.dao.DAOCsv;
import com.codecool.player.Player;
import com.codecool.player.Players;

import java.io.FileNotFoundException;
import java.util.*;
import java.util.Scanner;

public class GameController {
    View view;
    Players players;
    LocalUtil util;
    Scanner scan;
    private Random randomGenerator;
    String databaseName;

    public GameController(String databaseName) throws FileNotFoundException {
        this.view = new View();
        this.databaseName = databaseName;
        this.players = new Players(databaseName);
        this.util = new LocalUtil();
        scan = new Scanner(System.in);
        randomGenerator = new Random();
    }

    void run(){
        setupGame();
        players.setupStyleRates();
        while (!players.isGameOver()){
            view.displayGameScreen(players);
            ComparisonOption comparison = ComparisonOption.getOption(scan.nextLine());
            if (comparison != ComparisonOption.NONE){
                players.playRound(comparison);

                // Animation
                view.displayAnimation(players.getPlayingCards(comparison));
            }
        }
        view.displayEndScreen(players);
        scan.nextLine();
    }

    private void setupGame(){
        view.printMessage("MENU:");
        List<String> optionsList = new ArrayList<String>();
        optionsList.add("Play 2 players game.");
        optionsList.add("Play 3 players game.");
        optionsList.add("Play 4 players game.");
        int playerChoice = util.getChoice(optionsList, "Chose game mode");
//        int playerChoice = 0;

        if (playerChoice == 0) {
            setupGameNoOfPlayers(2);
        }   else if (playerChoice == 1) {
            setupGameNoOfPlayers(3);
        } else if (playerChoice == 2) {
            setupGameNoOfPlayers(4);
        }
    }

    private void setupGameNoOfPlayers(int playersQty) {
        DAO dao = new DAOCsv();

        view.clear();

        for (int i = 0; i < playersQty; i++){
            StringBuilder playerNameBuider = new StringBuilder();
            playerNameBuider.append("Player");
            playerNameBuider.append(i);
            players.addPlayer(new Player(playerNameBuider.toString()));
        }
//        players.addPlayer(new Player("Player1"));
//        players.addPlayer(new Player("Player2"));

        try {
            players.setAllCards(dao.loadDatabase(databaseName));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        List<Card> allCardsCopy = util.deepCopyAList(players.getAllCards());
        divideCardStack(players, allCardsCopy);
    }

    private void divideCardStack(Players players, List<Card> allCards) {
        discardExcessCards(allCards, players);
        int counter = 0;
        int playerId;
        while (allCards.size() > 0) {
            Card nextCard = getRandomCard(allCards);
            playerId = counter % players.getPlayersList().size();
            players.addCard(nextCard, playerId);
            counter++;
        }
    }

    private void discardExcessCards (List<Card> cardList, Players players) {
        int qtyCardsToDiscard = cardList.size()% players.getPlayersList().size();
        for (int index = 0; index < qtyCardsToDiscard; index++) {
            Card removedCard = getRandomCard(cardList);
        }
    }

    private Card getRandomCard (List<Card> cardList) {
        //Return card from list, remove said card from list

        int index = randomGenerator.nextInt(cardList.size());
        Card pickedCard = cardList.get(index);
        cardList.remove(pickedCard);

        return pickedCard;
    }

    private ComparisonOption getCompareChoice() {
        String key = scan.nextLine();
        return ComparisonOption.getOption(key);
    }
}
