package elements;

import common.CommonActions;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utils.Log;
import utils.Waits;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static common.CommonActions.getDriver;
import static utils.AllureAttachmentTools.attachScreenshotPNG;
import static utils.Waits.genereConditionsLocation;

public class BaseElement {
   private static List<WebElement> currentDQWE = new ArrayList<>();
   public static final String DQWE_ATT_NAME = "data-qa";
   public static final By BY_DQWE_ATT_NAME = By.cssSelector("[" + DQWE_ATT_NAME + "]");

   protected Visibility visibility = Visibility.VISIBLE;
   protected String elementName = "No Name";
   protected int type = 0;

   protected WebElement webElement;

   //   protected String pageURL;
   protected boolean isNewWindowAfterClic = false;
   protected boolean isNewURLAfterClic = false;

   protected ArrayList<By> bys = new ArrayList();
   protected ArrayList<By> filterBys = new ArrayList();
   protected String byContainText = "";
   protected String byEqualsText = "";
   protected BaseElement parent;
   protected BaseElement children;

   public BaseElement() {
   }

   public BaseElement(ArrayList<By> bys) {
      this.bys = bys;
   }


   public BaseElement(ArrayList<By> bys, String Text, boolean isTextEquals) {
      this(bys);
      this.setText(Text, isTextEquals);
   }

   public BaseElement(ArrayList<By> bys, Visibility visibility) {
      this(bys);
      this.visibility = visibility;
   }

   public BaseElement(ArrayList<By> bys, String name, Visibility visibility) {
      this(bys);
      this.elementName = name;
      this.visibility = visibility;
   }

   public BaseElement(ArrayList<By> bys, String Text, boolean isTextEquals, Visibility visibility) {
      this(bys, visibility);
      this.setText(Text, isTextEquals);
   }


   protected ArrayList<WebElement> getElements() throws Exception {
      ArrayList<List<WebElement>> allFindGroupElements = new ArrayList<>();
      ArrayList<WebElement> elementsAfterFilter = new ArrayList<>();
      Waits.elementWait(genereConditionsLocation(this));
      for (By by : bys) {
         allFindGroupElements.add(CommonActions.getDriver().findElements(by));
      }
      for (int i = 0; i < allFindGroupElements.size(); i++) {
         int a = allFindGroupElements.get(i).size();
         for (int j = 0; j < a; j++) {
            int occurrences = 0;
            for (List<WebElement> groupElements : allFindGroupElements) {
               if (groupElements.contains(allFindGroupElements.get(i).get(j))) {
                  occurrences++;
               }
            }
            if (occurrences == allFindGroupElements.size() && !elementsAfterFilter.contains(allFindGroupElements.get(i).get(j))) {
               elementsAfterFilter.add(allFindGroupElements.get(i).get(j));
            }
         }
      }
      elementsAfterFilter = filters(elementsAfterFilter);
      if (elementsAfterFilter.size() < 1)
         throw new Exception("Element '" + this.elementName + "' not found.\n" + this);
      return elementsAfterFilter;
   }

   protected WebElement getElement() {
      Log.println(" Finde element '" + this.elementName + "'");
      return Allure.step("Найти элемент '" + this.elementName + "'", () -> {
         ArrayList<WebElement> webElements = this.getElements();
         if (webElements.size() > 1) {
            Log.println("* More than one element (" + webElements.size() + ") found. Returned first from list.", "y");
         }
         this.webElement = webElements.get(0);
         return this.webElement;
      });
   }

   /**
    * Возвращает дочерние элементы.
    */
   public static ArrayList<WebElement> getChildrens(WebElement parent) {
      return new ArrayList<>(parent.findElements(By.tagName("*")));
   }
   public ArrayList<WebElement> getChildrens() {
      return new ArrayList<>(this.getWebElement().findElements(By.tagName("*")));
   }

