package com.obsqura.commands;

import com.obsqura.utility.ExcelUtility;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().pageLoadTimeout(10,TimeUnit.SECONDS);
    }

    @BeforeMethod
    public void setup() {
        testInitialize("chrome");
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
        Date date = new Date();
        String currentDate = dateFormat.format(date);
        if (ITestResult.FAILURE == result.getStatus())
        {
            TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
            File screenshot = takeScreenshot.getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File("./Screenshots/"+result.getName()+currentDate+".png"));
        }
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
    @Test(priority = 22,enabled = false)
    public void verifyRegistration() throws IOException {
        driver.get("http://demo.guru99.com/test/newtours/register.php");
        List<String> excelList = new ArrayList<String>();
        ExcelUtility excel = new ExcelUtility();
        String excelPath = "//src//main//resources//test_data.xlsx";
        String excelSheetName = "regstrationData";
        excelList = excel.readDataFromExcel(excelPath,excelSheetName);
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
    @Test(priority = 23,enabled = false)
    public void verifyBuffaloCartLogin() throws InterruptedException {
        driver.get("https://buffalocart.com/demo/billing/public/login");
        WebElement userName = driver.findElement(By.id("username"));
        userName.sendKeys("admin");
        WebElement password = driver.findElement(By.id("password"));
        password.sendKeys("123456");
        WebElement submit = driver.findElement(By.xpath("//button[@class='btn btn-primary']"));
        submit.click();
        WebElement endTour = driver.findElement(By.xpath("//button[@class='btn btn-default btn-sm']"));
        endTour.click();
        WebElement userManagement = driver.findElement(By.xpath("//span[@class='title']"));
        userManagement.click();
        Thread.sleep(500);
        List<String> expectedTabValues = new ArrayList<String>();
        expectedTabValues.add("Users");
        expectedTabValues.add("Roles");
        expectedTabValues.add("Sales Commission Agents");
        List<WebElement> userManagementWebElement = driver.findElements(By.xpath("//ul[@class='treeview-menu menu-open']//span"));
        List<String> actualTabValues = new ArrayList<String>();
        for (int i = 0;i< userManagementWebElement.size();i++)
        {
            actualTabValues.add(userManagementWebElement.get(i).getText());
        }
        Assert.assertEquals(actualTabValues,expectedTabValues,"Values are not matching");
    }
    @Test(priority = 24,enabled = false)
    public void verifyDoubleClick()
    {
        driver.get("http://demo.guru99.com/test/simple_context_menu.html");
        WebElement doubleClickButton = driver.findElement(By.xpath("//button"));
        Actions action = new Actions(driver);
        action.doubleClick(doubleClickButton).build().perform();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }
    @Test(priority = 25,enabled = false)
    public void verfyRightClick()
    {
        driver.get("http://demo.guru99.com/test/simple_context_menu.html");
        WebElement rightClickButton = driver.findElement(By.xpath("//span[@class='context-menu-one btn btn-neutral']"));
        Actions action = new Actions(driver);
        action.contextClick(rightClickButton).build().perform();
    }
    @Test(priority = 26,enabled = false)
    public void verifyMouseOver()
    {
        driver.get("https://demoqa.com/menu/");
        WebElement mainItem = driver.findElement(By.xpath("//a[text()='Main Item 2']"));
        Actions action = new Actions(driver);
        action.moveToElement(mainItem).build().perform();
        //action.moveToElement(mainItem,50,50).build().perform();
        //action.moveByOffset(100,100).build().perform();
    }
    @Test(priority = 27,enabled = false)
    public void verifyDragAndDrop()
    {
        driver.get("https://demoqa.com/droppable");
        WebElement dragElement = driver.findElement(By.id("draggable"));
        WebElement dropElement = driver.findElement(By.id("droppable"));
        Actions action = new Actions(driver);
        action.dragAndDrop(dragElement,dropElement).build().perform();
    }
    @Test(priority = 28,enabled = false)
    public void verifyDragAndDropByOffset()
    {
        driver.get("https://demoqa.com/dragabble");
        WebElement dragAndDropElement = driver.findElement(By.id("dragBox"));
        int xOffset1 = dragAndDropElement.getLocation().getX();
        int yOffset1 = dragAndDropElement.getLocation().getY();
        System.out.println(xOffset1);
        System.out.println(yOffset1);
        int x = xOffset1 + 20;
        int y = yOffset1 + 20;
        Actions action = new Actions(driver);
        action.dragAndDropBy(dragAndDropElement,x,y).build().perform();
    }
    @Test(priority = 29,enabled = false)
    public void verifyClickAndHold()
    {
        driver.get("https://demoqa.com/resizable");
        WebElement resizableElement = driver.findElement(By.xpath("//div[@id='resizableBoxWithRestriction']//span"));
        Actions action = new Actions(driver);
        action.clickAndHold(resizableElement).dragAndDropBy(resizableElement,500,500).build().perform();
    }
    @Test(priority = 30,enabled = false)
    public void takeScreenshot() throws IOException {
        driver.get("https://www.google.com/");
        TakesScreenshot takeScreenshot = (TakesScreenshot) driver;
        File screenshot = takeScreenshot.getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File("./Screenshots/"+"test.png"));
    }
    @Test(priority = 31,enabled = false)
    public void verifyClickAndHoldNew()
    {
        driver.get("https://selenium08.blogspot.com/2020/01/click-and-hold.html");
        WebElement dragElement = driver.findElement(By.xpath("//li[@name='G']"));
        WebElement dropElement = driver.findElement(By.xpath("//li[@name='A']"));
        Actions action = new Actions(driver);
        action.clickAndHold(dragElement).dragAndDrop(dragElement,dropElement).build().perform();
    }
    @Test(priority = 32,enabled = false)
    public void verifyScreenshotMethod()
    {
        driver.get("https://www.google.com/");
        String actualTitle = driver.getTitle();
        String expectedTitle = "Google123";
        Assert.assertEquals(actualTitle,expectedTitle,"Invalid Title");
    }
    @Test(priority = 33,enabled = false)
    @Deprecated
    public void verifyWaitsInSelenium()
    {
        driver.get("http://demo.guru99.com/test/newtours/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebElement submit = driver.findElement(By.xpath("//input[@name='submit']"));
        WebDriverWait wait = new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.visibilityOf(submit));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@name='submit']")));
    }
    @Test(priority = 34,enabled = false)
    public void verifyRobotClass() throws AWTException {
        driver.get("http://demowebshop.tricentis.com/register");
        WebElement firstName = driver.findElement(By.id("FirstName"));
        firstName.click();
        Robot robot = new Robot();
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_TAB);
        robot.delay(2000);
        robot.keyPress(KeyEvent.VK_TAB);
    }
    @Test(priority = 35,enabled = true)
    public void verifyFileUploadUsingRobotClass() throws AWTException {
        driver.get("https://www.monsterindia.com/seeker/registration");
        //file path passed as a parameter to StringSelection
        StringSelection selection = new StringSelection("C:\\Users\\user\\Desktop\\obsqura\\sample.txt");
        //Clipboard copy
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(selection,null);
        WebElement chooseCV = driver.findElement(By.xpath("//span[@class='browse-text']"));
        chooseCV.click();
        Robot robot = new Robot();
        robot.delay(2000);
        //Pressing Ctrl + V
        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        //Releasing Ctrl + V
        robot.keyRelease(KeyEvent.VK_CONTROL);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);
    }
}

