package org.harryng.demo.user;

import io.quarkus.test.junit.QuarkusTest;
import io.vertx.core.impl.logging.Logger;
import io.vertx.core.impl.logging.LoggerFactory;
import org.harryng.demo.quarkus.user.service.UserService;
import org.harryng.demo.quarkus.util.SessionHolder;
import org.harryng.demo.quarkus.util.page.PageInfo;
import org.junit.jupiter.api.Test;

import javax.inject.Inject;
import java.util.HashMap;
import java.util.Map;

@QuarkusTest
public class TestUser {

    static Logger logger = LoggerFactory.getLogger(TestUser.class);

    @Inject
    protected UserService userService;

    @Test
    public void selectUser() throws Exception {
        var pageInfo = new PageInfo(0, 2);
        var users = userService.findByConditionPaged(
                SessionHolder.createAnonymousSession(),
                "username = :username",
                pageInfo,
                Map.of("username", "username 3"),
                new HashMap<>()
        );
        logger.info("list: " + users.getContent().size() + " total: " + users.getTotal());
    }

}
