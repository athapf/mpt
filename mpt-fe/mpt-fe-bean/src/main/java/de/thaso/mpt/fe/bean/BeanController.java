package de.thaso.mpt.fe.bean;

import de.thaso.mpt.fe.bean.model.OverviewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * BeanController
 *
 * @author thaler
 * @since 23.09.16
 */
@Named
@RequestScoped
public class BeanController {

    private static final Logger LOG = LoggerFactory.getLogger(BeanController.class);

    @Inject
    private OverviewModel overviewModel;

    public void doSomething() {
        LOG.info("do something ...");
    }
}
