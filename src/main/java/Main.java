import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_BLACK = "\u001B[30m";
   public static final String ANSI_RED = "\u001B[31m";
   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_YELLOW = "\u001B[33m";
   public static final String ANSI_BLUE = "\u001B[34m";
   public static final String ANSI_PURPLE = "\u001B[35m";
   public static final String ANSI_CYAN = "\u001B[36m";
   public static final String ANSI_GRAY = "\u001B[37m";
   static int asd = -1;

   public static void print(String st) {
      if (asd == -1) {
         asd = Thread.currentThread().getStackTrace().length;
      }
      StackTraceElement[] a = Thread.currentThread().getStackTrace();
      System.out.println(ANSI_GRAY + "| ".repeat(a.length - asd) + ANSI_RESET + "" + st);

   }

   public static void pr1() {
      print("pr1");
      pr2();
   }

   public static void pr2() {
      print("pr2");
      pr3();
   }

   public static void pr3() {
      print("pr3");
   }

   public static void main(String[] args) throws Exception {




   }

   public static void createTestRole(Statement statement) throws SQLException {
      try {
         statement.executeQuery("INSERT INTO public.role (name)  VALUES ('Test_role')");
      } catch (Exception ex) {
      }
   }

   public static void deleteTestRole(Statement statement) throws SQLException {
      try {
         statement.executeQuery("DELETE FROM public.role WHERE name='Test_role'");
      } catch (Exception ex) {
      }
   }


   public static String resp(HttpURLConnection con) {
      try (final BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()))) {
         String inputLine;
         final StringBuilder content = new StringBuilder();
         while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
         }
         return content.toString();
      } catch (final Exception ex) {
         ex.printStackTrace();
         return "";
      }
   }
}
