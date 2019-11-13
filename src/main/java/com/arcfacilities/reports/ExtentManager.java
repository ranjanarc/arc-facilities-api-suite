package com.arcfacilities.reports;

import java.io.File;

import com.arcfacilities.utilities.DateUtils;
import com.relevantcodes.extentreports.ExtentReports;

public class ExtentManager {
    static ExtentReports extent;
    final static String filePath = System.getProperty("user.dir") +"\\" + new File(ReporterConstants.AUTOMATION_REPORTS_FOLDER)  +"\\" + DateUtils.getDate() + "\\" + "report.html";
    
    //final static String filePath = System.getProperty("user.dir") +"\\" + (ReporterConstants.AUTOMATION_REPORTS_FOLDER)  +"\\" + DateUtils.getDate() + "\\" + "report.html";
    public synchronized static ExtentReports getReporter() {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
        }
        
        return extent;
    }
}