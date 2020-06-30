package com.codecool.player;

import java.util.List;

public class Players {
    List<Player> players;
    int currentPlayer;

    public Players(List<Player> players) {
        this.players = players;
        this.currentPlayer = 0;
    }
}
