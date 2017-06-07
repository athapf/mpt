package de.thaso.mpt.fe.bean.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
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

    public String save() {
        LOG.info("login");
        return "login";
    }

    public String cancel() {
        LOG.info("cancel");
        return "login";
    }
}
