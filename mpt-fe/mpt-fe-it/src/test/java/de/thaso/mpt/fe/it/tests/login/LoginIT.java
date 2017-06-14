package de.thaso.mpt.fe.it.tests.login;

import de.thaso.mpt.fe.it.base.SeleniumTestBase;
import de.thaso.mpt.fe.it.pages.LoginPage;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * DailyPlannerOverviewIT
 *
 * @author thaler
 * @since 27.02.17
 */
public class LoginIT extends SeleniumTestBase {

    @Test
    public void testLoginPage_allElementsOnPage() {
        final LoginPage loginPage = startBrowser("page/login.xhtml", LoginPage.class);

        assertThat(loginPage.getNameInput().isVisible(), is(true));
        assertThat(loginPage.getPasswordInput().isVisible(), is(true));
        assertThat(loginPage.getLoginButton().isVisible(), is(true));
        assertThat(loginPage.getRegisterLink().isVisible(), is(true));
    }

    @Test
    public void testLoginPage_loginWithUnknownUser() {
        final LoginPage loginPage = startBrowser("page/login.xhtml", LoginPage.class);

        loginPage.getNameInput().setValue("unknown");
        loginPage.getPasswordInput().setValue("password");
        loginPage.getLoginButton().click();

        final LoginPage nextPage = loginPage.nextPage(LoginPage.class);
        assertThat(nextPage.getMessageLabel().isVisible(), is(true));
        assertThat(nextPage.getMessageLabel().getText(), is("Falsches Passwort"));
    }
}
