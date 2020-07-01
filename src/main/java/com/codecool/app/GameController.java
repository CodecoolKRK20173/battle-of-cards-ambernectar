package com.codecool.app;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import com.codecool.player.Player;
import com.codecool.player.Players;
import java.util.*;
import java.util.Scanner;

public class GameController {
    View view;
    Util util;
    Players players;
    Scanner scan;

    public GameController() {
        this.view = new View();
        this.util = new Util();
        this.players = new Players();
        scan = new Scanner(System.in);
    }

    void run(){
        setupGame2();
        while (!players.isGameOver()){
            view.displayGameScreen(players);
            players.playRound(scan.nextLine());
        }
        view.displayEndScreen(players.getWinnerName());
        scan.nextLine();
    }

    private void setupGame2(){
        view.printMessage("MENU:");
        List<String> optionsList = new ArrayList<String>();
        optionsList.add("Play 2 players game.");
        optionsList.add("Play 3 players game.");
        optionsList.add("Play 4 players game.");
//        int playerChoice = util.getChoice(optionsList, "Chose game mode");
        int playerChoice = 1;

        if (playerChoice == 0) {
            setupGame3Players();
        }   else if (playerChoice == 1) {
            setupGame3Players();
        } else if (playerChoice == 2) {
            setupGame3Players();
        }
    }



    private void setupGame3Players(){
        view.clear();

        players.addPlayer(new Player("Player1"));
        players.addPlayer(new Player("Player2"));
        players.addPlayer(new Player("Player3"));

        players.addCard(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")), 0);
        players.addCard(new Card("Druga Pomoc",10, 510, 45, new BeerStyle("Lager")), 1);
        players.addCard(new Card("Trzecia Pomoc",13, 500, 43, new BeerStyle("Lager")), 2);

        players.addCard(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")), 0);
        players.addCard(new Card("Żywe IPA",32, 700, 70, new BeerStyle("IPA")), 1);
        players.addCard(new Card("Żywieckie IPA",29, 690, 60, new BeerStyle("IPA")), 2);

        players.addCard(new Card("Guiness",32, 1000, 80, new BeerStyle("Stout", "Coffee")), 0);
        players.addCard(new Card("Guines",30, 800, 75, new BeerStyle("Stout", "Coffee")), 1);
    }
}