   protected ArrayList<WebElement> filters(ArrayList<WebElement> elements) throws Exception {
      Log.printlnAL("--Filters" + this.elementName + ": elements before filter: " + elements.size(), "c");
      if (type > 0 && this.bys.contains(BY_DQWE_ATT_NAME))
         elements = filterByCssAttribute(elements, DQWE_ATT_NAME, Integer.toString(this.type));
      if (this.parent != null)
         elements = filterByParent(elements, this.parent);
      if (this.children != null)
         elements = filterByChildren(elements, this.children);
      if (this.filterBys.size() > 0)
         elements = filterBy(elements);
      if (!byEqualsText.equals(""))
         elements = filterByTextEquals(elements, this.byEqualsText);
      if (!byContainText.equals(""))
         elements = filterByTextContains(elements, this.byContainText);
      return elements;
   }

   protected ArrayList<WebElement> filterBy(ArrayList<WebElement> elements) {
      Log.printlnAL("    Filters By: ", "c");
      Log.printlnAL("      elements before filter: " + elements.size(), "c");
      boolean flag = true;
      ArrayList<WebElement> elementsAfterFilter = new ArrayList<>();
      ArrayList<List<WebElement>> allFindFilterGroupElements = new ArrayList<>();
      for (By by : filterBys) {
         allFindFilterGroupElements.add(CommonActions.getDriver().findElements(by));
      }
      for (WebElement element : elements) {
         for (List<WebElement> filterGroup : allFindFilterGroupElements) {
            for (WebElement filterElement : filterGroup) {
               if (element.equals(filterElement)) {
                  flag = false;
                  break;
               }
            }
         }
         if (flag) {
            elementsAfterFilter.add(element);
         }
         flag = true;
      }
      Log.printlnAL("      elements after filter : " + elementsAfterFilter.size(), "c");
      return elementsAfterFilter;
   }

   protected ArrayList<WebElement> filterByChildren(ArrayList<WebElement> elements, BaseElement children) throws Exception {
      Log.printlnAL("    Filters Children: ", "c");
      Log.printlnAL("      elements before filter: " + elements.size(), "c");
      ArrayList<WebElement> elementsAfterFilter = new ArrayList<>();
      for (WebElement child1 : children.getElements()) {
         for (WebElement element : elements) {
            for (WebElement child2 : element.findElements(By.tagName("*"))) {
               if (child1.equals(child2)) {
                  elementsAfterFilter.add(child1);
               }
            }

         }
      }
      Log.printlnAL("      elements after filter : " + elementsAfterFilter.size(), "c");
      return elementsAfterFilter;
   }

   protected ArrayList<WebElement> filterByParent(ArrayList<WebElement> elements, BaseElement parent) throws Exception {
      Log.printlnAL("    Filters Parent: ", "c");
      Log.printlnAL("      elements before filter: " + elements.size(), "c");
      ArrayList<WebElement> elementsAfterFilter = new ArrayList<>();
      for (WebElement child1 : parent.getElements()) {
         for (WebElement child2 : child1.findElements(By.tagName("*"))) {
            for (WebElement element : elements) {
               if (element.equals(child2)) {
                  elementsAfterFilter.add(element);
               }
            }
         }
      }
      Log.printlnAL("      elements after filter : " + elementsAfterFilter.size(), "c");
      return elementsAfterFilter;
   }

   protected static ArrayList<WebElement> filterByCssAttribute(ArrayList<WebElement> elements, String
           cssAttribute, String cssValue) {
      Log.printlnAL("    Filters CSS Att: ", "c");
      Log.printlnAL("      elements before filter: " + elements.size(), "c");
      ArrayList<WebElement> elementsAfterFilter = new ArrayList<>();
      for (WebElement webElement : elements) {
         String attribute = webElement.getAttribute(cssAttribute);
         if (attribute != null) {
            if (attribute.equalsIgnoreCase(cssValue)) {
               elementsAfterFilter.add(webElement);
            }
         }
      }
      Log.printlnAL("      elements after filter : " + elementsAfterFilter.size(), "c");
      return elementsAfterFilter;
   }

