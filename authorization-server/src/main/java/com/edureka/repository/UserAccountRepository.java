package com.edureka.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edureka.model.UserAccount;

@Repository
public interface UserAccountRepository extends JpaRepository<UserAccount, Long>{
	
	Optional<UserAccount> findByUserName(String userName);
	

}
