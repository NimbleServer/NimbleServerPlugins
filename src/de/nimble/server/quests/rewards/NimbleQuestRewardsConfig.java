package de.nimble.server.quests.rewards;

import de.nimble.server.config.SQLConfig;
import de.nimble.server.quests.rewards.NimbleQuestReward;
import de.nimble.server.sql.NimbleConnection;

import java.sql.*;
import java.util.List;

public class NimbleQuestRewardsConfig extends SQLConfig {

  private final String TABLE_NAME = "questrewards";
  private static NimbleQuestRewardsConfig config;

  private NimbleQuestRewardsConfig() {
    super();
  }

  public static NimbleQuestRewardsConfig getInstance() {
    if(config == null) {
      config = new NimbleQuestRewardsConfig();
    }
    return config;
  }

  @Override
  public void createTable() {
    Connection con = NimbleConnection.getConnection();
    Statement stmnt = null;
    String query = "create table " + TABLE_NAME + "("
        + "quest_id text,"
        + ")";
    try {
      stmnt = con.createStatement();
      stmnt.executeQuery(query);
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmnt != null) {
          stmnt.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public List<NimbleQuestReward> getRewards(String id) {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement("select * from " + TABLE_NAME + " where quest_id = ?");
      ps.setString(1, id);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {}

    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
        if (con != null) {
          con.close();
        }
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
