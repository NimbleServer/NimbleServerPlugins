package de.nimble.server.config;

import de.nimble.server.sql.NimbleConnection;

import java.sql.*;

public abstract class SQLConfig {

  private String dbName;
  private Connection con;

  public SQLConfig(String dbName) {
    this.dbName = dbName;
    createTable();
  }

  public abstract void createTable();

  public int updateID(String tableName) {
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
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
    return -1;
  }

  public void update(String tableName, String columnName, int id, String value) {
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
    }
  }

  public Object get(String tableName, String columnName, int id) {
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
    }
    return null;
  }

  public void initConnection() {
    this.con = NimbleConnection.getConnection(dbName);
  }

  public Connection getConnection(String dbName) {
    initConnection();
    return con;
  }

  public void setDBName(String dbName) {
    this.dbName = dbName;
  }

  public String getDBName() {
    return this.dbName;
  }
}
