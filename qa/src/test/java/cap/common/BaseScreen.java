package cap.common;

import PayzliPOS_MobileApp.PayzliPOS_MobileApp_PageContainer;
import cap.helpers.Constants;
import com.google.common.collect.Ordering;
import io.appium.java_client.*;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.android.StartsActivity;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.ios.IOSElement;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.openqa.selenium.*;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.RemoteWebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;

import static io.appium.java_client.touch.WaitOptions.waitOptions;
import static io.appium.java_client.touch.offset.PointOption.point;
import static java.time.Duration.ofMillis;
import static java.lang.String.valueOf;

public class BaseScreen {

    protected final WebDriver driver;
    protected final WebDriverWait wait;
    public AppiumDriver appiumDriver = null;

    public BaseScreen(WebDriver driver) {
        //PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
        PageFactory.initElements(new AppiumFieldDecorator(driver, Duration.ofSeconds(5)), this);
        this.driver = driver;
//        wait = new WebDriverWait(this.driver, Integer.parseInt(System.getProperty(Constants.OBJECT_WAIT_TIME)));
//        wait = new WebDriverWait(this.driver, WaitTimeUtil.getWaitTime(Constants.OBJECT_WAIT_TIME));
        wait = new WebDriverWait(this.driver, Integer.parseInt("30"));
    }


    public void startAndroidActivity(String strAppPackage, String strAppActivity) {
        ((StartsActivity) driver).startActivity(new Activity(strAppPackage, strAppActivity));
    }

    public void hideMobileKeyboard() {
        ((HidesKeyboard) driver).hideKeyboard();
    }

    public WebElement waitForElement(WebElement element) {
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    protected WebElement waitForElement(By by) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public WebElement waitForElementClickable(By by) {
        return wait.until(ExpectedConditions.elementToBeClickable(by));
    }

    public WebElement waitForElementClickable(WebElement element) {
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public WebElement waitForElementToBeVisible(By element) {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(element));
    }

    static String strPlatformName = System.getProperty("platformName");

    public boolean click(WebElement element) {
        WebElement ele;
        try {
            ele = waitForElement(element);
            ele.click();
            return true;
        } catch (Exception e) {
            System.out.println("\n Click Element:: " + e.getMessage());
            return false;
        }
    }

    public boolean click(By by) {
        WebElement ele;
        try {
            ele = waitForElement(by);
            ele.click();
            return true;
        } catch (Exception e) {
            System.out.println("\n Click Element:: " + e.getMessage());
            return false;
        }
    }

    public boolean verifyElement(WebElement element) {
        try {
            waitForElement(element).isDisplayed();
            return true;
        } catch (Exception e) {
            System.out.println("\n Click Element:: " + e.getMessage());
            return false;
        }
    }

    public static boolean verifyElementWithoutWait(WebElement element) {
        boolean blResult = false;

        try {
            if (element.isDisplayed()) {
                Thread.sleep(2000);
                blResult = true;
            } else {
                blResult = false;
            }
        } catch (Exception e) {
            System.out.println("\n Click Element:: " + e.getMessage());
        }

        return blResult;
    }

    public void waitForSecond(int i) {
        try {
            Thread.sleep(i * 1000);
        } catch (Exception e) {
            System.out.println("\n waitForSecond:: " + e.getMessage());
        }
    }

    public boolean enterValue(WebElement element, String strVlaue) {
        try {
            waitForElement(element).click();
            waitForElement(element).clear();
            waitForElement(element).sendKeys(strVlaue);
            return true;
        } catch (Exception e) {
            System.out.println("\n Enter a value - Exception:: " + e.getMessage());
            return false;
        }
    }

    public boolean enterValueIOS(WebElement element, String strValue) {
        try {
            waitForElement(element).click();
            clearTextBox(element);
            waitForElement(element).sendKeys(strValue);
            return true;
        } catch (Exception e) {
            System.out.println("\n Enter a value - Exception:: " + e.getMessage());
            return false;
        }
    }

    public boolean enterValueWithDoubleClick(WebElement element, String strVlaue) {
        try {
            waitForElement(element).click();
            waitForElement(element).click();
            waitForElement(element).sendKeys(strVlaue);
            return true;
        } catch (Exception e) {
            System.out.println("\n Enter a value - Exception:: " + e.getMessage());
            return false;
        }
    }


    public boolean enterValueWithoutClear(WebElement element, String strVlaue) {
        try {
            waitForElement(element).sendKeys(strVlaue);
            return true;
        } catch (Exception e) {
            System.out.println("\n Enter a value - Exception:: " + e.getMessage());
            return false;
        }
    }

    public void swipeUpToDownUsingDimension() {
        //  swipeFromUpToBottom();
        Dimension size = driver.manage().window().getSize();
        int anchor = (int) (size.width / 2);
        // Swipe up to scroll down
        int startPoint = (int) (size.height - 10);
        int endPoint = 10;
        if (strPlatformName.equals(Constants.ANDROID)) {
            new TouchAction(((AndroidDriver) driver))
                    .longPress(point(anchor, startPoint))
                    .moveTo(point(anchor, endPoint))
                    .release()
                    .perform();
        } else if (strPlatformName.equals(Constants.IOS)) {
            new TouchAction(((IOSDriver) driver))
                    .longPress(point(anchor, startPoint))
                    .moveTo(point(anchor, endPoint))
                    .release()
                    .perform();
        }
    }

    public void jsSwipeDown() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap scrollObject = new HashMap();
        scrollObject.put("direction", "down");
        js.executeScript("mobile: scroll", scrollObject);
    }

