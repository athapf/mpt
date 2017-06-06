package de.thaso.mpt.fe.bean.overview;

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
 * @since 2017-06-06
 */
public class OverviewProducer {
    private static final Logger LOG = LoggerFactory.getLogger(OverviewProducer.class);

    @Produces
    @RequestScoped
    @Named("overviewModel")
    public OverviewModel produceOverviewModel(@New OverviewModel overviewModel) {
        LOG.info("produce overview model ...");
        return overviewModel;
    }
}
