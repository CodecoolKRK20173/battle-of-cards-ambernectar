package com.codecool.player;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import org.junit.Test;

import static org.junit.Assert.*;

public class PlayersTest {

    @Test
    public void iteratePlayersStart() {
        Players players = createTestObject();
        assertEquals("Player1", players.getCurrentPlayerName());
    }

    @Test
    public void iteratePlayersOneStep() {
        Players players = createTestObject();
        players.iteratePlayers();
        assertEquals("Player2", players.getCurrentPlayerName());
    }

    @Test
    public void iteratePlayersSwitch() {
        Players players = createTestObject();
        players.iteratePlayers();
        players.iteratePlayers();
        players.iteratePlayers();
        assertEquals("Player1", players.getCurrentPlayerName());
    }

    @Test
    public void moveCards() {
        Players players = createTestObject();
        players.moveCards(0);
        assertEquals("Żywiec IPA", players.getPlayersList().get(0).getTopCard().getName());
        assertEquals(5, players.getPlayersList().get(0).getCardList().size());
        assertEquals(2, players.getPlayersList().get(1).getCardList().size());
    }

    @Test
    public void isGameOverFalse() {
        Players players = createTestObject();
        assertEquals(false, players.isGameOver());
    }

    @Test
    public void isGameOverTrue() {
        Players players = createTestObject();
        players.moveCards(0);
        players.moveCards(0);
        players.moveCards(1);
        assertEquals(true, players.isGameOver());
    }

    @Test
    public void findWinner() {
        // TODO
    }

    private Players createTestObject(){
        Players players = new Players();

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

        return players;
    }
}