package roles;

public abstract class Roles {

   public enum roles {
      JACK,
      JACK2,
      JACK3,
      JACK4,
      JACK5,
      JACK6,
      JACK7,
      JACK8,
      JACK_TEST5,
      JACK_TEST6,
      JACK_TEST7,
      USER_WRONG_PASSWORD,
      BIG_JACK
   }

   public abstract class Jack extends Roles {
      public static final String LOGIN = "jack";
      public static final String PASSWORD = "jack";
   }

   public abstract class Jack2 extends Roles {
      public static final String LOGIN = "jack2";
      public static final String PASSWORD = "jack2";
   }

   public abstract class Jack3 extends Roles {
      public static final String LOGIN = "jack3";
      public static final String PASSWORD = "jack3";
   }

   public abstract class Jack4 extends Roles {
      public static final String LOGIN = "jack4";
      public static final String PASSWORD = "jack4";
   }

   public abstract class Jack5 extends Roles {
      public static final String LOGIN = "jack5";
      public static final String PASSWORD = "jack5";
   }

   public abstract class Jack6 extends Roles {
      public static final String LOGIN = "jack6";
      public static final String PASSWORD = "jack6";
   }

   public abstract class Jack7 extends Roles {
      public static final String LOGIN = "jack7";
      public static final String PASSWORD = "jack7";
   }

   public abstract class Jack8 extends Roles {
      public static final String LOGIN = "jack8";
      public static final String PASSWORD = "jack8";
   }

   public abstract class Jack_test5 extends Roles {
      public static final String LOGIN = "jack_test5";
      public static final String PASSWORD = "jack_test5";
   }

   public abstract class Jack_test6 extends Roles {
      public static final String LOGIN = "jack_test6";
      public static final String PASSWORD = "jack_test6";
   }
   public abstract class Jack_test7 extends Roles {
      public static final String LOGIN = "jack_test7";
      public static final String PASSWORD = "jack_test7";
   }
   public abstract class BigJack extends Roles {
      public static final String LOGIN = "bigjack";
      public static final String PASSWORD = "bigjack";
   }

   public abstract class UserWrongPassword extends Roles {
      public static final String LOGIN = "jack";
      public static final String PASSWORD = "jack12345";
   }

   public static String getRolePassword(roles element) {
      switch (element) {
         case JACK:
            return Jack.PASSWORD;
         case JACK2:
            return Jack2.PASSWORD;
         case JACK3:
            return Jack3.PASSWORD;
         case JACK4:
            return Jack4.PASSWORD;
         case JACK5:
            return Jack5.PASSWORD;
         case JACK6:
            return Jack6.PASSWORD;
         case JACK7:
            return Jack7.PASSWORD;
         case JACK8:
            return Jack8.PASSWORD;
         case JACK_TEST5:
            return Jack_test5.PASSWORD;
         case JACK_TEST6:
            return Jack_test6.PASSWORD;
         case JACK_TEST7:
            return Jack_test7.PASSWORD;
         case USER_WRONG_PASSWORD:
            return UserWrongPassword.PASSWORD;
         case BIG_JACK:
            return BigJack.PASSWORD;
         default:
            return null;
      }
   }

   public static String getRoleLogin(roles element) {
      switch (element) {
         case JACK:
            return Jack.LOGIN;
         case JACK2:
            return Jack2.LOGIN;
         case JACK3:
            return Jack3.LOGIN;
         case JACK4:
            return Jack4.LOGIN;
         case JACK5:
            return Jack5.LOGIN;
         case JACK6:
            return Jack6.LOGIN;
         case JACK7:
            return Jack7.LOGIN;
         case JACK8:
            return Jack8.LOGIN;
         case JACK_TEST5:
            return Jack_test5.LOGIN;
         case JACK_TEST6:
            return Jack_test6.LOGIN;
         case JACK_TEST7:
            return Jack_test7.LOGIN;
         case USER_WRONG_PASSWORD:
            return UserWrongPassword.LOGIN;
         case BIG_JACK:
            return BigJack.LOGIN;
         default:
            return null;
      }
   }
}
