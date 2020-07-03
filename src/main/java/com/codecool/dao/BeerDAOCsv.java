package com.codecool.dao;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BeerDAOCsv implements BeerDAO {
    @Override
    public List<Card> loadDatabase(String filename) throws FileNotFoundException {

//        source:
//        https://stackoverflow.com/questions/15749192/how-do-i-load-a-file-from-resource-folder

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream("database2.csv");

//        String theString = IOUtils.toString(inputStream, StandardCharsets.UTF_8);

//        source:
//https://stackoverflow.com/questions/309424/how-do-i-read-convert-an-inputstream-into-a-string-in-java
//        StringWriter writer = new StringWriter();
//        IOUtils.copy(inputStream, writer,  "UTF-8");
//        String theString = writer.toString();


        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";


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

//        //TODO remove temp code
//        loadedCards.add(new Card("Pierwsza Pomoc",15, 520, 42, new BeerStyle("Lager")));
//        loadedCards.add(new Card("Druga Pomoc",10, 510, 45, new BeerStyle("Lager")));
//        loadedCards.add(new Card("Trzecia Pomoc",13, 500, 43, new BeerStyle("Lager")));
//        loadedCards.add(new Card("Żywiec IPA",30, 670, 67, new BeerStyle("IPA")));
//        loadedCards.add(new Card("Żywe IPA",32, 700, 70, new BeerStyle("IPA")));
//        loadedCards.add(new Card("Żywieckie IPA",29, 690, 60, new BeerStyle("IPA")));
//        loadedCards.add(new Card("Guiness",32, 1000, 80, new BeerStyle("Stout", "Coffee")));
//        loadedCards.add(new Card("Guines",30, 800, 75, new BeerStyle("Stout", "Coffee")));

        return loadedCards;
//        scanner.useDelimiter(",");

    }

    @Override
    public void SaveDatabase(List<Card> cardList) {
        for (int index= 0; index<cardList.size(); index++) {

        }
    }
}
