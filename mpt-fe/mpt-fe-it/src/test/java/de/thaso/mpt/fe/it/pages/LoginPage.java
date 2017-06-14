package de.thaso.mpt.fe.it.pages;

import de.thaso.mpt.fe.it.components.ButtonCO;
import de.thaso.mpt.fe.it.components.InputCO;
import de.thaso.mpt.fe.it.components.LabelCO;
import de.thaso.mpt.fe.it.components.LinkCO;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * OverviewPage
 *
 * @author thaler
 * @since 27.02.17
 */
public class LoginPage extends StandardPage {

    @FindBy(how = How.CSS, css = "*[id$='nameInput']")
    private InputCO nameInput;

    @FindBy(how = How.CSS, css = "*[id$='passwordInput']")
    private InputCO passwordInput;

    @FindBy(how = How.CSS, css = "*[id$='loginButton']")
    private ButtonCO loginButton;

    @FindBy(how = How.CSS, css = "*[id$='registerLink']")
    private LinkCO registerLink;

    @FindBy(how = How.CSS, css = "*[id$='message']")
    private LabelCO messageLabel;

    @Override
    public String getPageId() {
        return "entryPage";
    }

    public InputCO getNameInput() {
        return nameInput;
    }

    public InputCO getPasswordInput() {
        return passwordInput;
    }

    public ButtonCO getLoginButton() {
        return loginButton;
    }

    public LinkCO getRegisterLink() {
        return registerLink;
    }

    public LabelCO getMessageLabel() {
        return messageLabel;
    }
}
