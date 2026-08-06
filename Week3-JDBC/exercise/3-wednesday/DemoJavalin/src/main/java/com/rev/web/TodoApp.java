package com.rev.web;

import com.rev.web.controllers.TodoController;
import io.javalin.Javalin;



public class TodoApp {


    public static void main(String[] args) {

        // Initialize and start Javalin
        Javalin app = Javalin.create().start(7000);

        // 1. Retrieve all Todos
        app.get("/api/todos", TodoController::getAllTodos);

        //2. Retrieve todo based on id
        app.get("/api/todos/{id}", TodoController::getTodoById);

        // 3. POST data   -- create todos
        app.post("/api/todos" , TodoController::createTodo);

        // 4. update Data
        app.put("/api/todos/{id}", TodoController::updateTodo);

        // 5. Delete todo based on id
        app.delete("/api/todos/{id}", TodoController::deleteTodo);

        // Centralized Exception Handling
        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            ctx.status(400);
            ctx.json(new ErrorResponse("An unexpected error occured."));
        });
        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500);
            ctx.json(new ErrorResponse("An unexpected server error occured."));
        });
    }
}

