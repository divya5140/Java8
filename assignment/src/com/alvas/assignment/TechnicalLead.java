package com.alvas.assignment;

import java.time.LocalDateTime;

public class TechnicalLead {

	private int id;
	
	private String name;
	
	private int age;
	
	private LocalDateTime joindate;
	
	private Boolean ismanager;

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

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LocalDateTime getJoindate() {
		return joindate;
	}

	public void setJoindate(LocalDateTime joindate) {
		this.joindate = joindate;
	}

	public Boolean getIsmanager() {
		return ismanager;
	}

	public void setIsmanager(Boolean ismanager) {
		this.ismanager = ismanager;
	}

	@Override
	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", age=" + age + ", joindate=" + joindate + ", ismanager="
				+ ismanager + "]";
	}

	public TechnicalLead(int id, String name, int age, LocalDateTime joindate, Boolean ismanager) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
		this.joindate = joindate;
		this.ismanager = ismanager;
	}

	
	
}
