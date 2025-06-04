package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class Utils {
   public static int countMatches(String string, String find) {
      return string.length() - string.replaceAll(find, "").length();
   }

   public static boolean equalsURLs(String url1, String url2) {
      if (url1.lastIndexOf("/") != url1.length() - 1) {
         url1 = url1 + "/";
      }
      if (url2.lastIndexOf("/") != url2.length() - 1) {
         url2 = url2 + "/";
      }
      return url1.length() == url2.length() && url1.equals(url2);
   }

   public static boolean isArraysEquals(int[] arr1, int[] arr2) {
      boolean flag;
      if (arr1.length != arr2.length) return false;
      for (int a : arr1) {
         flag = true;
         for (int b : arr2) {
            if (a == b){
               flag = false;
               break;
            }
         }
         if (flag) return false;
      }
      return true;
   }
   public static boolean isArraysContain(int[] arr, int[] sequence) {
      boolean flag;
      for (int a : sequence) {
         flag = true;
         for (int b : arr) {
            if (a == b){
               flag = false;
               break;
            }
         }
         if (flag) return false;
      }
      return true;
   }
   public static int[] combineArrays(int[] arr1, int[] arr2) {
      ArrayList<Integer> values = new ArrayList<>();

      for (int a : arr1) {
         values.add(a);
      }
      for (int b : arr2) {
         if (!values.contains(b)) values.add(b);
      }
      int[] arr = new int[values.size()];
      for (int i = 0; i < values.size(); i++) {
         arr[i] = values.get(i);
      }
      Arrays.sort(arr);
      return arr;
   }
}