    public void jsSwipeUp() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        HashMap scrollObject = new HashMap();
        scrollObject.put("direction", "up");
        js.executeScript("mobile: scroll", scrollObject);
    }

    public void jsSwipeLeft() {
        waitForSecond(5);
        waitForSecond(2);
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.9D);
        int endx = (int) (size.width * 0.1D);
        int starty = size.height / 2;

        TouchAction<?> action = new TouchAction((PerformsTouchActions) driver);
        action.press(PointOption.point(startx, starty)).moveTo(PointOption.point(endx, starty)).release().perform();

    }

    public void scroll(WebElement element) {
        TouchActions action = null;
        if (strPlatformName.equals(Constants.ANDROID)) {
            action = new TouchActions(((AndroidDriver) driver));
        } else if (strPlatformName.equals(Constants.IOS)) {
            action = new TouchActions(((IOSDriver) driver));
        }
        action.scroll(element, 10, 10);
        action.perform();
    }

    public static PayzliPOS_MobileApp_PageContainer pageContainer;

    public void takeScreenshot(WebDriver driver) {
        try {
            pageContainer.myScenario.attach(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES),
                    "image/png", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void jsIOSNextDatePicker(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Map<String, Object> params = new HashMap<>();
        params.put("order", "next");
        params.put("offset", 0.15);
        params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: selectPickerWheelValue", params);
    }

    public void iOSLeftSwipeScreen() {

        final int ANIMATION_TIME = 200; // ms
        final HashMap<String, String> scrollObject = new HashMap<String, String>();

        scrollObject.put("direction", "left");
        JavascriptExecutor js = (JavascriptExecutor) driver;

        try {
            js.executeScript("mobile:swipe", scrollObject);
            Thread.sleep(ANIMATION_TIME); // always allow swipe action to complete
        } catch (Exception e) {
            System.err.println("mobileSwipeScreenIOS(): FAILED \n" + e.getMessage());
            return;
        }
    }

    public static void attachStepLog(String strKey, String strValue) {
        try {
            pageContainer.printTestDataMap.put(strKey, strValue);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void scrollDown() {
        TouchAction action = new TouchAction((PerformsTouchActions) driver);
        Dimension size = driver.manage().window().getSize();
        int width = size.width;
        int height = size.height;
        int middleOfX = width / 2;
        int startYCoordinate = (int) (height * .7);
        int endYCoordinate = (int) (height * .2);
        action.press(PointOption.point(middleOfX, startYCoordinate))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .moveTo(PointOption.point(middleOfX, endYCoordinate)).release().perform();
    }

    public void navigateToBack() {
        try {
            driver.navigate().back();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clickEnterKey() {
        ((AndroidDriver) driver).pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    public boolean waitForElementToBeClick(WebElement element) {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            return true;
        } catch (Exception e) {
            System.out.println("\n Click Element:: " + e.getMessage());
            return false;
        }
    }

    public void swipeUp() {
        Dimension size = driver.manage().window().getSize();

        int startx = (int) (size.width * 0.5);
        int starty = (int) (size.height * 0.8);

        int endx = (int) (size.width * 0.2);
        int endy = (int) (size.height * 0.2);

        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startx, starty))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endx, endy)).release().perform();
    }

    public void swipeUpDirection() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.2);
        int starty = (int) (size.height * 0.5);
        int endx = (int) (size.width * 0.2);
        int endy = (int) (size.height * 0.2);
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startx, starty))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endx, endy)).release().perform();
        waitForSecond(2);
    }

    public void swipeHorizontalAndroid(List<WebElement> lstElements) {
        AndroidElement toElement = (AndroidElement) lstElements.get(0);
        AndroidElement fromElement = (AndroidElement) lstElements.get(2);
        int centerY = fromElement.getLocation().y + (fromElement.getSize().height / 2);
        int startX = fromElement.getLocation().x;
        int endX = toElement.getLocation().x;
        TouchAction act = new TouchAction((PerformsTouchActions) driver);
        act.press(PointOption.point(startX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(endX, centerY))
                .release()
                .perform();
    }

    public void swipeHorizontalIOS(List<WebElement> lstElements) {
        System.out.println("Total Elements " + lstElements.size());
        IOSElement toElement = (IOSElement) lstElements.get(0);
        IOSElement fromElement = (IOSElement) lstElements.get(2);
        int centerY = fromElement.getLocation().y + (fromElement.getSize().height / 2);
        int startX = fromElement.getLocation().x;
        int endX = toElement.getLocation().x;
        TouchAction act = new TouchAction((PerformsTouchActions) driver);
        act.press(PointOption.point(startX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(endX, centerY))
                .release()
                .perform();
    }

    public void jsClick(WebElement element) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", element);
        waitForSecond(1);
    }

    public void jsClick(By by) {
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("var elem=arguments[0]; setTimeout(function() {elem.click();}, 100)", by);
        waitForSecond(1);
    }


    public void mouseOver(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).build().perform();
    }

    public void mouseClick(WebElement element) {
        Actions actions = new Actions(driver);
        actions.moveToElement(element).click(element).build().perform();
    }

    public boolean verifyElementWithoutWait(By element) {
        boolean isVerify;
        try {
            isVerify = driver.findElement(element).isDisplayed();
        } catch (NoSuchElementException error) {
            error.getMessage();
            isVerify = false;
        }
        return isVerify;
    }

    public void mouseClick(By by) {
        WebElement elmnt = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(elmnt).click(elmnt).build().perform();
    }

    public void mouseHover(By by) {
        WebElement elmnt = driver.findElement(by);
        Actions actions = new Actions(driver);
        actions.moveToElement(elmnt).build().perform();

    }

    public boolean verifyElement(By element) {
        boolean isVerify = false;
        try {
            isVerify = driver.findElement(element).isDisplayed();
        } catch (NoSuchElementException error) {
            error.getMessage();
            isVerify = false;
        }
        return isVerify;
    }

    public List<WebElement> waitForElements(List<WebElement> element) {
        return wait.until(ExpectedConditions.visibilityOfAllElements(element));
    }


    public void waitForInvisibilityOfElement(int inWait, By by) {
        try {
            new WebDriverWait(driver, inWait).until(ExpectedConditions.invisibilityOfElementLocated(by));
        } catch (Exception e) {
            System.out.println("Exception for invisibility: " + e.getMessage());
        }
    }

    public void swipeDownDirection() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width * 0.4);
        int starty = (int) (size.height * 0.4);
        int endx = (int) (size.width * 0.2);
        int endy = (int) (size.height * 0.6);
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startx, starty))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endx, endy)).release().perform();
    }

    public void scrollRandom(String text) {
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true))" +
                        ".scrollIntoView(new UiSelector().text(\"" + text + "\"))"));
    }

    public void scrollHorizontalIntoView(String strTextToScroll) {
        driver.findElement(MobileBy
                .AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true)).setAsHorizontalList().scrollIntoView("
                        + "new UiSelector().text(\"" + strTextToScroll + "\"));"));
    }


    public void swipeLeftListOfElements(List<WebElement> elements, int xOffset) {
        try {
            Point points = elements.get(elements.size() - 1).getLocation();
            TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
            touchAction.press(PointOption.point(points.getX(), points.getY() + 50)).waitAction(waitOptions(Duration.ofSeconds(2)))
                    .moveTo(PointOption.point(xOffset, points.getY() + 50)).release().perform();
        } catch (Exception ignored) {
            System.out.println("Catch>>>>>>>>>>>>>>>>>>");
        }
    }

    public boolean tapElementInMiddleByCoOrdinates(WebElement element) {
        boolean elementTapped = false;
        try {
            int startPointXOfElement = element.getLocation().getX();
            int startPointYOfElement = element.getLocation().getY();
            int X = (int) (startPointXOfElement * 1.3);
            int Y = (int) (startPointYOfElement * 1.02);
            tapByCooridantes(X, Y);
            elementTapped = true;
        } catch (Exception e) {
            System.out.println(e);
        }
        return elementTapped;
    }


    public void scrollVerticalIntoView(String strTextToScroll) {
        driver.findElement(MobileBy.AndroidUIAutomator(
                "new UiScrollable(new UiSelector().scrollable(true)).setAsVerticalList().scrollIntoView(" +
                        "new UiSelector().text(\"" + strTextToScroll + "\"))"));

    }

    public void swipeHorizontalAndroidDashboard(List<WebElement> lstElements) {
        System.out.println("Total Elements " + lstElements.size());
        AndroidElement toElement = null;
        AndroidElement fromElement = null;
        if (lstElements.size() == 3) {
            toElement = (AndroidElement) lstElements.get(1);
            fromElement = (AndroidElement) lstElements.get(2);
        }
        if (lstElements.size() == 2) {
            toElement = (AndroidElement) lstElements.get(0);
            fromElement = (AndroidElement) lstElements.get(1);
        }
        int centerY = fromElement.getLocation().y + (fromElement.getSize().height / 2);
        int startX = fromElement.getLocation().x;
        int endX = toElement.getLocation().x;
        TouchAction act = new TouchAction((PerformsTouchActions) driver);
        act.press(PointOption.point(startX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(endX, centerY))
                .release()
                .perform();
    }


    public void swipeHorizontalIOSDashboard(List<WebElement> lstElements) {
        System.out.println("Total Elements " + lstElements.size());
        IOSElement toElement = null;
        IOSElement fromElement = null;
        if (lstElements.size() == 3) {
            toElement = (IOSElement) lstElements.get(1);
            fromElement = (IOSElement) lstElements.get(2);
        }
        if (lstElements.size() == 2) {
            toElement = (IOSElement) lstElements.get(0);
            fromElement = (IOSElement) lstElements.get(1);
        }
        int centerY = fromElement.getLocation().y + (fromElement.getSize().height / 2);
        int startX = fromElement.getLocation().x;
        int endX = toElement.getLocation().x;
        TouchAction act = new TouchAction((PerformsTouchActions) driver);
        act.press(PointOption.point(startX, centerY))
                .waitAction(WaitOptions.waitOptions(Duration.ofSeconds(3)))
                .moveTo(PointOption.point(endX, centerY))
                .release()
                .perform();
    }

    public void swipeByElement(WebElement startElement, WebElement endElement) {
        int startX = startElement.getLocation().getX() + (startElement.getSize().getWidth() / 2);
        int startY = startElement.getLocation().getY() + (startElement.getSize().getHeight() / 2);
        int endX = endElement.getLocation().getX() + (endElement.getSize().getWidth() / 2);
        int endY = endElement.getLocation().getY() + (endElement.getSize().getHeight() / 2);
        new TouchAction((PerformsTouchActions) driver)
                .press(point(startX, startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();

    }


    public void tapByCooridantes(int Xcoordinate, int Ycoordinate) {
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.tap(PointOption.point(Xcoordinate, Ycoordinate)).perform();
    }

    public void tapCooridinatesByElement(WebElement element) {
        waitForSecond(3);
        int Xcoordinate = element.getLocation().x;
        int Ycoordinate = element.getLocation().y;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.tap(PointOption.point(Xcoordinate, Ycoordinate)).perform();
    }

    public void reLaunchApp() {
        ((AppiumDriver<WebElement>) driver).terminateApp("qa.sample.app");
        System.out.println("App Teriminated");
        waitForSecond(3);
        ((AppiumDriver<WebElement>) driver).launchApp();
        System.out.println("App ReLaunched");
    }

    public void swipeForYearPaymentHistory() {
        int startx = 675;
        int starty = 879;
        int endx = 690;
        int endy = 1055;
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startx, starty))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endx, endy)).release().perform();
    }

    public void reLaunchAppAndroid() {
        ((AppiumDriver<WebElement>) driver).closeApp();
        System.out.println("App Teriminated");
        waitForSecond(3);
        ((AppiumDriver<WebElement>) driver).activateApp("qa.app.android");
        System.out.println("App ReLaunched");
    }

    public boolean enterValueWithoutWait(WebElement element, String strValue) {
        try {
            waitForSecond(3);
            element.sendKeys(strValue);
            return true;
        } catch (Exception e) {
            System.out.println("\n Enter a value - Exception:: " + e.getMessage());
            return false;
        }
    }

    public void enterValueUsingAppium(String strValue, By by) {
        try {
            appiumDriver = (AppiumDriver) driver;
            appiumDriver.findElement(by).sendKeys(strValue);
        } catch (Exception e) {
            System.out.println("\n Enter a value - Exception:: " + e.getMessage());
        }
    }

    public boolean clickWithoutWait(WebElement element) {
        try {
            waitForSecond(2);
            element.click();
            return true;
        } catch (Exception e) {
            System.out.println("\n Click Element Without wait:: " + e.getMessage());
            return false;
        }
    }

    public void scrollDownIntoViewIOS(String strTextToScroll) {
        appiumDriver = (AppiumDriver) driver;
        RemoteWebElement element = (RemoteWebElement) appiumDriver.findElement(By.name(strTextToScroll));
//        RemoteWebElement element = (RemoteWebElement)appiumDriver.findElementsByName(strTextToScroll)
        String elementID = element.getId();
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("element", elementID);
        scrollObject.put("toVisible", "not an empty string");
        appiumDriver.executeScript("mobile:scroll", scrollObject);

    }

    public boolean exitLoop(int seconds, long startTime) {
        return (System.currentTimeMillis() - startTime) < seconds;
    }

    public WebElement waitForElementIgnoreStale(WebElement element) {
        return wait.until(ExpectedConditions.refreshed(ExpectedConditions.visibilityOf(element)));
    }


    public void fluentWait(By by) {
        wait.withTimeout(30, TimeUnit.SECONDS);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public void swipeLeftIOS() {
        waitForSecond(1);
        Dimension size = driver.manage().window().getSize();

        int startY = (int) (size.height / 2);
        int startX = (int) (size.width * 0.05);
        int endX = (int) (size.width * 0.90);
        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(startX, startY))
                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(endX, startY))
                .release()
                .perform();
    }

    public void swipeLeftIOS2() {
        waitForSecond(1);
        Dimension size = driver.manage().window().getSize();
        System.out.println(size);
        int startX = (int) (size.height / 2);
        System.out.println("StartX>>>>>>>" + startX);
        int startY = (int) (size.width * 0.98);
        System.out.println("StartY>>>>>>>" + startY);
        int endY = (int) (size.width * 0.70);
        System.out.println("EndY>>>>>>>>>" + endY);
        new TouchAction((PerformsTouchActions) driver)
                .press(PointOption.point(startX, startY))
//                .waitAction(WaitOptions.waitOptions(Duration.ofMillis(1000)))
                .moveTo(PointOption.point(startX, endY))
                .release()
                .perform();
    }

    public boolean enterValue(By element, String strValue) {
        try {
            waitForElement(element).click();
            waitForElement(element).clear();
            waitForElement(element).sendKeys(strValue);
            return true;
        } catch (Exception e) {
            System.out.println("\n Enter a value - Exception:: " + e.getMessage());
            return false;
        }
    }

    public int sumArrayList(ArrayList<String> arr) {
        int sum = 0; // initialize sum
        String value = "";
        int i;

        // Iterate through all elements and add them to sum
        for (i = 0; i < arr.size(); i++)
            value += arr.get(i);
        System.out.println(sum);
        return sum;
    }

    public void fluentWaitUntilVisible(By by) {
        wait.withTimeout(30, TimeUnit.SECONDS);
        wait.pollingEvery(250, TimeUnit.MILLISECONDS);
        wait.ignoring(NoSuchElementException.class);
        wait.until(ExpectedConditions.visibilityOfElementLocated(by));
    }

    public void scrollByCoordinates(int Xcoordinate, int Ycoordinate, int X1coordinate, int Y1coordinate) {
        int startX = Xcoordinate;
        int startY = Ycoordinate;
        int endX = X1coordinate;
        int endY = Y1coordinate;
        new TouchAction((PerformsTouchActions) driver)
                .press(point(startX, startY))
                .waitAction(waitOptions(ofMillis(1000)))
                .moveTo(point(endX, endY))
                .release().perform();
    }

    public static void scrollToEle(AndroidDriver driver, String elementName, boolean scrollDown) {
        String listID = ((RemoteWebElement) driver.findElementByAndroidUIAutomator("new UiSelector().className(\"android.widget.ListView\")")).getId();
        String direction;
        if (scrollDown) {
            direction = "down";
        } else {
            direction = "up";
        }
        HashMap<String, String> scrollObject = new HashMap<String, String>();
        scrollObject.put("direction", direction);
        scrollObject.put("element", listID);
        scrollObject.put("text", elementName);
        driver.executeScript("mobile: scrollTo", scrollObject);
    }

