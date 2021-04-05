package com.meraj.springboot.model;

import java.time.LocalDate;

public class AlreadyBooked {
	public String Error;
	public LocalDate startDate;
	public LocalDate endDate;
	public int roomNumber;

	@Override
	public String toString() {
		return "AlreadyBooked [Error=" + Error + ", startDate=" + startDate + ", endDate=" + endDate + ", roomNumber="
				+ roomNumber + "]";
	}

	public AlreadyBooked(String error, LocalDate startDate, LocalDate endDate, int roomNumber) {
		super();
		Error = error;
		this.startDate = startDate;
		this.endDate = endDate;
		this.roomNumber = roomNumber;
	}

	public String getError() {
		return Error;
	}

	public void setError(String error) {
		Error = error;
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

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

}
