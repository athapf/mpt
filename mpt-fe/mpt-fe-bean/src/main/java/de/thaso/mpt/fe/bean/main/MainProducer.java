package de.thaso.mpt.fe.bean.main;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * OverviewProducer
 *
 * @author thaler
 * @since 27.09.16
 */
public class MainProducer {

    private static final Logger LOG = LoggerFactory.getLogger(MainProducer.class);

    @Produces
    @RequestScoped
    @Named("mainModel")
    public MainModel produceOverviewModel(@New MainModel mainModel) {
        LOG.info("produce main model ...");
        mainModel.setSimpleMessage("Hello, i'm the controller!");
        return mainModel;
    }
}
