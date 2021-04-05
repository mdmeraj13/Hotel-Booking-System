package com.meraj.springboot.model;

import java.time.LocalDate;

public class Query {
	public long id;
	public LocalDate startDate;
	public LocalDate endDate;

	@Override
	public String toString() {
		return "Query [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}

	public Query(long id, LocalDate startDate, LocalDate endDate) {
		super();
		this.id = id;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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
