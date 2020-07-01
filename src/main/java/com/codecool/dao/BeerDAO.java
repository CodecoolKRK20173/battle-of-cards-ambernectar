package com.codecool.dao;

import com.codecool.cards.Card;
import com.codecool.player.Player;

import java.io.FileNotFoundException;
import java.util.List;

public interface BeerDAO {
    public List<Card> loadDatabase(String filename) throws FileNotFoundException;
    public void SaveDatabase(List<Card> cardList);
}
