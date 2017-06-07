package de.thaso.mpt.fe.bean.overview;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

/**
 * OverviewController
 *
 * @author thaler
 * @since 2017-06-06
 */
@Named
@RequestScoped
public class OverviewController {

    private static final Logger LOG = LoggerFactory.getLogger(OverviewController.class);

    public String changePassword() {
        LOG.info("changePassword");
        return "register";
    }
}
