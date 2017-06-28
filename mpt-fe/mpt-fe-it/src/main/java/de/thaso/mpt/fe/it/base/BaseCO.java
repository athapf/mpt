package de.thaso.mpt.fe.it.base;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * BaseComponentObject
 *
 * @author thaler
 * @since 27.02.17
 */
public abstract class BaseCO {

    private static Logger LOG = LoggerFactory.getLogger(BaseCO.class);

    private WebElement webElement;
    private RemoteWebDriver webDriver;
    private String cssSelector;

    public void injectElement(final RemoteWebDriver webDriver, final WebElement webElement, final String cssSelector) {
        this.webDriver = webDriver;
        this.webElement = webElement;
        this.cssSelector = cssSelector;
    }

    protected WebElement getWebElement() {
        return webElement;
    }

    protected boolean isPresent(final WebElement proxiedElement) {
        if (proxiedElement == null || !isProxy(proxiedElement)) {
            return (proxiedElement != null);
        }

        try {
            // trigger search for proxied web elements
            proxiedElement.toString();
            return true;
        } catch (final NoSuchElementException nsee) {
            //do nothing
        } catch (StaleElementReferenceException e) {
            //do nothing
        }
        return false;
    }

    protected boolean isProxy(final WebElement webelement) {
        return webelement != null && webelement.getClass().getName().contains("Proxy");
    }

    protected boolean waitForElement() {
        //waitForAjax();

        try {
            final Boolean result = new WebDriverWait(webDriver, 1000)
                    .until(new ExpectedCondition<Boolean>() {
                        @Override
                        public Boolean apply(final WebDriver driver) {
                            try {
                                try {
                                    webElement.getAttribute("id");
                                } catch (StaleElementReferenceException se) {
                                    webElement = webDriver.findElementByCssSelector(cssSelector);
                                }
                            } catch (NoSuchElementException e) {
                                LOG.info("waitForElement failed : " + e.getMessage());
                                return false;
                            }
                            return true;
                        }
                    });
            return result;
        } catch (TimeoutException e) {
            LOG.info("wait for element failed: " + e.getMessage());
            return false;
        }
    }

    protected void doClick(final WebElement webElement) {
        waitForElement();
        webElement.click();
    }

    protected Object executeScript(final String script, final Object... args) {
        return webDriver.executeScript(script, args);
    }

    protected void triggerEvent(final String eventName) {
        executeScript("var event = document.createEvent('Event');"
                + "event.initEvent('" + eventName + "', true, true); "
                + "return arguments[0].dispatchEvent(event)", webElement);
    }

    protected void waitForAjax() {
        final Object obj = new Object();
        while ((boolean) executeScript("return ajaxindicator;")) {
            synchronized (obj) {
                try {
                    LOG.info("waitForAjax - another 50 ms");
                    obj.wait(50);
                } catch (InterruptedException e) {
                    LOG.info("wait for ajaxw failed: " + e.getMessage());
                }
            }
        }
    }
}
