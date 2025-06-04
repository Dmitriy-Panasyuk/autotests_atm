package pages;

public class PageURL {

   public static String HOME = "-";
   public static String PAYMENT = HOME + "/payment";

   //==================================================================================
   //   Страницы каталогов    =========================================================
   //==================================================================================
//   public static String CATALOG = PAYMENT + "/catalog";
//   public static String CATALOG_EMPLOYEES = CATALOG + "/employees";
//   public static String EMPLOYEES_ROLES = CATALOG_EMPLOYEES + "/roles";
//   public static String EMPLOYEES_PROJECT_MANAGERS = CATALOG_EMPLOYEES + "/project-managers";
//   public static String EMPLOYEES_RESPONSIBLE = CATALOG_EMPLOYEES + "/responsible";
//   public static String RESPONSIBLE_DIRECTORS = EMPLOYEES_RESPONSIBLE + "/directors";
//   public static String RESPONSIBLE_BUDGET_ADMINS = EMPLOYEES_RESPONSIBLE + "/budget-admins";
//   public static String RESPONSIBLE_ECONOMIST = EMPLOYEES_RESPONSIBLE + "/economists";
//
//   public static String CATALOG_COMPANIES = CATALOG + "/structure/companies";
//   public static String CATALOG_COMPANIES_FINANCIAL_RESPONSIBILITY = CATALOG_COMPANIES + "/financial-responsibility";
//   public static String COMPANIES_FINANCIAL_RESPONSIBILITY_COST_CENTER = CATALOG_COMPANIES_FINANCIAL_RESPONSIBILITY + "/cost-center";
//   public static String CATALOG_PROGRAMS = CATALOG + "/structure/programs";
//   public static String CATALOG_PROJECTS = CATALOG + "/structure/projects";
//
//   public static String CATALOG_FIN_DOCS = CATALOG + "/financial-docs";
//   public static String FIN_DOCS_CONTRACTORS = CATALOG_FIN_DOCS + "/contractors";
//   public static String FIN_DOCS_PAYMENT = CATALOG_FIN_DOCS + "/payment-docs";
//   public static String FIN_DOCS_CONTRACTS = CATALOG_FIN_DOCS + "/contracts";
//   public static String FIN_DOCS_COSTS = CATALOG_FIN_DOCS + "/costs";
   //==================================================================================
   //   Страницы каталогов v2    ======================================================
   //==================================================================================
   public static String CATALOG = "/directory";
   public static String CATALOG_EMPLOYEES = CATALOG + "/administration/employees";
   public static String EMPLOYEES_ROLES = CATALOG + "/administration/roles";
   public static String EMPLOYEES_PROJECT_MANAGERS = CATALOG_EMPLOYEES + "/project-managers";
   public static String EMPLOYEES_RESPONSIBLE = CATALOG_EMPLOYEES + "/responsible";
   public static String RESPONSIBLE_DIRECTORS = EMPLOYEES_RESPONSIBLE + "/directors";
   public static String RESPONSIBLE_BUDGET_ADMINS = EMPLOYEES_RESPONSIBLE + "/budget-admins";
   public static String RESPONSIBLE_ECONOMIST = EMPLOYEES_RESPONSIBLE + "/economists";

   public static String CATALOG_COMPANIES = CATALOG + "/structure/companies";
   public static String CATALOG_COMPANIES_FINANCIAL_RESPONSIBILITY = CATALOG_COMPANIES + "/financial-responsibility";
   public static String COMPANIES_FINANCIAL_RESPONSIBILITY_COST_CENTER = CATALOG_COMPANIES_FINANCIAL_RESPONSIBILITY + "/cost-center";
   public static String CATALOG_PROGRAMS = CATALOG + "/structure/subjects";
   public static String CATALOG_PROJECTS = CATALOG + "/structure/projects";

   public static String CATALOG_FIN_DOCS = CATALOG + "/financial-docs";
   public static String FIN_DOCS_CONTRACTORS = CATALOG_FIN_DOCS + "/contractors";
   public static String FIN_DOCS_PAYMENT = CATALOG_FIN_DOCS + "/payment-types";
   public static String FIN_DOCS_CONTRACTS = CATALOG_FIN_DOCS + "/contracts";
   public static String FIN_DOCS_COSTS = CATALOG_FIN_DOCS + "/costs";
   //==================================================================================
   //==================================================================================
   //==================================================================================
   public static void refreshURLs (){
      PAYMENT = HOME + "/payment";
      CATALOG = PAYMENT + "/catalog";
      CATALOG_EMPLOYEES = CATALOG + "/employees";
      EMPLOYEES_ROLES = CATALOG_EMPLOYEES + "/roles";
//      EMPLOYEES_PROJECT_MANAGERS = CATALOG_EMPLOYEES + "/project-managers";
//      EMPLOYEES_RESPONSIBLE = CATALOG_EMPLOYEES + "/responsible";
//      RESPONSIBLE_DIRECTORS = EMPLOYEES_RESPONSIBLE + "/directors";
//      RESPONSIBLE_BUDGET_ADMINS = EMPLOYEES_RESPONSIBLE + "/budget-admins";
//      RESPONSIBLE_ECONOMIST = EMPLOYEES_RESPONSIBLE + "/economists";
      CATALOG_COMPANIES = CATALOG + "/structure/companies";
      CATALOG_COMPANIES_FINANCIAL_RESPONSIBILITY = CATALOG_COMPANIES + "/financial-responsibility";
      COMPANIES_FINANCIAL_RESPONSIBILITY_COST_CENTER = CATALOG_COMPANIES_FINANCIAL_RESPONSIBILITY + "/cost-center";
      CATALOG_PROGRAMS = CATALOG + "/structure/programs";
      CATALOG_PROJECTS = CATALOG + "/structure/projects";
      CATALOG_FIN_DOCS = CATALOG + "/financial-docs";
//      FIN_DOCS_CONTRACTORS = CATALOG_FIN_DOCS + "/contractors";
      FIN_DOCS_PAYMENT = CATALOG_FIN_DOCS + "/payment-docs";
//      FIN_DOCS_CONTRACTS = CATALOG_FIN_DOCS + "/contracts";
//      FIN_DOCS_COSTS = CATALOG_FIN_DOCS + "/costs";
   }
}
