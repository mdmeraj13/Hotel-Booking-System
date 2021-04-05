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

import com.meraj.springboot.model.Hotel;
import com.meraj.springboot.model.Support;
import com.meraj.springboot.repositories.HotelRepository;
import com.meraj.springboot.repositories.SupportRepository;

@RestController
@RequestMapping("/hotel")
public class HotelController {
    
	@Autowired
	HotelRepository hotelRepo;
	
	@Autowired 
	SupportRepository supportRepo;
	
	@GetMapping("/all")
	public List<Hotel> findAll() {
		return hotelRepo.findAll();
	}
	
	@PostMapping("/insert")
	public Hotel createHotel(@RequestBody Hotel hotel){
		Hotel insertedHotel = hotelRepo.insert(hotel);
		
		long id = hotel.getId();
		HashMap<LocalDate,int[]> roomStatus = new HashMap<LocalDate,int[]>();
		Support support = new Support(id,roomStatus);
		supportRepo.insert(support);
		return insertedHotel;
	}
	
	@PutMapping("/updatestatus/{id}")
	public Hotel updateStatus(@PathVariable long id) {
		Hotel hotel = hotelRepo.findById(id).get();
		hotel.setStatus(!hotel.isStatus());
		hotelRepo.save(hotel);
		return  hotel;
	}
	
	@GetMapping("/{location}")
	public List<Hotel> findByLocation(@PathVariable String location){
		return hotelRepo.findByLocationAndStatus(location,true);
	}
}
