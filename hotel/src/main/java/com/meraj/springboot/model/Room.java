package com.meraj.springboot.model;

public class Room {

	public int roomId;
	public long hotelId;
	public String roomType;
	public int roomRent;
	public String bookingStatus;
	public String paymentStatus;

	@Override
	public String toString() {
		return "Room [roomId=" + roomId + ", hotelId=" + hotelId + ", roomType=" + roomType + ", roomRent=" + roomRent
				+ ", bookingStatus=" + bookingStatus + ", paymentStatus=" + paymentStatus + "]";
	}

	public Room(int roomId, long hotelId, String roomType, int roomRent, String bookingStatus, String paymentStatus) {
		super();
		this.roomId = roomId;
		this.hotelId = hotelId;
		this.roomType = roomType;
		this.roomRent = roomRent;
		this.bookingStatus = bookingStatus;
		this.paymentStatus = paymentStatus;
	}

	public int getRoomId() {
		return roomId;
	}

	public void setRoomId(int roomId) {
		this.roomId = roomId;
	}

	public long getHotelId() {
		return hotelId;
	}

	public void setHotelId(long hotelId) {
		this.hotelId = hotelId;
	}

	public String getRoomType() {
		return roomType;
	}

	public void setRoomType(String roomType) {
		this.roomType = roomType;
	}

	public int getRoomRent() {
		return roomRent;
	}

	public void setRoomRent(int roomRent) {
		this.roomRent = roomRent;
	}

	public String getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(String bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

}
