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

import com.rscoelho.sandbox.model.Contact;
import io.netty.handler.codec.http.HttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.rxjava.ext.web.handler.BodyHandler;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.atomic.AtomicInteger;




public class MyWebVerticle extends AbstractVerticle {
    private Map<Integer, Contact> contacts = new TreeMap<>();
    private static final AtomicInteger COUNTER = new AtomicInteger();

    @Override
    public void start(Future<Void> startFuture) throws Exception {
        Router router = Router.router(vertx);

        router.route("/").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/html").end("<h1>Meu Primeiro Server com Vertx Funcionando</h1>");
        });

        router.route("/api/contact*").handler(BodyHandler.create());
        router.post("/api/contact").handler(this::addContact);

        vertx.createHttpServer().requestHandler(router::accept).listen(8080, ar -> {
            if (ar.succeeded()) {
                startFuture.complete();
            } else {
                startFuture.fail(ar.cause());
            }
        });

    }

    private void addContact(RoutingContext context) {
        Contact contact = Json.decodeValue(context.getBodyAsString(), Contact.class);
        contact.setId(COUNTER.getAndIncrement());
        contacts.put(contact.getId(), contact);

        context.response()
            .setStatusCode(HttpResponseStatus.CREATED.code())
            .putHeader("content-type", "application/json; charset=utf-8")
            .end(Json.encodePrettily(contact));
    }
    
    private void handleGet(RoutingContext routingContext) {
        HttpServerResponse response = routingContext.response();
        String welcome = routingContext.request().getParam("welcome");
        response.end("Reply: " + welcome);
    }
}