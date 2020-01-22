package com.fadx.maven.fashion.Base;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;
 
public class RetryAnalyzer extends BaseClass implements IRetryAnalyzer {
	   private int retryCount = 0;
	   private int maxRetryCount = 1;

	   public boolean retry(ITestResult result) {

	       if (retryCount < maxRetryCount) {

	           retryCount++;
	           return true;
	           
	       }	       
	       
	       return false;
	   }	   
	   
}