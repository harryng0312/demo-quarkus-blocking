package org.harryng.demo.quarkus.router.ws;

import org.harryng.demo.quarkus.base.controller.AbstractController;
import org.harryng.demo.quarkus.user.entity.UserImpl;
import org.harryng.demo.quarkus.user.service.UserService;
import org.harryng.demo.quarkus.util.SessionHolder;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import java.util.HashMap;

@Singleton
@Named("userRouter")
public class UserRouter extends AbstractController {

    @Inject
    protected UserService userService;

    public UserImpl getUserById(long id) throws Exception {
        return userService.getById(SessionHolder.createAnonymousSession(), id, new HashMap<>());
    }

    public int addUser(UserImpl user) throws Exception {
        return userService.add(SessionHolder.createAnonymousSession(), user, new HashMap<>());
    }
}
