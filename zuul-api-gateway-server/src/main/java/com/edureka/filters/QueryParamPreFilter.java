package com.edureka.filters;

import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_DECORATION_FILTER_ORDER;
import static org.springframework.cloud.netflix.zuul.filters.support.FilterConstants.PRE_TYPE;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;

public class QueryParamPreFilter extends ZuulFilter {

	public static final String USER_HEADER = "X-User-Header";

	@Override
	public int filterOrder() {
		return PRE_DECORATION_FILTER_ORDER - 1; // run before PreDecoration
	}

	@Override
	public String filterType() {
		return PRE_TYPE;
	}

	public boolean shouldFilter() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication != null && authentication.getPrincipal() != null;
	}

	public Object run() {

		RequestContext requestContext = RequestContext.getCurrentContext();
		requestContext.addZuulRequestHeader(USER_HEADER, (String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		return null;
	}
}