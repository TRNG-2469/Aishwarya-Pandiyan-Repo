package com.rev.web;

import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {
        // Create  anew Javalin instance and start it on a port

        Javalin app = Javalin.create().start(7000);

        // define a GET and point
        app.get("/", ctx -> ctx.result("Hello World!"));

        app.get("/hello", ctx -> ctx.result("Hello World Again"));

        app.get("/user/{name}", ctx -> {
            String name = ctx.pathParam("name");
            ctx.result("Hello Again" + name.toUpperCase() );

        });

        // query param
        app.get("/user", ctx-> {
            String name = ctx.queryParam("name");
            String age = ctx.queryParam("age");
            ctx.result("Hello Again"  + name.toUpperCase()+ " " + age);
        });
    }

}


