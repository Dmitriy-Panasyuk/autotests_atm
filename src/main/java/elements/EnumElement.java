package elements;

import common.CommonActions;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import utils.Log;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.countMatches;

public class EnumElement extends BaseElement implements Enabled  {
   public static final int DATA_QA_VALUE = 4;
   public static final String CSS_IDENTIFIER = "aria-labelledby";
   public static String[] idAttributes = {"aria-controls", "id"};


   public EnumElement(ArrayList<By> bys) {
      super(bys);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public EnumElement(ArrayList<By> bys, String name) {
      this(bys);
      this.elementName = name;
   }

   public EnumElement(ArrayList<By> bys, String Text, boolean isTextEquals) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public EnumElement(ArrayList<By> bys, Visibility visibility) {
      super(bys, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public EnumElement(ArrayList<By> bys, String name, Visibility visibility) {
      this(bys, visibility);
      this.elementName = name;
   }

   public EnumElement(ArrayList<By> bys, String Text, boolean isTextEquals, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public EnumElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }

   public EnumElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }


   public ArrayList<WebElement> getEnumItems() {
      Log.println(" Get list items");
      return Allure.step("Получить элементы списка '" + this.elementName + "'", step -> {
         ArrayList<WebElement> webElements = new ArrayList<>();
         this.clickEnum();
         String identifiers = this.findEnumID();
         for (WebElement webElement : CommonActions.getDriver().findElements(By.cssSelector("[id]"))) {
            if (webElement.getAttribute("id").contains(identifiers) && webElement.getTagName().contains("ul")) {
               List<WebElement> child = webElement.findElements(By.xpath("./*"));
               for (WebElement webElement2 : child) {
                  if (webElement2.getTagName().contains("li")) {
                     webElements.add(webElement2);
                  }
               }
               return webElements;
            }
         }
         return webElements;
      });

   }

   public boolean isEnable() {
      ButtonElement dropIcon = get(buttons.CHECK_ENABLE);
      dropIcon.setParent(this);
//      Log.println(dropIcon.getElement().getAttribute("class"), "r");
      if (dropIcon.getElement().getAttribute("class").contains("Mui-disabled")) {
         return false;
      }
      return true;
   }


   public void clickEnum() {
      Log.println(" Click enum '" + this.elementName + "' drop icon");
      Allure.step("Активировать список '" + this.elementName + "'", step -> {
         ButtonElement dropIcon = get(buttons.DROP_ARROW);
         dropIcon.setParent(this);
         try {
            dropIcon.clickElement();
         } catch (Exception ex) {
            Log.println("* The element '" + dropIcon.elementName + "' cannot be clicked");
         }
      });
   }

   public void clickOnEnumItem(int itemIndex) {
      Log.println("Click on enum item id'" + itemIndex + "'");
      Allure.step("Выбрать элемент №'" + (itemIndex + 1) + "' списка '" + this.elementName + "'", step -> {
         this.getElement();
         ArrayList<WebElement> enumItems = this.getEnumItems();
         Allure.step("Клик по элементу '" + enumItems.get(itemIndex).getText() + "'", step2 -> {
            try {
               clickElement(enumItems.get(itemIndex), false, false);
            } catch (Exception ex) {
               Log.println("* The item '" + itemIndex + "' cannot be clicked");
               throw new Exception("The item '" + itemIndex + "' cannot be clicked");
            }
         });
      });
   }

   public void clickOnEnumItem(String itemName) {
      Log.println("Click on enum item '" + itemName + "'");
      Allure.step("Выбрать элемент '" + itemName + "' списка '" + this.elementName + "'", step -> {
         this.getElement();
         Allure.step("Клик по элементу '" + itemName + "'", step2 -> {
            try {
               ArrayList<WebElement> enumItems = this.getEnumItems();
               for (WebElement webElement : enumItems) {
                  if (webElement.getText().trim().equalsIgnoreCase(itemName.trim())) {
                     clickElement(webElement, false, false);
                     break;
                  }
               }
            } catch (Exception ex) {
               Log.println("* The item '" + itemName + "' cannot be clicked");
               throw new Exception("The item '" + itemName + "' cannot be clicked");
            }
         });
      });
   }

   private String findEnumID() {
      Log.println(" Find Enum '" + this.elementName + "' id");
      return Allure.step("Найти id списка'", step -> {
         String id;
         for (String idAtt : idAttributes) {
            try {
               id = this.getElement().getAttribute(idAtt);
               if (countMatches(id, ":") == 2) {
                  return id.substring(id.indexOf(":"), id.lastIndexOf(":") + 1);
               }

            } catch (Exception ex) {
            }
         }
         for (String idAtt : idAttributes) {
            List<WebElement> child = this.getElement().findElements(By.cssSelector("*"));
            for (WebElement webElement : child) {
               try {
                  id = webElement.getAttribute(idAtt);
                  if (countMatches(id, ":") == 2) {
                     return id.substring(id.indexOf(":"), id.lastIndexOf(":") + 1);
                  }
               } catch (Exception ex) {
               }
            }
         }
         throw new Exception("Id for enum '" + this.elementName + "' not found.");
      });
   }

   private enum labels {
      IDENTIFIER,
   }

   private enum buttons {
      DROP_ARROW,
      CHECK_ENABLE
   }

   private ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case CHECK_ENABLE:
            bys.add(By.tagName("button"));
            bys.add(By.className("MuiIconButton-root"));
            bys.add(By.className("MuiAutocomplete-popupIndicator"));
            bys.add(By.cssSelector("[title='Open']"));
            return new ButtonElement(bys, "Иконка раскрытия списка", Visibility.INVISIBLE);
         case DROP_ARROW:
            bys.add(By.tagName("svg"));
            bys.add(By.className("MuiSvgIcon-root"));
            bys.add(By.cssSelector("[data-testid='ArrowDropDownIcon']"));
            return new ButtonElement(bys, "Иконка раскрытия списка", Visibility.INVISIBLE);
      }
      return new ButtonElement(bys);
   }

   private LabelElement get(labels element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case IDENTIFIER:
            return new LabelElement(bys, "Элемент содержащий id списка", Visibility.INVISIBLE);
      }
      return new LabelElement(bys);
   }
}
