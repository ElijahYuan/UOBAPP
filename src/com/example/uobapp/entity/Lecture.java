package com.example.uobapp.entity;

public class Lecture {
	private int id;
	private String lecture;
	private String lecturer;
	private int weekDay;
	private String timeFrom;
	private String timeTo;
	private String location;
	private String note;
	
	public Lecture(int id, String lecture, String lecturer, int weekDay,
			String timeFrom, String timeTo, String location, String note) {
		super();
		this.id = id;
		this.lecture = lecture;
		this.lecturer = lecturer;
		this.weekDay = weekDay;
		this.timeFrom = timeFrom;
		this.timeTo = timeTo;
		this.location = location;
		this.note = note;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getLecture() {
		return lecture;
	}
	public void setLecture(String lecture) {
		this.lecture = lecture;
	}
	public String getLecturer() {
		return lecturer;
	}
	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}
	public int getWeekDay() {
		return weekDay;
	}
	public void setWeekDay(int weekDay) {
		this.weekDay = weekDay;
	}
	public String getTimeFrom() {
		return timeFrom;
	}
	public void setTimeFrom(String timeFrom) {
		this.timeFrom = timeFrom;
	}
	public String getTimeTo() {
		return timeTo;
	}
	public void setTimeTo(String timeTo) {
		this.timeTo = timeTo;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}	
	
}
