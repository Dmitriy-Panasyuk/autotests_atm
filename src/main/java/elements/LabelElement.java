package elements;

import org.openqa.selenium.By;

import java.util.ArrayList;

public class LabelElement extends BaseElement {
   public static final int DATA_QA_VALUE = 1;

   public LabelElement(ArrayList<By> bys) {
      super(bys);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public LabelElement(ArrayList<By> bys, String name) {
      this(bys);
      this.elementName = name;
   }

   public LabelElement(ArrayList<By> bys, String Text, boolean isTextEquals) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public LabelElement(ArrayList<By> bys, Visibility visibility) {
      super(bys, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public LabelElement(ArrayList<By> bys, String name, Visibility visibility) {
      this(bys, visibility);
      this.elementName = name;
   }

   public LabelElement(ArrayList<By> bys, String Text, boolean isTextEquals, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
   }

   public LabelElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name) {
      super(bys, Text, isTextEquals);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }

   public LabelElement(ArrayList<By> bys, String Text, boolean isTextEquals, String name, Visibility visibility) {
      super(bys, Text, isTextEquals, visibility);
      this.type = DATA_QA_VALUE;
      if (this.bys.contains(BY_DQWE_ATT_NAME)) {
         this.bys.set(this.bys.indexOf(BY_DQWE_ATT_NAME), By.cssSelector("[" + DQWE_ATT_NAME + "='" + this.type + "']"));
      }
      this.elementName = name;
   }
}
