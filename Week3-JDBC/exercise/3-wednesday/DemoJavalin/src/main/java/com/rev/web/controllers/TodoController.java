package com.rev.web.controllers;

import com.rev.web.Model.Todo;

import io.javalin.http.Context;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class TodoController {
    // create some dummy data, this can be replaced by database
    private static final ConcurrentHashMap<Integer, Todo> db = new ConcurrentHashMap<>();
    private static final AtomicInteger idSequence = new AtomicInteger(0);

    static {
        int id1 = idSequence.incrementAndGet();
        db.put(1, new Todo(id1, "Learn Javalin", false));
        int id2 = idSequence.incrementAndGet();
        db.put(2, new Todo(id2, "Build a simple app", false));
    }

    //1. GET All Todos
    public static void getAllTodos(Context ctx){
        List<Todo> todos = new ArrayList<>(db.values());
        ctx.json(todos);
    }

    //2. GET ONE: Read 1 Todo
    public static void getTodoById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Todo todo = db.get(id);
        if(todo==null) {
            throw new NotFoundResponse("Todo with id "+id+" not found");
        }
        ctx.json(todo);
    }

    // 3. Create Todo
    public static void createTodo(Context ctx) {
    Todo payload=ctx.BodyAsClass(Todo.class);
    int newId=idSequence.incrementAndGet();
    Todo newTodo=new Todo(newId,payload.getTitle(),payload.isCompleted());
    db.put(newId,newTodo);
    ctx.status(201);
    ctx.json(newTodo);
    }

    // 4. update Todo

    // make a check to see if title or completed are null or not
    // for you all to implement
    public static void updateTodo(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));

        if (!db.containsKey(id)) {
            throw new NotFoundResponse("Todo with id " + id + " not found");
        }

        Todo payload = ctx.bodyAsClass(Todo.class);

        if (payload.getTitle() == null || payload.getTitle().isBlank()) {
            throw new BadRequestResponse("Title cannot be null or empty");
        }

        Todo updated = new Todo(id, payload.getTitle(), payload.isCompleted());
        db.put(id, updated);
        ctx.json(updated);
    }
    // 5. Delete To Do

    public static void deleteTodo(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        db.remove(id);
        ctx.staus(204);
    }
}
