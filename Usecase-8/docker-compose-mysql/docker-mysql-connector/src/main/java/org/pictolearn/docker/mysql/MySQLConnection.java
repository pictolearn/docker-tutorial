package org.pictolearn.docker.mysql;

import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Map;

/**
 * MYSQL connection checker, tries to connect to mysql database.
 *
 */
public class MySQLConnection {
	public static void main(String[] args) throws Exception {
		String ipAddr = InetAddress.getLocalHost().getHostName();
		System.out.println("Printing IP address of the host " + ipAddr);
		Map<String, String> env = System.getenv();
		for (String envName : env.keySet()) {
			System.out.format("%s=%s%n", envName, env.get(envName));
		}
		Thread.sleep(10000);

		boolean connected = false;
		while (!connected) {
			try {
				
				// Note the way the mysql container is used here.
				String url = "jdbc:mysql://db:3306/Users?autoReconnect=false&useSSL=false";
				String user = "root";
				String password = "root";
				System.out.println("Connecting to URL " + url);
				// Load the Connector/J driver
				Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
				// Establish connection to MySQL
				Connection conn = DriverManager.getConnection(url, user, password);
				System.out.println("Connection was successful");
				connected = true;
			} catch (Exception e) {
				System.err.println("Error connecting to database");
				e.printStackTrace();
				Thread.sleep(5000);
			}
		}

	}
}
