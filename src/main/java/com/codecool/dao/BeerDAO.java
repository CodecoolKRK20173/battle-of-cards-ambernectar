package com.codecool.dao;

import com.codecool.cards.Card;
import com.codecool.cards.Hand;

import java.io.FileNotFoundException;
import java.util.List;

public interface BeerDAO {
    public Hand loadDatabase(String filename) throws FileNotFoundException;
    public void SaveDatabase(List<Card> cardList);
}
