package de.thaso.mpt.fe.bean.navigation;

/**
 * MaskEnum
 *
 * @author thaler
 * @since 2017-06-09
 */
public enum MaskEnum {
    BACK(TargetBuilder.BACK),
    LOGIN("/page/login.xhtml"),
    REGISTER("/page/register.xhtml"),
    OVERVIEW("/page/overview.xhtml");

    private String page;

    MaskEnum(final String page) {
        this.page = page;
    }

    public String value() {
        return page;
    }
}
