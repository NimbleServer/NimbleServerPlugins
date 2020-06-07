package de.nimble.server.mysql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import de.nimble.server.enchantmentsystem.config.UserEnchantmentSQL;

public class Test {
	
	public static void main(String[] args) {
		Connection con = NimbleConnection.getConnection(UserEnchantmentSQL.DB_NAME);
		try {
			try {
				Statement stmnt = con.createStatement();
				stmnt.execute(
						"create table if not exists customenchantments(id integer, enchantmentname text, displayname text, description text, type text, multiplier real)");
				stmnt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			
			PreparedStatement ps = con.prepareStatement("select id from customenchantments where enchantmentname = 'test'");
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				System.out.println(rs.getString("id"));
			}
			
			rs.close();
			ps.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}