package com.devoteam.nl.helloworld;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Promise;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.handler.BodyHandler;

import static io.netty.handler.codec.http.HttpResponseStatus.*;

public class MainVerticle extends AbstractVerticle {

    @Override
    public void start(Promise<Void> startPromise) throws Exception {
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.get("/").handler(routingContext -> {
            routingContext.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .end(new JsonObject().put("message", "hello world").encodePrettily());
        });

        router.post("/*").handler(routingContext -> {
            JsonObject requestParameters = routingContext.getBodyAsJson();
            System.out.println(requestParameters.encode());
            if (requestParameters == null) {
                routingContext.fail(400);
            }
            routingContext.response()
                    .putHeader("content-type", "application/json; charset=utf-8")
                    .setStatusCode(201)
                    .end(new JsonObject()
                            .put("message", "hello " + requestParameters.getString("name") + " " + requestParameters.getString("surname"))
                            .encodePrettily());
        });

        vertx
                .createHttpServer()
                .requestHandler(router)
                .listen(8888, http -> {
                    if (http.succeeded()) {
                        startPromise.complete();
                        System.out.println("HTTP server started on port 8888");
                    } else {
                        startPromise.fail(http.cause());
                    }
                });

/*
    vertx.createHttpServer().requestHandler(req -> {
      req.response()
        .putHeader("content-type", "text/plain")
        .end("Hello from Vert.x!");
    }).listen(8888, http -> {
      if (http.succeeded()) {
        startPromise.complete();
        System.out.println("HTTP server started on port 8888");
      } else {
        startPromise.fail(http.cause());
      }
    });
*/

    }
}
