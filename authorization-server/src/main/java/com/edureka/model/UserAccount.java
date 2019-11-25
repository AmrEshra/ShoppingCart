package com.edureka.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class UserAccount {

	@Id
	@GeneratedValue
	private Long id;
	
	private String userName;
	private String password;
	
	public UserAccount(String userName, String password) {
		this.userName = userName;
		this.password = password;
	}	
}
