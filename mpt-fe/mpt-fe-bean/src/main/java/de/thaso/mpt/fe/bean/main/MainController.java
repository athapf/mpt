package de.thaso.mpt.fe.bean.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * MainController
 *
 * @author thaler
 * @since 23.09.16
 */
@Named
@RequestScoped
public class MainController {

    private static final Logger LOG = LoggerFactory.getLogger(MainController.class);

    @Inject
    private MainModel mainModel;

    public void doSomething() {
        LOG.info("do something ...");
    }
}
