package common;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.PageURL;
import utils.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.logging.Level;

import static common.Config.PLATFORM_AND_BROWSER;
import static constants.Constant.*;
import static pages.PageURL.refreshURLs;

/**
 *
 */
public class CommonActions {
   private static HashMap<String, WebDriver> drivers = new HashMap<>();


   /**
    * Получить драйвер. В случае если драйвер не был создан, создает драйвер.
    */
   public static WebDriver getDriver() {
      if (drivers.get(Thread.currentThread().getName()) == null) {
         return createDriver();
      }
      return drivers.get(Thread.currentThread().getName());
   }

   /**
    * Закрыть драйвер.
    */
   public static void closeDriver() {
      try {
         if (drivers.get(Thread.currentThread().getName()) != null) {
            drivers.get(Thread.currentThread().getName()).quit();
            drivers.remove(Thread.currentThread().getName());

         }
      } catch (Exception ex) {
         Log.println("Error closing driver window ");
         Log.println(ex.getMessage());
      }
   }
   /**
    * Закрыть все драйвера.
    */
   public static void closeAllDrivers() {
      try {
         for (WebDriver driver : drivers.values()) {
            if (driver != null) {
               driver.quit();
            }
         }
         drivers = new HashMap<>();
      } catch (Exception ex) {
         Log.println("Error closing drivers window ");
         Log.println(ex.getMessage());
      }
   }

   /**
    * Создать драйвер.
    */
   private static WebDriver createDriver() {
      Log.println("==== Create WebDriver");
      WebDriver driver = null;
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--incognito");
      options.addArguments("--disable-dev-shm-usage");

      LoggingPreferences logPrefs = new LoggingPreferences();
      logPrefs.enable(LogType.BROWSER, Level.ALL);
//      options.setCapability(LOGGING_PREFS, logPrefs);
      switch (PLATFORM_AND_BROWSER) {
         case "Windows 10":
            Log.println("==== Platform: Windows 10");
            driver = new ChromeDriver(options);
//            clearCookie();
            JS.js = (JavascriptExecutor) driver;
            Log.println("Current HOME = " + PageURL.HOME);
            Log.println("Current PAYMENT = " + PageURL.PAYMENT);
            break;
         case "Linux":
            Log.println("==== Platform: Linux");
            setCurrentURL();
            options.addArguments("--session-request-timeout 120");
            options.addArguments("--remote-allow-origins=*");
            options.addArguments("--no-sandbox");  //linux options
            options.addArguments("--headless");   //linux options
            options.addArguments("--disable-gpu");  //linux options
            Log.println("- Create ChromeDriver");
            driver = new ChromeDriver(options);
            Log.println("- ChromeDriver created");
            JS.js = (JavascriptExecutor) driver;
            Log.println("- JS created");
            break;
         default:
            Log.println("Incorrect platform or browser name " + PLATFORM_AND_BROWSER);
//            fail("Incorrect platform or browser name " + PLATFORM_AND_BROWSER);
      }
      driver.manage().window().maximize();
      driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT);
      driver.manage().timeouts().pageLoadTimeout(PAGE_LOAD_WAIT);
      Log.println("==== Implicit Wait Timeout: " + driver.manage().timeouts().getImplicitWaitTimeout().getSeconds() + " sec");
      Log.println("==== Page Load Timeout: " + driver.manage().timeouts().getPageLoadTimeout().getSeconds() + " sec");
      Log.println("==== Element Visible Timeout: " + ELEMENT_VISIBLE_WAIT.getSeconds() + " sec");
      drivers.put(Thread.currentThread().getName(), driver);
      return driver;
   }

   public static void setCurrentPlatform() {
      PLATFORM_AND_BROWSER = System.getProperty("os.name");
   }

   public static void clearCookie() {
      drivers.get(Thread.currentThread().getName()).manage().deleteAllCookies();
   }

   public static void setCurrentURL() {
      String env = getlinuxEnv("SERV_URL");
      PageURL.HOME = env;
      refreshURLs();
   }

   public static void refreshPage() {
      String url = getDriver().getCurrentUrl();
      getDriver().navigate().refresh();
      WebDriverWait wait = new WebDriverWait(getDriver(), PAGE_LOAD_WAIT);
      wait.until(ExpectedConditions.urlToBe(url));
   }

   public static String getlinuxEnv(String envName) {
      String terminalLine;
      Process process;
      try {
         process = Runtime.getRuntime().exec("printenv");
         BufferedReader br = new BufferedReader(
                 new InputStreamReader(process.getInputStream()));
         while ((terminalLine = br.readLine()) != null) {
            Log.println("line: " + terminalLine);
            if (terminalLine.indexOf(envName) == 0) {
               process.waitFor();
               process.destroy();
               return terminalLine.substring(terminalLine.indexOf("=") + 1, terminalLine.length());
            }
         }
         process.waitFor();
         process.destroy();
      } catch (Exception ex) {
         return "";
      }
      return "";
   }

   public static String getlinuxComand(String comand) {
      String terminalLine;
      Process process;
      try {
         process = Runtime.getRuntime().exec(comand);
         BufferedReader br = new BufferedReader(
                 new InputStreamReader(process.getInputStream()));
         StringBuilder s = new StringBuilder();
         while ((terminalLine = br.readLine()) != null) {
            s.append(terminalLine);
         }
         process.waitFor();
         process.destroy();
         return s.toString();
      } catch (Exception ex) {
         return "";
      }
   }

   public static class JS {
      protected static JavascriptExecutor js;

      public static void removeItemFromLocalStorage(String item) {
         js.executeScript(String.format(
                 "window.localStorage.removeItem('%s');", item));
      }

      public static boolean isItemPresentInLocalStorage(String item) {
         return !(js.executeScript(String.format(
                 "return window.localStorage.getItem('%s');", item)) == null);
      }

      public static String getItemFromLocalStorage(String key) {
         return (String) js.executeScript(String.format(
                 "return window.localStorage.getItem('%s');", key));
      }

      public static String getKeyFromLocalStorage(int key) {
         return (String) js.executeScript(String.format(
                 "return window.localStorage.key('%s');", key));
      }

      public static Long getLocalStorageLength() {
         return (Long) js.executeScript("return window.localStorage.length;");
      }

      public static String executeScript(String script) {
         return js.executeScript(script).toString();
      }
   }
}
