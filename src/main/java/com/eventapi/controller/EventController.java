package com.eventapi.controller;

import com.eventapi.model.Event;
import com.eventapi.service.EventService;
import io.javalin.Javalin;
import io.javalin.http.Context;

public class EventController {

    private final EventService service = new EventService();

    public void registerRoutes(Javalin app) {
        app.get("/events",         this::getAll);
        app.get("/events/{id}",    this::getById);
        app.post("/events",        this::create);
        app.put("/events/{id}",    this::update);
        app.delete("/events/{id}", this::delete);
    }

    private void getAll(Context ctx) {
        try {
            ctx.json(service.getAllEvents());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno: " + e.getMessage());
        }
    }

    private void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            service.getEventById(id)
                    .ifPresentOrElse(
                            ctx::json,
                            () -> ctx.status(404).result("Evento não encontrado")
                    );
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID inválido");
        } catch (Exception e) {
            ctx.status(500).result("Erro interno: " + e.getMessage());
        }
    }

    private void create(Context ctx) {
        try {
            Event event = ctx.bodyAsClass(Event.class);
            ctx.status(201).json(service.createEvent(event));
        } catch (IllegalArgumentException e) {
            ctx.status(400).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno: " + e.getMessage());
        }
    }

    private void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Event event = ctx.bodyAsClass(Event.class);
            ctx.json(service.updateEvent(id, event));
        } catch (IllegalArgumentException e) {
            ctx.status(404).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno: " + e.getMessage());
        }
    }

    private void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            service.deleteEvent(id);
            ctx.status(204);
        } catch (IllegalArgumentException e) {
            ctx.status(404).result(e.getMessage());
        } catch (Exception e) {
            ctx.status(500).result("Erro interno: " + e.getMessage());
        }
    }
}