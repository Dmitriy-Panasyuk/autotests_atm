package elements;

import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;

public class DataElement extends BaseElement implements Enabled {

   public static final int DATA_QA_VALUE = 6;

   public DataElement(ArrayList<By> bys) {
      super(bys);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public DataElement(ArrayList<By> bys, String name) {
      this(bys);
      this.elementName = name;
   }

   public DataElement(ArrayList<By> bys, String Text, boolean isTextEquals) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public DataElement(ArrayList<By> bys, Visibility visibility) {
      super(bys, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public DataElement(ArrayList<By> bys, String name, Visibility visibility) {
      this(bys, visibility);
      this.elementName = name;
   }


   public DataElement(ArrayList<By> bys, String Text, boolean isTextEquals, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public DataElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }

   public DataElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }
   public boolean isEnable()  {
      if (this.getElementType().equals("icon")) {
         for (WebElement child : this.getChildrens()){
            if(child.getTagName().equals("button")){
               if(child.getAttribute("aria-label").contains("Выберите дату")){
                  if(child.getAttribute("class").contains("Mui-disabled")){
                     return false;
                  }
                  return true;
               }
            }
         }
         return false;
      } else if (this.getElementType().equals("modal")) {
         return true;
      }
      return false;
   }
   public void setRandomActiveDate() {
      Allure.step("Выбрать случайную активную дату для элемента '" + this.elementName + "'", () -> {
         ButtonElement calendarElement = Navigation.get(Navigation.buttons.DATE_ICON);
         calendarElement.setParent(this);
         calendarElement.clickButton();
         ButtonElement dayElement = Navigation.get(Navigation.buttons.DAY);
         ArrayList<WebElement> elements = dayElement.getElements();
         for (int i = 0; i < 12; i++) {
            if (elements.size() > 0) {
               elements.get(elements.size() - 1).click();
               break;
            }
            Navigation.get(Navigation.buttons.NEXT_MONTH).clickButton();
            Thread.sleep(100);
            elements = dayElement.getElements();
         }
         if (elements.size() < 1) {
            throw new Exception("There are no active dates for the next year.");
         }
      });
   }

   public void setDate(int day, int month, int year) {
      Allure.step("Выбрать дату '" + this.elementName + "'", step -> {
         step.parameter("Дата", "" + day + "." + month + "." + year);
         ButtonElement calendarElement = Navigation.get(Navigation.buttons.DATE_ICON);
         calendarElement.setParent( this);
         calendarElement.clickButton();
         Navigation.get(Navigation.buttons.YEAR_HANDLER).clickButton();
         ButtonElement yearElement = Navigation.get(Navigation.buttons.YEAR);
         yearElement.setEqualsText(String.valueOf(year));
         yearElement.clickButton();
         int currentMonth = monthConverter(Navigation.get(Navigation.labels.CURRENT_MONTH_YEAR).getWebElement().getText().split(" ")[0]);
         if (currentMonth > month) {
            WebElement preMonth = Navigation.get(Navigation.buttons.PREVIOUS_MONS).getWebElement();
            for (int i = currentMonth; i > month; i--) {
               preMonth.click();
            }
         } else if (currentMonth < month) {
            WebElement nextMonth = Navigation.get(Navigation.buttons.NEXT_MONTH).getWebElement();
            for (int i = currentMonth; i < month; i++) {
               nextMonth.click();
            }
         }
         Thread.sleep(100);
         ButtonElement dayElement = Navigation.get(Navigation.buttons.DAY);
         dayElement.setEqualsText(String.valueOf(day));
         dayElement.clickButton();
      });
   }

   private String getElementType() {
      for (WebElement child : this.getChildrens()){
         if(child.getTagName().equals("button")){
            if(child.getAttribute("aria-label").contains("Выберите дату")){
               return "icon";
            }
         }
      }
      return "modal";
   }
   public int monthConverter(String monthName) {
      switch (monthName.trim().toLowerCase()) {
         case "январь":
            return 1;
         case "февраль":
            return 2;
         case "март":
            return 3;
         case "апрель":
            return 4;
         case "май":
            return 5;
         case "июнь":
            return 6;
         case "июль":
            return 7;
         case "август":
            return 8;
         case "сентябрь":
            return 9;
         case "октябрь":
            return 10;
         case "ноябрь":
            return 11;
         case "декабрь":
            return 12;
      }
      return 0;
   }

   private class Navigation {
      public enum buttons {
         DATE_ICON,
         NEXT_MONTH,
         PREVIOUS_MONS,
         YEAR_HANDLER,
         DAY,
         YEAR
      }

      public enum labels {
         CURRENT_MONTH_YEAR,
      }

      public enum inputs {
         FILTER_VALUE
      }

      public static ButtonElement get(Navigation.buttons element) {
         ArrayList<By> bys = new ArrayList<>();
         BaseElement parent;
         ButtonElement buttonElement;
         switch (element) {
            case DATE_ICON:
               bys.add(By.cssSelector("[data-testid='CalendarIcon']"));
               return new ButtonElement(bys, "Иконка календаря", Visibility.INVISIBLE);
            case NEXT_MONTH:
               bys.add(By.cssSelector("[data-popper-placement*='-start']"));
               parent = new BaseElement(bys, "Форма календаря", Visibility.INVISIBLE);
               bys = new ArrayList<>();
               bys.add(By.cssSelector("[title='Следующий месяц']"));
               buttonElement = new ButtonElement(bys, "Следующий месяц", Visibility.INVISIBLE);
               buttonElement.setParent(parent);
               return buttonElement;
            case PREVIOUS_MONS:
               bys.add(By.cssSelector("[data-popper-placement*='-start']"));
               parent = new BaseElement(bys, "Форма календаря", Visibility.INVISIBLE);
               bys = new ArrayList<>();
               bys.add(By.cssSelector("[title='Предыдущий месяц']"));
               buttonElement = new ButtonElement(bys, "Предыдущий месяц", Visibility.INVISIBLE);
               buttonElement.setParent(parent);
               return buttonElement;
            case YEAR_HANDLER:
               bys.add(By.cssSelector("[data-popper-placement*='-start']"));
               parent = new BaseElement(bys, "Форма календаря", Visibility.INVISIBLE);
               bys = new ArrayList<>();
               bys.add(By.cssSelector("[aria-label='открыт календарный вид, переключить на годовой вид']"));
               buttonElement = new ButtonElement(bys, "Открыть выбор года", Visibility.INVISIBLE);
               buttonElement.setParent(parent);
               return buttonElement;
            case DAY:
               bys.add(By.cssSelector("[role='dialog']"));
               bys.add(By.cssSelector("[data-popper-placement]"));
               parent = new BaseElement(bys, "Форма календаря", Visibility.INVISIBLE);
               bys = new ArrayList<>();
               bys.add(By.className("MuiPickersDay-dayWithMargin"));
               bys.add(By.cssSelector("[type='button']"));
               buttonElement = new ButtonElement(bys, "Выбрать день", Visibility.INVISIBLE);
               buttonElement.addFilterBys(By.cssSelector("[disabled]"));
               buttonElement.setParent(parent);
               return buttonElement;
            case YEAR:
               bys.add(By.cssSelector("[data-popper-placement*='-start']"));
               parent = new BaseElement(bys, "Форма календаря", Visibility.INVISIBLE);
               bys = new ArrayList<>();
               bys.add(By.className("MuiPickersYear-yearButton"));
               buttonElement = new ButtonElement(bys, "Выбрать год", Visibility.INVISIBLE);
               buttonElement.setParent(parent);
               return buttonElement;
         }
         return new ButtonElement(bys);
      }

      public static InputElement get(Navigation.inputs element) {
         ArrayList<By> bys = new ArrayList<>();
         switch (element) {
            case FILTER_VALUE:
               bys.add(By.cssSelector("input[placeholder='Значение фильтра']"));
               return new InputElement(bys, "Значение фильтра", Visibility.INVISIBLE);

         }
         return new InputElement(bys);
      }

      public static LabelElement get(Navigation.labels element) {
         ArrayList<By> bys = new ArrayList<>();
         switch (element) {
            case CURRENT_MONTH_YEAR:
               bys.add(By.className("MuiPickersCalendarHeader-label"));
               bys.add(By.cssSelector("[id]"));
               return new LabelElement(bys, "Выбранные месяц и год", Visibility.INVISIBLE);
         }
         return new LabelElement(bys);
      }
   }

}
