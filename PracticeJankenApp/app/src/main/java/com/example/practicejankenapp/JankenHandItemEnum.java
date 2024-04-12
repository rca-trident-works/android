package com.example.practicejankenapp;

public enum JankenHandItemEnum {

    PAPER(0, R.drawable.janken_pa, "パー"),
    ROCK(1, R.drawable.janken_pa, "グー"),
    SCISSORS(2, R.drawable.janken_choki, "チョキ");

    private final int id;
    private final int imageId;
    private final String name;

    JankenHandItemEnum(int id, int imageId, String name) {
        this.id = id;
        this.imageId = imageId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public int getImageId() {
        return imageId;
    }

    public String getName() {
        return name;
    }
}
