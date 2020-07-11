package de.nimble.server.sql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NimbleConnection {
	
	public static Connection getConnection() {
		try {
			return DriverManager.getConnection("jdbc:sqlite:NimbleServer.db");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
}