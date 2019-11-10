/**
 * Copyright 2005-2015 Red Hat, Inc.
 * <p>
 * Red Hat licenses this file to you under the Apache License, version
 * 2.0 (the "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.  See the License for the specific language governing
 * permissions and limitations under the License.
 */
package com.rscoelho.sandbox.vertx;

import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.BiConsumer;

import com.rscoelho.sandbox.model.Contact;

import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.BodyHandler;

public class MyWebVerticle extends AbstractVerticle {
    private Map<Integer, Contact> contacts = new TreeMap<>();
    private static final AtomicInteger COUNTER = new AtomicInteger();

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Runner.runExample(MyWebVerticle.class);
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new MyWebVerticle());
        System.out.println(">>> Verticle starting upon Port 8080");
    }

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);

        router.route().handler(BodyHandler.create());

        router.post("/api/contact").handler(this::addContact);
        router.get("/api/contact/:id").handler(this::readById);
        router.get("/api/contact").handler(requestHandler -> {
            requestHandler.response().putHeader("content-type", "application-json; charset=utf-8")
                    .end(Json.encodePrettily(contacts));
        });

        vertx.createHttpServer().requestHandler(router).listen(8080);
    }

    private void addContact(RoutingContext context) {
        Contact contact = Json.decodeValue(context.getBodyAsString(), Contact.class);
        contact.setId(COUNTER.getAndIncrement());
        contacts.put(contact.getId(), contact);

        context.response().setStatusCode(HttpResponseStatus.CREATED.code())
                .putHeader("content-type", "application/json; charset=utf-8").end(Json.encodePrettily(contact));
    }

    private void readById(final RoutingContext context) {
        BiConsumer<RoutingContext, Integer> errorConsumer = (c, code) -> {
            c.response().setStatusCode(code).end();
        };

        final Integer idInteger = Integer.valueOf(context.request().getParam("id"));

        Contact contact = contacts.computeIfAbsent(idInteger, (k) -> {
            errorConsumer.accept(context, 404);
            throw new IllegalArgumentException();
        });

        context.response().putHeader("content-type", "application/json; charset=utf-8")
                .end(Json.encodePrettily(contact));
    }
}