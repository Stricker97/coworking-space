package ch.zli.m223;

import java.time.LocalDate;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import ch.zli.m223.model.ApplicationUser;
import ch.zli.m223.model.Booking;
import ch.zli.m223.model.BookingTime;
import io.quarkus.arc.profile.IfBuildProfile;

@IfBuildProfile("dev")
@ApplicationScoped
public class IntegrationTestDataService {

  @Inject
  EntityManager entityManager;


  @Transactional
  void reloadDatabase() {
    entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY FALSE").executeUpdate();
    entityManager.createNativeQuery("TRUNCATE TABLE entry_tags RESTART IDENTITY").executeUpdate();
    entityManager.createNativeQuery("TRUNCATE TABLE applicationuser RESTART IDENTITY").executeUpdate();
    entityManager.createNativeQuery("TRUNCATE TABLE category RESTART IDENTITY").executeUpdate();
    entityManager.createNativeQuery("TRUNCATE TABLE entry RESTART IDENTITY").executeUpdate();
    entityManager.createNativeQuery("SET REFERENTIAL_INTEGRITY TRUE").executeUpdate();
    generateTestData();
  }

  @Transactional
  void generateTestData() {
    //ApplicationUser
    var raphe = new ApplicationUser();
    raphe.setFirstname("Raphael");
    raphe.setLastname("Stricker");
    raphe.setEmail("raphael.stricker@gmail.com");
    raphe.setPassword("1234");
    raphe.setReenteredPassword("1234");
    raphe.setIsAdmin(true);

    var rem = new ApplicationUser();
    rem.setFirstname("Nicolas");
    rem.setLastname("Mueller");
    rem.setEmail("nicolas.mueller@lernende.bww.ch");
    rem.setPassword("12345");
    rem.setReenteredPassword("12345");
    rem.setIsAdmin(false);

    var mark = new ApplicationUser();
    mark.setFirstname("Cedric");
    mark.setLastname("Markstaller");
    mark.setEmail("cedric.markstaller@lernende.bww.ch");
    mark.setPassword("123456");
    mark.setReenteredPassword("12345");
    mark.setIsAdmin(false);
    
    //Bookings
    var booking1 = new Booking();
    booking1.setDate(LocalDate.parse("'2022-12-07'"));
    booking1.setBookingTime(BookingTime.MORNING);
    booking1.setIsAccepted(true);

    var booking2 = new Booking();
    booking2.setDate(LocalDate.parse("'2022-12-08'"));
    booking2.setBookingTime(BookingTime.AFTERNOON);
    booking2.setIsAccepted(true);

    var booking3 = new Booking();
    booking3.setDate(LocalDate.parse("'2022-12-08'"));
    booking3.setBookingTime(BookingTime.FULLDAY);
    booking3.setIsAccepted(false);
    }
}
