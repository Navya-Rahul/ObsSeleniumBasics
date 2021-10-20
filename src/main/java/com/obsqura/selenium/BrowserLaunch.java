package com.obsqura.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BrowserLaunch {
    static WebDriver driver;
    public static void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\selenium\\driverfiles\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
        }
        else if (browser.equalsIgnoreCase("firefox")){
            System.setProperty("webdriver.gecko.driver","C:\\selenium\\driverfiles\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
        else if (browser.equalsIgnoreCase("edge")){
            System.setProperty("webdriver.edge.driver","C:\\selenium\\driverfiles\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        driver.get("http://demo.guru99.com/test/newtours/");
    }
    public static void main(String[] args) {
        BrowserLaunch.testInitialize("chrome");
        String title = driver.getTitle();
        System.out.println(title);
        String url = driver.getCurrentUrl();
        System.out.println(url);
        String source = driver.getPageSource();
        System.out.println(source);
        driver.close();
    }
}
