package ch.zli.m223.service;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import io.quarkus.arc.profile.IfBuildProfile;
import io.quarkus.runtime.StartupEvent;
import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.BookingTime;

@IfBuildProfile("dev")
@ApplicationScoped
public class TestDataService {
    
    @Inject
    EntityManager entityManager;

    @Transactional
    void generateTestData(@Observes StartupEvent event) {
        //ApplicationUser
        var raphe = new ApplicationUser();
        raphe.setFirstname("Raphael");
        raphe.setLastname("Stricker");
        raphe.setEmail("raphael.stricker@gmail.com");
        raphe.setPassword("1234");
        raphe.setIsAdmin(true);

        var rem = new ApplicationUser();
        rem.setFirstname("Nicolas");
        rem.setLastname("Mueller");
        rem.setEmail("nicolas.mueller@lernende.bww.ch");
        rem.setPassword("12345");
        rem.setIsAdmin(false);

        var mark = new ApplicationUser();
        mark.setFirstname("Cedric");
        mark.setLastname("Markstaller");
        mark.setEmail("cedric.markstaller@lernende.bww.ch");
        mark.setPassword("123456");
        mark.setIsAdmin(false);
        
        //Bookings
        var booking1 = new Booking();
        booking1.setDate(LocalDate.parse("'2022-12-07'"));
        booking1.setBookingTime(BookingTime.MORNING);

        var booking2 = new Booking();
        booking2.setDate(LocalDate.parse("'2022-12-08'"));
        booking2.setBookingTime(BookingTime.AFTERNOON);

        var booking3 = new Booking();
        booking3.setDate(LocalDate.parse("'2022-12-08'"));
        booking3.setBookingTime(BookingTime.FULLDAY);
    }
}
