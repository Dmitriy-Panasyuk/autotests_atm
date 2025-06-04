package pages.payment;

import elements.*;
import org.openqa.selenium.By;
import pages.BasePage;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class RequestPageND extends BasePage {


   public enum labels {
      REQUEST_NUM,
      STATUS_PAID,
      STATUS_REJECTED,
      TOASTIFY_SUCCESS,
      COMMENT
   }

   public enum enums {
      COMPANY_NAME,
      FINANCIAL_NAME,
      COST_CENTER_NAME,
      CONTRACTORS_TYPE_NAME,
      CONTRACTORS_NAME,
      CONTRACT_NUMBER,
      COST_DOCUMENT_TYPE,
      PROJECT,
      TASK,
      EXPENCES_TASK,
      CURRENCY,
      REVIEWERS,
      ADDRESSEE
   }

   public enum dates {
      COST_DATA,
   }

   public enum inputs {
      FILE_PATH,
      PAYMENT_DISCRIPTION,
      COST_DOCUMENT_NUMBER,
      PROJECT_TASKS,
      VALUE,
      DOC_DATA,
      REQUEST_NUMBER_1C,
      COST_DATA_1C,
      ON_REVISION_COMMENT,
      COMMENT,
      COMMENT_FILE_PATH
   }

   public enum buttons {
      DELETE,
      DELETE_SUBMIT,
      SAVE,
      RETURN,
      ATTACH_FILE,
      ATTACH_FILE_SUBMIT,
      DELETE_FILE,
      DELETE_FILE_SUBMIT,
      DOWNLOAD_FILE,
      REGISTRATION,
      REGISTRATION_SUBMIT,
      ON_APPROVAL,
      ON_APPROVAL_SUBMIT,
      APPROV,
      APPROV_SUBMIT,
      ON_AFFIRMATION,
      ON_AFFIRMATION_SUBMIT,
      AFFIRM,
      AFFIRM_SUBMIT,
      ON_PAYMENT,
      ON_PAYMENT_SUBMIT,
      REGISTR,
      REGISTR_SUBMIT,
      ADD_PAYMENT,
      ADD_PAYMENT_SUBMIT,
      ON_REVISION,
      ON_REVISION_SUBMIT,
      REJECT,
      REJECT_SUBMIT,
      REVIEWERS,
      ASSIGN_TO_YOURSELF,
      ADD_COMMENT,
      CANCEL,
      CONFIRM,
      COMMENT_DOWNLOAD_FILE
   }

   public static DataElement get(dates element) {
      ArrayList<By> bys = new ArrayList<>();
      BaseElement parent;
      DataElement dataElement;
      switch (element) {
         case COST_DATA:
            bys.add(By.className("MuiTextField-root"));
            parent = new BaseElement(bys, "Срок оплаты", false, Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.className("Mui-readOnly"));
            bys.add(By.className("MuiInputBase-adornedEnd"));
            dataElement = new DataElement(bys, "Срок оплаты", Visibility.INVISIBLE);
            dataElement.setParent(parent);
            return dataElement;

      }
      return new DataElement(bys);
   }

   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      BaseElement parent;
      switch (element) {
         case CONFIRM:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Отменить");
         case CANCEL:
            bys.add(By.cssSelector("[type='button']"));
            return new ButtonElement(bys, "Отменить", true, "Отменить");
         case ADD_COMMENT:
            bys.add(By.tagName("button"));
            bys.add(By.cssSelector("[type='button']"));
            bys.add(By.className("MuiButton-contained"));
            return new ButtonElement(bys, "Добавить комментарий", true, "Добавить комментарий",Visibility.VISIBLE);
         case REJECT_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить отклонение заявки");
         case REJECT:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Отклонить", true, "Отклонить",Visibility.VISIBLE);
         case ON_REVISION_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить отправку на доработку");
         case ON_REVISION:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "На доработку", true, "На доработку");
         case ADD_PAYMENT_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить добавление оплаты");
         case ADD_PAYMENT:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Добавить оплату", true, "Добавить оплату");
         case REGISTR_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить оформление");
         case REGISTR:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Оформить", true, "Оформить");
         case ON_PAYMENT_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить утверждение отправку на оплату");
         case ON_PAYMENT:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "На оплату", true, "На оплату");
         case AFFIRM_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить утверждение");
         case AFFIRM:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Утвердить", true, "Утвердить");
         case ON_AFFIRMATION_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить отправку на утверждение");
         case ON_AFFIRMATION:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "На утверждение", true, "На утверждение");
         case APPROV_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить согласование");
         case APPROV:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Согласовать", true, "Согласовать");
         case ON_APPROVAL_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить отправку на согласование");
         case ON_APPROVAL:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "На согласование", true, "На согласование");
         case REGISTRATION_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить", true, "Подтвердить отправку на оформление");
         case REGISTRATION:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "На оформление", true, "На оформление");
         case DELETE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Удалить", true, "Удалить заявку");
         case DELETE_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить удаление");
         case SAVE:
            bys.add(BY_DQWE_ATT_NAME);
