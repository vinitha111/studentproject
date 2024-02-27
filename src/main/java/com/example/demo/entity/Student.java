package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
//@AllArgsConstructor
//@NoArgsConstructor
//@Data
public class Student {
	@Id
	@GeneratedValue
	private int id;
	private String name;
	private String location;
	public Student(int id, String name, String location) {
	
		super();
		this.id = id;
		this.name = name;
		this.location = location;
	}
	public Student() {
		super();
	}
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
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	
	

}
