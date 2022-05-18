package org.harryng.demo.quarkus.base.router;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.mutiny.core.Vertx;

import javax.inject.Inject;

public abstract class AbstractRouter {

    @Inject
    Vertx vertx;
    @Inject
    ObjectMapper objectMapper;
//    @Inject
//    HttpServerRequest serverRequest;
//    @Inject
//    HttpServerResponse serverResponse;
    // @Inject
    // protected RoutingContext routingContext;

//    @Inject
//    protected Uni<Mutiny.Session> transSession;

//    public HttpServerRequest getServerRequest() {
//        return serverRequest;
//    }
//
//    public HttpServerResponse getServerResponse() {
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