//            bys.add(By.cssSelector("[colortoken='var(--accept-action)']"));
            return new ButtonElement(bys, "Сохранить", true, "Сохранить заявку");
         case RETURN:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "К реестру", true,  "Возврат к реестру");
         case ATTACH_FILE:
            bys.add(By.className("dropzone"));
            return new ButtonElement(bys, "Прикрепить файл");
         case ATTACH_FILE_SUBMIT:
            bys.add(By.id("file-submit"));
            return new ButtonElement(bys, "Подтвердить прикрепление файла");
         case DELETE_FILE:
            bys.add(By.className("MuiSvgIcon-fontSizeSmall"));
            bys.add(By.cssSelector("[data-testid='DeleteIcon']"));
            return new ButtonElement(bys, "Удалить файл");
         case DOWNLOAD_FILE:
            bys.add(By.cssSelector("[data-testid='FileDownloadIcon']"));
            return new ButtonElement(bys, "Скачать файл");
         case COMMENT_DOWNLOAD_FILE:
            bys.add(By.className("MuiCollapse-wrapperInner"));
            parent = new BaseElement(bys,  Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.cssSelector("[data-testid='FileDownloadIcon']"));
            ButtonElement button = new ButtonElement(bys, "Скачать файл");
            button.setParent(parent);
            return button;
         case DELETE_FILE_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить удаление файла");
         case REVIEWERS:
//            bys.add(By.className("MuiButtonBase-root"));
            bys.add(By.cssSelector("[type='button']"));
            return new ButtonElement(bys, "Наблюдатели",false,"Наблюдатели", Visibility.INVISIBLE);
         case ASSIGN_TO_YOURSELF:
            bys.add(By.className("MuiButtonBase-root"));
            return new ButtonElement(bys, "Назначить на себя",true,"Назначить на себя", Visibility.INVISIBLE);
      }
      return new ButtonElement(bys);
   }

   public static InputElement get(inputs element) {
      ArrayList<By> bys = new ArrayList<>();
      BaseElement parent;
      InputElement inputElement;
      switch (element) {
         case COMMENT:
//            bys.add(By.className("MuiTextField-root"));
//            parent = new BaseElement(bys, "Комментарий", true, Visibility.INVISIBLE);
//            bys = new ArrayList<>();
            bys.add(By.tagName("textarea"));
            bys.add(By.cssSelector("[name='message']"));
            inputElement = new InputElement(bys, "Комментарий", Visibility.INVISIBLE);
//            inputElement.setParent(parent);
            return inputElement;
         case ON_REVISION_COMMENT:
            bys.add(By.className("MuiTextField-root"));
            parent = new BaseElement(bys, "Введите комментарий", false, Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.tagName("textarea"));
            bys.add(By.cssSelector("[required]"));
//            bys.add(By.cssSelector("[aria-describedby]"));
            inputElement = new InputElement(bys, "Комментарий при отправке на доработку", Visibility.INVISIBLE);
            inputElement.setParent(parent);
            return inputElement;
         case COST_DATA_1C:
            bys.add(By.cssSelector("[role='dialog']"));
            parent = new BaseElement(bys,  Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.tagName("input"));
            bys.add(By.cssSelector("[required]"));
            bys.add(By.cssSelector("[type='date']"));
            inputElement = new InputElement(bys, "Дата заявки 1С", Visibility.INVISIBLE);
            inputElement.setParent(parent);
            return inputElement;
         case REQUEST_NUMBER_1C:
            bys.add(By.cssSelector("[role='dialog']"));
            parent = new BaseElement(bys,Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.tagName("input"));
            bys.add(By.cssSelector("[required]"));
            bys.add(By.cssSelector("[type='text']"));
            inputElement = new InputElement(bys, "Номер заявки 1С", Visibility.INVISIBLE);
            inputElement.setParent(parent);
            return inputElement;
         case FILE_PATH:
            bys.add(By.id("upload-request"));
            bys.add(By.cssSelector("[accept]"));
            return new InputElement(bys, "Путь к файлу", Visibility.INVISIBLE);
         case COMMENT_FILE_PATH:
//            bys.add(By.className("MuiDialogContent-root"));
//            parent = new BaseElement(bys, Visibility.INVISIBLE);

//            bys = new ArrayList<>();
            bys.add(By.id("upload-modal"));
            bys.add(By.cssSelector("[accept]"));
            inputElement = new InputElement(bys, "Путь к файлу", Visibility.INVISIBLE);
//            inputElement.setParent(parent);
            return inputElement;
         case PAYMENT_DISCRIPTION:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiTextField-root"));
            parent = new BaseElement(bys, "Описание заявки", false, Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.className("MuiInputBase-input"));
            bys.add(By.cssSelector("[id='body.description']"));
            inputElement = new InputElement(bys, "Описание заявки", Visibility.INVISIBLE);
            inputElement.setParent(parent);
            return inputElement;
         case COST_DOCUMENT_NUMBER:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiTextField-root"));
            parent = new BaseElement(bys, "Номер платежного документа", false, Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.className("MuiInputBase-input"));
            bys.add(By.cssSelector("[id='body.payment_document.payment_number']"));
            inputElement = new InputElement(bys, "Номер платежного документа", Visibility.INVISIBLE);
            inputElement.setParent(parent);
            return inputElement;
         case PROJECT_TASKS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiTextField-root"));
            parent = new BaseElement(bys, "Задачи по проекту", false, Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.className("MuiInputBase-input"));
            bys.add(By.cssSelector("[id='body.additional_analytics']"));
            inputElement = new InputElement(bys, "Задачи по проекту", Visibility.INVISIBLE);
            inputElement.setParent(parent);
            return inputElement;
         case VALUE:

            bys.add(By.className("MuiTextField-root"));
            parent = new BaseElement(bys, "Сумма", false, Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiInputBase-input"));
            inputElement = new InputElement(bys, "Сумма", Visibility.INVISIBLE);
            inputElement.setParent(parent);
            return inputElement;
         case DOC_DATA:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiTextField-root"));
            parent = new BaseElement(bys, "Дата документа", false, Visibility.INVISIBLE);
            bys = new ArrayList<>();
            bys.add(By.className("MuiInputBase-input"));
            inputElement = new InputElement(bys, "Дата документа", Visibility.INVISIBLE);
            inputElement.setParent(parent);
            return inputElement;
      }
      return new InputElement(bys);
   }

   public static LabelElement get(labels element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case COMMENT:
            bys.add(By.className("MuiAccordion-rounded"));
            return new LabelElement(bys, "Комментарий", Visibility.VISIBLE);
         case STATUS_REJECTED:
            bys.add(By.className("MuiChip-label"));
            return new LabelElement(bys, "Отклонена", true, "Статус Оплачена", Visibility.VISIBLE);
         case STATUS_PAID:
            bys.add(By.className("MuiChip-label"));
            return new LabelElement(bys, "Оплачена", true, "Статус Оплачена", Visibility.VISIBLE);
         case REQUEST_NUM:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("h5[data-qa]"));
            return new LabelElement(bys/*, "Заявка №",false*/, "Номер заявки");
         case TOASTIFY_SUCCESS:
            bys.add(By.className("toastify-success"));
            return new LabelElement(bys, "Всплывающее окно Заявка сохранена", Visibility.POPUP);
      }
      return new LabelElement(bys);
   }

   public static EnumElement get(enums element) {
      ArrayList<By> bys = new ArrayList<>();
      BaseElement parent;
      EnumElement enumElement;
      switch (element) {
         case ADDRESSEE:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys, "Адресат",false, "Адресат", Visibility.INVISIBLE);
            return enumElement;
         case COMPANY_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys, "Компания", false, "Список компаний");
            return enumElement;
         case FINANCIAL_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys, "Центр финансововой ответственности (ЦФО)", false,  "Список ЦФО");
            return enumElement;
         case COST_CENTER_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys, "Место возникновения затрат (МВЗ)", false, "Список МВЗ");
            return enumElement;
         case CONTRACTORS_TYPE_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys,"Тип контрагента", false, "Список Тип контрагента");
            return enumElement;
         case CONTRACTORS_NAME:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys, "Контрагент *", true, "Список Контрагенты");
            return enumElement;
         case CONTRACT_NUMBER:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys,"Номер договора", false,  "Список Номеров договоров");
            return enumElement;
         case COST_DOCUMENT_TYPE:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys,  "Вид платежного документа", false,"Список Виды платежного документа");
            return enumElement;
         case PROJECT:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys, "Проект", false,  "Список Проекты");
            return enumElement;
         case TASK:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys, "Корпоративная задача", false,  "Список Корпоративная задача");
            return enumElement;
         case EXPENCES_TASK:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys,  "Статья расходов", false,"Список Статья расходов");
            return enumElement;
         case CURRENCY:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys, "Валюта", false,  "Список Валют");
            return enumElement;
         case REVIEWERS:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.className("MuiFormControl-fullWidth"));
            enumElement = new EnumElement(bys,"Наблюдатели",false,  "Наблюдатели", Visibility.INVISIBLE);
            return enumElement;
      }
      return null;
   }
}
