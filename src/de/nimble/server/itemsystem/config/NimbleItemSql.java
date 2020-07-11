package de.nimble.server.itemsystem.config;

import de.nimble.server.NimbleServer;
import de.nimble.server.config.SQLConfig;
import de.nimble.server.enchantmentsystem.enchants.NimbleEnchantment;
import de.nimble.server.itemsystem.items.NimbleItem;
import de.nimble.server.itemsystem.items.types.NimbleItemType;
import de.nimble.server.itemsystem.items.types.WeaponItem;
import de.nimble.server.sql.NimbleConnection;
import org.bukkit.Material;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class NimbleItemSql extends SQLConfig {

  private final String TABLE_NAME = "customitems";

  private static NimbleItemSql itemSql;

  public static NimbleItemSql getInstance() {
    if (itemSql == null) {
      itemSql = new NimbleItemSql();
    }
    return itemSql;
  }

  private NimbleItemSql() {
    super();
  }

  @Override
  public void createTable() {
    Connection con = NimbleConnection.getConnection();
    Statement stmnt = null;
    try {
      stmnt = con.createStatement();
      stmnt.execute(
          "create table if not exists "
              + TABLE_NAME
              + "("
              + "id integer not null,"
              + "displayname text not null,"
              + "description text not null,"
              + "type text not null,"
              + "enchantments text,"
              + "material text)");
      stmnt.close();
      con.close();
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      try {
        if (stmnt != null) {
          stmnt.close();
        }
        if (con != null) {
          con.close();
        }
      } catch (SQLException throwables) {
        throwables.printStackTrace();
      }
    }
  }

  public NimbleItem getItem(int id) {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;
    ResultSet rs = null;

    NimbleItem item = null;

    try {
      ps = con.prepareStatement("select * from " + TABLE_NAME + " where id = ?");
      ps.setInt(1, id);
      rs = ps.executeQuery();

      while (rs.next()) {

        NimbleItemType type = NimbleItemType.valueOf(rs.getString("type"));
        switch (type) {
          case WEAPON:
            item = new WeaponItem();
            break;
          case USE:
            break;
          case NONE:
            break;
          default:
            break;
        }
        item.setType(type);
        item.setID(rs.getInt("id"));
        item.setDisplayName(rs.getString("displayname"));
        item.setDescription(rs.getString("description"));
        item.setMaterial(rs.getString("material"));
        for (NimbleEnchantment e : getEnchantmentsFromString(rs.getString("enchantments"))) {
          item.addEnchantment(e);
        }
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
    return item;
  }

  public List<NimbleItem> getItems() {
    Connection con = NimbleConnection.getConnection();
    Statement stmnt = null;
    ResultSet rs = null;
    List<NimbleItem> items = new ArrayList<>();
    try {
      stmnt = con.createStatement();
      rs = stmnt.executeQuery("select * from " + TABLE_NAME);

      while (rs.next()) {
        items.add(getItem(rs.getInt("id")));
      }

    } catch (SQLException throwables) {
      throwables.printStackTrace();
    } finally {
      try {
        if (stmnt != null) {
          stmnt.close();
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

    return items;
  }

  public void createNewItem(
      String displayName,
      String description,
      NimbleItemType type,
      String enchantments,
      String material) {
    Connection con = NimbleConnection.getConnection();
    PreparedStatement ps = null;

    try {
      ps = con.prepareStatement("insert into customitems values(?, ?, ?, ?, ?, ?)");
      ps.setInt(1, updateID(TABLE_NAME));
      ps.setString(2, displayName);
      ps.setString(3, description);
      ps.setString(4, type.toString());
      ps.setString(5, enchantments);
      ps.setString(6, material);

      ps.execute();

      ps.close();
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
      } catch (SQLException e) {
        e.printStackTrace();
      }
    }
  }

  public String getEnchantmentsAsString(List<NimbleEnchantment> enchantments) {
    StringBuilder enchantmentsString = new StringBuilder();
    enchantments.stream()
        .forEach(
            e -> {
              if (e != enchantments.get(enchantments.size() - 1)) {
                enchantmentsString.append(e.getID() + ";");
              } else {
                enchantmentsString.append(e.getID());
              }
            });
    return enchantmentsString.toString();
  }

  public List<NimbleEnchantment> getEnchantmentsFromString(String enchantments) {
    List<NimbleEnchantment> ench = new ArrayList<>();
    String[] splitted = enchantments.split(";");

    Arrays.asList(splitted).stream()
        .forEach(
            e -> ench.add(NimbleServer.enchantmentManager.getEnchantment(Integer.parseInt(e))));

    return ench;
  }

  public void setID(int id) {
    update(TABLE_NAME, "id", id, String.valueOf(id));
  }

  public int getID(int id) {
    return Integer.parseInt(String.valueOf(get(TABLE_NAME, "id", id)));
  }

  public void setDisplayName(int id, String displayName) {
    update(TABLE_NAME, "displayname", id, displayName);
  }

  public String getDisplayName(int id) {
    return String.valueOf(get(TABLE_NAME, "displayname", id));
  }

  public void setDescription(int id, String description) {
    update(TABLE_NAME, "description", id, description);
  }

  public String getDescription(int id) {
    return String.valueOf(get(TABLE_NAME, "description", id));
  }

  public void setEnchantments(int id, List<NimbleEnchantment> enchantments) {
    update(TABLE_NAME, "enchantments", id, getEnchantmentsAsString(enchantments));
  }

  public List<NimbleEnchantment> getEnchantments(int id) {
    return getEnchantmentsFromString(String.valueOf(get(TABLE_NAME, "enchantments", id)));
  }

  public void setMaterial(int id, Material material) {
    update(TABLE_NAME, "material", id, material.toString());
  }
}
