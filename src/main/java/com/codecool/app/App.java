package com.codecool.app;

import java.io.IOException;

public class App
{
    public static void main( String[] args ) throws IOException {
        //lines which allow to generate dummy data
        //Watchout !! - data is generated in main folder
//        DataGenerator dataGenerator = new DataGenerator("database2.csv");
//        dataGenerator.generateDatabase(100);

        GameController gameController = new GameController("pyszne_z_pianka_piwo_piwo.csv");
        gameController.run();
    }
}
