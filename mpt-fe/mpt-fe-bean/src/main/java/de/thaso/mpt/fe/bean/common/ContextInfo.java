package de.thaso.mpt.fe.bean.common;

import de.thaso.mpt.be.domain.service.NickNameData;

import javax.enterprise.context.SessionScoped;
import java.io.Serializable;

/**
 * ContextModel
 *
 * @author thaler
 * @since 2017-06-07
 */
@SessionScoped
public class ContextInfo implements Serializable {

    private static final long serialVersionUID = 4442432023153746905L;

    private NickNameData user;
    private String backUrl;

    public NickNameData getUser() {
        return user;
    }

    public void setUser(final NickNameData user) {
        this.user = user;
    }

    public String getBackUrl() {
        return backUrl;
    }

    public void setBackUrl(final String backUrl) {
        this.backUrl = backUrl;
    }
}
