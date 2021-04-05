package com.meraj.springboot.model;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "booking")
public class BookingDetails {
	@Id
	private long userId;

	private long hotelId;
	private int noOfRooms;
	private String msg;
	private List<Integer> roomsList;
	private String location;
	private boolean paymentStatus;
	private LocalDate startDate;
	private LocalDate endDate;

	@Override
	public String toString() {
		return "BookingDetails [userId=" + userId + ", hotelId=" + hotelId + ", noOfRooms=" + noOfRooms + ", msg=" + msg
				+ ", roomsList=" + roomsList + ", location=" + location + ", paymentStatus=" + paymentStatus
				+ ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	public BookingDetails(long userId, long hotelId, int noOfRooms, String msg, List<Integer> roomsList,
			String location, boolean paymentStatus, LocalDate startDate, LocalDate endDate) {
		super();
		this.userId = userId;
		this.hotelId = hotelId;
		this.noOfRooms = noOfRooms;
		this.msg = msg;
		this.roomsList = roomsList;
		this.location = location;
		this.paymentStatus = paymentStatus;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public int getNoOfRooms() {
		return noOfRooms;
	}

	public void setNoOfRooms(int noOfRooms) {
		this.noOfRooms = noOfRooms;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public List<Integer> getRoomsList() {
		return roomsList;
	}

	public void setRoomsList(List<Integer> roomsList) {
		this.roomsList = roomsList;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public boolean isPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(boolean paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public LocalDate getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}

	public LocalDate getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}

}
