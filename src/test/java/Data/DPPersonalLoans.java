package Data;

import Reporting.Report;
import org.testng.annotations.DataProvider;

public class DPPersonalLoans {
    Utility utility = new Utility();
    Report report = new Report();


    @DataProvider
    public Object[][] calculatePersonalLoans(){
        report.start_test("calculateLoan");
        Object testData[][] = utility.getTestData("CalculatePersonalLoan.xlsx");
        return testData;
    }
    @DataProvider
    public Object[][] calculateHomeLoans(){
        report.start_test("calculateHomeLoan");
        Object testData[][] = utility.getTestData("CalculateHomeLoan.xlsx");
        return testData;
    }

}
