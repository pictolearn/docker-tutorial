package org.pictolearn.docker.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName="proxyFilter")
/**
 * This proxy filter forwards the request to a Proxy Servlet
 * with the URI
 * @author agane
 *
 */
public class ProxyFilter implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(ProxyFilter.class);


	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
	    logger.debug("Proxy filter");
		HttpServletRequest req = (HttpServletRequest) request;
		request.setAttribute("uri", req.getRequestURI().substring(req.getContextPath().length()));
		request.getRequestDispatcher("/ProxyServlet").forward(request, response);
	}

	public void init(FilterConfig arg0) throws ServletException {
	}

}
