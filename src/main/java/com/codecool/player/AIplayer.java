package com.codecool.player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AIplayer extends Player {
    public AIplayer(String ownerName) {
        super(ownerName);
    }


    @Override
    public String getOption() {
        Random randomGenerator = new Random();

        List<String> options = new ArrayList<String>();
        options.add("a");
        options.add("s");
        options.add("d");
        options.add("f");

        int index = randomGenerator.nextInt(options.size());
        return options.get(index);
    }
}
