package com.edureka;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;

@Configuration
public class MyResourceServerConfigurerAdapter extends ResourceServerConfigurerAdapter {


	@Override
	public void configure(HttpSecurity http) throws Exception {
		http
        .authorizeRequests()
        .antMatchers("/signup").permitAll()
        .antMatchers(HttpMethod.POST, "/customers").permitAll();
	}

}
