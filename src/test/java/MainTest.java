import java.net.URL;

public class MainTest {

   public static void main(String[] args) {
      System.out.println("Main");


      URL url = Main.class.getResource("/test_doc.txt");

      System.out.println(url.getFile().substring(1));
//      TestNG testNG = new TestNG();
//      List<XmlSuite> xmlSuites = new ArrayList<>();
//
//      XmlSuite xmlSuite = new XmlSuite();
//
//      List<XmlTest> xmlTestList = new ArrayList<>();
//      XmlTest xmlTest = new XmlTest(xmlSuite);
//
//      List<XmlClass> xmlClassList = new ArrayList<>();
//
//      xmlClassList.add(new XmlClass(LoginTest.class));
//      xmlClassList.add(new XmlClass(CommonTest.class));
//      xmlTest.setXmlClasses(xmlClassList);
//      xmlTestList.add(xmlTest);
//
//      xmlSuite.setTests(xmlTestList);
//      xmlSuite.setName("sdfs");
//
//      xmlSuites.add(xmlSuite);
//      testNG.setXmlSuites(xmlSuites);
//      testNG.setDefaultSuiteName("asd");
//      testNG.setDefaultTestName("qwe");
//      testNG.run();
   }

}
