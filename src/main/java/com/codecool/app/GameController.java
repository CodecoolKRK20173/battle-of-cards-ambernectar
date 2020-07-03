package com.codecool.app;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import com.codecool.dao.BeerDAO;
import com.codecool.dao.BeerDAOCsv;
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
        while (!players.isGameOver()){
            view.displayGameScreen(players);
            ComparisonOption comparison = ComparisonOption.getOption(scan.nextLine());
            if (comparison != ComparisonOption.NONE){
                players.playRound(comparison);
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
            setupGame2Players();
        }   else if (playerChoice == 1) {
            setupGame3Players();
        } else if (playerChoice == 2) {
            setupGame3Players();
        }
    }

    private void setupGame2Players() {
        BeerDAO dao = new BeerDAOCsv();

        view.clear();

        players.addPlayer(new Player("Player1"));
        players.addPlayer(new Player("Player2"));
        List<Card> allCards = new ArrayList<Card>();

        try {
            allCards = dao.loadDatabase(databaseName);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        divideCardStack(players, allCards);

//        players.addCard(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")), 0);
//        players.addCard(new Card("Druga Pomoc",10, 510, 45, new BeerStyle("Lager")), 1);
//        players.addCard(new Card("Trzecia Pomoc",13, 500, 43, new BeerStyle("Lager")), 2);
//
//        players.addCard(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")), 0);
//        players.addCard(new Card("Żywe IPA",32, 700, 70, new BeerStyle("IPA")), 1);
//        players.addCard(new Card("Żywieckie IPA",29, 690, 60, new BeerStyle("IPA")), 2);
//
//        players.addCard(new Card("Guiness",32, 1000, 80, new BeerStyle("Stout", "Coffee")), 0);
//        players.addCard(new Card("Guines",30, 800, 75, new BeerStyle("Stout", "Coffee")), 1);

    }

    private void divideCardStack(Players players, List<Card> allCards) {
        discardExcessCards(allCards, players);
        int counter = 0;
        int playerId;
        while (allCards.size() > 0) {
            Card nextCard = getRandomCard(allCards);
            playerId = counter % players.getPlayersList().size();
            players.addCard(nextCard, playerId);
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

    private void setupGame3Players(){
        view.clear();

        players.addPlayer(new Player("Player1"));
        players.addPlayer(new Player("Player2"));
        players.addPlayer(new Player("Player3"));

        players.addCard(new Card("Pierwsza Pomoc",10, 520, 42, new BeerStyle("Lager")), 0);
        players.addCard(new Card("Druga Pomoc",10, 510, 45, new BeerStyle("Lager")), 1);
        players.addCard(new Card("Trzecia Pomoc",10, 500, 43, new BeerStyle("Lager")), 2);

        players.addCard(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")), 0);
        players.addCard(new Card("Żywe IPA",32, 700, 70, new BeerStyle("IPA")), 1);
        players.addCard(new Card("Żywieckie IPA",29, 690, 60, new BeerStyle("IPA")), 2);

        players.addCard(new Card("Guiness",32, 1000, 80, new BeerStyle("Stout", "Coffee")), 0);
        players.addCard(new Card("Guines",30, 800, 81, new BeerStyle("Stout", "Coffee")), 1);
        players.addCard(new Card("Guines",31, 700, 78, new BeerStyle("Stout", "Coffee")), 2);
    }

    private ComparisonOption getCompareChoice() {
        String key = scan.nextLine();
        return ComparisonOption.getOption(key);
    }
}
