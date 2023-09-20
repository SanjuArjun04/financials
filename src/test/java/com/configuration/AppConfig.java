package com.configuration;

import java.io.FileInputStream;
import java.util.Properties;
import java.io.IOException;

public class AppConfig {
	
	private Properties properties;
	
	public AppConfig() {
        properties = new Properties();
        try{
        	FileInputStream input = new FileInputStream("src/test/resources/application.properties");
            properties.load(input);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	
	public String getChromeDriverPath() {
        return properties.getProperty("webdriver.chrome");
    }

    public String getGeckoDriverPath() {
        return properties.getProperty("webdriver.fireFox");
    }

    public String getAppUrl() {
        return properties.getProperty("app.url");
    }
    
    public String getBrowser() {
    	return properties.getProperty("browser");
    }
    
    public int getWaitTime() {
    	return Integer.parseInt(properties.getProperty("browser.load.wait"));
    }

}
