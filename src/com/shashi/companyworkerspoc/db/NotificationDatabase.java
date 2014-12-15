package com.shashi.companyworkerspoc.db;

import com.j256.ormlite.field.DatabaseField;

//@Table(name = "WorkerNotification")

public class NotificationDatabase {

	@DatabaseField(generatedId = true, canBeNull = false)
	int id;
	@DatabaseField(canBeNull = true)
	String groupName;
	@DatabaseField(canBeNull = true)
	String timeToReport;
	@DatabaseField(canBeNull = true)
	String locationToReport;
	@DatabaseField(canBeNull = true)
	String readStatus;
	@DatabaseField(canBeNull = true)
	String comments;

	public NotificationDatabase() {
		// TODO Auto-generated constructor stub
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getTimeToReport() {
		return timeToReport;
	}

	public void setTimeToReport(String timeToReport) {
		this.timeToReport = timeToReport;
	}

	public String getLocationToReport() {
		return locationToReport;
	}

	public void setLocationToReport(String locationToReport) {
		this.locationToReport = locationToReport;
	}

	public String getReadStatus() {
		return readStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setReadStatus(String readStatus) {
		this.readStatus = readStatus;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("id=").append(id);
		sb.append(", ").append(groupName);
		sb.append(", ").append(locationToReport);
		sb.append(", ").append(timeToReport);
		sb.append(", ").append(readStatus);
		sb.append(", ").append(comments);
		return sb.toString();
	}
}
