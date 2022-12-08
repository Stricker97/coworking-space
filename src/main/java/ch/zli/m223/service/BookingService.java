package ch.zli.m223.service;

import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;

@ApplicationScoped
public class BookingService {
    @Inject
    EntityManager entityManager;

    @Transactional
    public Booking createBooking(Booking booking) {
        return entityManager.merge(booking);
    }

    @Transactional
    public Booking readBooking(Long id) {
        var entity = entityManager.find(Booking.class, id);
        return entity;
    }

    @Transactional
    public void deleteBooking(Long id) {
        var entity = entityManager.find(Booking.class, id);
        entityManager.remove(entity);
    }

    @Transactional
    public void deleteOwnBooking(Long id) {
        var userId = entityManager.find(ApplicationUser.class, id);
        var booking = entityManager.find(Booking.class, userId);
        entityManager.remove(booking);
    }

    @Transactional
    public Booking updateBooking(Long id, Booking booking) {
        booking.setId(id);
        return entityManager.merge(booking);
    }

    @Transactional
    public Booking updateOwnBooking(Long id) {
        var userId = entityManager.find(ApplicationUser.class, id);
        var booking = entityManager.find(Booking.class, userId);
        return entityManager.merge(booking);
    }

    public List<Booking> findAll() {
        var query = entityManager.createQuery("FROM Entry", Booking.class);
        return query.getResultList();
    }

    @Transactional
    public void accept(Long id, Booking booking, Boolean isAccepted) {
        var entity = entityManager.find(Booking.class, id);
        entity.setIsAccepted(isAccepted);
    }
}
