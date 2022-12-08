package ch.zli.m223.controller;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.ApplicationUserService;


@Path("/registration")
@Tag(name = "User", description = "Handling of user")
@RolesAllowed({ "User" })
public class ApplicationUserController {
  
  @Inject
  ApplicationUserService userService;

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Creates a new user. Also known as registration.", 
      description = "Creates a new user if the reentered password matches the first one."
  )
  @PermitAll
  public void create(ApplicationUser applicationUser) {
    if(applicationUser.getPassword().equals(applicationUser.getReenteredPassword())) {
        userService.createUser(applicationUser);
    }
  }

  @Path("/changePassword/{id}")
  @PATCH
  @Operation(
      summary = "Updates the password of the user.",
      description = "Updates the password by the id of its user."
  )
  public ApplicationUser updatePassword(@PathParam("id") Long id, String newPassword) {
      return userService.updatePassword(id, newPassword);
  }
}
