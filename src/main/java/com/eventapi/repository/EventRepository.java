package com.eventapi.repository;

import com.eventapi.config.Database;
import com.eventapi.model.Event;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EventRepository {

    public List<Event> findAll() throws SQLException {
        List<Event> events = new ArrayList<>();
        String sql = "SELECT * FROM events";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                events.add(mapRow(rs));
            }
        }
        return events;
    }

    public Optional<Event> findById(int id) throws SQLException {
        String sql = "SELECT * FROM events WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(mapRow(rs));
                }
            }
        }
        return Optional.empty();
    }

    public Event save(Event event) throws SQLException {
        String sql = "INSERT INTO events (title, description, location, event_date, capacity) " +
                "VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setString(3, event.getLocation());
            ps.setString(4, event.getEventDate().toString()); // SQLite armazena como TEXT
            ps.setInt(5, event.getCapacity());
            ps.executeUpdate();

            try (ResultSet keys = ps.getGeneratedKeys()) {
                if (keys.next()) {
                    event.setId(keys.getInt(1));
                }
            }
        }
        return event;
    }

    public boolean update(int id, Event event) throws SQLException {
        String sql = "UPDATE events SET title=?, description=?, location=?, " +
                "event_date=?, capacity=? WHERE id=?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, event.getTitle());
            ps.setString(2, event.getDescription());
            ps.setString(3, event.getLocation());
            ps.setString(4, event.getEventDate().toString());
            ps.setInt(5, event.getCapacity());
            ps.setInt(6, id);

            return ps.executeUpdate() > 0;
        }
    }

    public boolean delete(int id) throws SQLException {
        String sql = "DELETE FROM events WHERE id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, id);
            return ps.executeUpdate() > 0;
        }
    }

    // Converte linha do ResultSet em objeto Event
    private Event mapRow(ResultSet rs) throws SQLException {
        return new Event(
                rs.getInt("id"),
                rs.getString("title"),
                rs.getString("description"),
                rs.getString("location"),
                LocalDate.parse(rs.getString("event_date")), // SQLite retorna TEXT
                rs.getInt("capacity")
        );
    }
}