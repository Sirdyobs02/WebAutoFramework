package Loans;

import Controller.TestMarshal;
import Data.DPPersonalLoans;
import Data.Utility;
import Reporting.Report;
import org.testng.annotations.*;

public class PersonalLoans extends Report{

    TestMarshal testMarshal = new TestMarshal();

    @BeforeClass
    public void setUpClass() throws Exception {
        start_report("Loans");
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
