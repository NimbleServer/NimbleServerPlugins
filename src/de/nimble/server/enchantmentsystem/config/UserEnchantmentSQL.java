package de.nimble.server.enchantmentsystem.config;

import de.nimble.server.config.SQLConfig;
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

  private final String TABLE_NAME = "customenchantments";

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
          "create table if not exists " + TABLE_NAME + "("
              + "id integer,"
              + "displayname text not null,"
              + "description text not null,"
              + "type text not null,"
              + "multiplier real)");
      stmnt.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public List<NimbleEnchantment> getEnchantments() {
    List<NimbleEnchantment> enchantments = new ArrayList<>();
    Connection con = getConnection(getDBName());
    try {
      PreparedStatement ps = con.prepareStatement("select * from " + TABLE_NAME);
      ResultSet rs = ps.executeQuery();

      while (rs.next()) {
        enchantments.add(getEnchantment(rs.getInt("id")));
      }

      rs.close();
      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return enchantments;
  }

  public NimbleEnchantment getEnchantment(int id) {
    return new EnchantmentBuilder()
        .id(getID(id))
        .displayName(getDisplayName(id))
        .description(getDescription(id))
        .type(getType(id))
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
      ps = con.prepareStatement("insert into " + TABLE_NAME + " values(?, ?, ?, ?, ?)");
      ps.setInt(1, updateID(TABLE_NAME));
      ps.setString(2, displayName);
      ps.setString(3, description);
      ps.setString(4, type.toString());
      ps.setDouble(5, multiplier);

      ps.execute();

      ps.close();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void setID(int id) {
    update(TABLE_NAME, "id", id, String.valueOf(id));
  }

  public int getID(int id) {
    return Integer.parseInt(String.valueOf(get(TABLE_NAME , "id", id)));
  }

  public void setDisplayName(int id, String displayName) {
    update(TABLE_NAME, "displayname", id, displayName);
  }

  public String getDisplayName(int id) {
    return String.valueOf(get(TABLE_NAME, "displayname", id));
  }

  public void setType(int id, EnchantmentType type) {
    update(TABLE_NAME, "type", id, type.toString());
  }

  public EnchantmentType getType(int id) {
    return EnchantmentType.getTypeByName(String.valueOf(get(TABLE_NAME, "type", id)));
  }

  public void setDescription(int id, String description) {
    update(TABLE_NAME, "description", id, description);
  }

  public String getDescription(int id) {
    return String.valueOf(get(TABLE_NAME, "description", id));
  }

  public void setMultiplier(int id, double multiplier) {
    update(TABLE_NAME, "multiplier", id, String.valueOf(multiplier));
  }

  public double getMultiplier(int id) {
    return Double.parseDouble(String.valueOf(get(TABLE_NAME, "multiplier", id)));
  }
}
