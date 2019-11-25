package com.project.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name= "customers")
public class Customer {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "firstName", nullable = false, length = 50)
	private String firstName;
	
	@Column(name = "lastName", nullable = false, length = 50)
	private String lastName;
	
	@Column(name = "username", unique = true, nullable = false, length = 50)
	private String userName;
	
	@Column(name = "password", nullable = false, length = 256)
	private String password;
	
	public Customer(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}
}
