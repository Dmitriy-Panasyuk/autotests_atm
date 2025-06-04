package utils;

import common.CommonActions;
import io.qameta.allure.Attachment;
import io.qameta.allure.Step;
import constants.Constant;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import java.io.*;


public class AllureAttachmentTools {

   public static File saveScreenshot(String path) {
      File scrFile = ((TakesScreenshot) CommonActions.getDriver()).getScreenshotAs(OutputType.FILE);
      try (InputStream inputStream = scrFile.toURI().toURL().openStream();
           FileOutputStream fos = new FileOutputStream(path + "\\" + scrFile.getName())) {
         fos.write(inputStream.readAllBytes());
      } catch (IOException e) {
         throw new RuntimeException(e);
      }
      scrFile = new File(path + "\\" + scrFile.getName());
      return scrFile;
   }

   @Attachment(value = "data", type = "text/plain", fileExtension = ".txt")
   @Step("Прикрепить текстовый файл")
   public static String attachDataTXT(String str) {
      return str;
   }

   @Attachment(value = "screenshot", type = "image/png", fileExtension = ".png")
   @Step("Прикрепить скриншот")
   public static byte[] attachScreenshotPNG() {
      try {
         System.out.println("Attach screenshot PNG");
         return saveScreenshot(Constant.ALLURE_RESULTS_PATH).toURI().toURL().openStream().readAllBytes();
      } catch (IOException ex) {
         System.out.println(ex.getMessage());
         attachDataTXT(ex.toString());
         return new byte[0];
      }
   }
}
