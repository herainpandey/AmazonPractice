package com.seleniumapi.suite.utils.reports.service;


import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.*;
import org.testng.xml.XmlSuite;

import java.io.File;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Reportermanager implements IReporter {

    private ExtentReports extent;
    private static final Logger log = LoggerFactory.getLogger(Reportermanager.class);
    @Override
    public void generateReport(List<XmlSuite> xmlSuites, List<ISuite> suites, String outputDirectory) {

        this.extent = new ExtentReports(outputDirectory + File.separator + "repor.html", true);

        Iterator var4 = suites.iterator();

        while (var4.hasNext()) {
            ISuite suite = (ISuite) var4.next();
            Map<String, ISuiteResult> result = suite.getResults();
            Iterator var7 = result.values().iterator();


            while (var7.hasNext()) {
                ISuiteResult r = (ISuiteResult) var7.next();
                ITestContext context = r.getTestContext();
                this.buildTestNode(context.getFailedTests(), LogStatus.FAIL);
                this.buildTestNode(context.getSkippedTests(), LogStatus.SKIP);
                this.buildTestNode(context.getPassedTests(), LogStatus.PASS);
                log.info("pushing result to dashboard");
            }
        }
        this.extent.flush();
        this.extent.close();
    }

    private void buildTestNode(IResultMap tests, LogStatus status){

        if(tests.size() > 0){
            Iterator var4 = tests.getAllResults().iterator();

            while(var4.hasNext()){
                ITestResult result = (ITestResult) var4.next();
                String methodName = result.getMethod().getMethodName();
                String description = result.getMethod().getDescription();
                ExtentTest test;

                if(description !=null && !description.isEmpty()){
                    test = this.extent.startTest(this.getMethodName(methodName, description),description);
                }else {
                    test = this.extent.startTest(methodName);
                }

                test.getTest().getStartedTime();
                test.getTest().setEndedTime(this.getTime(result.getEndMillis()));
                test.assignCategory(result.getMethod().getGroups());
                String message = "test" + status.toString().toLowerCase() ;

                if(result.getThrowable() != null){
                    message = result.getThrowable().getMessage();
                }


                List<String> logList = Reporter.getOutput(result);


                for(int i=0; i< logList.size() ; ++i){
                    if(status == LogStatus.PASS){
                        test.log(status, (String)logList.get(i));
                    }else if (i == logList.size() -1){
                        test.log(status, (String)logList.get(i));
                        test.log(status, result.getThrowable());
                    }else{
                        test.log(LogStatus.PASS, (String)logList.get(i));
                    }
                }

                this.extent.endTest(test);
            }
        }
        }

    private Date getTime(long millis){
            Calendar calendar = Calendar.getInstance();
            calendar.setTimeInMillis(millis);
            return  calendar.getTime();
        }

    private String getMethodName(String methodName, String description){
            if(description !=null && !description.isEmpty()){
                Pattern p = Pattern.compile(" (.)");
                Matcher m = p.matcher(description);
                StringBuffer sb = new StringBuffer();

                while(m.find()){
                    m.appendReplacement(sb, m.group(1).toUpperCase());
                }

                m.appendTail(sb);
                return methodName + "(" + sb.toString() + ")";
            }
            else{
                return methodName;
            }
        }


    public void addTestResultForDashboard(ITestContext context){
        try {
            String projectName = context.getSuite().getName();
            Integer testFailCount = context.getFailedTests().size();
            Integer testPassCount = context.getPassedTests().size();
            Integer testSkippedCount = context.getSkippedTests().size();
            Long testStartTime = context.getStartDate().getTime();
            Long testEndTime = context.getEndDate().getTime();
            String timeStamp = System.getProperty("timeStamp");

        }catch (Exception var13){
            var13.printStackTrace();
        }
        }
}
