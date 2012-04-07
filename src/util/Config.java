package util;

import java.util.Properties;

public class Config {

	public static final String WINDOW_WIDTH_CONFIG_KEY = "window.width";
	public static final String WINDOW_HEIGHT_CONFIG_KEY = "window.height";
	public static final String WINDOW_FULLSCREEN_CONFIG_KEY = "window.fullscreen"; 
	
	private Properties properties;
	
	public Config(Properties p){
		
		this.properties = p;
		
	}
	
	public String getString(String key){
		
		return properties.getProperty(key);
		
	}
	
	public Integer getInteger(String key){
		
		return Integer.parseInt(properties.getProperty(key));
		
	}
	
	public Boolean getBoolean(String key){
		
		return Boolean.parseBoolean(properties.getProperty(key));
		
	}
	
}
