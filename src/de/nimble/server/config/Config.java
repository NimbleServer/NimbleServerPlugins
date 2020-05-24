package de.nimble.server.config;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

public class Config {
	
	protected File file;
	protected FileConfiguration fc;
	protected String fileName; 
	
	public Config(String fileName) {
		this.fileName = fileName;
		this.file = new File("plugins/enchantments/", fileName + ".yml");
		this.fc = YamlConfiguration.loadConfiguration(file);
	}
	
	protected void setFile() {
		if(this.fileName != null && (!(this.fileName.equals("")))) {
			this.file = new File("plugins/enchantments/", fileName + ".yml");
		} else {
			this.file = new File("plugins/enchantments/defaultConfig.yml");
		}
	}
	
	public File getFile() {
		return this.file;
	}
	
	protected void setFileConfiguration() {
		if(file != null) {
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
	
}