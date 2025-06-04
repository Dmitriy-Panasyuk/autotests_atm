package pages.payment;

import elements.ButtonElement;
import elements.InputElement;
import elements.LabelElement;
import elements.Visibility;
import org.openqa.selenium.By;
import pages.BasePage;

import java.util.ArrayList;

import static elements.BaseElement.BY_DQWE_ATT_NAME;

public class RequestPage extends BasePage {

   public enum buttons {
      DELETE,
      DELETE_SUBMIT,
      SAVE,
      RETURN,
      ATTACH_FILE,
      ATTACH_FILE_SUBMIT,
      DELETE_FILE,
      DELETE_FILE_SUBMIT,
      DOWNLOAD_FILE

   }
   public enum inputs {
      FILE_PATH
   }

   public enum labels {
      REQUEST_NUM,
   }


   public static ButtonElement get(buttons element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case DELETE:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "Удалить",true, "Удалить заявку");
         case DELETE_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement(bys, "Подтвердить удаление");
         case SAVE:
            bys.add(By.cssSelector("[colortoken='var(--accept-action)']"));
            return new ButtonElement(bys, "Сохранить заявку");
         case RETURN:
            bys.add(BY_DQWE_ATT_NAME);
            return new ButtonElement(bys, "К реестру",true,"Возврат к реестру");
         case ATTACH_FILE:
            bys.add(By.cssSelector("[data-testid='AttachFileIcon']"));
            return new ButtonElement( bys, "Прикрепить файл");
         case ATTACH_FILE_SUBMIT:
            bys.add(By.id("file-submit"));
            return new ButtonElement( bys, "Подтвердить прикрепление файла");
         case DELETE_FILE:
            bys.add(By.className("MuiSvgIcon-fontSizeSmall"));
            bys.add(By.cssSelector("[data-testid='DeleteIcon']"));
            return new ButtonElement(bys, "Удалить файл");
         case DOWNLOAD_FILE:
            bys.add(By.cssSelector("[data-testid='FileDownloadIcon']"));
            return new ButtonElement(bys, "Скачать файл");
         case DELETE_FILE_SUBMIT:
            bys.add(By.cssSelector("[type='submit']"));
            return new ButtonElement( bys, "Подтвердить удаление файла");
      }
      return new ButtonElement(bys);
   }
   public static InputElement get(inputs element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case FILE_PATH:
            bys.add(By.id("upload-request"));
            bys.add(By.cssSelector("[accept]"));
            return new InputElement(bys,"Путь к файлу", Visibility.INVISIBLE);

      }
      return new InputElement(bys);
   }
   public static LabelElement get(labels element) {
      ArrayList<By> bys = new ArrayList<>();
      switch (element) {
         case REQUEST_NUM:
            bys.add(BY_DQWE_ATT_NAME);
            bys.add(By.cssSelector("h5[data-qa]"));
//            bys.add(By.className("MuiTypography-root"));
            return new LabelElement(bys/*, "Заявка №",false*/, "Номер заявки");
      }
      return new LabelElement(bys);
   }

}
