package com.wipro.moviebookingapplication.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.wipro.moviebookingapplication.entity.Booking;

@Service
public interface BookingServices {

    List<Booking> getAllBookings();

    Booking getBookingById(int id);

    Booking saveBooking(Booking booking);

    Booking updateBooking(int id, Booking bookingDetails);

    void deleteBooking(int id);

}
