package base.common;

import common.CommonActions;
import constants.Constant;
import io.qameta.allure.Step;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;
import pages.PageURL;
import utils.Log;

import java.io.IOException;

import static constants.Constant.TEST_RUN_ID;
import static pages.PageURL.refreshURLs;
import static utils.AllureTools.*;
import static utils.Log.getTestFinalLog;

@Test(priority = 0)
public class CommonTest {
    @BeforeSuite
    @Step("До всего")
    public void beforeSuite() {
        PageURL.HOME = "http://uitestingplayground.com/";
        refreshURLs();
        Constant.setRunID();
        System.out.println();
        System.out.println("---- Before suite " + TEST_RUN_ID);
        System.out.println();
        CommonActions.setCurrentPlatform();
        configureAllureResultPath();
        deleteResultDir();
        createResultDir();
        deleteTemptDir();
        createTemptDir();
    }

    @AfterSuite
    @Step("После всего")
    public void afterSuite() throws IOException {
        Log.textLevelOFF();
        System.out.println(getTestFinalLog());

        System.out.println("-After suite " + TEST_RUN_ID);
//      CommonActions.clearCookie();
        CommonActions.closeAllDrivers();
        createHTML();
        System.out.println();
        System.out.println("---- End suite");
        System.out.println();

    }
}
