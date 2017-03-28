package org.pictolearn.docker.mysql;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

/**
 * Hello world!
 *
 */
public class MySQLConnection {
	public static void main(String[] args) throws Exception {
		String ipAddr = InetAddress.getLocalHost().getHostName();

		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
		    System.out.format("%s=%s%n", envName, env.get(envName));
		}
		Thread.sleep(10000);
		String url = "jdbc:mysql://mysql:3306/Users?autoReconnect=false&useSSL=false";
		String user = "root";
		String password = "root";
		System.out.println("Connecting to URL " + url );
		// Load the Connector/J driver
		Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		// Establish connection to MySQL
		Connection conn = DriverManager.getConnection(url, user, password);
		System.out.println("Connection was successful");
	}
}
