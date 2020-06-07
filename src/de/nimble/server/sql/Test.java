package de.nimble.server.sql;

import java.sql.*;

public class Test {

  public static void main(String[] args) {
    Connection con = NimbleConnection.getConnection("testbla");
    try {
      try {
        Statement stmnt = con.createStatement();
        stmnt.execute(
            "create table if not exists customenchantments(" +
                    "id integer," +
                    "displayname text," +
                    "description text," +
                    "type text," +
                    "multiplier real)");
        stmnt.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }

      PreparedStatement ps =
          con.prepareStatement("insert into customenchantments values(6, 'test', 'description', 'type', 3)");
      ps.execute();

      ps = con.prepareStatement("select max(id) from customenchantments");

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        System.out.println(rs.getInt("max(id)"));
      }

      rs.close();
      ps.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
