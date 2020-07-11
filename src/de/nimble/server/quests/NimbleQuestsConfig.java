package de.nimble.server.quests;

import de.nimble.server.config.SQLConfig;
import de.nimble.server.sql.NimbleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class NimbleQuestsConfig extends SQLConfig {

	@Override
	public void createTable() {
		Connection con = NimbleConnection.getConnection();
		PreparedStatement ps = null;
		try {
			ps = con.prepareStatement(""); // TODO add query to create table
		} catch (SQLException throwables) {
			throwables.printStackTrace();
		} finally {
			try {
        if (ps != null) {
          ps.close();
				}
        if (con != null) {
          con.close();
				}
			} catch (SQLException throwables) {
				throwables.printStackTrace();
			}

		}
	}

}