   protected static ArrayList<WebElement> filterByTextEquals(ArrayList<WebElement> elements, String text) {
      Log.printlnAL("    Filters text '" + text + "' Equals: ", "c");
      Log.printlnAL("      elements before filter: " + elements.size(), "c");
      ArrayList<WebElement> elementsAfterFilter = new ArrayList<>();
      String elementText;
      for (WebElement webElement : elements) {
         try {
            elementText = webElement.getText();
         } catch (Exception ex) {
            continue;
         }
         if (elementText.trim().equalsIgnoreCase(text.trim())) {
            elementsAfterFilter.add(webElement);
         }
      }
      if (elementsAfterFilter.size() < 1) {
         for (WebElement webElement : elements) {
            for (WebElement child : webElement.findElements(By.tagName("*"))) {
               try {
                  elementText = child.getText();
               } catch (Exception ex) {
                  continue;
               }
               if (elementText.trim().equalsIgnoreCase(text.trim())) {
                  elementsAfterFilter.add(child);
               }
            }
         }
      }
      Log.printlnAL("      elements after filter : " + elementsAfterFilter.size(), "c");
      return elementsAfterFilter;
   }

   protected static ArrayList<WebElement> filterByTextContains(ArrayList<WebElement> elements, String text) {
      Log.printlnAL("    Filters text '" + text + "' Contains: ", "c");
      Log.printlnAL("      elements before filter: " + elements.size(), "c");
      ArrayList<WebElement> elementsAfterFilter = new ArrayList<>();
      String elementText;
      for (WebElement webElement : elements) {
         try {
            elementText = webElement.getText();
         } catch (Exception ex) {
            continue;
         }
         if (elementText.toLowerCase().contains(text.toLowerCase())) {
            elementsAfterFilter.add(webElement);
         }
      }
      if (elementsAfterFilter.size() < 1) {
         for (WebElement webElement : elements) {
            for (WebElement child : webElement.findElements(By.tagName("*"))) {
               try {
                  elementText = child.getText();
               } catch (Exception ex) {
                  continue;
               }
               if (elementText.toLowerCase().contains(text.toLowerCase())) {
                  elementsAfterFilter.add(child);
               }
            }
         }
      }
      Log.printlnAL("      elements after filter : " + elementsAfterFilter.size(), "c");
      return elementsAfterFilter;
   }

   public void moveToElement() {
      Log.println(" Move to element '" + this.elementName + "'");
//      System.out.print(CommonActions.getlinuxComand("du -sh /dev/*"));
      Allure.step("Навести указатель на элемент '" + this.elementName + "'", step -> {
         Actions action = new Actions(CommonActions.getDriver());
         action.moveToElement(this.getElement());
         action.perform();
      });
   }

   public static void moveToElement(WebElement webElement) {
      Log.println(" Move to element");
      Allure.step("Навести указатель на элемент", step -> {
         Actions action = new Actions(CommonActions.getDriver());
         action.moveToElement(webElement);
         action.perform();
      });
   }

   public void clickElement() throws Exception {
      try {
         if (this.isNewURLAfterClic && this.isNewWindowAfterClic) {
            String oldURL = getDriver().getCurrentUrl();
            int nWindow = getDriver().getWindowHandles().toArray().length;
            this.click();
            Waits.newWindow(nWindow);
            Object[] windowHandles = CommonActions.getDriver().getWindowHandles().toArray();
            CommonActions.getDriver().switchTo().window((String) windowHandles[0]);
            CommonActions.getDriver().close();
            CommonActions.getDriver().switchTo().window((String) windowHandles[windowHandles.length-1]);
            Waits.newURL(oldURL);
            Waits.pageLoad();
         } else if (this.isNewURLAfterClic) {
            String oldURL = getDriver().getCurrentUrl();
            this.click();
            Waits.newURL(oldURL);
            Waits.pageLoad();
         } else if (this.isNewWindowAfterClic) {
            int nWindow = getDriver().getWindowHandles().toArray().length;
            this.click();
            Waits.newWindow(nWindow);
            Object[] windowHandles = CommonActions.getDriver().getWindowHandles().toArray();
            CommonActions.getDriver().switchTo().window((String) windowHandles[0]);
            CommonActions.getDriver().close();
            CommonActions.getDriver().switchTo().window((String) windowHandles[windowHandles.length-1]);
         } else {
            click();
         }
      } catch (Exception ex) {
         throw new Exception("The element '" + this.elementName + "' cannot be clicked");
      }
   }

