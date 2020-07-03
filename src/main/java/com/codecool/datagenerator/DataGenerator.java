package com.codecool.datagenerator;

import java.io.*;
import java.security.SecureRandom;
import java.util.Random;

public class DataGenerator {
    private Random randomGenerator;
    private static final String AB = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
    private static SecureRandom rnd = new SecureRandom();
//    private FileWriter csvOutputFile;

    public DataGenerator(String filename) throws IOException {
        randomGenerator = new Random();
//        csvOutputFile = new FileWriter(filename, true);
    }

    public void generateDatabase (int databaseSize) throws IOException {
        for (int index = 0; index < databaseSize; index++) {
            String row = generateRow();
            saveToFile(row, "database2.csv");
        }
    }

    public String generateRow() {
        StringBuilder line = new StringBuilder();
        line.append(randomString(5));
        line.append(",");
        line.append(randomInt());
        line.append(",");
        line.append(randomInt());
        line.append(",");
        line.append(randomInt());
        line.append(",");
        line.append(randomString(5));
        int chance = (int) Math.random();
        if (chance < 0.2) {
            line.append(",");
            line.append(randomString(5));
        }

        return line.toString();
    }

    //source:
    //https://stackoverflow.com/questions/41107/how-to-generate-a-random-alpha-numeric-string
    private String randomString(int len){
        StringBuilder sb = new StringBuilder( len );
        for( int i = 0; i < len; i++ )
            sb.append( AB.charAt( rnd.nextInt(AB.length()) ) );
        return sb.toString();
    }

    private int randomInt() {
        return randomGenerator.nextInt( 999);
    }


    public void saveToFile(String row, String filename) throws IOException {
        FileWriter csvOutputFile = new FileWriter(filename, true);
        try (PrintWriter out = new PrintWriter(csvOutputFile)) {
            out.println(row);
        }
    }


}