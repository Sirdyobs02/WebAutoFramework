package Selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class SeleniumActions extends SeleniumDriver{


    public SeleniumActions(){
        super();
    }


    WebElement element = null;
    String currentWindowHandle;
    public void navigate(){
        try{
            start_browser(prop.getProperty("browser"));
            webDriver.get(prop.getProperty("url"));
            webDriver.manage().window().maximize();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void element_click(String elementSelector, String selectorValue){
        try {
            switch (elementSelector){
                case "id":
                    webDriverWaitToBeClickable(elementSelector,selectorValue);
                    webDriver.findElement(By.id(selectorValue)).click();

                    break;
                case "css":
                    webDriverWaitToLoad(elementSelector,selectorValue);
                    webDriver.findElement(By.cssSelector(selectorValue)).click();
                    break;
                case "xpath":
                    webDriverWaitToLoad(elementSelector,selectorValue);
                    webDriver.findElement(By.xpath(selectorValue)).click();
                    break;
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }


    public boolean validate(String selector, String selectorValue, String io) {
        WebElement webElement = null;
        boolean valid = false;
        switch (selector){
            case "id":
                webDriverWaitToLoad(selector,selectorValue);
               webElement = webDriver.findElement(By.id(selectorValue));
               break;
            case "css":
                webDriverWaitToLoad(selector,selectorValue);
                webElement = webDriver.findElement(By.cssSelector(selectorValue));
                break;
            case "xpath":
                webDriverWaitToLoad(selector,selectorValue);
                webElement = webDriver.findElement(By.xpath(selectorValue));
                break;
            case "tagName":
                webDriverWaitToLoad(selector,selectorValue);
                webElement = webDriver.findElement(By.tagName(selectorValue));
                break;
        }
        String[] validate_values = io.split(":");
        if (validate_values[0].equalsIgnoreCase("out"))
            if (webElement.getAttribute("innerText").equalsIgnoreCase(validate_values[1]))
                valid = true;

        return valid;
    }

    public void hoverOverAndClick(String parentElementSelector,String parentElementSelectorValue,
                                  String childElementSelector,String childElementSelectorValue) throws InterruptedException {

        Actions actions =new Actions(webDriver);
        WebElement parentWebElement = null;
        WebElement childWebElement = null;

        switch (parentElementSelector){
            case "id":
                parentWebElement = webDriver.findElement(By.id(parentElementSelectorValue));
                break;
            case "css":
                parentWebElement = webDriver.findElement(By.cssSelector(parentElementSelectorValue));
                break;
            case "xpath":
                parentWebElement = webDriver.findElement(By.xpath(parentElementSelectorValue));
                break;
            case "tagName":
                parentWebElement = webDriver.findElement(By.tagName(parentElementSelectorValue));
                break;
        }
        actions.moveToElement(parentWebElement).perform();
        Thread.sleep(3000);
        switch (childElementSelector){
            case "id":
                childWebElement = webDriver.findElement(By.id(childElementSelectorValue));
                break;
            case "css":
                childWebElement = webDriver.findElement(By.cssSelector(childElementSelectorValue));
                break;
            case "xpath":
                childWebElement = webDriver.findElement(By.xpath(childElementSelectorValue));
                break;
            case "tagName":
                childWebElement = webDriver.findElement(By.tagName(childElementSelectorValue));
                break;
            case "linkText":
                childWebElement = webDriver.findElement(By.linkText(childElementSelectorValue));
        }
        actions.moveToElement(childWebElement);
        currentWindowHandle = webDriver.getWindowHandle();
        actions.click().build().perform();

    }

    public void switchToNewTab(){
        Set<String> handles = webDriver.getWindowHandles();
        for(String actual: handles){
            if(!actual.equalsIgnoreCase(currentWindowHandle)) {
                webDriver.switchTo().window(actual);
            }
        }
    }

    public void scrollElementIntoView(String selector, String selectorValue) throws InterruptedException {

        WebElement element = null;

        switch (selector) {
            case "id":
                element = webDriver.findElement(By.id(selectorValue));
                break;
            case "css":
                element = webDriver.findElement(By.cssSelector(selectorValue));
                break;
            case "xpath":
                element = webDriver.findElement(By.xpath(selectorValue));
                break;
            case "tagName":
                element = webDriver.findElement(By.tagName(selectorValue));
                break;
        }
       //((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", element);
        ((JavascriptExecutor) webDriver).executeScript("javascript:window.scrollBy(250,350)");
        Thread.sleep(500);
    }

    public void selectFromDropdownUsingValue(String selector,String selectorValue,String valueToSelect){
        //webDriver.switchTo().frame(webDriver.findElement(By.tagName("om-personal-loans-calculator")));

        String input[] = valueToSelect.split(":");
        Select webElement = null;
        switch (selector) {
            case "id":
                webElement = new Select(webDriver.findElement(By.id(selectorValue)));
                break;
            case "css":
                webElement = new Select(webDriver.findElement(By.cssSelector(selectorValue)));
                break;
            case "xpath":
                webElement = new Select(webDriver.findElement(By.xpath(selectorValue)));
                break;
            case "tagName":
                webElement = new Select(webDriver.findElement(By.tagName(selectorValue)));
                break;
        }
        webElement.selectByValue("R50000");
        //JavascriptExecutor executer = (JavascriptExecutor)webDriver;
        //executer.executeScript("document.getElementById('loanAmount').setAttribute('state', 'valid');");
        //executer.executeScript("document.getElementById('loanAmount').setAttribute('value', 'R50000');");
        //executer.executeScript("document.getElementByClassName('theme-default secondary-large').removeAttribute('disabled');");

        //List<WebElement> dropDownElement =  webElement.getOptions().se;
        //webElement.selectByValue("R50000");
        //for(int i = 0; i <= dropDownElement.size(); i++){

       // }



    }

    public void selectDropdownUsingOptions(String dropDownSelector, String dropDownSelectorValue,
                                           String optionsSelector,String optionsSelectorValue,String valueToSelect) throws InterruptedException {
        WebElement dropdownElement = null;
        Thread.sleep(3000);
        switch (dropDownSelector) {
            case "id":
                webDriverWaitToBeClickable(dropDownSelector,dropDownSelectorValue);
                webDriver.findElement(By.id(dropDownSelectorValue)).click();
                break;
            case "css":
                webDriverWaitToBeClickable(dropDownSelector,dropDownSelectorValue);
                //webDriver.findElement(By.cssSelector(dropDownSelectorValue)).click();
                break;
            case "xpath":
                webDriverWaitToBeClickable(dropDownSelector,dropDownSelectorValue);
                webDriver.findElement(By.xpath(dropDownSelectorValue)).click();
                break;
            case "tagName":
                webDriverWaitToBeClickable(dropDownSelector,dropDownSelectorValue);
                webDriver.findElement(By.tagName(dropDownSelectorValue)).click();
                break;
        }
        List<WebElement> optionsElement = null;

        switch (optionsSelector) {
            case "id":
                optionsElement = webDriver.findElements(By.id(optionsSelectorValue));
                break;
            case "css":
                optionsElement = webDriver.findElements(By.cssSelector(optionsSelectorValue));
                break;
            case "xpath":
                optionsElement = webDriver.findElements(By.xpath(optionsSelectorValue));
                break;
            case "tagName":
                optionsElement = webDriver.findElements(By.tagName(optionsSelectorValue));
                break;
        }

        for(int i = 0; i <= optionsElement.size() - 1; i++) {
            if (optionsElement.get(i).getAttribute("text").equalsIgnoreCase(valueToSelect.split(":")[1])){
                JavascriptExecutor executer = (JavascriptExecutor)webDriver;
                executer.executeScript("arguments[0].click();",optionsElement.get(i));
                webDriver.navigate().refresh();
                executer.executeScript("arguments[0].click();",optionsElement.get(i));
                //optionsElement.get(i).
            }
        }


        //dropdownElement.
    }

    public void webDriverWaitToBeClickable(String selector,String selectorValue){
        WebDriverWait wait = new WebDriverWait(webDriver,30);
        switch (selector) {
            case "id":
                wait.until(ExpectedConditions.elementToBeClickable(By.id(selectorValue)));
                break;
            case "css":
                wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(selectorValue)));
                break;
            case "xpath":
                wait.until(ExpectedConditions.elementToBeClickable(By.xpath(selectorValue)));
                break;
            case "tagName":
                wait.until(ExpectedConditions.elementToBeClickable(By.tagName(selectorValue)));
                break;
        }
    }

    public void webDriverWaitToLoad(String selector,String selectorValue) {
        WebDriverWait wait = new WebDriverWait(webDriver, 30);
        switch (selector) {
            case "id":
                wait.until(ExpectedConditions.presenceOfElementLocated(By.id(selectorValue)));
                break;
            case "css":
                wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(selectorValue)));
                break;
            case "xpath":
                wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(selectorValue)));
                break;
            case "tagName":
                wait.until(ExpectedConditions.presenceOfElementLocated(By.tagName(selectorValue)));
                break;
        }
    }



}