   public static void clickElement(WebElement webElement, boolean isOpenNewWindow, boolean isOpenNewURL) {
      Allure.step("Клик по элементу", step2 -> {
         try {
            if (isOpenNewWindow && isOpenNewURL) {
               String oldURL = getDriver().getCurrentUrl();
               int nWindow = getDriver().getWindowHandles().toArray().length;
               click(webElement);
               Waits.newWindow(nWindow);
               Object[] windowHandles = CommonActions.getDriver().getWindowHandles().toArray();
               CommonActions.getDriver().switchTo().window((String) windowHandles[0]);
               CommonActions.getDriver().close();
               CommonActions.getDriver().switchTo().window((String) windowHandles[windowHandles.length-1]);
               Waits.newURL(oldURL);
               Waits.pageLoad();
            } else if (isOpenNewURL) {
               String oldURL = getDriver().getCurrentUrl();
               click(webElement);
               Waits.newURL(oldURL);
               Waits.pageLoad();
            } else if (isOpenNewWindow) {
               int nWindow = getDriver().getWindowHandles().toArray().length;
               click(webElement);
               Waits.newWindow(nWindow);
               Object[] windowHandles = CommonActions.getDriver().getWindowHandles().toArray();
               CommonActions.getDriver().switchTo().window((String) windowHandles[0]);
               CommonActions.getDriver().close();
               CommonActions.getDriver().switchTo().window((String) windowHandles[windowHandles.length-1]);
            } else {
               click(webElement);
            }
         } catch (Exception ex) {
            throw new Exception("The element cannot be clicked");
         }
      });
   }

   private static void click(WebElement webElement) throws Exception {
      if (!clickOnCentre(webElement)) {
         if (!clickOnLeftUp(webElement)) {
            Log.println("* The element cannot be clicked", "y");
            throw new Exception("The element cannot be clicked");
         }
      }
   }

   private void click() throws Exception {
      if (!clickOnCentre(this.getWebElement())) {
         if (!clickOnLeftUp(this.getWebElement())) {
            Log.println("* The element '" + this.elementName + "' cannot be clicked", "y");
            throw new Exception("The element '" + this.elementName + "' cannot be clicked");
         }
      }
   }

   public static boolean clickOnCentre(WebElement webElement) {
      return Allure.step("Клик по центру элемента", step2 -> {
         Log.println(" Click on Centre");
         try {
            Waits.visibleElement(webElement);
            Waits.clickableElement(webElement);
            moveToElement(webElement);
            webElement.click();
            return true;
         } catch (Exception ex) {
            Log.println("* Center not clicable", "y");
            attachScreenshotPNG();
            return false;
         }
      });
   }

   public static boolean clickOnLeftUp(WebElement webElement) {
      return Allure.step("Клик по левому верхнему углу элемента", step2 -> {
         Log.println(" Click on LeftUp");
         try {
            Waits.visibleElement(webElement);
            Waits.clickableElement(webElement);
            int height = webElement.getSize().getHeight();
            int width = webElement.getSize().getWidth();
            moveToElement(webElement);
            Actions act = new Actions(CommonActions.getDriver());
            act.moveToElement(webElement,(-width / 2) + 2, (-height / 2) + 2).build().perform();
            act.click().build().perform();
            return true;
         } catch (Exception ex) {
            Log.println("* LeftUp not clicable", "y");
            attachScreenshotPNG();
            return false;
         }
      });
   }

   public void clickOutside() {
      clickOutside(this.getElement());
   }

