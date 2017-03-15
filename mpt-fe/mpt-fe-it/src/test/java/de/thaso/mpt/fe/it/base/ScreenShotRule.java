package de.thaso.mpt.fe.it.base;

import org.apache.commons.io.FileUtils;
import org.junit.rules.TestWatcher;
import org.junit.runner.Description;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;

/**
 * ScreenShotRule
 *
 * @author thaler
 * @since 26.09.16
 */
public class ScreenShotRule extends TestWatcher {
    private WebDriver browser;

    public ScreenShotRule() {
        browser = null;
    }

    @Override
    protected void failed(Throwable e, Description description) {
        TakesScreenshot takesScreenshot = (TakesScreenshot) browser;

        File scrFile = takesScreenshot.getScreenshotAs(OutputType.FILE);
        File destFile = getDestinationFile(description.getClassName() + "#" + description.getMethodName());
        try {
            FileUtils.copyFile(scrFile, destFile);
        } catch (IOException ioe) {
            throw new RuntimeException(ioe);
        }
    }

    private File getDestinationFile(final String name) {
        String absoluteFileName = "./target/screenshots" + "/" + name + ".png";

        return new File(absoluteFileName);
    }

    public void setBrowser(final WebDriver browser) {
        this.browser = browser;
    }
}
