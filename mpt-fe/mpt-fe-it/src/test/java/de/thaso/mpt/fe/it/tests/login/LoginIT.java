package de.thaso.mpt.fe.it.tests.login;

import de.thaso.mpt.fe.it.base.SeleniumTestBase;
import de.thaso.mpt.fe.it.pages.LoginPage;
import de.thaso.mpt.fe.it.pages.OverviewPage;
import de.thaso.mpt.fe.it.pages.RegisterPage;
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

    private static final String EMPTY = "";

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
        assertThat(nextPage.getNameInput().getValue(), is("unknown"));
        assertThat(nextPage.getPasswordInput().getValue(), is(EMPTY));
    }

    @Test
    public void testLoginPage_loginWithValidUser() {
        updateDatabase();
        final LoginPage loginPage = startBrowser("page/login.xhtml", LoginPage.class);

        loginPage.getNameInput().setValue("Name");
        loginPage.getPasswordInput().setValue("geheim");
        loginPage.getLoginButton().click();

        final OverviewPage overviewPage = loginPage.nextPage(OverviewPage.class);
        assertThat(overviewPage.getNameText().getText(), is("Full Name"));
        assertThat(overviewPage.getProfilLink().isVisible(), is(true));
    }

    @Test
    public void testLoginPage_goToRegisterPage() {
        final LoginPage loginPage = startBrowser("page/login.xhtml", LoginPage.class);

        loginPage.getRegisterLink().click();

        final RegisterPage registerPage = loginPage.nextPage(RegisterPage.class);
        assertThat(registerPage.getPageId(), is("registerPage"));
    }
}
