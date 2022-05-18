package org.harryng.demo.quarkus.router;

import io.vertx.core.Future;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.ext.web.RoutingContext;
import io.vertx.mutiny.core.http.HttpServerResponse;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class HttpRouter {
    static Logger logger = LoggerFactory.getLogger(HttpRouter.class);

    public void handleDefault(RoutingContext context) {
        logger.info("into /http handler");
        context.next();
    }

    public void handleError(RoutingContext context, HttpServerResponse response) {
        logger.error("Error[" + context.response().getStatusCode() + "]:" + context.response().getStatusMessage());
        if (!response.ended()) {
            response.putHeader("X-Correlation-ID", context.request().getHeader("X-Correlation-ID"));
            int sttCode = context.response().getStatusCode();
            context.response().setStatusCode(sttCode < 300 ? 500 : sttCode);
            response.end(String.join("",
                            "{" +
                                    "\"correlationId\":",
                            "\"" + context.request().getHeader("X-Correlation-ID") + "\",",
                            "\"code\":",
                            "\"" + context.response().getStatusCode() + "\",",
                            "\"message\":\"",
                            context.response().getStatusMessage(), "\"}"))
                    .subscribe().with(v -> Future.succeededFuture());
        }
        context.next();
    }
}
