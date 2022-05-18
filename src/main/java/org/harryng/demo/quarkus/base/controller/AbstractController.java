package org.harryng.demo.quarkus.base.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.vertx.core.http.HttpServerRequest;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.mutiny.core.Vertx;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public abstract class AbstractController {

    @Inject
    Vertx vertx;
    @Inject
    ObjectMapper objectMapper;
//
    @Inject
    HttpServletRequest httpServletRequest;

    @Inject
    HttpServletResponse httpServletResponse;

    public HttpServletRequest getHttpServletRequest() {
        return httpServletRequest;
    }

    public HttpServletResponse getHttpServletResponse() {
        return httpServletResponse;
    }

    public Vertx getVertx() {
        return vertx;
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }
}
