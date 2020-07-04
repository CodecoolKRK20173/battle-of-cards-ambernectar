package com.codecool.app;

public enum ComparisonOption {
    IBU,
    PERCENTAGE,
    PRICE,
    STYLE,
    CHEAT,
    NONE;

    public static ComparisonOption getOption(String key){
        ComparisonOption result = NONE;
        switch (key) {
            case "a":
                result = IBU;
                break;
            case "s":
                result = PRICE;
                break;
            case "d":
                result = PERCENTAGE;
                break;
            case "f":
                result = STYLE;
            case "h":
                result = CHEAT;
        }
        return result;
    }
}
