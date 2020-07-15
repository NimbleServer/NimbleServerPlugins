package de.nimble.server.quests;

import de.nimble.server.config.SQLConfig;
import de.nimble.server.quests.objectives.NimbleQuestObjective;
import de.nimble.server.quests.rewards.NimbleQuestReward;
import de.nimble.server.sql.NimbleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NimbleQuestConfig extends SQLConfig {

  private final String TABLE_NAME = "nimblequests";
  private static NimbleQuestConfig config;

  private NimbleQuestConfig() {
    super();
  }

  public static NimbleQuestConfig getInstance() {
    if (config == null) {
      config = new NimbleQuestConfig();
    }
    return config;
  }

  @Override
  public void createTable() {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    try {
      ps =
          con.prepareStatement(
              "create table if not exists "
                  + TABLE_NAME
                  + "("
                  + "id integer,"
                  + "name text,"
                  + "description text,"
                  + "type text,"
                  + "rewards text,"
                  + "objectives text,"
                  + ")");
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

  public void createNewQuest(
      String name, String description, String type, String rewards, String objectives) {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;

    try {
      ps = con.prepareStatement("insert into " + TABLE_NAME + " values(?, ?, ?, ?, ?, ?)");

      ps.setInt(1, updateID(TABLE_NAME));
      ps.setString(2, name);
      ps.setString(2, description);
      ps.setString(3, type);
      ps.setString(4, rewards);
      ps.setString(5, objectives);

      ps.execute();
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

  public void setName(int id, String name) {
    update(TABLE_NAME, "name", id, name);
  }

  public String getName(int id) {
    return String.valueOf(get(TABLE_NAME, "name", id));
  }

  public void setDescription(int id, String description) {
    update(TABLE_NAME, "description", id, description);
  }

  public String getDescription(int id) {
    return String.valueOf(get(TABLE_NAME, "description", id));
  }

  public void setType(int id, NimbleQuestType type) {
    update(TABLE_NAME, "type", id, type.toString());
  }

  public NimbleQuestType getType(int id) {
    return NimbleQuestType.valueOf(String.valueOf(get(TABLE_NAME, "type", id)));
  }

  public void setRewards(int id, int rewardsId) {
    update(TABLE_NAME, "rewards", id, String.valueOf(rewardsId));
  }

  public List<NimbleQuestReward> getRewards(int id) {
    String objectiveId = String.valueOf(get(TABLE_NAME, "rewards", id));
    List<NimbleQuestReward> rewards = new ArrayList<>();
    // TODO get from the NimbleQuestRewardsConfig all rewards with this id
    return rewards;
  }

  public void setObjectives(int id, int objectivesId) {
    update(TABLE_NAME, "objectives", id, String.valueOf(objectivesId));
  }

  public List<NimbleQuestObjective> getObjectives(int id) {
    String objectiveId = String.valueOf(get(TABLE_NAME, "rewards", id));
    List<NimbleQuestObjective> objectives = new ArrayList<>();
    // TODO get from the NimbleQuestObjectivesConfig all rewards with this id
    return objectives;
  }
}
