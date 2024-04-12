package com.example.practicejankenapp;

public enum JankenHandItemEnum {

    PAPER(2, R.drawable.janken_pa, "パー"),
    ROCK(0, R.drawable.janken_gu, "グー"),
    SCISSORS(1, R.drawable.janken_choki, "チョキ");

    private final int number;
    private final int imageId;
    private final String name;

    JankenHandItemEnum(int number, int imageId, String name) {
        this.number = number;
        this.imageId = imageId;
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
    public static JankenHandItemEnum getByNumber(int number) {
        for (JankenHandItemEnum item : values()) {
            if (item.getNumber() == number) {
                return item;
            }
        }
        return null;
    }
}
