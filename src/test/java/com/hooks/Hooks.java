package com.hooks;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import com.configuration.AppConfig;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {
    
	private static WebDriver driver;

	private static final Logger log = LogManager.getLogger(Hooks.class);
    
    public Hooks() {	
    }
	
	@Before(order = 1)
	public void beforeScenario(Scenario scenario) {
		log.info("loading driver");
		AppConfig appConfig = new AppConfig();
		if(appConfig.getBrowser().equalsIgnoreCase("chrome")) {
			System.setProperty("webdriver.chrome.driver", appConfig.getChromeDriverPath());
			ChromeOptions options = new ChromeOptions();
			driver = new ChromeDriver(options);
			log.info("loading chrom driver");
		}
		else if(appConfig.getBrowser().equalsIgnoreCase("fireFox")) {
			System.setProperty("webdriver.gecko.driver", appConfig.getGeckoDriverPath());
			FirefoxOptions options = new FirefoxOptions();
     		driver = new FirefoxDriver(options);
     		log.info("loading firefox driver");
		}
		else {
			log.error("error loading driver");
			throw new IllegalArgumentException("Unsupported browser specified: " + appConfig.getBrowser());
		}
	}
	
	public static WebDriver getDriver() {
		return driver;
	}
	
	@After(order = 1)
	public void afterScenario(Scenario scenario) {
		if (scenario.isFailed()) {
            try {
                // Take a screenshot and embed it in the report
                byte[] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
                scenario.attach(screenshot, "image/png", scenario.getId());
            } catch (WebDriverException e) {
                log.error("Failed to capture screenshot: " + e.getMessage());
            }
        }
        // Quit the WebDriver
        if (driver != null) {
            driver.quit();
        }
	}	

}