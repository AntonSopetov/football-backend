package com.juno.football.model;

public class Player {
    private Long telegramId;
    private String name;

    public Player(Long telegramId, String name) {
        this.telegramId = telegramId;
        this.name = name;
    }

    public Long getTelegramId() { return telegramId; }
    public String getName() { return name; }
}
