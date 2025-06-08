package pages;

public class PageURL {

   public static String HOME = "-";

   public static String DYNAMIC_ID = HOME + "/dynamicid";
   public static String MULTICLASS = HOME + "/classattr";
   //==================================================================================
   //==================================================================================
   //==================================================================================
   public static void refreshURLs (){
      DYNAMIC_ID = HOME + "/dynamicid";
      MULTICLASS = HOME + "/classattr";
   }
}