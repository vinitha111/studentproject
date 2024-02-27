package com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
/*
 * @AllArgsConstructor
 * 
 * @NoArgsConstructor
 * 
 * @Data
 */
public class Teacher {
	@Id
	@GeneratedValue
      private int id;
	public Teacher() {
		super();
	}
	private String name;
    private long phoneNumber;

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
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public Teacher(int id, String name, long phoneNumber) {
		super();
		this.id = id;
		this.name = name;
		this.phoneNumber = phoneNumber;
	}
	
 
}
