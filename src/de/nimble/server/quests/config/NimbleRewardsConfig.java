package de.nimble.server.quests.config;

import de.nimble.server.quests.rewards.NimbleQuestReward;
import de.nimble.server.sql.NimbleConnection;

import java.sql.*;
import java.util.List;

public class NimbleRewardsConfig {

  public NimbleRewardsConfig() {}

  public void createRewardsTable() {
    Connection con = NimbleConnection.getConnection("NimbleQuests");
    Statement stmnt = null;
    String query = "create table questrewards(" + "quest_id text," + ")";
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
    Connection con = NimbleConnection.getConnection("NimbleQuests");
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement("select * from questrewards where quest_id = ?");
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
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
    return null;
  }
}
