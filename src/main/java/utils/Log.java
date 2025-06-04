package utils;

import java.util.ArrayList;

import static common.Config.PLATFORM_AND_BROWSER;

public class Log {
   private static final boolean ADVANCED_LOG = false;

   public static String testLog = "";
   public static int testNum = 0;
   public static int finalLogLenth = 57;

   public static int textLevelNull = 0;
   public static boolean isTextLevel = false;
   public static int currentTextLevel = 0;
   public static int displayedTextLevel = 0;
   public static ArrayList<String> classNames = new ArrayList<>();
   public static ArrayList<ArrayList<String>> testNames = new ArrayList<>();
   public static ArrayList<ArrayList<String>> testStatus = new ArrayList<>();
   public static ArrayList<String> currentTestNames = new ArrayList<>();

   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_BLACK = "\u001B[30m";
   public static final String ANSI_RED = "\u001B[31m";
   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_YELLOW = "\u001B[33m";
   public static final String ANSI_BLUE = "\u001B[34m";
   public static final String ANSI_PURPLE = "\u001B[35m";
   public static final String ANSI_CYAN = "\u001B[36m";
   public static final String ANSI_GRAY = "\u001B[37m";

   public static String getTestFinalLog() {
      try{
         testNum = 0;
         testLog = "";
         testLog += "  Tests resume";
         testLog += "\n  " + "=".repeat(finalLogLenth);
         for (String className : classNames) {
            if(testNames.get(classNames.indexOf(className)).contains("Failed")||testNames.get(classNames.indexOf(className)).contains("Skipped")){
               testLog += "\n  " + ANSI_RED + className+ ANSI_RESET;
            }else{
               testLog += "\n  " + className;
            }
            testLog += "\n  " + "-".repeat(finalLogLenth);
            for (String testName : testNames.get(classNames.indexOf(className))) {
               String status = testStatus.get(classNames.indexOf(className)).get(testNames.get(classNames.indexOf(className)).indexOf(testName));
               switch (status) {
                  case "Success" -> status = ANSI_GREEN + "Success" + ANSI_RESET;
                  case "Failed" -> status = ANSI_RED + "Failed" + ANSI_RESET;
                  case "Skipped" -> status = ANSI_YELLOW + "Skipped" + ANSI_RESET;
               }
               testNum += 1;
               testLog += "\n   ";
               testLog += testNum + " ".repeat(4 - ("" + testNum).length());
               testLog += "| ";
               if (testName.length() > finalLogLenth - 17) testName = testName.substring(0, finalLogLenth - 18);
               testLog += testName;
               testLog += " ".repeat(finalLogLenth - 17 - testName.length());
               testLog += "| ";
               testLog += status;

            }
            testLog += "\n  " + "-".repeat(finalLogLenth);
         }
         testLog += "\n  " + "=".repeat(finalLogLenth) + "\n";
         return testLog;
      }catch (Exception ex){
         return "* error log ";
      }
   }

   public static void printFinalTestLog() {
      System.out.print("  " + "=".repeat(finalLogLenth));
      System.out.println(testLog);
      System.out.println("  " + "=".repeat(finalLogLenth) + "\n");
   }

   public static void textLevelON() {
      isTextLevel = true;
      currentTextLevel = 0;
      displayedTextLevel = -1;
      textLevelNull = Thread.currentThread().getStackTrace().length;
   }

   public static void textLevelOFF() {
      isTextLevel = false;
      textLevelNull = 0;
   }

   public static void printlnAL(Object string, String color) {
      if (PLATFORM_AND_BROWSER.equals("Windows 10") && ADVANCED_LOG) {
         print(string + "\n", color);
      }
   }
   public static void printlnAL(Object string) {
         printlnAL(string, "");
   }
   public static void println(Object string) {
      println(string, "");
   }

   public static void print(Object string) {
      print(string, "");
   }

   public static void println() {
      println("", "");
   }

   public static void print() {
      print("", "");
   }

   public static void println(Object string, String color) {
      print(string + "\n", color);
   }

   public static void print(Object string, String color) {
      if (isTextLevel) {
//         int level = Thread.currentThread().getStackTrace().length - textLevelNull;
//         if(currentTextLevel<level){
//            displayedTextLevel+=1;
//         } else if (currentTextLevel>level) {
//            displayedTextLevel-=1;
//         }
//         currentTextLevel=level;
//         string = " ".repeat(displayedTextLevel) + string;
      }
      if (color.equals("")) {
         System.out.print(string);
      } else if (color.equals("r".toLowerCase())) {
         System.out.print(ANSI_RED + string + ANSI_RESET);
      } else if (color.equals("g".toLowerCase())) {
         System.out.print(ANSI_GREEN + string + ANSI_RESET);
      } else if (color.equals("y".toLowerCase())) {
         System.out.print(ANSI_YELLOW + string + ANSI_RESET);
      } else if (color.equals("b".toLowerCase())) {
         System.out.print(ANSI_BLUE + string + ANSI_RESET);
      } else if (color.equals("p".toLowerCase())) {
         System.out.print(ANSI_PURPLE + string + ANSI_RESET);
      } else if (color.equals("c".toLowerCase())) {
         System.out.print(ANSI_CYAN + string + ANSI_RESET);
      } else if (color.equals("gr".toLowerCase())) {
         System.out.print(ANSI_GRAY + string + ANSI_RESET);
      }
   }

   public static void printClassTitle(String className) {
      String title;
      if (classNames.contains(className)) {
         title = "After class " + className;
         testNames.add(currentTestNames);
         currentTestNames = new ArrayList<>();
      } else {
         title = "Before class " + className;
         classNames.add(className);
         testStatus.add(new ArrayList<>());
      }
      Log.textLevelOFF();
      Log.println("\n" + "=".repeat(title.length()));
      Log.println(title, "p");
      Log.println("=".repeat(title.length()));
   }

   public static void printTestTitle(String testName) {
      currentTestNames.add(testName);
      String title = "Start test: " + testName +" | Thread: "+ Thread.currentThread().getName();
      Log.textLevelOFF();
      Log.println("\n" + "-".repeat(title.length()));
      Log.println(title, "p");
      Log.println("-".repeat(title.length()));
   }
}
