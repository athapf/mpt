package de.thaso.mpt.fe.it.pages;

import de.thaso.mpt.fe.it.components.ButtonCO;
import de.thaso.mpt.fe.it.components.InputCO;
import de.thaso.mpt.fe.it.components.LabelCO;
import de.thaso.mpt.fe.it.components.LinkCO;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * RegisterPage
 *
 * @author thaler
 * @since 2017-06-16
 */
public class RegisterPage extends StandardPage {

    @FindBy(how = How.CSS, css = "*[id$='nameInput']")
    private InputCO nameInput;

    @FindBy(how = How.CSS, css = "*[id$='nickInput']")
    private InputCO nickInput;

    @FindBy(how = How.CSS, css = "*[id$='passwordInput']")
    private InputCO passwordInput;

    @FindBy(how = How.CSS, css = "*[id$='verifyInput']")
    private InputCO verifyInput;

    @FindBy(how = How.CSS, css = "*[id$='saveButton']")
    private ButtonCO saveButton;

    @FindBy(how = How.CSS, css = "*[id$='cancelButton']")
    private ButtonCO cancelButton;

    @FindBy(how = How.CSS, css = "*[id$='message']")
    private LabelCO messageLabel;

    @Override
    public String getPageId() {
        return "registerPage";
    }

    public InputCO getNameInput() {
        return nameInput;
    }

    public InputCO getNickInput() {
        return nickInput;
    }

    public InputCO getPasswordInput() {
        return passwordInput;
    }

    public InputCO getVerifyInput() {
        return verifyInput;
    }

    public ButtonCO getSaveButton() {
        return saveButton;
    }

    public ButtonCO getCancelButton() {
        return cancelButton;
    }

    public LabelCO getMessageLabel() {
        return messageLabel;
    }
}
