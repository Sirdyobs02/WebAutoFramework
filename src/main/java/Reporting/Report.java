package Reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
//import com.relevantcodes.extentreports.reporter.ExtentSparkReporter;
//import com.relevantcodes.extentreports.reporter.configuration.Theme;

public class Report {


    protected static ExtentReports extentReport;
    protected static ExtentTest extentTest;
    protected static ExtentTest extentNode;



    private String testName = "";
    //protected ExtentSparkReporter spark;
    protected static ExtentSparkReporter spark;
    private String reportLocation = "";
    private String tester_name = "";

    public Report(){

    }


    public void start_report(String reportName) {
        if (extentReport == null) {
            String html_report = System.getProperty("user.dir") + "/Reports/"+reportName+".html";
            extentReport = new ExtentReports();
            spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/test.html");
            extentReport.attachReporter(spark);
//            extentReport.addSystemInfo("Environment", "Just Testing")
//                        .addSystemInfo("Ran by", "Luxolo Dyobiso");
        }
    }



    public ExtentReports getReportInstance(String reportName){
       /* extentReport = new ExtentReports();
        spark = new ExtentSparkReporter(System.getProperty("user.dir") + "/Reports/"+reportName+".html");
        extentReport.attachReporter(spark);
        spark.config().setTheme(Theme.DARK);
        //String html_report = System.getProperty("user.dir") + "/Reports/PersonalLoans.html";
        //extentReport = new ExtentReports(html_report, true);
        extentReport.setSystemInfo("Environment", "OM");
        extentReport.setSystemInfo("Ran by", "Luxolo Dyobiso");

        */
        return extentReport;
    }

    public void start_test(String testName) {
        if(!testName.equalsIgnoreCase(this.testName))
            extentTest = extentReport.createTest(testName);
    }

    public void add_step(String nodeName) {
        extentNode = extentTest.createNode(nodeName);
    }


    public void end_test() {
        //extentTest = null;
    }



    public void reportStep(String stepStatus, String testStep){
        if(stepStatus.equalsIgnoreCase("PASS")){
            extentNode.pass("Test: " + testStep + " PASSED");
        }
        else if (stepStatus.equalsIgnoreCase("FAIL")){
            extentNode.pass("Test: " + testStep + " FAILED");
        }
        else if (stepStatus.equalsIgnoreCase("SKIP")){
            extentNode.pass("Test: " + testStep + " IS SKIPPED");
        }
        else if (stepStatus.equalsIgnoreCase("INFO")){
            extentNode.pass("INFO: " + testStep );
        }
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }


    public void save_report(){
        //extentReport.flush();
    }



    public String getReportLocation() {
        return reportLocation;
    }

    public void setReportLocation(String reportLocation) {
        this.reportLocation = reportLocation;
    }

    public String getTester_name() {
        return tester_name;
    }

    public void setTester_name(String tester_name) {
        this.tester_name = tester_name;
    }


}
