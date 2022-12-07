package ch.zli.m223.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
public class Booking {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(readOnly = true)
    private Long id;

    @Column(nullable = false)
    private LocalDate date;

    @Enumerated(EnumType.STRING)
    private BookingTime bookingTime;

    public LocalDate getDate (){
        return date;
    }

    public void setDate (LocalDate date) {
        this.date = date;
    }

    public BookingTime getBookingTime() {
        return bookingTime;
    }

    public void setBookingTime(BookingTime bookingTime) {
        this.bookingTime = bookingTime;
    }
}
