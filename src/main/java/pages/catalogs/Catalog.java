package pages.catalogs;

import elements.ButtonElement;
import org.openqa.selenium.By;
import pages.BasePage;
import pages.PageURL;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class Catalog extends BasePage {
   private static String URL = PageURL.CATALOG;

   public enum buttons {
      ROLES,
      DIRECTORS,
      BUDGET_ADMINS,
      ECONOMISTS,
      PROJECT_MANAGERS,
      COMPANIES,
      FINANCIAL_RESPONSILIBILITYS,
      COST_CENTERS,
      PROGRAMS,
      PROJECTS,
      CONTRACTORS,
      PAYMENT_DOCS,
      CONTRACTS,
      COSTS,
      ROW_MENU,
      ROW_MENU_CHANGE_STATUS,
      ROW_MENU_CHANGE_STATUS_CONFIRM,
      ROW_MENU_CHANGE_STATUS_ICON,
      ROW_MENU_CHANGE_STATUS_SAVE_ICON
   }

   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case ROW_MENU:
            bys.add(By.cssSelector("[data-testid='MoreVertIcon']"));
            return new ButtonElement(bys, "Дополнительное меню строки");
         case ROW_MENU_CHANGE_STATUS:
            bys.add(By.cssSelector("[data-testid='VerifiedUserIcon']"));
            return new ButtonElement(bys, "Изменить статус");
         case ROW_MENU_CHANGE_STATUS_CONFIRM:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys,"Подтвердить",true, "Подтвердить изменение статуса");
         case ROW_MENU_CHANGE_STATUS_ICON:
            bys.add(By.className("MuiDataGrid-editBooleanCell"));
            return new ButtonElement(bys, "Статус");
         case ROW_MENU_CHANGE_STATUS_SAVE_ICON:
            bys.add(By.cssSelector("[aria-label='Save']"));
            return new ButtonElement(bys, "Сохранить статус");
         case ROLES:

            bys.add(By.className("MuiTreeItem-label"));
            return new ButtonElement(bys, "Сотрудники и роли", true,  "Кнопка каталога 'Сотрудники и роли'");
         case DIRECTORS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Руководители ЦФО", true,  "Кнопка каталога 'Руководители ЦФО'");
         case BUDGET_ADMINS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Администраторы бюджета", true,  "Кнопка каталога 'Администраторы бюджета'");
         case ECONOMISTS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Экономисты", true,  "Кнопка каталога 'Экономисты'");
         case PROJECT_MANAGERS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Руководители проектов", true,  "Кнопка каталога 'Руководители проектов'");
         case COMPANIES:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Компании", true,  "Кнопка каталога 'Компании'");
         case FINANCIAL_RESPONSILIBILITYS:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Центры финансовой ответственности (ЦФО)", true,  "Кнопка каталога 'Центры финансовой ответственности (ЦФО)'");
         case COST_CENTERS:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Места возникновения затрат (МВЗ)", true,  "Кнопка каталога 'Места возникновения затрат (МВЗ)'");
         case PROGRAMS:
            bys.add(BY_DQWE_ATT_NAME);
//            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Корпоративные задачи", true,  "Кнопка каталога 'Корпоративные задачи'");
         case PROJECTS:
            bys.add(BY_DQWE_ATT_NAME);
//            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Проекты", true,  "Кнопка каталога 'Проекты'");
         case CONTRACTORS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Контрагенты", true,  "Кнопка каталога 'Контрагенты'");
         case PAYMENT_DOCS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Платежные документы", true,  "Кнопка каталога 'Платежные документы'");
         case CONTRACTS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Договоры", true,  "Кнопка каталога 'Договоры'");
         case COSTS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("[role='treeitem']"));
            return new ButtonElement(bys, "Статьи расходов", true,  "Кнопка каталога 'Статьи расходов'");
      }
      return new ButtonElement(bys);
   }

}
