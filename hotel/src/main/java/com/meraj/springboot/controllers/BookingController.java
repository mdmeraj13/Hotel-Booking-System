package com.meraj.springboot.controllers;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meraj.springboot.model.BookingDetails;
import com.meraj.springboot.model.Hotel;
import com.meraj.springboot.model.Support;
import com.meraj.springboot.repositories.BookingRepository;
import com.meraj.springboot.repositories.HotelRepository;
import com.meraj.springboot.repositories.SupportRepository;

@RestController
@RequestMapping("/booking")
public class BookingController {

	@Autowired
	HotelRepository hotelRepo;

	@Autowired
	BookingRepository bookingRepo;

	@Autowired
	SupportRepository supportRepo;
	
	
	@GetMapping("/all")
	public List<BookingDetails> findAllBooking(){
		return bookingRepo.findAll();
	}
	

	// 2-> paid 1->un-paid 0->unbooked

	@PostMapping
	public BookingDetails bookingHotel(@RequestBody BookingDetails booking) {
		LocalDate startDate = booking.getStartDate();
		LocalDate endDate = booking.getEndDate();
		LocalDate today = LocalDate.now();
		
		if(startDate.isBefore(today)  || endDate.isBefore(today) || endDate.isBefore(startDate)) {
			booking.setHotelId(0);
			booking.setUserId(0);
			booking.setLocation(null);
			booking.setEndDate(null);
			booking.setNoOfRooms(0);
			booking.setPaymentStatus(false);
			booking.setRoomsList(null);
			booking.setStartDate(null);

			booking.setMsg("*** Error *** Enter Date is Not in proper manner");
			return booking;
		}
		List<Integer> roomRequest = booking.getRoomsList();
		int noOfRooms = booking.getNoOfRooms();
		if (noOfRooms != roomRequest.size()) {
			booking.setHotelId(0);
			booking.setUserId(0);
			booking.setLocation(null);
			booking.setEndDate(null);
			booking.setNoOfRooms(0);
			booking.setPaymentStatus(false);
			booking.setRoomsList(null);
			booking.setStartDate(null);

			booking.setMsg("*** Error *** noOfRooms and roomRequest list length is not equale");
			return booking;
		}

		long hotelId = booking.getHotelId();
		boolean paymentStatus = booking.isPaymentStatus();
		Support support = supportRepo.findById(hotelId).get();
		Hotel hotel = hotelRepo.findById(hotelId).get();
		int hotelRooms = hotel.getRooms();
		HashMap<LocalDate, int[]> currentStatus = support.getRoomStatus();

		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			if (currentStatus.containsKey(date) == true) {
				int[] currentRoomStatus = currentStatus.get(date);

				for (Integer num : roomRequest) {
					if (currentRoomStatus[num - 1] != 0) {
						booking.setHotelId(0);
						booking.setUserId(0);
						booking.setLocation(null);
						booking.setEndDate(null);
						booking.setNoOfRooms(0);
						booking.setPaymentStatus(false);
						booking.setRoomsList(null);
						booking.setStartDate(null);
						booking.setMsg("*** Error *** Room is Already Booked in your preferred time");
						return booking;
					} else {
						if (paymentStatus == true) {
							currentRoomStatus[num - 1] = 2;
						} else {
							currentRoomStatus[num - 1] = 1;
						}
					}
				}
				currentStatus.replace(date, currentRoomStatus);
			} else {
				int[] currentRoomStatus = new int[hotelRooms];
				for (int i = 0; i < hotelRooms; i++) {
					currentRoomStatus[i] = 0;
				}

				for (Integer num : roomRequest) {
					if (paymentStatus == true) {
						currentRoomStatus[num - 1] = 2;
					} else {
						currentRoomStatus[num - 1] = 1;
					}
				}
				currentStatus.put(date, currentRoomStatus);
			}
		}
		support.setRoomStatus(currentStatus);
		supportRepo.save(support);

		bookingRepo.insert(booking);
		booking.setMsg("Room Successfully Booked");
		return booking;

	}
	
	
	@SuppressWarnings("null")
	@PutMapping("/cancel/{id}")
	public BookingDetails cancelBooking(@PathVariable long id) {
		if(bookingRepo.findById(id)==null){
			BookingDetails bookingDetails = new BookingDetails(0,0,0,"UserId not found",null,null, false,null,null);
			bookingDetails.setMsg("UserId not found"); 
			return bookingDetails;
	    }
		
		BookingDetails bookingDetails = bookingRepo.findById(id).get();
		if(bookingDetails==null) { 
			bookingDetails.setMsg("No any Booking is done with this Id");
			return bookingDetails;
		}
		long hotelId = bookingDetails.getHotelId();
		Support support = supportRepo.findById(hotelId).get();
		HashMap<LocalDate,int[]> roomStatus = support.getRoomStatus();
		LocalDate startDate = bookingDetails.getStartDate();
		LocalDate endDate = bookingDetails.getEndDate();
		List<Integer> roomList = bookingDetails.getRoomsList();
		boolean paymentStatus = bookingDetails.isPaymentStatus();
		
		for (LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			if(roomStatus.containsKey(date)) {
				int [] currentRooms = roomStatus.get(date);
				for(Integer num:roomList) {
					currentRooms[num-1]=0;
				}
				roomStatus.replace(date, currentRooms);
			}
		}
		
		support.setRoomStatus(roomStatus);
		supportRepo.save(support);
		
		bookingDetails.setMsg("Room Un-Booked Successfully");
		
		if(paymentStatus==true) {
			bookingDetails.setPaymentStatus(false);
		}
		
		bookingRepo.save(bookingDetails);
		
		return bookingDetails;
	}
	
	
	
	
	
	
	
	
	
	
}
