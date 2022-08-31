package Controller;

import Reporting.Report;
import Selenium.SeleniumActions;
import Selenium.SeleniumDriver;

public class TestMarshal extends SeleniumDriver {

    Report report = new Report();
    private String testName;

    public boolean test(String testStep,String testDesc,String action,
                        String selector,String selectorValue,String io) throws InterruptedException {
        boolean test_passed = false;
        report.reportStep("INFO","Executing "+testStep+" by "+ action+ " using selector "+selector);
        SeleniumActions seleniumActions = new SeleniumActions();
        switch (action){
            case "navigate":
                seleniumActions.navigate();
                test_passed = true;
                break;
            case "validate":
                seleniumActions.validate(selector,selectorValue,io);
                test_passed = true;
                break;
            case "click":
                seleniumActions.element_click(selector,selectorValue);

                test_passed = true;
                break;
            case "hoverOverAndClick":
                String pcSelector[] = selector.split("~");
                String pcSelectorValues[] = selectorValue.split("~");
                seleniumActions.hoverOverAndClick(pcSelector[0],pcSelectorValues[0],
                                                  pcSelector[1],pcSelectorValues[1]);
                test_passed = true;
                break;
            case "switchTab":
                seleniumActions.switchToNewTab();
                test_passed = true;
                break;
            case "select":
                seleniumActions.selectFromDropdownUsingValue(selector,selectorValue,io);
                test_passed = true;
                break;
            case "scroll":
                seleniumActions.scrollElementIntoView(selector,selectorValue);
                test_passed = true;
                break;
            case "selectOptions":
                String dropDownSelector[] = selector.split("~");
                String dropDownSelectorValue[] = selectorValue.split("~");
                seleniumActions.selectDropdownUsingOptions(dropDownSelector[0],dropDownSelectorValue[0],
                                                             dropDownSelector[1],dropDownSelectorValue[1],io);
                test_passed = true;
                break;
            case "endTest":
                webDriver.quit();
                test_passed = true;
                report.end_test();
                break;

        }
        if (test_passed){
            report.reportStep("PASS","Success");
        }else{
            report.reportStep("FAIL","FAILED");
        }
        return test_passed;
    }




    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }


}