//--------------------------------------------------------------------------------------------------

    public String currentDate() {
        String strCurrentDate = null;
        DateTimeFormatter date = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDateTime now = LocalDateTime.now();
        strCurrentDate = date.format(now);
        return strCurrentDate;
    }

    public static String removeWordFromString(String strActualString, String wordToRemove) {
        String strNewString = strActualString.replaceAll(wordToRemove, "");
        strNewString = strNewString.trim();
        System.out.print(strNewString);
        return strNewString;
    }

    public static String randomGeneratedValueInt(String initialNumber) {
        Random rand = new Random(); //instance of random class
        String number = initialNumber;
        int upperbound = 999999999;
        int intRandom = rand.nextInt(upperbound);
        String randomMobileNumber = valueOf(intRandom);
        String mobileNumber = number.concat(randomMobileNumber);
        System.out.println(mobileNumber+" <<<<<<<<<<<<<<<");
        int i = 0;
        while ((mobileNumber.length() != 10)) {
            if (i == 10) {
                break;
            }
            randomGeneratedValueInt(initialNumber);
            i++;
        }
        return mobileNumber;
    }

    public boolean verifyElementsNotPresent(List<WebElement> element) {
        boolean isVerified = true;
        for (WebElement product : element) {
            try {
                if (product.isDisplayed()) {
                    isVerified = false;
                    break;
                }
            } catch (Exception e) {

            }
        }
        return isVerified;
    }

    public boolean verifyElements(List<WebElement> element) {
        boolean isVerified = false;
        for (WebElement product : element) {
            if (product.isDisplayed()) {
                isVerified = true;
            } else {
                isVerified = false;
                break;
            }
        }
        return isVerified;
    }

    public String generateRandomNumbers(int count) {

        Random rand = new Random();
        String number = "";
        for (int i = 1; i <= count; i++) {
            number = number.concat("9");
        }
        int upperbound = Integer.parseInt(number);
        int random = rand.nextInt(upperbound);
        String randomNumber = valueOf(random);
        return randomNumber;
    }

    public void swipeOverGridIOS(WebElement element) {
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);

        int elementHeight = element.getRect().getHeight();//Y
        int elementWidth = element.getRect().getWidth();//X

        int startPointXOfElement = (int) (elementWidth * 0.7);
        int startPointYOfElement = elementHeight / 2;

        int startTapPointXOfElement = startPointXOfElement + element.getLocation().getX();
        int startTapPointYOfElement = startPointYOfElement + element.getLocation().getY();

        int endTapPointXOfElement = (int) (element.getLocation().getX() * 2.5);
        int endTapPointYOfElement = startPointYOfElement + element.getLocation().getY();

        Point startLocation = new Point(startTapPointXOfElement, startTapPointYOfElement);
        Point endLocation = new Point(endTapPointXOfElement, endTapPointYOfElement);

        touchAction.press(PointOption.point(startLocation))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
                .moveTo(PointOption.point(endLocation))
                .release()
                .perform();
        waitForSecond(2);
    }

    public void tapOnMiddleOfElementWithCoOrdinates(WebElement element) {
        waitForSecond(2);
        int elementHeight = element.getRect().getHeight();
        int elementWidth = element.getRect().getWidth();

        int tapHeightOfElement = elementHeight / 2;
        int tapWidthOfElement = elementWidth / 2;

        int tapPointXOfElement = tapWidthOfElement + element.getLocation().getX();
        int tapPointYOfElement = tapHeightOfElement + element.getLocation().getY();
        tapByCooridantes(tapPointXOfElement, tapPointYOfElement);
    }

    public void tapOnSelectOptionWithCoordinates(WebElement element) {
        waitForSecond(2);
        int elementHeight = element.getRect().getHeight();
        int elementWidth = element.getRect().getWidth();

        int tapHeightOfElement = elementHeight / 2;
        int tapWidthOfElement = (int) (elementWidth / 1.2);

        int tapPointXOfElement = tapWidthOfElement + element.getLocation().getX();
        int tapPointYOfElement = tapHeightOfElement + element.getLocation().getY();
        tapByCooridantes(tapPointXOfElement, tapPointYOfElement);
    }

    public void tapOnSelectOptionWithCoordinatesForSchedule(WebElement element) {
        waitForSecond(2);
        int elementHeight = element.getRect().getHeight();
        int elementWidth = element.getRect().getWidth();

        int tapHeightOfElement = elementHeight / 2;
        int tapWidthOfElement = (int) (elementWidth / 1.08);

        int tapPointXOfElement = tapWidthOfElement + element.getLocation().getX();
        int tapPointYOfElement = tapHeightOfElement + element.getLocation().getY();
        tapByCooridantes(tapPointXOfElement, tapPointYOfElement);
        System.out.println(tapPointXOfElement + "-" + tapPointYOfElement);
    }

    public void clickLastKeyInKeyboard() {
        By lastKeyInKeyboard = (By.xpath("//XCUIElementTypeKey[@name='space']/following::XCUIElementTypeButton[1]"));
        try {
            waitForElement(lastKeyInKeyboard);
            click(lastKeyInKeyboard);
        } catch (Exception e) {
            System.out.println("\n Click Element:: " + e.getMessage());
        }
    }

    public String takeFirstLineAndRemoveOthersFromString(String entireString) {
        int newLineIndex = entireString.indexOf("\n");
        return entireString.substring(0, newLineIndex);
    }

    public boolean verifyElementWithoutTryCatch(WebElement element) {
        if (waitForElement(element).isDisplayed()) {
            return true;
        } else {
            System.out.println("\n Unable to verify the element" + element);
            return false;
        }
    }

    public boolean verifyElementWithoutTryCatch(By element) {
        boolean isVerify;
        if (driver.findElement(element).isDisplayed()) {
            isVerify = true;
        } else {
            isVerify = false;
        }
        return isVerify;
    }

    public static String strImageDirectory = System.getProperty("user.dir") + "/config/files/";

    public void pushFileToDevice(String strImageName) {
        try {
            System.out.println("strImageDirectory: " + strImageDirectory + strImageName);
            if (System.getProperty("PLATFORM").equalsIgnoreCase("android")) {
                ((AndroidDriver<WebElement>) driver).pushFile("/storage/emulated/0/Downloads/" + strImageName + "", new File(strImageDirectory + strImageName));
            } else if (System.getProperty("PLATFORM").equalsIgnoreCase("ios")) {
                ((IOSDriver<WebElement>) driver).pushFile("On My iPhone/Downloads/" + strImageName + "", new File(strImageDirectory + strImageName));
            }
            System.out.println("After Push>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isAscending(List<Date> dates) {
        for (int i = 0; i < dates.size() - 1; i++) {
            if (dates.get(i).compareTo(dates.get(i + 1)) >= 0) {
                return false;
            }
        }
        return true;
    }

    public static boolean isDateAscending(List<Date> dates) {
        boolean isAscending = true;
        for (int i = 1; i < dates.size(); i++) {
            if (dates.get(i).compareTo(dates.get(i - 1)) < 0) {
                isAscending = false;
                break;
            }
        }
        return isAscending;
    }


    public static boolean isDescending(List<Date> dates) {
        for (int i = 0; i < dates.size() - 1; i++) {
            if (dates.get(i).compareTo(dates.get(i + 1)) < 0) {
                return false;
            }
        }
        return true;
    }

    public void clearTextBox(WebElement element) {
        int countText = element.getText().length() / 5;
        for (int i = 0; i <= countText; i++) {
            element.clear();
        }
    }

    public static boolean notVerifyElement(WebElement element) {
        boolean blResult = true;
        try {
            if (element.isDisplayed()) {
                blResult = false;
            }
        } catch (Exception e) {
        }
        return blResult;
    }


    public boolean verifyElementForFilter(WebElement element) {

        try {
            waitForElement(element).isDisplayed();
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public void swipeDownDirection2() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.2);
        int startY = (int) (size.height * 0.4);
        int endX = (int) (size.width * 0.2);
        int endY = (int) (size.height * 0.9);
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
        waitForSecond(1);
    }

    public static boolean verifyAscendingOrder(List<WebElement> elementList, String strType) {
        boolean isSorted = false;
        try {
            if (strType.equals("string")) {
                List<String> eleStringSortedList = new ArrayList<String>();
                for (WebElement eleItem : elementList) {
                    eleStringSortedList.add(eleItem.getText().toLowerCase());
                    System.out.println("------------------------------\n" + eleItem.getText());
                }
                isSorted = Ordering.natural().isOrdered(eleStringSortedList);


            } else if (strType.equals("int")) {
                List<Integer> eleIntegerList = new ArrayList<Integer>();
                for (WebElement eleItem : elementList) {
                    eleIntegerList.add(Integer.valueOf(eleItem.getText()));
                }
                isSorted = Ordering.natural().isOrdered(eleIntegerList);

            } else if (strType.equals("stringwithnumbers")) {

                List<String> eleStringSortedList = new ArrayList<String>();
                List<String> eleStringUnSortedList = new ArrayList<String>();
                for (WebElement eleItem : elementList)
                    if (!eleItem.getText().equals("")) {
                        String strText = eleItem.getText().toLowerCase().replace("“", "\"").replace("”", "\"");
                        eleStringUnSortedList.add(strText);
                    }
                eleStringSortedList.addAll(eleStringUnSortedList);
                Collections.sort(eleStringSortedList);
                if (eleStringUnSortedList.equals(eleStringSortedList)) {
                    isSorted = true;
                }
            }
        } catch (Exception e) {
            System.out.println(new StringBuilder().append("************Exception:  ")
                    .append(e.getLocalizedMessage())
                    .append("   occured in:")
                    .append(e.getStackTrace()[0])
                    .append("********************"));
        }
        return isSorted;
    }

    public static boolean verifyDescendingOrder(List<WebElement> elementList, String strType, String strDateFormat) {
        boolean isSorted = false;

        try {
            if (strType.equalsIgnoreCase("string")) {
                List<String> eleStringSortedList = new ArrayList<String>();
                for (WebElement eleItem : elementList) {
                    eleStringSortedList.add(eleItem.getText().toLowerCase());
                    System.out.println("------------------------------\n" + eleItem.getText());
                }
                isSorted = Ordering.natural().reverse().isOrdered(eleStringSortedList);

            } else if (strType.equals("int")) {
                List<Integer> eleIntegerList = new ArrayList<Integer>();
                for (WebElement eleItem : elementList) {
                    eleIntegerList.add(Integer.valueOf(eleItem.getText()));

                }
                isSorted = Ordering.natural().reverse().isOrdered(eleIntegerList);

            } else if (strType.equals("stringwithnumbers")) {

                List<String> eleStringSortedList = new ArrayList<String>();
                List<String> eleStringUnSortedList = new ArrayList<String>();
                for (WebElement eleItem : elementList)
                    if (!eleItem.getText().equals("")) {
                        String strText = eleItem.getText().toLowerCase().replace("“", "\"").replace("”", "\"");
                        eleStringSortedList.add(strText);
                    }
                eleStringUnSortedList.addAll(eleStringSortedList);

                Collections.sort(eleStringSortedList, Collections.reverseOrder());

                if (eleStringUnSortedList.equals(eleStringSortedList)) {
                    isSorted = true;
                }
            }
        } catch (Exception e) {
            System.out.println(new StringBuilder().append("************Exception:  ")
                    .append(e.getLocalizedMessage())
                    .append("   occured in:")
                    .append(e.getStackTrace()[0])
                    .append("********************"));
        }
        return isSorted;
    }

    public static boolean isAscendingForSales(List<Double> values) {
        boolean isSorted = false;
        for (int i = 1; i < values.size(); i++) {
            System.out.println("print i>>" + i);
            System.out.println("print i>>" + (i - 1));
            if (values.get(i) >= values.get(i - 1)) {
                System.out.println(values.get(i));
                System.out.println(values.get(i - 1));
                isSorted = true;
            }
        }
        return isSorted;
    }

    public static boolean isListInAscendingOrder(List<Integer> numbers) {
        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i) > numbers.get(i + 1)) {
                return false;
            }
        }
        return true;
    }

    public void tapSelect(WebElement element) {
        waitForSecond(2);
        int elementHeight = element.getRect().getHeight();
        int elementWidth = element.getRect().getWidth();

        int tapHeightOfElement = (int) (elementHeight / 1.5);
        int tapWidthOfElement = (int) (elementWidth / 1.2);

        int tapPointXOfElement = tapWidthOfElement + element.getLocation().getX();
        int tapPointYOfElement = tapHeightOfElement + element.getLocation().getY();

        tapByCooridantes(tapPointXOfElement, tapPointYOfElement);
    }

    public void tapOnSelectOptionWithCoordinatesForAppointments(WebElement element) {
        waitForSecond(2);
        int elementHeight = element.getRect().getHeight();
        int elementWidth = element.getRect().getWidth();

        int tapHeightOfElement = elementHeight / 2;
        int tapWidthOfElement = (int) (elementWidth / 1.1);

        int tapPointXOfElement = tapWidthOfElement + element.getLocation().getX();
        int tapPointYOfElement = tapHeightOfElement + element.getLocation().getY();

        System.out.println(tapPointXOfElement + " " + tapPointYOfElement);
        tapByCooridantes(tapPointXOfElement, tapPointYOfElement);
    }

    public void swipeUpDirectionOnGrid(WebElement element) {
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        int elementHeight = element.getRect().getHeight();
        int elementWidth = element.getRect().getWidth();
        int startPointXOfElement = elementWidth / 2;
        int startPointYOfElement = (int) (elementHeight / 1.2);
        int startTapPointXOfElement = startPointXOfElement + element.getLocation().getX();
        int startTapPointYOfElement = startPointYOfElement + element.getLocation().getY();
        int endTapPointXOfElement = startPointXOfElement + element.getLocation().getX();
        int endTapPointYOfElement = startPointXOfElement + (int) (element.getLocation().getY() / 3.3);
        Point startLocation = new Point(startTapPointXOfElement, startTapPointYOfElement);
        Point endLocation = new Point(endTapPointXOfElement, endTapPointYOfElement);
        touchAction.press(PointOption.point(startLocation))
                .waitAction(new WaitOptions().withDuration(Duration.ofMillis(600)))
                .moveTo(PointOption.point(endLocation))
                .release()
                .perform();
        waitForSecond(2);
    }

    public boolean waitForElementToBeClick(By element) {
        boolean isVerified = false;
        try {
            wait.until(ExpectedConditions.elementToBeClickable(element));
            isVerified = true;
        } catch (Exception e) {
            System.out.println("\n Click Element:: " + e.getMessage());
        }
        return isVerified;
    }

    public void swipeUpDirectionWhenKeyboardVisibleIOS() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width / 2);
        int starty = (int) (size.height / 2);
        int endx = (int) (size.width * 0.2);
        int endy = (int) (size.height * 0.2);
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startx, starty))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endx, endy)).release().perform();
        waitForSecond(1);
    }

    public void swipeDownDirectionFormMiddleScreen() {
        Dimension size = driver.manage().window().getSize();
        int startx = (int) (size.width / 2.5);
        int starty = (int) (size.height / 2.5);
        int endx = (int) (size.width * 1);
        int endy = (int) (size.height * 1);
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startx, starty))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endx, endy)).release().perform();
        waitForSecond(1);
    }

    public void swipeHorizontalFromRightToLeft() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.6);
        int startY = (int) (size.height * 0.4);
        int endX = (int) (size.width * 0.1);
        int endY = (int) (size.height * 0.4);
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
        waitForSecond(1);
    }

    public void swipeHorizontalFromLeftToRight() {
        Dimension size = driver.manage().window().getSize();
        int startX = (int) (size.width * 0.1);
        int startY = (int) (size.height * 0.4);
        int endX = (int) (size.width * 0.6);
        int endY = (int) (size.height * 0.4);
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        System.out.println(">>>>>>>>>>>>>>>Start Point" + startX + "-" + startY);
        System.out.println(">>>>>>>>>>>>>>>End Point" + endX + "-" + endY);
        touchAction.press(PointOption.point(startX, startY))
                .waitAction(waitOptions(Duration.ofSeconds(1)))
                .moveTo(PointOption.point(endX, endY)).release().perform();
        waitForSecond(1);
    }

    public void tapOnMiddleOfScreen() {
        TouchAction touchAction = new TouchAction((PerformsTouchActions) driver);
        // get the size of the screen
        Dimension size = driver.manage().window().getSize();
        // calculate the middle point of the screen
        int centreX = (int) (size.width * 0.5);
        int centreY = (int) (size.height * 0.5);
        // tap on the middle of the screen
        touchAction.tap(PointOption.point(centreX, centreY)).perform();
    }

    public String getTheValueOfTimerAdded(String strPreviousTime, String strAddedTime) {

        int previousHours = Integer.parseInt(strPreviousTime.substring(0, 3));
        int previousMinutes = Integer.parseInt(strPreviousTime.substring(4, 6));

        int addedHours = Integer.parseInt(strAddedTime.substring(0, 2));
        int addedMinutes = Integer.parseInt(strAddedTime.substring(3, 5));

        int previousTotalMinutes = (previousHours * 60) + previousMinutes;
        int addedTotalMinutes = (addedHours * 60) + addedMinutes;

        int resultTotalMinutes = previousTotalMinutes + addedTotalMinutes;
        int resultHours = resultTotalMinutes / 60;
        int resultMinutes = resultTotalMinutes % 60;

        String resultTimeStr = String.format("%03d:%02d", resultHours, resultMinutes);
        System.out.println("Result Time: " + resultTimeStr);
        return resultTimeStr;
    }
}


