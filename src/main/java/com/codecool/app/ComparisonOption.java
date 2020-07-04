package com.codecool.app;

import com.codecool.comparator.ComparatorIbu;
import com.codecool.comparator.ComparatorPercentage;
import com.codecool.comparator.ComparatorPrice;
import com.codecool.comparator.ComparatorStyle;

import java.util.Comparator;
import java.util.Map;

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

    public static Comparator getComparator(ComparisonOption ch, Map<String, Integer> styles){
        switch (ch){
            case IBU:
                return new ComparatorIbu();
            case PRICE:
                return new ComparatorPrice();
            case PERCENTAGE:
                return new ComparatorPercentage();
            case STYLE:
                return new ComparatorStyle(styles);
        }
        return null;
    }
}
