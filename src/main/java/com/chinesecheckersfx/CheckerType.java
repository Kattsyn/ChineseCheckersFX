package com.chinesecheckersfx;


public enum CheckerType {
    NONE("...", "..."),
    WHITE("#c0c0c0", "белый"),
    RED("#b40000", "красный"),
    GREEN("#005000", "зеленый"),
    YELLOW("#c8bc00", "желтый"),
    BLUE("#0000b4", "синий"),
    PURPLE("#79278d", "фиолетовый"),
    ORANGE("#f08800", "оранжевый");

    final String color;
    final String ruColorName;

    CheckerType(String color, String ruColorName) {
        this.color = color;
        this.ruColorName = ruColorName;
    }

}
