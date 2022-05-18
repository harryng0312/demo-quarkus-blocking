package org.harryng.demo.quarkus.router;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.smallrye.mutiny.unchecked.Unchecked;
import io.vertx.core.Future;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.RoutingContext;
import org.harryng.demo.quarkus.base.router.AbstractRouter;
import org.harryng.demo.quarkus.base.service.BaseService;
import org.harryng.demo.quarkus.router.ws.MethodMapperRouter;
import org.harryng.demo.quarkus.util.ReactiveUtil;
import org.harryng.demo.quarkus.util.SessionHolder;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.LinkedHashMap;
import java.util.Map;

@ApplicationScoped
public class CommonWsRouter extends AbstractRouter {

    static Logger logger = LoggerFactory.getLogger(CommonWsRouter.class);

    @Inject
    protected MethodMapperRouter mapperRouter;

    protected String getMethodId(JsonObject rootJson) {
        return rootJson.getString("method");
    }

    protected Object[] getParamValues(JsonArray paramsJsonArr, Class<?>[] paramClasses) throws Exception {
        var paramValues = new Object[paramsJsonArr.size()];
        if (paramsJsonArr.size() == paramClasses.length - 2) {
            for (int i = 0; i < paramsJsonArr.size(); i++) {
                try {
                    paramValues[i] = getObjectMapper().readValue(
                            paramsJsonArr.getValue(i).toString(),
                            paramClasses[i + 1]);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            throw new Exception("method params are not matched");
        }
        return paramValues;
    }

    protected SessionHolder getSessionHolder(RoutingContext ctx) {
        return SessionHolder.createAnonymousSession();
    }

    public void handleDefault(RoutingContext ctx) {
        // accept websocket

    }

    public void handleError(RoutingContext ctx) {
        logger.error("Error[" + ctx.response().getStatusCode() + "]:" + ctx.response().getStatusMessage());
        ctx.response().end(ctx.response().getStatusMessage()).compose(v -> Future.succeededFuture());
    }
}
