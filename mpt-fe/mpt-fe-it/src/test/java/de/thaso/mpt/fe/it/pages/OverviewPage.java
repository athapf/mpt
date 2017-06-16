package de.thaso.mpt.fe.it.pages;

import de.thaso.mpt.fe.it.components.LabelCO;
import de.thaso.mpt.fe.it.components.LinkCO;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

/**
 * OverviewPage
 *
 * @author thaler
 * @since 2017-06-16
 */
public class OverviewPage extends StandardPage {

    @FindBy(how = How.CSS, css = "*[id$='nameText']")
    private LabelCO nameText;

    @FindBy(how = How.CSS, css = "*[id$='profilLink']")
    private LinkCO profilLink;

    @Override
    public String getPageId() {
        return "overviewPage";
    }

    public LabelCO getNameText() {
        return nameText;
    }

    public LinkCO getProfilLink() {
        return profilLink;
    }
}
