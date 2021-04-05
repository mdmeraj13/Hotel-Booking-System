package com.meraj.springboot.model;

import java.time.LocalDate;
import java.util.HashMap;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "support")
public class Support {
	@Id
	private long id;
	private HashMap<LocalDate, int[]> roomStatus;

	@Override
	public String toString() {
		return "Support [id=" + id + ", roomStatus=" + roomStatus + "]";
	}

	public Support(long id, HashMap<LocalDate, int[]> roomStatus) {
		super();
		this.id = id;
		this.roomStatus = roomStatus;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public HashMap<LocalDate, int[]> getRoomStatus() {
		return roomStatus;
	}

	public void setRoomStatus(HashMap<LocalDate, int[]> roomStatus) {
		this.roomStatus = roomStatus;
	}

	/*
	 * private HashMap<LocalDate, boolean[]> roomStatus;
	 * 
	 * @Override public String toString() { return "Support [id=" + id +
	 * ", roomStatus=" + roomStatus + "]"; }
	 * 
	 * public Support(long id, HashMap<LocalDate, boolean[]> roomStatus) { super();
	 * this.id = id; this.roomStatus = roomStatus; }
	 * 
	 * 
	 * public long getId() { return id; }
	 * 
	 * public void setId(long id) { this.id = id; }
	 * 
	 * public HashMap<LocalDate, boolean[]> getRoomStatus() { return roomStatus; }
	 * 
	 * public void setRoomStatus(HashMap<LocalDate, boolean[]> roomStatus) {
	 * this.roomStatus = roomStatus; }
	 */

}
