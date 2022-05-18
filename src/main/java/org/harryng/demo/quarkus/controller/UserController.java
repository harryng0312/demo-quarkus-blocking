package org.harryng.demo.quarkus.controller;

import org.harryng.demo.quarkus.base.controller.AbstractController;
import org.harryng.demo.quarkus.user.entity.UserImpl;
import org.harryng.demo.quarkus.user.service.UserService;
import org.harryng.demo.quarkus.util.SessionHolder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;

@ApplicationScoped
// @RequestScoped
@Path("/user")
@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
public class UserController extends AbstractController {
    @Inject
    protected UserService userService;
    protected Logger logger = LoggerFactory.getLogger(UserController.class);

    @GET
    @Path("/get-user-by-id")
    public Response getUserByIdBlock(@QueryParam("id") long id) {
        var rs = Response.ok().build();
        try {
            var strBuilder = new StringBuilder();
//            strBuilder.append(getServerRequest().uri()).append("\n");
            var user = userService.getById(SessionHolder.createAnonymousSession(), id, Collections.emptyMap());
            rs = Response.ok().entity(user).build();
        } catch (Exception e) {
            logger.error("", e);
        }
        return rs;
    }

    @POST
    @Path("/add-user")
    public Response addUser(String reqBodyStr) throws RuntimeException, Exception {
        var rs = Response.ok().build();
        try {
            var strBuilder = new StringBuilder();
//            strBuilder.append(getServerRequest().uri()).append("\n");
            var user = userService.add(SessionHolder.createAnonymousSession(),
                    getObjectMapper().readValue(reqBodyStr, UserImpl.class), Collections.emptyMap());
            rs = Response.ok().entity(user).build();
        } catch (Exception e) {
            logger.error("", e);
        }
        return rs;
    }

    @PUT
    @Path("/edit-user")
    public Response editUser(String reqBodyStr) throws RuntimeException, Exception {
        var rs = Response.ok().build();
        try {
            var strBuilder = new StringBuilder();
//            strBuilder.append(getServerRequest().uri()).append("\n");
            var user = userService.edit(SessionHolder.createAnonymousSession(),
                    getObjectMapper().readValue(reqBodyStr, UserImpl.class), Collections.emptyMap());
            rs = Response.ok().entity(user).build();
        } catch (Exception e) {
            logger.error("", e);
        }
        return rs;
    }

    @DELETE
    @Path("/remove-user")
    public Response removeUser(@QueryParam("id") long id) throws RuntimeException, Exception {
        var rs = Response.ok().build();
        try {
            var strBuilder = new StringBuilder();
//            strBuilder.append(getServerRequest().uri()).append("\n");
            var user = userService.remove(SessionHolder.createAnonymousSession(), id, Collections.emptyMap());
            rs = Response.ok().entity(user).build();
        } catch (Exception e) {
            logger.error("", e);
        }
        return rs;
    }

}
