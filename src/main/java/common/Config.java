package common;

import utils.AllureTools;

import java.io.File;

public class Config {
   public static String PLATFORM_AND_BROWSER = "win_chrome";
   public static String WIN_ALLURE_RESULTS_PATH = (new File(AllureTools.class.getProtectionDomain().getCodeSource().getLocation().getFile())).getParent().replace("\\target","")+ "\\allure-results";
   public static String WIN_ALLURE_HTML_PATH = WIN_ALLURE_RESULTS_PATH + "\\ReportHTML";
   public static String WIN_ALLURE_REPORT_PATH = "C:\\***\\Prog\\allure-2.29.0\\bin\\allure";
   public static String LINUX_ALLURE_RESULTS_PATH = "/app/allure-results";
   public static String LINUX_ALLURE_HTML_PATH = LINUX_ALLURE_RESULTS_PATH + "/ReportHTML";
   public static String LINUX_ALLURE_REPORT_PATH = "/opt/allure-2.29.0/bin/allure";
}
