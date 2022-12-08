package ch.zli.m223.controller;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.inject.Inject;
import javax.validation.Valid;
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

import ch.zli.m223.model.Booking;
import ch.zli.m223.service.BookingService;

@Path("/bookings")
@Tag(name = "Bookings", description = "Handling of bookings")
@RolesAllowed({ "Admin" })
public class BookingController {

    @Inject
    BookingService bookingService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(summary = "Index all bookings.", description = "Returns a list of all bookings.")
    public List<Booking> index() {
        return bookingService.findAll();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Operation(
        summary = "Creates a new booking.",
        description = "Creates a new booking and returns the newly added booking."
    )
    @RolesAllowed({ "User", "Admin" })
    public Booking create(@Valid Booking booking) {
        return bookingService.createBooking(booking);
    }

    @Path("/{id}")
    @GET
    @Operation(
        summary = "Reads a booking.",
        description = "Reads a booking by its id."
    )
    public void book(@PathParam("id") Long id) {
        bookingService.readBooking(id);
    }

    @Path("/{id}")
    @DELETE
    @Operation(
        summary = "Deletes a booking.",
        description = "Deletes a booking by its id."
    )
    public void delete(@PathParam("id") Long id) {
        bookingService.deleteBooking(id);
    }

    @Path("/{id}")
    @DELETE
    @Operation(
        summary = "Deletes a booking of the user.",
        description = "Deletes a booking by its id."
    )
    @RolesAllowed({ "User", "Admin" })
    public void deleteOwn(@PathParam("id") Long id) {
        bookingService.deleteOwnBooking(id);
    }

    @Path("/{id}")
    @PUT
    @Operation(
        summary = "Updates a booking.",
        description = "Updates a booking by its id."
    )
    public Booking update(@PathParam("id") Long id, @Valid Booking booking) {
        return bookingService.updateBooking(id, booking);
    }

    @Path("/{id}")
    @PUT
    @Operation(
        summary = "Updates a booking of a user.",
        description = "Updates a booking by its id."
    )
    @RolesAllowed({ "User", "Admin" })
    public Booking updateOwn(@PathParam("id") Long id, @Valid Booking booking) {
        return bookingService.updateOwnBooking(id);
    }

    @Path("/{id}")
    @PATCH
    @Operation(
        summary = "Accepts or declines a booking of a user.",
        description = "Accepts or declines a booking by its id."
    )
    public void accept(@PathParam("id") Long id, Booking booking, Boolean isAccepted) {
        bookingService.accept(id, booking, isAccepted);
    }
}
