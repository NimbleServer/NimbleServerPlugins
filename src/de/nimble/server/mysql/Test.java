package de.nimble.server.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {
	
	public static void main(String[] args) {
		Connection con = NimbleConnection.getConnection("userEnchantments");
		try {
			PreparedStatement ps = con.prepareStatement("create table if not exists userEnchantments(id integer, name string) ");
			ps.execute();
			ps = con.prepareStatement("insert into userEnchantments values(1, 'test')");
			ps.execute();
			ps = con.prepareStatement("select * from userEnchantments");
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println("name = " + rs.getString("name"));
				System.out.println("id = " + rs.getInt("id"));
			}
			
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}