package de.thaso.mpt.fe.bean.register;

import de.thaso.mpt.fe.bean.common.ContextInfo;
import de.thaso.mpt.fe.bean.navigation.MaskEnum;
import de.thaso.mpt.fe.bean.navigation.TargetBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * RegisterController
 *
 * @author thaler
 * @since 2017-06-06
 */
@Named
@RequestScoped
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @Inject
    private ContextInfo contextInfo;

    public String save() {
        LOG.info("login");
        return "login";
    }

    public String cancel() {
        LOG.info("cancel");
        return TargetBuilder.create(MaskEnum.BACK).build();
    }
}
