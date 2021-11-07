package com.obsqura.commands;

import com.obsqura.utility.ExcelUtility;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Commands {
    WebDriver driver;

    public void testInitialize(String browser) {
        if (browser.equalsIgnoreCase("chrome")) {
            System.setProperty("webdriver.chrome.driver", "C:\\selenium\\driverfiles\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver();
        } else if (browser.equalsIgnoreCase("firefox")) {
            System.setProperty("webdriver.gecko.driver", "C:\\selenium\\driverfiles\\geckodriver-v0.30.0-win64\\geckodriver.exe");
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("edge")) {
            System.setProperty("webdriver.edge.driver", "C:\\selenium\\driverfiles\\edgedriver_win64\\msedgedriver.exe");
            driver = new EdgeDriver();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
    }

    @BeforeMethod
    public void setup() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown() {
        //driver.close();
        //driver.quit();
    }

    @Test(priority = 1, enabled = false)
    public void verifyUserLogin() {
        driver.get("http://demowebshop.tricentis.com/");
        WebElement login = driver.findElement(By.xpath("//a[@class='ico-login']"));
        login.click();
        WebElement userName = driver.findElement(By.id("Email"));
        userName.sendKeys("navyanaveenam@gmail.com");
        WebElement password = driver.findElement(By.id("Password"));
        password.sendKeys("Rithul@12");
        WebElement submit = driver.findElement(By.xpath("//input[@class='button-1 login-button']"));
        submit.click();
        WebElement loginText = driver.findElement(By.xpath("//div[@class='header-links']//a[@class='account']"));
        String actualUserName = loginText.getText();
        String expectedUsername = "navyanaveenam@gmail.com";
        Assert.assertEquals(actualUserName, expectedUsername, "LOGIN FAILED");

    }

    @Test(priority = 2, enabled = false)
    public void verifyHomePageTitle() {
        driver.get("http://demowebshop.tricentis.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Demo Web Shop";
        Assert.assertEquals(actualTitle, expectedTitle, "INVALID HOMEPAGE TITLE");
    }

    @Test(priority = 3, enabled = false)
    public void verifyFileUpload() {
        driver.get("https://demo.guru99.com/test/upload/");
        WebElement chooseFile = driver.findElement(By.id("uploadfile_0"));
        chooseFile.sendKeys("C:\\selenium\\data\\sample.txt");
        WebElement accept = driver.findElement(By.id("terms"));
        accept.click();
        WebElement submit = driver.findElement(By.id("submitbutton"));
        submit.click();
    }

    @Test(priority = 4, enabled = false)
    public void verifyConfirmationAlert() {
        driver.get("http://demo.guru99.com/test/delete_customer.php");
        WebElement customerId = driver.findElement(By.name("cusid"));
        customerId.sendKeys("1234");
        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        Alert alert = driver.switchTo().alert();
        //alert.accept();
        //alert.dismiss();
        String alertText = alert.getText();
        alert.accept();
        System.out.println(alertText);
    }

    @Test(priority = 5, enabled = false)
    public void verifySimpleAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement alertOne = driver.findElement(By.id("alertButton"));
        alertOne.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(priority = 6, enabled = false)
    public void verifyAlertAfterFiveSeconds() throws InterruptedException {
        driver.get("https://demoqa.com/alerts");
        WebElement alertTwo = driver.findElement(By.id("timerAlertButton"));
        alertTwo.click();
        Thread.sleep(5000);
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(priority = 7, enabled = false)
    public void verifyToolsQAConfirmationAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement alertThree = driver.findElement(By.id("confirmButton"));
        alertThree.click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(priority = 8, enabled = false)
    public void verifypromptAlert() {
        driver.get("https://demoqa.com/alerts");
        WebElement alertFour = driver.findElement(By.id("promtButton"));
        alertFour.click();
        Alert alert = driver.switchTo().alert();
        alert.sendKeys("Rithul");
        alert.accept();
    }

    @Test(priority = 9, enabled = false)
    public void verifyFramesInHtml() {
        driver.get("https://demoqa.com/frames");
        //driver.switchTo().frame("frame1");
        //driver.switchTo().frame(1);
        WebElement frame = driver.findElement(By.id("frame1"));
        driver.switchTo().frame(frame);
        WebElement frameOneText = driver.findElement(By.id("sampleHeading"));
        String frameText = frameOneText.getText();
        System.out.println(frameText);
        //driver.switchTo().parentFrame();
        driver.switchTo().defaultContent();
    }

    @Test(priority = 10, enabled = false)
    public void verifyTotalNumberOfFrames() {
        driver.get("https://demoqa.com/frames");
        List<WebElement> frame = driver.findElements(By.tagName("iframe"));
        int totalFrames = frame.size();
        System.out.println(totalFrames);
    }

    @Test(priority = 11, enabled = false)
    public void verifySingleCheckboxDemo() {
        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement checkbox = driver.findElement(By.id("gridCheck"));
        checkbox.click();
        boolean status = checkbox.isSelected();
        Assert.assertTrue(status, "Checkbox is not selected");
    }

    @Test(priority = 12, enabled = false)
    public void verifyMultipleWindow() {
        driver.get("http://demo.guru99.com/popup.php");
        String parentWindow = driver.getWindowHandle();
        System.out.println(parentWindow);
        WebElement clickHere = driver.findElement(By.linkText("Click Here"));
        clickHere.click();
        Set<String> windows = driver.getWindowHandles();
        System.out.println(windows);
        Iterator<String> handles = windows.iterator();
        while (handles.hasNext()) {
            String childWindow = handles.next();
            if (!childWindow.equals(parentWindow)) {
                driver.switchTo().window(childWindow);
                WebElement email = driver.findElement(By.xpath("//input[@name='emailid']"));
                email.sendKeys("navyanaveenam@gmail.com");
                WebElement submit = driver.findElement(By.xpath("//input[@name='btnLogin']"));
                submit.click();
                driver.close();
            }
        }
        driver.switchTo().window(parentWindow);
    }

    @Test(priority = 13,enabled = false)
    public void verifySimpleFormDemo() {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement messageBox = driver.findElement(By.id("single-input-field"));
        messageBox.sendKeys("HELLO, HOW ARE YOU?");
        WebElement showMessage = driver.findElement(By.id("button-one"));
        showMessage.click();
        String expectedMessage = "HELLO, HOW ARE YOU?";
        WebElement messageSection = driver.findElement(By.id("message-one"));
        String tempMessage = messageSection.getText();
        String actualMessage = tempMessage.substring(15);
        Assert.assertEquals(actualMessage,expectedMessage, "Entered Message is not matching");
    }
    @Test(priority = 14,enabled = false)
    public void verifyTwoInputFields()
    {
        driver.get("https://selenium.obsqurazone.com/simple-form-demo.php");
        WebElement valueOne = driver.findElement(By.id("value-a"));
        valueOne.sendKeys("10");
        WebElement valueTwo = driver.findElement(By.id("value-b"));
        valueTwo.sendKeys("20");
        WebElement getTotal = driver.findElement(By.id("button-two"));
        getTotal.click();
        WebElement resultSection = driver.findElement(By.id("message-two"));
        String tempResult = resultSection.getText();
        String actualResult = tempResult.substring(14);
        String expectedResult = "30";
        Assert.assertEquals(actualResult,expectedResult,"Addition result is not matching");
    }
    @Test(priority = 15,enabled = false)
    public void verifyMultipleCheckboxDemo()
    {
        driver.get("https://selenium.obsqurazone.com/check-box-demo.php");
        WebElement button = driver.findElement(By.id("button-two"));
        String actualValue = driver.findElement(By.id("is_checked")).getAttribute("value");
        Assert.assertEquals(actualValue,"true","Checkbox is not selected");
    }
    @Test(priority = 16,enabled = false)
    public void verifyDropdowns()
    {
        driver.get("http://demo.guru99.com/test/newtours/register.php");
        WebElement dropdown = driver.findElement(By.name("country"));
        Select select = new Select(dropdown);
        //select.selectByVisibleText("INDIA");
        //select.selectByValue("INDIA");
        select.selectByIndex(10);
    }
    @Test(priority = 17,enabled = false)
    public void verifyDropdownValues()
    {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        List<String> expectedColorValues = new ArrayList<String>();
        expectedColorValues.add("Red");
        expectedColorValues.add("Yellow");
        expectedColorValues.add("Green");
        WebElement colorDropdown = driver.findElement(By.id("single-input-field"));
        Select select = new Select(colorDropdown);
        List<WebElement> actualColorWebElements = select.getOptions();
        List<String> actualColorValues = new ArrayList<String>();
        for(int i = 1;i < actualColorValues.size();i++)
        {
            actualColorValues.add(actualColorWebElements.get(i).getText());
        }
        System.out.println(actualColorValues);
        Assert.assertEquals(actualColorValues,expectedColorValues,"Dropdown value mismatch found in color list selection");
    }
    @Test(priority = 18,enabled = false)
    public void verifyMultipleDropdowns()
    {
        driver.get("https://selenium.obsqurazone.com/select-input.php");
        List<String> expectedSelectedOption = new ArrayList<String>();
        expectedSelectedOption.add("Red");
        expectedSelectedOption.add("Yellow");
        expectedSelectedOption.add("Green");
        WebElement multiColorDropdown = driver.findElement(By.id("multi-select-field"));
        Select select = new Select(multiColorDropdown);
        select.selectByIndex(0);
        select.selectByIndex(1);
        select.selectByIndex(2);
        //select.deselectAll();
        //select.deselectByIndex(1);
        //select.deselectByValue("Green");
        //select.deselectByVisibleText("Red");
        List<WebElement> selectedOptions = select.getAllSelectedOptions();
        List<String> actualSelectedOption = new ArrayList<String>();
        for(int i = 0;i < selectedOptions.size();i++)
        {
            actualSelectedOption.add(selectedOptions.get(i).getText());
        }
        Assert.assertEquals(actualSelectedOption,expectedSelectedOption,"Dropdown value mismatch found in color list selection");
        WebElement firstSelectedOption = select.getFirstSelectedOption();
        String actualFirstSelectedOption = firstSelectedOption.getText();
        String expectedFirstSelectedOption = "Red";
        Assert.assertEquals(actualFirstSelectedOption,expectedFirstSelectedOption,"Dropdown value mismatch found in color list selection");
    }
    @Test(priority = 19,enabled = false)
    public void verifyUsingJavascriptExecutor()
    {
        driver.get("http://demowebshop.tricentis.com/login");
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("document.getElementById('newsletter-email').value='test@gmail.com'");
        executor.executeScript("document.getElementById('newsletter-subscribe-button').click()");
    }
    @Test(priority = 20,enabled = false)
    public  void verifyStaticTable()
    {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<WebElement> tableWebElement = driver.findElements(By.xpath("//table[@id='customers']//tr//td"));
        List<String> tableCellValues = new ArrayList<String>();
        for (int i =0;i<tableWebElement.size();i++)
        {
            tableCellValues.add(tableWebElement.get(i).getText());
        }
        System.out.println(tableCellValues);
    }
    @Test(priority = 21,enabled = false)
    public  void verifyTableRow()
    {
        driver.get("https://www.w3schools.com/html/html_tables.asp");
        List<String> expectedCellValues = new ArrayList<String>();
        expectedCellValues.add("Island Trading");
        expectedCellValues.add("Helen Bennett");
        expectedCellValues.add("UK");
        //List<WebElement> tableWebElement = driver.findElements(By.xpath("//table[@id='customers']//tr//td"));
        List<WebElement> rowWebElement = driver.findElements(By.xpath("//table[@id='customers']//tr"));
        List<String> tableCellValues = new ArrayList<String>();
        for (int i =0;i<rowWebElement.size();i++)
        {
            List<WebElement> actualRowWebElement = driver.findElements(By.xpath("//table[@id='customers']//tr["+i+"]/td"));
            for (int j = 0; j < actualRowWebElement.size(); j++) {
                tableCellValues.add(actualRowWebElement.get(j).getText());
            }
        }
        if (tableCellValues.get(9).equals("Island Trading")) {
            Assert.assertEquals(tableCellValues.get(9), expectedCellValues.get(0), "expected element not available in the table");
        }
    }
    @Test(priority = 22)
    public void verifyRegistration() throws IOException {
        driver.get("http://demo.guru99.com/test/newtours/register.php");
        List<String> excelList = new ArrayList<String>();
        ExcelUtility excel = new ExcelUtility();
        String excelPath = "C:\\Users\\user\\IdeaProjects\\seleniumcommands\\test_data.xlsx";
        String excelSheetName = "regstrationData";
        excelList = excel.readDataFromExcel(excelPath,excelSheetName);
        /*List<WebElement> regTableList = driver.findElements(By.xpath("//tr/td/input"));
        for (int i = 0;i< regTableList.size();i++)
        {
            if(excelList.get(i).equals("INDIA")) {
                WebElement countryDropdown = driver.findElement(By.xpath("//select[@name='country']"));
                Select select = new Select(countryDropdown);
                select.selectByValue(excelList.get(8));
                continue;
            }
            regTableList.get(i).sendKeys(excelList.get(i));
        }*/
        driver.findElement(By.xpath("//input[@name='firstName']")).sendKeys(excelList.get(0));
        driver.findElement(By.xpath("//input[@name='lastName']")).sendKeys(excelList.get(1));
        driver.findElement(By.xpath("//input[@name='phone']")).sendKeys(excelList.get(2));
        driver.findElement(By.xpath("//input[@name='userName']")).sendKeys(excelList.get(3));
        driver.findElement(By.xpath("//input[@name='address1']")).sendKeys(excelList.get(4));
        driver.findElement(By.xpath("//input[@name='city']")).sendKeys(excelList.get(5));
        driver.findElement(By.xpath("//input[@name='state']")).sendKeys(excelList.get(6));
        driver.findElement(By.xpath("//input[@name='postalCode']")).sendKeys(excelList.get(7));
        WebElement countryDropdown = driver.findElement(By.xpath("//select[@name='country']"));
        Select select = new Select(countryDropdown);
        select.selectByValue(excelList.get(8));
        driver.findElement(By.xpath("//input[@name='email']")).sendKeys(excelList.get(9));
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys(excelList.get(10));
        driver.findElement(By.xpath("//input[@name='confirmPassword']")).sendKeys(excelList.get(11));
        driver.findElement(By.xpath("//input[@name='submit']")).click();
    }
}

