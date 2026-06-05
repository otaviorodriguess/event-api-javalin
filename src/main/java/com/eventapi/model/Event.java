package com.eventapi.model;

import java.time.LocalDate;

public class Event {

    private Integer id;
    private String title;
    private String description;
    private String location;
    private LocalDate eventDate;
    private int capacity;

    // Construtor vazio (Jackson precisa pra desserializar)
    public Event() {}

    public Event(Integer id, String title, String description,
                 String location, LocalDate eventDate, int capacity) {
        this.id          = id;
        this.title       = title;
        this.description = description;
        this.location    = location;
        this.eventDate   = eventDate;
        this.capacity    = capacity;
    }

    public Integer getId()                        { return id; }
    public void setId(Integer id)                 { this.id = id; }

    public String getTitle()                      { return title; }
    public void setTitle(String title)            { this.title = title; }

    public String getDescription()                { return description; }
    public void setDescription(String description){ this.description = description; }

    public String getLocation()                   { return location; }
    public void setLocation(String location)      { this.location = location; }

    public LocalDate getEventDate()               { return eventDate; }
    public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }

    public int getCapacity()                      { return capacity; }
    public void setCapacity(int capacity)         { this.capacity = capacity; }
}