package de.nimble.server.mysql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class NimbleConnection {
	
	public static Connection getConnection(String path) {
		try {
			return DriverManager.getConnection("jdbc:sqlite:" + path + ".db");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}