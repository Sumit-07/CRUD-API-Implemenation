package com.example.demo;

public class Marksheet {
	private int id;
	private String classId;
	public String getClassId() {
		return classId;
	}
	public void setClassId(String class_id) {
		this.classId = class_id;
	}
	private String name;
	private int marks;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getMarks() {
		return marks;
	}
	public void setMarks(int marks) {
		this.marks = marks;
	}
	
	

}
