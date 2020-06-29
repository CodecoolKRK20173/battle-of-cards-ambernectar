package com.codecool.app;

import java.util.List;
import java.util.Scanner;

public class Util {
    View view;

    public Util() {
        this.view = new View();
    }

    public int switchChoice (List<String> optionsList) {
        boolean correctOption = false;
        int choice = 999;
        String message = "Chose your option";
        Scanner input = new Scanner(System.in);

        while (correctOption = false) {
            view.clear();
            view.printOptions(optionsList, message);
            String StringChoice = input.nextLine();
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
}
