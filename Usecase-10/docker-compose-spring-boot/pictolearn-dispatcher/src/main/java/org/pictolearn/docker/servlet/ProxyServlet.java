package org.pictolearn.docker.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebServlet(urlPatterns = "/proxyServlet/*", loadOnStartup = 1)
/**
 * Servlet which queries the list of hostnames
 * and reverse proxies the request to web layer
 * 
 * @author pictolearn
 *
 */
public class ProxyServlet extends HttpServlet {

	private static final long serialVersionUID = 2787920473586060865L;

	private static final Logger logger = LoggerFactory.getLogger(ProxyServlet.class);

	/**
	 * Applies only to GET REQUESTS. The following method picks up a random host with name as "web" as specified
	 * in the docker-compose file with the service name as "web" when the web container scales up
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getRequestURI().substring(request.getContextPath().length());
		path = path.substring(path.indexOf("/proxyServlet/") + "/proxyServlet/".length(), path.length());
		logger.debug("Path to query for GET Request: {}  ", path);
		if (StringUtils.isEmpty(path)) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("Invalid GET CALL");
			out.close();
			return;
		}
		String ipAddress = getRandomIpAddress(response);

		if(ipAddress == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("No Hosts Found");
			return;
		}
		
		String url = "http://" + ipAddress + ":8080/" + path;
		logger.debug("GET url :{} ", url);

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("GET");
		response.addHeader("WEB-HOST", ipAddress);
		sendResponse(response, con);
	}

	/**
	 * Applies only to POST REQUEST
	 */
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String path = request.getRequestURI().substring(request.getContextPath().length());
		path = path.substring(path.indexOf("/proxyServlet/") + "/proxyServlet/".length(), path.length());
		logger.debug("Path to query for POST Request: {}  ", path);
		if (StringUtils.isEmpty(path)) {
			response.setContentType("text/html");
			PrintWriter out = response.getWriter();
			out.println("Invalid POST CALL empty URI");
			out.close();
			return;
		}
		String ipAddress = getRandomIpAddress(response);

		if(ipAddress == null) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("No Hosts Found");
			return;
		}
		
		response.addHeader("WEB-HOST", ipAddress);
		String url = "http://" + ipAddress + ":8080/" + path;

		URL obj = new URL(url);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		logger.debug("POST url :{} ", url);

		Enumeration<String> headerNames = request.getHeaderNames();
		Set<String> headers = new HashSet<>();
		while (headerNames.hasMoreElements()) {
			headers.add(headerNames.nextElement());
		}

		if (!CollectionUtils.isEmpty(headers)) {
			for (String header : headers) {
				String value = request.getHeader(header);
				logger.debug("Adding header to the request  {} with value {} ", new Object[] { header, value });
				con.setRequestProperty(header, value);
			}
		}

		con.setDoOutput(true);
		String body = getBody(request);
		logger.debug("Body : {} ", body);
		OutputStream wr = con.getOutputStream();
		wr.write(body.getBytes("UTF-8"));
		wr.flush();
		wr.close();
		sendResponse(response, con);

	}

	public String getBody(HttpServletRequest request) throws IOException {

		String body = null;
		StringBuilder stringBuilder = new StringBuilder();
		BufferedReader bufferedReader = null;

		try {
			InputStream inputStream = request.getInputStream();
			if (inputStream != null) {
				bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
				char[] charBuffer = new char[128];
				int bytesRead = -1;
				while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
					stringBuilder.append(charBuffer, 0, bytesRead);
				}
			} else {
				stringBuilder.append("");
			}
		} catch (IOException ex) {
			throw ex;
		} finally {
			if (bufferedReader != null) {
				try {
					bufferedReader.close();
				} catch (IOException ex) {
					throw ex;
				}
			}
		}

		body = stringBuilder.toString();
		return body;
	}

	/**
	 * return random IP Address. Note the host nane "web" which queries
	 * all the hosts in the given docker-machine with the name web
	 * 
	 * @param response
	 * @return
	 * @throws UnknownHostException
	 * @throws IOException
	 */
	private String getRandomIpAddress(HttpServletResponse response) throws UnknownHostException, IOException {
		List<String> ipAddr = new ArrayList<>();
		for (InetAddress addr : InetAddress.getAllByName("web")) {
			logger.debug("Hostnames {}", addr.getHostAddress());
			ipAddr.add(addr.getHostAddress());
		}
		int size = ipAddr.size();
		if (size == 0) {
			logger.error("Size less than 1");
			return null;
		}

		logger.debug("Total hosts : {} ", size);

		int random = 0;
		if (size == 1) {
			random = 0;
		}else if (size > 1){
			random = ThreadLocalRandom.current().nextInt(0, ipAddr.size() - 1);
		}

		logger.debug("Random : {} " , random );
		String ipAddrStr = ipAddr.get(random);
		logger.debug("Returned IP addr : {} ", ipAddrStr);
		return ipAddrStr;
	}

	/**
	 * send response to client include all the headers and the response body
	 * 
	 * @param response
	 * @param con
	 * @throws ProtocolException
	 * @throws IOException
	 */
	private void sendResponse(HttpServletResponse response, HttpURLConnection con)
			throws ProtocolException, IOException {

		int responseCode = con.getResponseCode();

		if (responseCode == 200) {
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			Map<String, List<String>> headerFields = con.getHeaderFields();
			if (!headerFields.isEmpty()) {
				for (String headerField : headerFields.keySet()) {
					Object value = headerFields.get(headerField);
					logger.debug("Adding header field : {} , header value : {} ", new Object[] { headerField, value });
					response.addHeader(headerField, value.toString());
				}
			}

			String inputLine;
			StringBuffer stringOutput = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				stringOutput.append(inputLine);
			}
			logger.debug("Received response : {}  ", stringOutput);
			PrintWriter out = response.getWriter();
			out.println(stringOutput.toString());
		} else {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			response.getWriter().println("Internal Server Error");
		}

	}
}
