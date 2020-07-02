package com.codecool.player;

public enum ComparisonOption {
    IBU,
    PERCENTAGE,
    PRICE,
    NONE;

    public static ComparisonOption getOption(String key){
        ComparisonOption result = NONE;
        switch (key) {
            case "a":
                result = IBU;
            case "s":
                result = PERCENTAGE;
            case "d":
                result = PRICE;
        }
        return result;
    }
}
