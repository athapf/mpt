package de.thaso.mpt.fe.it.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;

/**
 * BasePageObject
 *
 * @author thaler
 * @since 27.02.17
 */
public abstract class BasePO {

    private static Logger LOG = LoggerFactory.getLogger(BasePO.class);

    private RemoteWebDriver webDriver;

    public static <T extends BasePO> T nextPage(final RemoteWebDriver webDriver, Class<T> pageClass) {
        try {
            final T page = pageClass.newInstance();
            final Field driverField = BasePO.class.getDeclaredField("webDriver");
            driverField.setAccessible(true);
            driverField.set(page, webDriver);

            page.waitForPage();

            for (final Field field : pageClass.getDeclaredFields()) {
                final FindBy findBy = field.getAnnotation(FindBy.class);
                if (findBy != null) {
                    final WebElement webElement = webDriver.findElementByCssSelector(findBy.css());

                    final BaseCO instance = (BaseCO)field.getType().newInstance();
                    instance.injectElement(webDriver, webElement, findBy.css());

                    field.setAccessible(true);
                    field.set(page, instance);
                }
            }
            return page;

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        return null;
    }

    public abstract boolean isCurrentPage();

    public RemoteWebDriver getWebDriver() {
        return webDriver;
    }

    public <T extends BasePO> T nextPage(Class<T> pageClass) {
        return nextPage(webDriver, pageClass);
    }

    public boolean waitForPage() {
        // waitForAjax();

        final ExpectedCondition<Boolean> waitForPageCondition = new ExpectedCondition<Boolean>() {

            @Override
            public Boolean apply(final WebDriver webDriver) {
                return isCurrentPage();
            }
        };

        try {
            new WebDriverWait(webDriver, 5).until(waitForPageCondition);
        } catch (Exception e) {
            return false;
        }
        return isCurrentPage();
    }
}
