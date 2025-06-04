package utils;

import elements.BaseElement;
import elements.Visibility;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

import static common.CommonActions.getDriver;
import static common.Config.PLATFORM_AND_BROWSER;
import static constants.Constant.ELEMENT_VISIBLE_WAIT;
import static constants.Constant.PAGE_LOAD_WAIT;
import static utils.Utils.equalsURLs;

public class Waits {

   public static ExpectedCondition<Boolean> genereConditionsLocation(BaseElement baseElement) {
      ExpectedCondition[] conditions;
      if (baseElement.getVisibility() == Visibility.POPUP) {
         conditions = new ExpectedCondition[baseElement.getBys().size() * 2];
      } else {
         conditions = new ExpectedCondition[baseElement.getBys().size()];
      }
      for (int i = 0; i < baseElement.getBys().size(); i++) {
         if (baseElement.getVisibility() == Visibility.VISIBLE) {
            conditions[i] = ExpectedConditions.visibilityOfElementLocated(baseElement.getBys().get(i));
         } else if (baseElement.getVisibility() == Visibility.INVISIBLE) {
            conditions[i] = ExpectedConditions.presenceOfElementLocated(baseElement.getBys().get(i));
         } else {
            conditions[i] = ExpectedConditions.presenceOfElementLocated(baseElement.getBys().get(i));
            conditions[baseElement.getBys().size() + i] = ExpectedConditions.numberOfElementsToBe(baseElement.getBys().get(i), 1);
         }
      }
      return ExpectedConditions.and(conditions);
   }

   public static ExpectedCondition<Boolean> genereConditionsInvisibylyty(BaseElement baseElement) {
      ExpectedCondition[] conditions;
      conditions = new ExpectedCondition[baseElement.getBys().size()];
      for (int i = 0; i < baseElement.getBys().size(); i++) {
         conditions[i] = ExpectedConditions.invisibilityOfElementLocated(baseElement.getBys().get(i));
      }
      return ExpectedConditions.and(conditions);
   }

   public static void elementWait(ExpectedCondition<Boolean> conditions) throws Exception {
      long startTime;
      startTime = System.currentTimeMillis();
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(conditions);
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting element \n  " + conditions,"y");
         long endTime = System.currentTimeMillis();
         long timeElapsed = endTime - startTime;
         throw new Exception("Timeout exceeded waiting element. Time wait: " + timeElapsed/1000 + " s");
      }

   }


   public static void urlEquals(String url) {
      try {
         if (url.lastIndexOf("/") == url.length() - 1) {
            url = url.substring(0, url.length() - 1);
         }
         WebDriverWait wait = new WebDriverWait(getDriver(), PAGE_LOAD_WAIT);
         wait.until(ExpectedConditions.or(ExpectedConditions.urlToBe(url), ExpectedConditions.urlToBe(url + "/")));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for url match","y");
      }

   }

   public static void urlNotEquals(String url) {
      try {
         if (url.lastIndexOf("/") == url.length() - 1) {
            url = url.substring(0, url.length() - 1);
         }
         WebDriverWait wait = new WebDriverWait(getDriver(), PAGE_LOAD_WAIT);
         wait.until(ExpectedConditions.and(ExpectedConditions.not(ExpectedConditions.urlToBe(url)), ExpectedConditions.not(ExpectedConditions.urlToBe(url + "/"))));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for url match","y");
      }
   }

   public static void urlNotContain(String url) {
      try {
         if (url.lastIndexOf("/") == url.length() - 1) {
            url = url.substring(0, url.length() - 1);
         }
         WebDriverWait wait = new WebDriverWait(getDriver(), PAGE_LOAD_WAIT);
         wait.until(ExpectedConditions.not(ExpectedConditions.urlContains(url)));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for url not match","y");
      }
   }

   public static void newURL(String oldURL) {
      new WebDriverWait(getDriver(), PAGE_LOAD_WAIT).until(
              d -> (!equalsURLs(oldURL, getDriver().getCurrentUrl()))
      );
   }

   public static void urlContain(String url) {
      try {
         if (url.lastIndexOf("/") == url.length() - 1) {
            url = url.substring(0, url.length() - 1);
         }
         WebDriverWait wait = new WebDriverWait(getDriver(), PAGE_LOAD_WAIT);
         wait.until(ExpectedConditions.or(ExpectedConditions.urlContains(url), ExpectedConditions.urlContains(url + "/")));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for url contain","y");
      }
   }

   public static void newWindow(int nWindowBeforeClick) {
      new WebDriverWait(getDriver(), PAGE_LOAD_WAIT).until(
              d -> (getDriver().getWindowHandles().toArray().length > nWindowBeforeClick)
      );
   }

   public static void pageLoad() {
      if (PLATFORM_AND_BROWSER.equals("Windows 10")) {
         new WebDriverWait(getDriver(), PAGE_LOAD_WAIT).until(
                 d -> ((JavascriptExecutor) d).executeScript("return document.readyState==\"complete\";")
         );
         new WebDriverWait(getDriver(), PAGE_LOAD_WAIT).until(
                 d -> ((JavascriptExecutor) d).executeScript("return document.styleSheets.length > 0;")
         );
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-qa]")));
      } else {
         new WebDriverWait(getDriver(), PAGE_LOAD_WAIT).until(
                 d -> ((JavascriptExecutor) d).executeScript("return document.readyState==\"complete\";")
         );
         new WebDriverWait(getDriver(), PAGE_LOAD_WAIT).until(
                 d -> ((JavascriptExecutor) d).executeScript("return document.styleSheets.length > 0;")
         );
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.cssSelector("[data-qa]")));
//         try {
//            Thread.sleep(8000);
//         } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//         }
      }
   }


   public static void locatedElementBy(By by) {
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.presenceOfElementLocated(by));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for element located","y");

      }
   }

   public static void locatedElementsBy(By by) {
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(by));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for elements located","y");

      }
   }

   public static void visibleElement(WebElement webElement) {
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.visibilityOf(webElement));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for element visible","y");

      }
   }

   public static void visibleElements(List<WebElement> webElements) {
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.visibilityOfAllElements(webElements));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for elements visible","y");

      }
   }

   public static void visibleElementsBy(By by) {
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(by));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for elements visible","y");

      }
   }


   public static void invisibleElement(WebElement webElement) throws Exception {
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.invisibilityOf(webElement));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for element invisible","y");
         throw new Exception("Timeout exceeded waiting for element invisible");
      }
   }

   public static void invisibleElement(BaseElement element) throws Exception {
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(genereConditionsInvisibylyty(element));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for element invisible","y");
         throw new Exception("Timeout exceeded waiting for element invisible");
      }
   }

   public static void clickableElement(WebElement webElement) throws Exception {
      try {
         WebDriverWait wait = new WebDriverWait(getDriver(), ELEMENT_VISIBLE_WAIT);
         wait.until(ExpectedConditions.elementToBeClickable(webElement));
      } catch (Exception ex) {
         Log.println("* Timeout exceeded waiting for element to be clickable","y");
         throw new Exception("Timeout exceeded waiting for element to be clickable");
      }
   }

}

