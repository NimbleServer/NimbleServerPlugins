package de.nimble.server.config;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class Config {

  protected File file;
  protected FileConfiguration fc;
  protected String fileName;

  public Config(String fileName) {
    this.fileName = fileName;
    this.file = new File("plugins/", fileName + ".yml");
    this.fc = YamlConfiguration.loadConfiguration(file);
  }

  public <T> void set(String key, T value) {
    fc.set(key, value);
    save();
  }

  public int getInt(String key) {
    return fc.getInt(key);
  }

  public byte getByte(String key) {
    return (byte) fc.getInt(key);
  }

  public String getString(String key) {
    return fc.getString(key);
  }

  public List<String> getStringList(String key) {
    return fc.getStringList(key);
  }

  public double getDouble(String key) {
    return fc.getDouble(key);
  }

  protected void setFile() {
    if (this.fileName != null && (!(this.fileName.equals("")))) {
      this.file = new File("plugins/", fileName + ".yml");
    } else {
      this.file = new File("plugins/defaultConfig.yml");
    }
  }

  public File getFile() {
    return this.file;
  }

  protected void setFileConfiguration() {
    if (file != null) {
      this.fc = YamlConfiguration.loadConfiguration(file);
    }
  }

  public FileConfiguration getFileConfiguration() {
    return this.fc;
  }

  protected void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getFileName() {
    return this.fileName;
  }

  public void save() {
    try {
      getFileConfiguration().save(file);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
