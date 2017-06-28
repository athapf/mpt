package de.thaso.mpt.fe.it.components;


import de.thaso.mpt.fe.it.base.BaseCO;

/**
 * ButtonCO
 *
 * @author thaler
 * @since 27.02.17
 */
public class InputCO extends BaseCO {

    public boolean isVisible() {
        return isPresent(getWebElement())&& getWebElement().isDisplayed();
    }

    public void setValue(final String value) {
        executeScript("arguments[0].value = arguments[1];", getWebElement(), value);
        triggerEvent("change");
        //waitForAjax();
    }

    public String getValue() {
        waitForElement();
        return getWebElement().getAttribute("value");
    }
}
