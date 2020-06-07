package de.nimble.server.enchantmentsystem.config;

import de.nimble.server.enchantmentsystem.enchants.EnchantmentBuilder;
import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantment;
import de.nimble.server.enchantmentsystem.enchants.types.EnchantmentType;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserEnchantmentSQL extends SQLConfig {
  /*
  TODO if id is given replace/modify enchantment
   */

  private static UserEnchantmentSQL config = null;

  public static UserEnchantmentSQL getInstance() {
    if (config == null) {
      config = new UserEnchantmentSQL();
    }
    return config;
  }

  private UserEnchantmentSQL() {
    super("UserEnchantments");
  }

  @Override
  public void createTable() {
    Connection con = getConnection(getDBName());
    try {
      Statement stmnt = con.createStatement();
      stmnt.execute(
          "create table if not exists customenchantments("
              + "id integer,"
              + "displayname text not null,"
              + "description text not null,"
              + "type text not null,"
              + "multiplier real)");
      stmnt.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public NimbleEnchantment getEnchantment(int id) {
    return createEnchantment(getConnection(getDBName()), id);
  }

  public List<NimbleEnchantment> getEnchantments() {
    List<NimbleEnchantment> enchantments = new ArrayList<>();
    Connection con = getConnection(getDBName());
    try {
      PreparedStatement ps = con.prepareStatement("select * from customenchantments");
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        enchantments.add(createEnchantment(con, rs.getInt("id")));
      }

      rs.close();
      ps.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return enchantments;
  }

  public NimbleEnchantment createEnchantment(Connection con, int id) {
    return new EnchantmentBuilder()
        .id(getID(con, id))
        .displayName(getDisplayName(con, id))
        .description(getDescription(con, id))
        .type(getType(con, id))
        .build();
  }

  public void createNewEnchantment(
      Connection con,
      String displayName,
      String description,
      EnchantmentType type,
      double multiplier) {
    PreparedStatement ps = null;

    try {
      ps = con.prepareStatement("insert into customenchantments values(?, ?, ?, ?, ?)");
      ps.setInt(1, updateID(con));
      ps.setString(2, displayName);
      ps.setString(3, description);
      ps.setString(4, type.toString());
      ps.setDouble(5, multiplier);

      ps.execute();

      ps.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void update(Connection con, String columnName, int id, String value) {
    PreparedStatement ps = null;
    try {
      ps =
          con.prepareStatement(
              "update customenchantments set '" + columnName + "' = ? where id = ?");
      ps.setString(1, value);
      ps.setInt(2, id);

      ps.execute();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public String get(Connection con, String columnName, int id) {
    try {
      String query = "select " + columnName + " from customenchantments where id = ?";
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
    return "No displayName";
  }

  public int updateID(Connection con) {
    Statement st = null;
    ResultSet rs = null;
    try {
      st = con.createStatement();
      rs = st.executeQuery("select max(id) from customenchantments");
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

  public void setID(Connection con, int id) {
    update(con, "id", id, String.valueOf(id));
  }

  public int getID(Connection con, int id) {
    return Integer.parseInt(get(con, "id", id));
  }

  public void setDisplayName(Connection con, int id, String displayName) {
    update(con, "displayname", id, displayName);
  }

  public String getDisplayName(Connection con, int id) {
    return get(con, "displayname", id);
  }

  public void setType(Connection con, int id, EnchantmentType type) {
    update(con, "type", id, type.toString());
  }

  public EnchantmentType getType(Connection con, int id) {
    return EnchantmentType.getTypeByName(get(con, "type", id));
  }

  public void setDescription(Connection con, int id, String description) {
    update(con, "description", id, description);
  }

  public String getDescription(Connection con, int id) {
    return get(con, "description", id);
  }

  public void setMultiplier(Connection con, int id, double multiplier) {
    update(con, "multiplier", id, String.valueOf(multiplier));
  }

  public double getMultiplier(Connection con, int id) {
    return Double.parseDouble(get(con, "multiplier", id));
  }
}
