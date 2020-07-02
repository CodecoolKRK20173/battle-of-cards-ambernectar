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


        //TODO cant find database.csv
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

        //TODO remove temp code
        loadedCards.add(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")));
        loadedCards.add(new Card("Druga Pomoc",10, 510, 45, new BeerStyle("Lager")));
        loadedCards.add(new Card("Trzecia Pomoc",13, 500, 43, new BeerStyle("Lager")));
        loadedCards.add(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")));
        loadedCards.add(new Card("Żywe IPA",32, 700, 70, new BeerStyle("IPA")));
        loadedCards.add(new Card("Żywieckie IPA",29, 690, 60, new BeerStyle("IPA")));
        loadedCards.add(new Card("Guiness",32, 1000, 80, new BeerStyle("Stout", "Coffee"));
        loadedCards.add(new Card("Guines",30, 800, 75, new BeerStyle("Stout", "Coffee")));

        return loadedCards;
//        scanner.useDelimiter(",");

    }

    @Override
    public void SaveDatabase(List<Card> cardList) {
        for (int index= 0; index<cardList.size(); index++) {

        }
    }
}
