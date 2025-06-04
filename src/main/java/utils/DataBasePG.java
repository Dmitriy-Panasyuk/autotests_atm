package utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

public class DataBasePG {

   private static String were = "options #> '{backend,triggers,finance_centers_ids}' is not null";
   public static int[] actualIDsFeat = {31, 32, 191, 223, 245};
   public static int[] actualIDsDev = {22, 23, 69, 79, 84};

   public static Statement createStatement(String user, String pass, String url) throws SQLException {
//      String url1 = "jdbc:postgresql://172.20.9.61:5432/workflow";
      Properties props = new Properties();
      props.setProperty("user", user);
      props.setProperty("password", pass);
      return DriverManager.getConnection(url, props).createStatement();
   }

   public static int[] getFinaceCentersIDs(Statement statement) throws Exception {
      String s = getFCOptions(statement);
      s = s.substring(s.indexOf("finance_centers_ids") + "finance_centers_ids".length());
      s = s.substring(s.indexOf("[") + "[".length(), s.indexOf("]"));
      String[] idsString = s.split(",");
      int[] ids = new int[idsString.length];
      for (int i = 0; i < ids.length; i++) {
         ids[i] = Integer.parseInt(idsString[i].trim());
      }
      return ids;
   }


   public static void addFinaceCentersIDs(Statement statement, int[] ids) throws Exception {
      Log.println("Add test finance centers IDs in DB");
      String s = getFCOptions(statement);
      String leftPart = s.substring(0, s.indexOf("finance_centers_ids") + "finance_centers_ids".length());
      s = s.substring(leftPart.length());
      leftPart = leftPart + s.substring(0, s.indexOf("[") + "[".length());
      String rightPart = s.substring(s.indexOf("]"));
      String[] idsString = new String[ids.length];
      for (int i = 0; i < ids.length; i++) {
         idsString[i] = Integer.toString(ids[i]);
      }
      Log.println();
      Log.println("Before");
      DataBasePG.printDBTab(statement, "SELECT * FROM public.transition WHERE options #> '{backend,triggers,finance_centers_ids}' is not null ");
      statement.execute("UPDATE transition " +
              "SET options = '" + leftPart + String.join(", ", idsString) + rightPart + "'::jsonb " +
              "WHERE options #> '{backend,triggers,finance_centers_ids}' is not null ");
      Log.println("After");
      DataBasePG.printDBTab(statement, "SELECT * FROM public.transition WHERE options #> '{backend,triggers,finance_centers_ids}' is not null ");
   }


   public static String getFCOptions(Statement statement) throws Exception {
      int optionsCol = -1;
      ResultSet rs = statement.executeQuery("SELECT * FROM public.transition WHERE " + were);
      ResultSetMetaData rsmd = rs.getMetaData();
      int column_count = rsmd.getColumnCount();
      int[] colLenghts = new int[column_count];
      ArrayList<String[]> table = new ArrayList<>();
      String[] row = new String[column_count];
      for (int i = 1; i <= column_count; i++) {
         row[i - 1] = rs.getMetaData().getColumnName(i);
         if (row[i - 1].equals("options")) {
            optionsCol = i - 1;
         }
      }
      table.add(row);
      while (rs.next()) {
         row = new String[column_count];
         for (int i = 1; i <= column_count; i++) {
            row[i - 1] = rs.getString(i);
         }
         table.add(row);
      }
      if (table.size() > 2) {
         throw new Exception("More than one settings line found. Please check your search parameters.");
      } else if (table.size() == 1) {
         throw new Exception("Settings line not found. Please check your search parameters.");
      }
      return table.get(1)[optionsCol];
   }

   public static void printDBTab(Statement statement, String sql) throws SQLException {
      ResultSet rs = statement.executeQuery(sql);
      ResultSetMetaData rsmd = rs.getMetaData();
      int column_count = rsmd.getColumnCount();
      int[] colLenghts = new int[column_count];
      ArrayList<String[]> table = new ArrayList<>();
      String[] row = new String[column_count];
      for (int i = 1; i <= column_count; i++) {
         row[i - 1] = rs.getMetaData().getColumnName(i);
         if (row[i - 1].length() > colLenghts[i - 1]) {
            colLenghts[i - 1] = row[i - 1].length();
         }
      }
      table.add(row);
      while (rs.next()) {
         row = new String[column_count];
         for (int i = 1; i <= column_count; i++) {
            row[i - 1] = rs.getString(i);
            if (row[i - 1] != null) {
               if (row[i - 1].length() > colLenghts[i - 1]) {
                  colLenghts[i - 1] = row[i - 1].length();
               }
            }

         }
         table.add(row);
      }
      int sum = 0;
      for (int i : colLenghts) {
         sum += i;
      }
      Log.println("-".repeat(sum + (colLenghts.length * 3) + 1));
      for (int i = 0; i < table.size(); i++) {
         for (int j = 0; j < table.get(i).length; j++) {
            if (table.get(i)[j] != null) {
               Log.print("| " + table.get(i)[j] + " ".repeat(colLenghts[j] - table.get(i)[j].length() + 1));
            } else {
               Log.print("| " + " ".repeat(colLenghts[j] + 1));
            }
         }
         Log.print("|\n");
         if (i == 0) {
            Log.println("-".repeat(sum + (colLenghts.length * 3) + 1));
         }
      }
      Log.println("-".repeat(sum + (colLenghts.length * 3) + 1));
      rs.close();
   }
}