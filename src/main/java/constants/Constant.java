package constants;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Constant {
   public static final Duration IMPLICIT_WAIT = Duration.ofSeconds(30);
   public static final Duration PAGE_LOAD_WAIT = Duration.ofSeconds(30);
   /**
    * Задержка перед получением элемента, до его появления в DOM и отображения.
    */
   public static final Duration ELEMENT_VISIBLE_WAIT = Duration.ofSeconds(30);

   /**
    * Путь к AllureReport.
    */
   public static String ALLURE_REPORT_PATH = "";

   /**
    * Путь к папке содержащей результаты тестов.
    */
   public static String ALLURE_RESULTS_PATH = "";

   public static String ALLURE_HTML_PATH = "";

   public static String TEST_RUN_ID = "";

   /**
    * ID генерируемый из даты, добавляется для генерируемых тестовых объектов и данных результатов.
    */
   public static void  setRunID() {
      Calendar calendar = new GregorianCalendar();
      DateFormat dateFormat = new SimpleDateFormat("MMddHHmmss");
      TEST_RUN_ID= dateFormat.format(calendar.getTime());
   }


}
