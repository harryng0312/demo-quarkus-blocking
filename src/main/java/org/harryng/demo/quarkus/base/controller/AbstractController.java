package org.harryng.demo.quarkus.base.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.mutiny.core.Vertx;

import javax.inject.Inject;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractController {

    @Inject
    Vertx vertx;
    @Inject
    ObjectMapper objectMapper;
//    @Inject
//    HttpRequest serverRequest;
//    @Inject
//    HttpResponse serverResponse;
    // @Inject
    // protected RoutingContext routingContext;

//    @Inject
//    protected Uni<Mutiny.Session> transSession;

//    public HttpRequest getServerRequest() {
//        return serverRequest;
//    }
//
//    public HttpResponse getServerResponse() {
//        return serverResponse;
//    }

    // public RoutingContext getRoutingContext(){
        // return routingContext;
    // }

    public Vertx getVertx() {
        return vertx;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
