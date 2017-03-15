package de.thaso.mpt.fe.it.base;

import de.thaso.mpt.fe.it.glassfish.GlassfishEmbeddedServer;
import org.apache.commons.lang3.StringUtils;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.openqa.selenium.firefox.FirefoxBinary;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.io.File;

/**
 * SeleniumTest
 *
 * @author thaler
 * @since 26.09.16
 */
public class SeleniumTestBase extends DbUnitTestBase {

    private static ApplicationServerBase appServer = new GlassfishEmbeddedServer();
    private FirefoxDriver driver;

    @BeforeClass
    public static void initEmbeddedServer() throws Exception {
        appServer.startEmbeddedServer();
    }

    @AfterClass
    public static void closeEmbeddedServer() throws Exception {
        appServer.stopEmbeddedServer();
    }

    @Rule
    public ScreenShotRule screenShotRule = new ScreenShotRule();

    @Before
    public void setUpSeleniumDriver() {
        final FirefoxBinary binary = new FirefoxBinary(new File(getProperties().getProperty("firefox.executable")));

        final String display = getProperties().getProperty("browser.display");
        if (StringUtils.isNotEmpty(display)) {
            binary.setEnvironmentProperty("DISPLAY", display);
        }
        driver = new FirefoxDriver(binary, null);

        screenShotRule.setBrowser(driver);
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().pageLoadTimeout(5, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(5, TimeUnit.SECONDS);
    }

    @After
    public void tearDownSeleniumDriver() {
        driver.close();
    }

    protected FirefoxDriver getDriver() {
        return driver;
    }

    public String getAppUrl() {
        return appServer.getApplicationUrl();
    }
}
