package com.cognixia.jump.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.time.LocalDateTime;

public class Transaction implements Serializable {

	private static final long serialVersionUID = 1L;

	private long id;

	private String description;

	private int initialAccountId;
	private int endAccountId;

	private LocalDateTime timestamp;
	
	public Transaction() {
		this("N/A", 0, 0, LocalDateTime.now());
		this.id = -1L;
	}

	public Transaction(String description, int initialAccountId, int endAccountId, LocalDateTime timestamp) {
		super();
		this.id = -1L;
		this.description = description;
		this.initialAccountId = initialAccountId;
		this.endAccountId = endAccountId;
		this.timestamp = timestamp;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getInitialAccountId() {
		return initialAccountId;
	}

	public void setInitialAccountId(int initialAccountId) {
		this.initialAccountId = initialAccountId;
	}

	public int getEndAccountId() {
		return endAccountId;
	}

	public void setEndAccountId(int endAccountId) {
		this.endAccountId = endAccountId;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "Transaction [id=" + id + ", description=" + description + ", initialAccountId=" + initialAccountId
				+ ", endAccountId=" + endAccountId + ", timestamp=" + timestamp + "]";
	}

}
