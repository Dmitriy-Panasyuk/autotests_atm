package utils;

import java.io.*;
import java.util.ArrayList;

import static common.Config.*;
import static constants.Constant.*;

public class AllureTools {
   private static final String BAT_NAME = "RunAllure.bat";

   public static void writeAllureRunBAT() throws IOException {
      File file = createFile(ALLURE_RESULTS_PATH, BAT_NAME);
      FileOutputStream fos = new FileOutputStream(file);
//      String s = ALLURE_REPORT_PATH + " serve " + ALLURE_RESULTS_PATH;
      String s = "allure generate --single-file " + ALLURE_RESULTS_PATH + " --report-dir " + ALLURE_HTML_PATH ;
      byte[] buffer = s.getBytes();
      fos.write(buffer, 0, buffer.length);
      fos.close();
      System.out.println("HTML path " + ALLURE_HTML_PATH);
   }
   public static void writeTempFile(String path) throws IOException {
      InputStream inputStream = AllureTools.class.getResourceAsStream("/"+path);
      File file = createFile(ALLURE_RESULTS_PATH+"/temp", path);
      FileOutputStream fos = new FileOutputStream(file);
      byte[] buffer = inputStream.readAllBytes();
      fos.write(buffer, 0, buffer.length);
      fos.close();
   }
   public static File createFile(String path, String fName) {
      File fLog = new File(path, fName);
      return fLog;
   }

   public static void createResultDir() {
      File file = new File(ALLURE_RESULTS_PATH);
      if (!file.exists()) {
         file.mkdir();
      }
      file = new File(ALLURE_HTML_PATH);
      if (!file.exists()) {
         file.mkdir();
      }
   }
   public static void createTemptDir()  {
      File file = new File(ALLURE_RESULTS_PATH+"/temp");
      if (!file.exists()) {
         file.mkdir();
      }
      ArrayList<String> tempFiles = new ArrayList<>();
      tempFiles.add("test_doc.txt");
      for (String tempFile: tempFiles){
         try {
            writeTempFile(tempFile);
         } catch (IOException e) {
            throw new RuntimeException(e);
         }
      }
   }
   public static void deleteResultDir() {
      deleteDirectory(ALLURE_RESULTS_PATH);
   }
   public static void deleteTemptDir() {
      deleteDirectory(ALLURE_RESULTS_PATH+"/temp");
   }
   public static void deleteDirectory(String directoryPath) {
      File directory = new File(directoryPath);
      File[] contents = directory.listFiles();
      if (contents != null) {
         for (File file : contents) {
            deleteDirectory(file.getPath());
         }
      }
      directory.delete();
   }
   public static void createHTML() throws IOException {
      switch (PLATFORM_AND_BROWSER) {
         case "Windows 10":
            System.out.println("createHTML Win");
            writeAllureRunBAT();
            Runtime.getRuntime().exec("cmd /c " + ALLURE_RESULTS_PATH + "\\" + BAT_NAME);
            System.out.println("createHTML Win Success");
            break;
         case "Linux":
            System.out.println("createHTML Linux");
            linuxCommand("allure generate --single-file " + ALLURE_RESULTS_PATH + " --report-dir " + ALLURE_HTML_PATH);
//            starLinuxWebSerwer(8082);
            break;
      }
   }

   public static void configureAllureResultPath() {
      try {
         switch (PLATFORM_AND_BROWSER) {
            case "Windows 10":
               ALLURE_RESULTS_PATH = WIN_ALLURE_RESULTS_PATH;
               ALLURE_REPORT_PATH = WIN_ALLURE_REPORT_PATH;
               ALLURE_HTML_PATH = WIN_ALLURE_HTML_PATH;
               break;
            case "Linux":
               linuxCommand("printenv");
               linuxCommand("allure --version");
//               linuxCommand("allure --help");
               ALLURE_REPORT_PATH = LINUX_ALLURE_REPORT_PATH;
               ALLURE_RESULTS_PATH = LINUX_ALLURE_RESULTS_PATH;
               ALLURE_HTML_PATH = LINUX_ALLURE_HTML_PATH;
               break;
         }
         System.out.println("ALLURE_REPORT_PATH " + ALLURE_REPORT_PATH);
         System.out.println("ALLURE_RESULTS_PATH " + ALLURE_RESULTS_PATH);
         System.out.println("ALLURE_HTML_PATH " + ALLURE_HTML_PATH);
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
      }

   }

   public static void starLinuxWebSerwer(int port) {
      linuxCommand("mkdir -p " + LINUX_ALLURE_HTML_PATH);
      linuxCommand("python3 -m http.server 8082 -d " + ALLURE_HTML_PATH);
   }

   public static void linuxCommand(String command) {
      System.out.println("\nlinuxCommand =========================== ");
      System.out.println(command);
      System.out.println("linuxResponse ====== ");
      String terminalLine;
      Process process;
      try {
         process = Runtime.getRuntime().exec(command);
         BufferedReader br = new BufferedReader(
                 new InputStreamReader(process.getInputStream()));
         while ((terminalLine = br.readLine()) != null)
            System.out.println("line: " + terminalLine);
         process.waitFor();
         System.out.println("exit: " + process.exitValue());
         process.destroy();
      } catch (Exception ex) {
         System.out.println(ex.getMessage());
         System.out.println("linuxCommand ======================= ");
      }
      System.out.println("END linuxCommand ======================= ");
   }

}
