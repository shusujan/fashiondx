package com.fadx.maven.fashion.Base;

import com.relevantcodes.extentreports.ExtentReports;



public class ExtentManager {
    public static ExtentReports extent;
    
    public synchronized static ExtentReports getReporter(String filePath) {
        if (extent == null) {
            extent = new ExtentReports(filePath, true);
            
        }
        
        return extent;
    }
}