   public static void clickOutside(WebElement webElement) {
      int height = webElement.getSize().getHeight();
      int width = webElement.getSize().getWidth();
      Actions act = new Actions(CommonActions.getDriver());
      act.moveToElement(webElement).moveByOffset(width + 2, height + 2).click().build().perform();
   }

   private String getElementTypeNameRU() {
      if (this.getClass().equals(ButtonElement.class)) {
         return "кнопку";
      } else if (this.getClass().equals(EnumElement.class)) {
         return "список";
      } else if (this.getClass().equals(InputElement.class)) {
         return "инпут";
      } else if (this.getClass().equals(LabelElement.class)) {
         return "лэйбл";
      } else if (this.getClass().equals(TableElement.class)) {
         return "таблицу";
      } else {
         return "элемент";
      }
   }

   public String toString() {
      String string = "  elementName : " + elementName;
      string += "\n         type : " + type;
      string += "\n   webElement : " + webElement;
      string += "\n          bys : " + Arrays.toString(bys.toArray()).replace("], ", "],\n" + " ".repeat(17));
      if (filterBys.size() > 0) {
         string += "\n    filterBys : " + Arrays.toString(filterBys.toArray()).replace("], ", "],\n" + " ".repeat(17));
      } else {
         string += "\n    filterBys : " + filterBys;
      }
      string += "\nbyContainText : " + byContainText;
      string += "\n byEqualsText : " + byEqualsText;
      if (parent != null) {
         string += "\n       parent : " + parent.elementName;
      } else {
         string += "\n       parent : ";
      }
      if (children != null) {
         string += "\n     children : " + children.elementName;
      } else {
         string += "\n     children : ";
      }
      return string;
   }

   public boolean isElemenExist() {
      try{
         return this.getWebElement() != null;
      }catch (Exception ex){
         return false;
      }
   }

   private void setText(String Text, boolean isTextEquals) {
      if (isTextEquals) {
         this.byEqualsText = Text;
      } else {
         this.byContainText = Text;
      }
   }

   public void setVisibility(Visibility visibility) {
      this.visibility = visibility;
   }

   public Visibility getVisibility() {
      return this.visibility;
   }

   public void setElementName(String elementName) {
      this.elementName = elementName;
   }

   public String getElementName() {
      return this.elementName;
   }

   public void setType(int type) {
      this.type = type;
   }

   public int getType() {
      return this.type;
   }

   public void setWebElement(WebElement webElement) {
      this.webElement = webElement;
   }

   public WebElement getWebElement() {
      return Allure.step("Найти " + getElementTypeNameRU() + " '" + this.elementName + "'", step -> {
         return this.getElement();
      });
   }

   public void addBys(By by) {
      this.bys.add(by);
   }

   public void setBys(ArrayList<By> bys) {
      this.bys = bys;
   }

   public ArrayList<By> getBys() {
      return this.bys;
   }

   public void addFilterBys(By by) {
      this.filterBys.add(by);
   }

   public void setFilterBys(ArrayList<By> bys) {
      this.filterBys = bys;
   }

   public ArrayList<By> getFilterBys() {
      return this.filterBys;
   }

   public void setContainText(String text) {
      this.byContainText = text;
   }

   public String getContainText() {
      return this.byContainText;
   }

   public void setEqualsText(String text) {
      this.byEqualsText = text;
   }

   public String getEqualsText() {
      return this.byEqualsText;
   }

   public void setParent(BaseElement parent) {
      this.parent = parent;
   }

   public BaseElement getParent() {
      return this.parent;
   }

   public void setChildren(BaseElement children) {
      this.children = children;
   }

   public BaseElement getChildren() {
      return this.children;
   }

   public boolean isNewWindowAfterClic() {
      return isNewWindowAfterClic;
   }

   public void setNewWindowAfterClic(boolean newWindowAfterClic) {
      isNewWindowAfterClic = newWindowAfterClic;
   }

   public boolean isNewURLAfterClic() {
      return isNewURLAfterClic;
   }

   public void setNewURLAfterClic(boolean newURLAfterClic) {
      isNewURLAfterClic = newURLAfterClic;
   }
}
