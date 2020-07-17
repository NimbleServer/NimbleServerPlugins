package de.nimble.server.quests.rewards;

import de.nimble.server.config.SQLConfig;
import de.nimble.server.quests.rewards.NimbleQuestReward;
import de.nimble.server.quests.rewards.types.NimbleDefaultQuestReward;
import de.nimble.server.quests.rewards.types.NimbleItemQuestReward;
import de.nimble.server.sql.NimbleConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NimbleQuestRewardsConfig extends SQLConfig {

  private final String TABLE_NAME = "questrewards";
  private static NimbleQuestRewardsConfig config;

  private NimbleQuestRewardsConfig() {
    super();
  }

  public static NimbleQuestRewardsConfig getInstance() {
    if (config == null) {
      config = new NimbleQuestRewardsConfig();
    }
    return config;
  }

  @Override
  public void createTable() {
    Connection con = NimbleConnection.getConnection();
    Statement stmnt = null;
    String query =
        "create table if not exists"
            + TABLE_NAME
            + "("
            + "id integer,"
            + "quest_id integer,"
            + "name text,"
            + "type text)";
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

  public void create(int id, int questId, String name, NimbleQuestRewardType type) {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement("insert into " + TABLE_NAME + " values(?, ?, ?, ?)");
      ps.setInt(1, updateID(TABLE_NAME));
      ps.setInt(2, questId);
      ps.setString(3, name);
      ps.setString(4, type.toString());
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    }
  }

  public NimbleQuestReward getById(int id) {
    NimbleQuestReward reward = null;
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = con.prepareStatement("select * from " + TABLE_NAME + " where id = ?");
      ps.setInt(1, id);
      rs = ps.executeQuery();

      while (rs.next()) {
        switch (NimbleQuestRewardType.valueOf(rs.getString("type"))) {
          case ITEM:
            reward = new NimbleItemQuestReward();
            break;
          case CURRENCY:
            break;
          case EXPERIENCE:
            break;
          case DEFAULT:
            reward = new NimbleDefaultQuestReward();
            break;
          default:
            reward = new NimbleDefaultQuestReward();
            break;
        }
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          rs.close();
        }
        if (con != null) {
          con.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }


    return reward;
  }

  public List<NimbleQuestReward> getByQuestId(int questId) {
    List<NimbleQuestReward> rewards = new ArrayList<>();
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = con.prepareStatement("select * from " + TABLE_NAME + " where quest_id = ?");
      ps.setInt(1, questId);
      rs = ps.executeQuery();

      while (rs.next()) {
        NimbleQuestReward reward = null;
        switch (NimbleQuestRewardType.valueOf(rs.getString("type"))) {
          case ITEM:
            reward = new NimbleItemQuestReward();
            break;
          case CURRENCY:
            break;
          case EXPERIENCE:
            break;
          case DEFAULT:
            reward = new NimbleDefaultQuestReward();
            break;
          default:
            reward = new NimbleDefaultQuestReward();
            break;
        }
        rewards.add(reward);
      }
    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (ps != null) {
          ps.close();
        }
        if (rs != null) {
          rs.close();
        }
        if (con != null) {
          con.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    return rewards;
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
