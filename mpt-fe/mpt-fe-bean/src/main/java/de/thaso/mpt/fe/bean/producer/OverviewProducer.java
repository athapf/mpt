package de.thaso.mpt.fe.bean.producer;

import de.thaso.mpt.fe.bean.model.OverviewModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
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
public class OverviewProducer {

    private static final Logger LOG = LoggerFactory.getLogger(OverviewProducer.class);

    @Produces
    @RequestScoped
    @Named("overviewModel")
    public OverviewModel produceOverviewModel(@New OverviewModel overviewModel) {
        LOG.info("produce overview model ...");
        overviewModel.setSimpleMessage("Hello, i'm the controller!");
        return overviewModel;
    }
}
