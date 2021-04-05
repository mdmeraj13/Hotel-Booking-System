package com.meraj.springboot.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.meraj.springboot.model.Hotel;
import com.meraj.springboot.model.Query;
import com.meraj.springboot.model.Room;
import com.meraj.springboot.model.Support;
import com.meraj.springboot.repositories.HotelRepository;
import com.meraj.springboot.repositories.SupportRepository;

@RestController
@RequestMapping("/query")
public class QueryController {
	
	@Autowired
	SupportRepository supportRepo;
	@Autowired
	HotelRepository hotelRepo;
	
	
	
	@GetMapping("/all")
	public List<Room> findAllRoomStatus(@RequestBody Query query){
		LocalDate startDate = query.getStartDate();
		LocalDate endDate = query.getEndDate();
		
		LocalDate today = LocalDate.now();
		if(startDate.isBefore(today)  || endDate.isBefore(today) || endDate.isBefore(startDate)) {
			return null;
		}
		List<Room> result  = new ArrayList<Room>();
		long hotelId = query.getId();
		Hotel hotel  = hotelRepo.findById(hotelId).get();
		int noOfRooms = hotel.getRooms();
		Support support = supportRepo.findById(hotelId).get();
		int[] finalRoomResult = new int[noOfRooms];
		HashMap<LocalDate,int[]> currentStatus = support.getRoomStatus();
		
		
		for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			if(currentStatus.containsKey(date)) {
				int[] currentRoomStatus = currentStatus.get(date);
				for(int i=0;i<currentRoomStatus.length;i++) {
					finalRoomResult[i]= currentRoomStatus[i];
				}
			}
		}
		
		for(int i=0;i<finalRoomResult.length;i++) {
			String roomType;
			int roomRent;
			if((i+1)%4==0) { roomType = "Double Bed";  roomRent=999; }
			else if((i+1)%5==0) { if((i+1)%10==0){ roomType = "Queen"; roomRent=2999; }else { roomType="King"; roomRent=2999; }}
			else if((i+1)%7==0) { roomType="Master BedRoom"; roomRent=5999; }
			else { roomType="Single Bed";  roomRent=599; }
			if(finalRoomResult[i]==0) {
				String bookingStatus = "Un-Booked";
				String paymentStatus = "Un-Paid";
				Room room = new Room(i+1,hotelId,roomType,roomRent,bookingStatus,paymentStatus);
				result.add(room);
			}else if(finalRoomResult[i]==1){
				String bookingStatus = "Booked";
				String paymentStatus = "Un-Paid";
				Room room = new Room(i+1,hotelId,roomType,roomRent,bookingStatus,paymentStatus);
				result.add(room);
			}else if(finalRoomResult[i]==2){
				String bookingStatus = "Booked";
				String paymentStatus = "Paid";
				Room room = new Room(i+1,hotelId,roomType,roomRent,bookingStatus,paymentStatus);
				result.add(room);
			}
			
		}
		
		return result;
	}
	
	@GetMapping
	public List<Room> findRoomStatus(@RequestBody Query query){
		LocalDate startDate = query.getStartDate();
		LocalDate endDate = query.getEndDate();
		
		LocalDate today = LocalDate.now();
		if(startDate.isBefore(today)  || endDate.isBefore(today) || endDate.isBefore(startDate)) {
			return null;
		}
		List<Room> result  = new ArrayList<Room>();
		long hotelId = query.getId();
		Hotel hotel  = hotelRepo.findById(hotelId).get();
		int noOfRooms = hotel.getRooms();
		Support support = supportRepo.findById(hotelId).get();
		int[] finalRoomResult = new int[noOfRooms];
		HashMap<LocalDate,int[]> currentStatus = support.getRoomStatus();
		
		
		for(LocalDate date = startDate; date.isBefore(endDate); date = date.plusDays(1)) {
			if(currentStatus.containsKey(date)) {
				int[] currentRoomStatus = currentStatus.get(date);
				for(int i=0;i<currentRoomStatus.length;i++) {
					finalRoomResult[i]= currentRoomStatus[i];
				}
			}
		}
		
		for(int i=0;i<finalRoomResult.length;i++) {
			String roomType;
			int roomRent;
			if((i+1)%4==0) { roomType = "Double Bed";  roomRent=999; }
			else if((i+1)%5==0) { if((i+1)%10==0){ roomType = "Queen"; roomRent=2999; }else { roomType="King"; roomRent=2999; }}
			else if((i+1)%7==0) { roomType="Master BedRoom"; roomRent=5999; }
			else { roomType="Single Bed";  roomRent=599; }
			if(finalRoomResult[i]==0) {
				String bookingStatus = "Un-Booked";
				String paymentStatus = "Un-Paid";
				Room room = new Room(i+1,hotelId,roomType,roomRent,bookingStatus,paymentStatus);
				result.add(room);
			}
		}
		
		return result;
	}
	
}
