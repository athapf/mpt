package de.thaso.mpt.fe.bean.login;

import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.be.domain.service.NickNameService;
import de.thaso.mpt.fe.bean.common.ContextInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * LoginController
 *
 * @author thaler
 * @since 2017-06-06
 */
@Named
@RequestScoped
public class LoginController {

    private static final Logger LOG = LoggerFactory.getLogger(LoginController.class);

    @EJB
    private NickNameService nickNameService;

    @Inject
    private LoginModel loginModel;

    @Inject
    private ContextInfo contextInfo;
    
    public String login() {
        LOG.info("login");

        final NickNameData nickNameData = nickNameService.login(loginModel.getName(), loginModel.getPassword());

        contextInfo.setUser(nickNameData);
        
        if (contextInfo.getUser() != null) {
            return "overview";
        }

        loginModel.setPassword(StringUtils.EMPTY);
        return "login";
    }

    public String register() {
        LOG.info("register");
        return "register.xhtml";
    }
}
