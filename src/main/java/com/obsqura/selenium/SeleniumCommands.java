package com.obsqura.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

public class SeleniumCommands {
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
        driver.get("http://demowebshop.tricentis.com/");
        //driver.navigate().to("http://demowebshop.tricentis.com/");
    }
    public static void main(String[] args) {
        SeleniumCommands.testInitialize("chrome");
        /*WebElement loginMenu = driver.findElement(By.className("ico-login"));
        loginMenu.click();
        WebElement rememberMe = driver.findElement(By.id("RememberMe"));
        boolean rememberStatus = rememberMe.isEnabled();
        System.out.println("Status of remember me checkbox - "+rememberStatus);
        WebElement login = driver.findElement(By.className("login-button"));
        boolean loginEnable = login.isEnabled();
        System.out.println("Enable status of login button - "+loginEnable);
        boolean loginDisplay = login.isDisplayed();
        System.out.println("Display status of login button - "+loginDisplay);*/
        WebElement registerMenu = driver.findElement(By.className("ico-register"));
        registerMenu.click();
        List<WebElement> genders = driver.findElements(By.name("Gender"));
        for (int i = 0;i<genders.size();i++)
        {
            String value = genders.get(i).getAttribute("id");
            if(value.equals("gender-female"))
            {
                genders.get(i).click();
            }
        }
        /*WebElement femaleRadioButton = driver.findElement(By.id("gender-female"));
        femaleRadioButton.click();
        boolean status = femaleRadioButton.isSelected();
        System.out.println("Radio button status - "+status);
        driver.navigate().back();
        driver.navigate().forward();
        driver.navigate().refresh();*/
    }
}
