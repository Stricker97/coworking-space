package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PATCH;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.openapi.annotations.Operation;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.service.ApplicationUserService;


@Path("/users")
@Tag(name = "Users", description = "Handling of users")
@RolesAllowed({ "Admin" })
public class ApplicationUserController {
  
  @Inject
  ApplicationUserService userService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Index all users.", 
      description = "Returns a list of all users."
  )
  public List<ApplicationUser> index() {
      return userService.findAll();
  }

  @POST
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  @Operation(
      summary = "Creates a new user. Also known as registration.", 
      description = "Creates a new user and returns the newly added user."
  )
  @PermitAll
  @RolesAllowed({ "User", "Admin" })
  public ApplicationUser create(ApplicationUser user) {
     return userService.createUser(user);
  }

  @Path("/{id}")
  @GET
  @Operation(
      summary = "Reads an user.",
      description = "Reads an user by its id."
  )
  public void read(@PathParam("id") Long id) {
      userService.readUser(id);
  }

  @Path("/{id}")
  @DELETE
  @Operation(
      summary = "Deletes an user.",
      description = "Deletes an user by its id."
  )
  public void delete(@PathParam("id") Long id) {
      userService.deleteUser(id);
  }

  @Path("/{id}")
  @PUT
  @Operation(
      summary = "Updates an user.",
      description = "Updates an user by its id."
  )
  public ApplicationUser update(@PathParam("id") Long id, ApplicationUser user) {
      return userService.updateUser(id, user);
  }

  @Path("/{id}")
  @PATCH
  @Operation(
      summary = "Updates the password of the user.",
      description = "Updates the password by the id of its user."
  )
  public ApplicationUser updatePassword(@PathParam("id") Long id, String newPassword) {
      return userService.updatePassword(id, newPassword);
  }
}
