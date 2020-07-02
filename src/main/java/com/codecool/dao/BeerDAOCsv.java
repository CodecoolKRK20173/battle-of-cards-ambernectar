package com.codecool.dao;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;
import com.codecool.player.Player;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BeerDAOCsv implements BeerDAO {
    @Override
    public List<Card> loadDatabase(String filename) throws FileNotFoundException {

        List<Card> loadedCards = new ArrayList<Card>();
        Scanner scanner;
        try {
            scanner = new Scanner(new File(filename));
            while(scanner.hasNext()){
                String[] row = scanner.next().split(",");
                String name = row[0];
                Integer ibu = Integer.parseInt(row[1]);
                int price = Integer.parseInt(row[2]);
                int percentage = Integer.parseInt(row[3]);
                BeerStyle style;

                if (row.length == 5) {
                    style = new BeerStyle(row[5]);
                } else {
                    style = new BeerStyle(row[5], row[6]);
                }
                loadedCards.add(new Card(name, ibu, price, percentage, style));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return loadedCards;
//        scanner.useDelimiter(",");

    }

    @Override
    public void SaveDatabase(List<Card> cardList) {
        for (int index= 0; index<cardList.size(); index++) {

        }
    }
}
