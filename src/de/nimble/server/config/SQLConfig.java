package de.nimble.server.config;

import de.nimble.server.sql.NimbleConnection;

import java.sql.*;

public abstract class SQLConfig {

  public SQLConfig() {
    createTable();
  }

  public abstract void createTable();

  public int updateID(String tableName) {
    Connection con = NimbleConnection.getConnection();
    Statement st = null;
    ResultSet rs = null;
    try {
      st = con.createStatement();
      rs = st.executeQuery("select max(id) from '" + tableName + "'");
      while (rs.next()) {
        return rs.getInt("max(id)") + 1;
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        rs.close();
        st.close();
        con.close();
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return -1;
  }

  public void update(String tableName, String columnName, int id, String value) {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    try {
      ps =
          con.prepareStatement(
              "update " + tableName + " set '" + columnName + "' = ? where id = ?");
      ps.setString(1, value);
      ps.setInt(2, id);

      ps.execute();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        ps.close();
        con.close();
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public Object get(String tableName, String columnName, int id) {
    Connection con = NimbleConnection.getConnection();
    try {
      String query = "select " + columnName + " from " + tableName + " where id = ?";
      PreparedStatement ps = con.prepareStatement(query);
      ps.setInt(1, id);

      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        return rs.getString(columnName);
      }

      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {

    }
    return null;
  }
}
