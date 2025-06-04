package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.Log;

import static utils.AllureAttachmentTools.attachScreenshotPNG;
import static utils.Log.currentTestNames;
import static utils.Log.testStatus;

public class Listner implements ITestListener {
   @Override
   public void onTestStart(ITestResult iTestResult) {
      Log.textLevelOFF();
      Log.printTestTitle(iTestResult.getName());
      Log.textLevelON();
   }

   @Override
   public void onTestSuccess(ITestResult iTestResult) {
      Log.println("Test " + iTestResult.getName() + " Success","g");
      testStatus.get(testStatus.size()-1).add("Success");
   }

   @Override
   public void onTestFailure(ITestResult iTestResult) {
      Log.println("*** Test " + iTestResult.getName() + " failed","r");
      attachScreenshotPNG();
      testStatus.get(testStatus.size()-1).add("Failed");
//      LogEntries logEntries = CommonActions.getDriver().manage().logs().get(LogType.BROWSER);
//      for (LogEntry entry : logEntries) {
//         System.out.println(new Date(entry.getTimestamp()) + " " + entry.getLevel() + " " + entry.getMessage());
//      }
   }

   @Override
   public void onTestSkipped(ITestResult iTestResult) {
      Log.println("*** Test " + iTestResult.getName() + " skipped","r");
      if(!currentTestNames.contains(iTestResult.getName())){
         currentTestNames.add(iTestResult.getName());
      }
      testStatus.get(testStatus.size()-1).add("Skipped");
   }

   @Override
   public void onTestFailedButWithinSuccessPercentage(ITestResult iTestResult) {
      Log.println("*** Test " + iTestResult.getName() + " failed but within success percentage","r");
      testStatus.get(testStatus.size()-1).add("Failed");
   }

   @Override
   public void onStart(ITestContext iTestContext) {
      Log.println("\n" + "=".repeat(80));
      Log.println("Start test suit");
      Log.println("=".repeat(80));
   }

   @Override
   public void onFinish(ITestContext iTestContext) {
      Log.println("\n" + "=".repeat(80));
      Log.println("Finish test suit");
      Log.println("=".repeat(80) + "\n");
   }
}
