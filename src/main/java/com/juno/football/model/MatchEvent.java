package com.juno.football.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MatchEvent {
    private Long id;
    private EventType eventType;              // Тип события (Футбол, Фифа, Дети)
    private String location;                  // Локация (например, MetroCity Mall Spot)
    private LocalDateTime dateTime;           // Дата и время
    private int maxCapacity;                  // Players limit (например, 12)
    private double priceGel;                  // Цена в лари (GEL)

    private List<Player> activeRoster = new ArrayList<>();
    private List<Player> waitlist = new ArrayList<>();

    public MatchEvent(Long id, EventType eventType, String location, LocalDateTime dateTime, int maxCapacity, double priceGel) {
        this.id = id;
        this.eventType = eventType;
        this.location = location;
        this.dateTime = dateTime;
        this.maxCapacity = maxCapacity;
        this.priceGel = priceGel;
    }

    // Геттеры
    public Long getId() { return id; }
    public EventType getEventType() { return eventType; }
    public String getLocation() { return location; }
    public int getMaxCapacity() { return maxCapacity; }
    public double getPriceGel() { return priceGel; }
    public List<Player> getActiveRoster() { return activeRoster; }
    public List<Player> getWaitlist() { return waitlist; }
}
