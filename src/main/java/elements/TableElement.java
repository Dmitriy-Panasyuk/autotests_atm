package elements;

import common.CommonActions;
import io.qameta.allure.Allure;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import utils.Log;
import utils.Waits;

import java.util.ArrayList;
import java.util.List;

public class TableElement extends BaseElement {
   public static final int DATA_QA_VALUE = 5;
   public static final String CSS_IDENTIFIER = "data-id";

   public TableElement(ArrayList<By> bys) {
      super(bys);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public TableElement(ArrayList<By> bys, String name) {
      this(bys);
      this.elementName = name;
   }

   public TableElement(ArrayList<By> bys, String Text, boolean isTextEquals) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public TableElement(ArrayList<By> bys, Visibility visibility) {
      super(bys, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public TableElement(ArrayList<By> bys, String name, Visibility visibility) {
      this(bys, visibility);
      this.elementName = name;
   }

   public TableElement(ArrayList<By> bys, String Text, boolean isTextEquals, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public TableElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }

   public TableElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }

   public ArrayList<WebElement> getTableItemsV1() {
      Log.println("Get tabel elements.");
      return Allure.step("Получить список элементов таблицы '" + this.elementName + "'", step -> {
         try {
            ArrayList<WebElement> tableItems = new ArrayList<>();
            boolean flag;
            ButtonElement row = Navigation.get(Navigation.buttons.ROW_v1);
            row.setParent(this);
            List<WebElement> visibleElements = row.getElements();
            Actions actions = new Actions(CommonActions.getDriver());
            do {
               flag = false;
               for (WebElement webElement : visibleElements) {
                  if (!tableItems.contains(webElement)) {
                     flag = true;
                     tableItems.add(webElement);
                  }
               }
               actions.moveToElement(tableItems.get(tableItems.size() - 1)).perform();
               visibleElements = row.getElements();
            } while (flag);
            return tableItems;
         } catch (Exception ex) {
            Log.println("* Table elements not found");
            throw new Exception("Table '" + this.elementName + "' elements not found");
         }
      });
   }
   public ArrayList<WebElement> getTableItemsV2() {
      Log.println("Get tabel elements.");
      return Allure.step("Получить список элементов таблицы '" + this.elementName + "'", step -> {
         try {
            ArrayList<WebElement> tableItems = new ArrayList<>();
            boolean flag;
            ButtonElement row = Navigation.get(Navigation.buttons.ROW_v2);
            row.setParent(this);
            List<WebElement> visibleElements = row.getElements();
            Actions actions = new Actions(CommonActions.getDriver());
            do {
               flag = false;
               for (WebElement webElement : visibleElements) {
                  if (!tableItems.contains(webElement)) {
                     flag = true;
                     tableItems.add(webElement);
                  }
               }
               actions.moveToElement(tableItems.get(tableItems.size() - 1)).perform();
               visibleElements = row.getElements();
            } while (flag);
            return tableItems;
         } catch (Exception ex) {
            Log.println("* Table elements not found");
            throw new Exception("Table '" + this.elementName + "' elements not found");
         }
      });
   }

   public ArrayList<WebElement> findTableString(String columnName, String value) {
      Log.println("Finde table string. Column name: '" + columnName + "' Value: '" + value + "'");
      return Allure.step("Получить элементы таблицы '" + this.elementName + "' после фильтра", step -> {
         step.parameter("Колонка фильтра", columnName);
         step.parameter("Фильтр", value);
         LabelElement colHeader = null;
         ArrayList<WebElement> tableString = null;
         if (this.getWebElement().getTagName().equals("div")) {
            colHeader = Navigation.get(Navigation.labels.COLUMN_HEADER_v1);
            colHeader.setEqualsText(columnName);
            tableString=findTableStringV1(colHeader, value);
         }else if(this.getWebElement().getTagName().equals("table")){
            colHeader = Navigation.get(Navigation.labels.COLUMN_HEADER_v2);
            colHeader.setEqualsText(columnName);
            tableString=findTableStringV2(colHeader, value);
         }

         return tableString;
      });
   }

   public ArrayList<WebElement> findTableString(int columnIndex, String value) {
      Log.println("Finde table string. Column №: '" + columnIndex + "' Value: '" + value + "'");
      return Allure.step("Получить элементы таблицы '" + this.elementName + "' после фильтра", step -> {
         step.parameter("Номер колонки фильтра", columnIndex);
         step.parameter("Фильтр", value);
         LabelElement colHeader = null;
         ArrayList<WebElement> tableString = null;
         if (this.getWebElement().getTagName().equals("div")) {
            colHeader = Navigation.get(Navigation.labels.COLUMN_HEADER_v1);
            colHeader.bys.add(By.cssSelector("[aria-colindex='" + columnIndex + "']"));
            tableString=findTableStringV1(colHeader, value);
         }else if(this.getWebElement().getTagName().equals("table")){
            colHeader = Navigation.get(Navigation.labels.COLUMN_HEADER_v2);
            colHeader.bys.add(By.cssSelector("[data-index='" + columnIndex + "']"));
            tableString=findTableStringV2(colHeader, value);
         }


         return tableString;
      });
   }

   private ArrayList<WebElement> findTableStringV1(LabelElement colHeader, String value) {
      return Allure.step("Получить элементы таблицы '" + this.elementName + "' после фильтра", step -> {
         step.parameter("Название колонки фильтра", colHeader);
         step.parameter("Фильтр", value);
         colHeader.moveToElement();
         Thread.sleep(20);
         Navigation.get(Navigation.buttons.COLUMN_MENU).clickButton();
         Thread.sleep(20);
         Navigation.get(Navigation.buttons.COLUMN_MENU_FILTER_v1).clickButton();
         Navigation.get(Navigation.inputs.FILTER_VALUE_v1).sendKeysInInput("RandomValuesdkfnsfnif8483489543958sdf");
         Thread.sleep(200);
         LabelElement paginationLabel = Navigation.get(Navigation.labels.PAGINATION_v1);
         paginationLabel.setParent(this);
         WebElement pagination = paginationLabel.getElements().get(0);
         String paginationText = pagination.getText();
         Navigation.get(Navigation.inputs.FILTER_VALUE_v1).clear();
         Navigation.get(Navigation.inputs.FILTER_VALUE_v1).sendKeysInInput(value);
         pagination = paginationLabel.getElements().get(0);
         Waits.elementWait(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(pagination, paginationText)));
         return this.getTableItemsV1();
      });
   }
   private ArrayList<WebElement> findTableStringV2(LabelElement colHeader, String value) {
      return Allure.step("Получить элементы таблицы '" + this.elementName + "' после фильтра", step -> {
         step.parameter("Название колонки фильтра", colHeader);
         step.parameter("Фильтр", value);
         ButtonElement filterIcon = Navigation.get(Navigation.buttons.COLUMN_MENU_FILTER_v2);
         filterIcon.setParent(colHeader);
         colHeader.moveToElement();
         filterIcon.clickButton();
         Navigation.get(Navigation.inputs.FILTER_VALUE_v2).sendKeysInInput("RandomValuesdkfnsfnif8483489543958sdf");
         Thread.sleep(200);
         LabelElement paginationLabel = Navigation.get(Navigation.labels.PAGINATION_v2);
         WebElement pagination = paginationLabel.getElement();
         String paginationText = pagination.getText();
         Navigation.get(Navigation.inputs.FILTER_VALUE_v2).clear();
         Navigation.get(Navigation.inputs.FILTER_VALUE_v2).sendKeysInInput(value);
         pagination = paginationLabel.getElement();
         Waits.elementWait(ExpectedConditions.not(ExpectedConditions.textToBePresentInElement(pagination, paginationText)));
         ArrayList<WebElement> tableElements = this.getTableItemsV2();
         clickOutside(Navigation.get(Navigation.inputs.FILTER_VALUE_v2).getElement());
         return tableElements;
      });
   }
   private class Navigation {
      public enum buttons {
         NEXT_PAGE_v1,
         COLUMN_MENU,
         COLUMN_MENU_FILTER_v1,
         COLUMN_MENU_FILTER_v2,
         ROW_v1,
         ROW_v2
      }

      public enum labels {
         COLUMN_HEADER_v1,
         COLUMN_HEADER_v2,
         PAGINATION_v1,
         PAGINATION_v2
      }

      public enum inputs {
         FILTER_VALUE_v1,
         FILTER_VALUE_v2
      }

      public static ButtonElement get(buttons element) {
         ArrayList<By> bys = new ArrayList<>();
         switch (element) {
            case NEXT_PAGE_v1:
               bys.add(By.cssSelector("[type='button']"));
               bys.add(By.cssSelector("[title='Перейти на следующую страницу']"));
               return new ButtonElement(bys, "Следующая страница таблицы", Visibility.INVISIBLE);
//            case NEXT_PAGE_v2:
//               bys.add(By.cssSelector("[type='button']"));
//               bys.add(By.cssSelector("[title='Перейти на следующую страницу']"));
//               return new ButtonElement(bys, "Следующая страница таблицы", Visibility.INVISIBLE);
            case COLUMN_MENU:
               bys.add(By.cssSelector("[type='button']"));
               bys.add(By.cssSelector("[aria-haspopup='menu']"));
               bys.add(By.cssSelector("[aria-label='Меню']"));
               return new ButtonElement(bys, "Меню колонки", Visibility.INVISIBLE);
            case COLUMN_MENU_FILTER_v1:
               bys.add(By.className("MuiSvgIcon-fontSizeSmall"));
               bys.add(By.cssSelector("[data-testid='FilterAltIcon']"));
               return new ButtonElement(bys, "Меню колонки, фильтр", Visibility.INVISIBLE);
            case COLUMN_MENU_FILTER_v2:
               bys.add(By.className("MuiButtonBase-root"));
               bys.add(By.cssSelector("[aria-label*='Отфильтровать по']"));
               return new ButtonElement(bys, "Фильтр", Visibility.INVISIBLE);
            case ROW_v1:
               bys.add(By.cssSelector("[role='row']"));
               return new ButtonElement(bys, "Строка таблицы", Visibility.INVISIBLE);
            case ROW_v2:
               bys.add(By.cssSelector("[data-index]"));
               bys.add(By.cssSelector("[style]"));
               bys.add(By.className("MuiTableRow-root"));
               return new ButtonElement(bys, "Строка таблицы", Visibility.INVISIBLE);
         }
         return new ButtonElement(bys);
      }

      public static InputElement get(inputs element) {
         ArrayList<By> bys = new ArrayList<>();
         switch (element) {
            case FILTER_VALUE_v1:
               bys.add(By.cssSelector("input[placeholder='Значение фильтра']"));
               return new InputElement(bys, "Значение фильтра", Visibility.INVISIBLE);
            case FILTER_VALUE_v2:
               bys.add(By.cssSelector("input[placeholder*='Отфильтровать по']"));
               return new InputElement(bys, "Значение фильтра", Visibility.INVISIBLE);
         }
         return new InputElement(bys);
      }

      public static LabelElement get(labels element) {
         ArrayList<By> bys = new ArrayList<>();
         switch (element) {
            case COLUMN_HEADER_v1:
               bys.add(By.className("MuiDataGrid-columnHeader"));
               bys.add(By.cssSelector("[role='columnheader']"));
               bys.add(By.cssSelector("[aria-colindex]"));
               return new LabelElement(bys, "Заголовок колонки v1", Visibility.INVISIBLE);
            case COLUMN_HEADER_v2:
               bys.add(By.className("MuiTableCell-stickyHeader"));
               bys.add(By.cssSelector("[aria-sort]"));
               return new LabelElement(bys, "Заголовок колонки v2", Visibility.INVISIBLE);
            case PAGINATION_v1:
               bys.add(By.className("MuiTablePagination-displayedRows"));
               return new LabelElement(bys, "Нумерация страниц");
            case PAGINATION_v2:
               bys.add(By.className("MuiTypography-body2"));
               return new LabelElement(bys ,"Нумерация страниц");
         }
         return new LabelElement(bys);
      }
   }

}
