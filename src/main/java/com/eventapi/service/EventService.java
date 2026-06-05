package com.eventapi.service;

import com.eventapi.model.Event;
import com.eventapi.repository.EventRepository;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public class EventService {

    private final EventRepository repository = new EventRepository();

    public List<Event> getAllEvents() throws SQLException {
        return repository.findAll();
    }

    public Optional<Event> getEventById(int id) throws SQLException {
        return repository.findById(id);
    }

    public Event createEvent(Event event) throws SQLException {
        if (event.getTitle() == null || event.getTitle().isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (event.getEventDate() == null) {
            throw new IllegalArgumentException("Data do evento é obrigatória");
        }
        if (event.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }
        return repository.save(event);
    }

    public Event updateEvent(int id, Event event) throws SQLException {
        if (event.getTitle() == null || event.getTitle().isBlank()) {
            throw new IllegalArgumentException("Título é obrigatório");
        }
        if (event.getEventDate() == null) {
            throw new IllegalArgumentException("Data do evento é obrigatória");
        }
        if (event.getCapacity() <= 0) {
            throw new IllegalArgumentException("Capacidade deve ser maior que zero");
        }
        boolean updated = repository.update(id, event);
        if (!updated) {
            throw new IllegalArgumentException("Evento não encontrado");
        }
        event.setId(id);
        return event;
    }

    public void deleteEvent(int id) throws SQLException {
        boolean deleted = repository.delete(id);
        if (!deleted) {
            throw new IllegalArgumentException("Evento não encontrado");
        }
    }
}