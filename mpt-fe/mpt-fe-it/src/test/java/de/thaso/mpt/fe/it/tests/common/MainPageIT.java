package de.thaso.mpt.fe.it.tests.common;

import de.thaso.mpt.fe.it.base.SeleniumTestBase;
import org.junit.Ignore;
import org.junit.Test;

/**
 * OverviewPageTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class MainPageIT extends SeleniumTestBase {

    @Test
    public void testMainPage() {
        updateDatabase("testMainPage.xml");
        //final LoginPage loginPage = startBrowser("main.xhtml", .class);
        getDriver().get(getAppUrl() + "/main.xhtml");
        getDriver().quit();
    }
}
