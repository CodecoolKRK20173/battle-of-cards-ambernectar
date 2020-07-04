package com.codecool.app;

import com.codecool.cards.Card;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class LocalUtil {
    View view;

    public LocalUtil() {
        this.view = new View();
    }

    public int getChoice (List<String> optionsList, String message) {
        boolean correctOption = false;
        int choice = 999;

        Scanner input = new Scanner(System.in);

        while (!correctOption) {
            view.clear();
            view.printOptions(optionsList, message);
            String StringChoice = input.nextLine();
//            choice = 1;
            choice = validateAsInt(StringChoice);
            if (choice <= optionsList.size()) {
                correctOption = true;
            }
            return choice;
        }

        input.close();
        return choice;
    }

    public int validateAsInt (String inputValue) {

        try {
            return  Integer.parseInt(inputValue);
        } catch (NumberFormatException e) {
            view.printMessage("Value is not number, returning 999");
            return 999;
        }
    }

    public List<Card> deepCopyAList(List<Card> listToCopy) {
        List<Card> copiedList = new ArrayList<Card>();
        for(Card nextObject : listToCopy) {
            copiedList.add(nextObject);
        }

        return copiedList;
    }
}
