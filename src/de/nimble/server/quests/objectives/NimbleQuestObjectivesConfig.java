package de.nimble.server.quests.objectives;

import de.nimble.server.config.SQLConfig;
import de.nimble.server.sql.NimbleConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class NimbleQuestObjectivesConfig extends SQLConfig {

  private final String TABLE_NAME = "nimblequestobjectives";
  private static NimbleQuestObjectivesConfig config;

  private NimbleQuestObjectivesConfig() {
    super();
  }

  public static NimbleQuestObjectivesConfig getInstance() {
    if (config == null) {
      config = new NimbleQuestObjectivesConfig();
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
                  + "questId integer,"
                  + "amount integer,"
                  + "name text,"
                  + "type text)");
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

  public List<NimbleQuestObjective> getByQuestId(int questId) {
    List<NimbleQuestObjective> objectives = new ArrayList<>();
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    try {
      ps = con.prepareStatement("select * from " + TABLE_NAME + " where questId = ?");
      ps.setInt(1, questId);
      rs = ps.executeQuery();

      while (rs.next()) {
        NimbleQuestObjective objective = new NimbleQuestObjective();
        objective.setId(rs.getInt("id"));
        objective.setQuestId(questId);
        objective.setAmount(rs.getInt("amount"));
        objective.setName(rs.getString("name"));
        objective.setType(NimbleQuestObjectiveType.valueOf(rs.getString("type")));

        objectives.add(objective);
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          ps.close();
        }
        if (ps != null) {
          rs.close();
        }
        if (con != null) {
          con.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }

    return objectives;
  }

  public NimbleQuestObjective getById(int id) {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    NimbleQuestObjective objective = new NimbleQuestObjective();

    try {
      ps = con.prepareStatement("select * from " + TABLE_NAME + " where id = ?");
      ps.setInt(1, id);

      rs = ps.executeQuery();

      while (rs.next()) {
        objective.setId(id);
        objective.setQuestId(rs.getInt("questId"));
        objective.setAmount(rs.getInt("amount"));
        objective.setName(rs.getString("name"));
        objective.setType(NimbleQuestObjectiveType.valueOf(rs.getString("type")));
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (rs != null) {
          rs.close();
        }
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

    return objective;
  }

  public void create(int questId, int amount, String name, String type) {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    try {
      ps = con.prepareStatement("insert into " + TABLE_NAME + " values(?, ?, ?, ?, ?)");

      ps.setInt(1, updateID(TABLE_NAME));
      ps.setInt(2, questId);
      ps.setInt(3, amount);
      ps.setString(5, name);
      ps.setString(5, type);

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

  public void setType(int id, NimbleQuestObjectiveType type) {
    update(TABLE_NAME, "type", id, type.toString());
  }

  public NimbleQuestObjectiveType getType(int id) {
    return NimbleQuestObjectiveType.valueOf(String.valueOf(get(TABLE_NAME, "type", id)));
  }

  public void setAmount(int id, int amount) {
    update(TABLE_NAME, "amount", id, String.valueOf(amount));
  }

  public int getAmount(int id) {
    return (int) get(TABLE_NAME, "amount", id);
  }
}
