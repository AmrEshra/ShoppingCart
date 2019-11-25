package com.edureka.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.edureka.repository.UserAccountRepository;

@Service
public class UserAccountService implements UserDetailsService{
	
	@Autowired
    private final UserAccountRepository userAccountRepository;

	public UserAccountService(UserAccountRepository userAccountRepository) {
		this.userAccountRepository = userAccountRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		return this.userAccountRepository.findByUserName(userName)
				.map(u -> new User(u.getUserName(), u.getPassword(), 
						true, true, true, true, 
						AuthorityUtils.createAuthorityList("ADMIN", "USER")))
				.orElseThrow(() -> new UsernameNotFoundException("Could not find user :" + userName));
	}
}
