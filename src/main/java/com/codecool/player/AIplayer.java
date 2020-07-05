package com.codecool.player;

import java.util.*;

public class AIplayer extends Player {
    private List<String> options;

    public AIplayer(String ownerName) {
        super(ownerName);
        this.options = new ArrayList<String>();
        options.add("a");
        options.add("s");
        options.add("d");
        options.add("f");
    }

    @Override
    public String getOption(Players players) {
        Random randomGenerator = new Random();

        Map<String, Double> allOptions = useCheatEngine(players);
        Map<String, Double> twoBestOptions = returnTwoGreatest(allOptions);

        List<String> keysAsArray = new ArrayList<String>(twoBestOptions.keySet());
        int index = randomGenerator.nextInt(keysAsArray.size());
        return keysAsArray.get(index);
    }

   private Map<String, Double> useCheatEngine (Players players) {

        double[] values = players.useCheat();
        Map<String, Double> valuesList = new HashMap<String, Double>();

       try {
           // " -1 to discard last element of array which store qty of all cards"
           for (int index = 0; index < values.length -1; index++) {
               valuesList.put(options.get(index), values[index]);
           }
       } catch (IndexOutOfBoundsException e ) {
           System.out.println("IndexOutOfBoundsException in AIPlayer");
           System.exit(0);
       };
        return valuesList;
    }

    private Map<String, Double> returnTwoGreatest (Map<String, Double> inputValues) {

        String biggestKey = "";
        String secondBiggestKey = "";
        Double biggestNum = Double.valueOf(0);
        Double secondBiggestNum = Double.valueOf(0);

        for(String key : inputValues.keySet()) {
            if (inputValues.get(key) > biggestNum) {
                secondBiggestNum = biggestNum;
                secondBiggestKey = biggestKey;
                biggestNum = inputValues.get(key);
                biggestKey = key;
            } else if (inputValues.get(key) > secondBiggestNum) {
                secondBiggestNum = inputValues.get(key);
                secondBiggestKey = key;
            }
        }

        Map<String, Double> twoBiggest = new HashMap<String, Double>();
        twoBiggest.put(biggestKey, biggestNum);
        twoBiggest.put(secondBiggestKey, secondBiggestNum);

        return twoBiggest;

    }
}
