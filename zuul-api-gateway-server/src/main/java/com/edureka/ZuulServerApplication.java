package com.edureka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableEurekaClient
@EnableZuulProxy
@EnableResourceServer
public class ZuulServerApplication extends WebSecurityConfigurerAdapter {

	public static void main(String[] args) {
		SpringApplication.run(ZuulServerApplication.class, args);
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().anyRequest().permitAll();
	}
	
//	@Bean
//	public PreFilter preFilter() {
//		return new PreFilter();
//	}
//
//	@Bean
//	public PostFilter postFilter() {
//		return new PostFilter();
//	}
//
//	@Bean
//	public ErrorFilter errorFilter() {
//		return new ErrorFilter();
//	}
//
//	@Bean
//	public RouteFilter routeFilter() {
//		return new RouteFilter();
//	}
//	
//	@Bean
//	public QueryParamPreFilter queryParamPreFilter() {
//		return new QueryParamPreFilter();
//	}
}
