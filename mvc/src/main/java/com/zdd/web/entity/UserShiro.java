package com.zdd.web.entity;

import java.io.Serializable;

import org.omg.PortableServer.SERVANT_RETENTION_POLICY_ID;

public class UserShiro implements Serializable{

	private String username;
	private String password;
	private int age;
	private String name;
	private String role;
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	
	
}
