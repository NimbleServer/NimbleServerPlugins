package de.nimble.server.enchantmentsystem.config;

import de.nimble.server.sql.NimbleConnection;

import java.sql.Connection;

public abstract class SQLConfig {

  private String dbName;
  private Connection con;

  public SQLConfig(String dbName) {
    this.dbName = dbName;
    createTable();
  }

  public abstract void createTable();

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
