package com.codecool.dao;

import com.codecool.cards.BeerStyle;
import com.codecool.cards.Card;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class BeerDAOCsv implements BeerDAO {
    @Override
    public List<Card> loadDatabase(String filename) throws FileNotFoundException {
        List<Card> loadedCards = new ArrayList<Card>();

        String result = loadDataAsAString(filename);

        List<String> resaultAsList = Arrays.asList(result.split("\n"));
        for (String nextString: resaultAsList) {
            loadedCards.add(makeCardFromString(nextString));
        }
        return loadedCards;
    }

    @Override
    public void SaveDatabase(List<Card> cardList) {
        for (int index= 0; index<cardList.size(); index++) {

        }
    }

    private String loadDataAsAString (String filename) throws FileNotFoundException {
//        source:
//        https://stackoverflow.com/questions/15749192/how-do-i-load-a-file-from-resource-folder

        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        InputStream inputStream = classloader.getResourceAsStream(filename);

        Scanner s = new Scanner(inputStream).useDelimiter("\\A");
        String result = s.hasNext() ? s.next() : "";

        return result;
    }

    private Card makeCardFromString (String inputString) {
        List<String> inputStringSpitted = Arrays.asList(inputString.split(","));
        String name = inputStringSpitted.get(0);
        Integer ibu = Integer.parseInt(inputStringSpitted.get(1));
        int price = Integer.parseInt(inputStringSpitted.get(2));
        int percentage = Integer.parseInt(inputStringSpitted.get(3));
        BeerStyle style;

        if (inputStringSpitted.size() == 5) {
            style = new BeerStyle(inputStringSpitted.get(4));
        } else {
            style = new BeerStyle(inputStringSpitted.get(4), inputStringSpitted.get(5));
        }
        return new Card(name, ibu, price, percentage, style);
    }


}