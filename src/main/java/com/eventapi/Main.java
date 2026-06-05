package com.eventapi;

import com.eventapi.config.Database;
import com.eventapi.controller.EventController;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.javalin.Javalin;
import io.javalin.json.JavalinJackson;

import java.sql.Connection;
import java.sql.Statement;

public class Main {

    public static void main(String[] args) throws Exception {

        try (Connection conn = Database.getConnection();
             Statement stmt = conn.createStatement()) {

            stmt.execute("""
                CREATE TABLE IF NOT EXISTS events (
                    id          INTEGER PRIMARY KEY AUTOINCREMENT,
                    title       TEXT    NOT NULL,
                    description TEXT,
                    location    TEXT,
                    event_date  TEXT    NOT NULL,
                    capacity    INTEGER NOT NULL
                )
            """);

            System.out.println("✅ Banco de dados pronto.");
        }

        Javalin app = Javalin.create(config -> {
            config.jsonMapper(new JavalinJackson().updateMapper(m -> {
                m.registerModule(new JavaTimeModule());
                m.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
            }));
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(rule -> rule.anyHost());
            });
        }).start(8080);

        new EventController().registerRoutes(app);

        System.out.println("🚀 API rodando em http://localhost:8080");
    }
}