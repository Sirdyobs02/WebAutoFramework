package Loans;

import Controller.TestMarshal;
import Data.DPPersonalLoans;
import Data.Utility;
import Reporting.Report;
//import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.markuputils.ExtentColor;
//import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.Status;
//import com.aventstack.extentreports.LogStatus;
import org.testng.ITestResult;
import org.testng.annotations.*;

public class PersonalLoans extends Report{

    Utility utility = new Utility();
    TestMarshal testMarshal = new TestMarshal();

    @BeforeClass
    public void setUpClass() throws Exception {
        start_report();
    }


    @BeforeMethod
    public void initialize() {

        //start_test("PersonalLoans");
    }

    @Test(priority = 1, dataProvider = "calculatePersonalLoans", dataProviderClass = DPPersonalLoans.class)
    public void calculateLoans(String test_step,String test_description,String action,
                               String element_selector,String selector_value,
                               String input_output_data) throws InterruptedException {
        add_step(test_step);
        testMarshal.test(test_step,test_description,action,element_selector,selector_value,input_output_data);

    }


   @Test(priority = 2,dataProvider = "calculateHomeLoans", dataProviderClass = DPPersonalLoans.class)
    public void PersonalLoan(String test_step,String test_description,String action,
                               String element_selector,String selector_value,
                               String input_output_data) throws InterruptedException {
          add_step(test_step);
          testMarshal.test(test_step,test_description,action,element_selector,selector_value,input_output_data);
    }



    @AfterTest
    public void end(){
        end_test();
    }

    @AfterClass
    public void teardown(){
        extentReport.flush();
    }
}
