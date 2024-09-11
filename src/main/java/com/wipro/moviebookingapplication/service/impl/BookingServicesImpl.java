package com.wipro.moviebookingapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wipro.moviebookingapplication.entity.Booking;
import com.wipro.moviebookingapplication.exceptions.ResourceNotFoundException;
import com.wipro.moviebookingapplication.repository.BookingRepository;
import com.wipro.moviebookingapplication.service.BookingServices;



@Service
public class BookingServicesImpl implements BookingServices {
	
	@Autowired
    private BookingRepository bookingRepository;

	@Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

	@Override
    public Booking getBookingById(int id) {
        return bookingRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with id: " + id));
    }

	@Override
    public Booking saveBooking(Booking booking) {
        return bookingRepository.save(booking);
    }

	@Override
    public Booking updateBooking(int id, Booking bookingDetails) {
        Booking booking = getBookingById(id);
        booking.setBooking_date(bookingDetails.getBooking_date());
        booking.setNumber_of_tickets(bookingDetails.getNumber_of_tickets());
        booking.setTotal_price(bookingDetails.getTotal_price());
        booking.setUser(bookingDetails.getUser());
        booking.setShowtime(bookingDetails.getShowtime());
        return bookingRepository.save(booking);
    }

	@Override
    public void deleteBooking(int id) {
        Booking booking = getBookingById(id);
        bookingRepository.delete(booking);
    }
    
}
