package de.thaso.mpt.fe.bean.overview;

import de.thaso.mpt.fe.bean.common.ContextInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * OverviewProducer
 *
 * @author thaler
 * @since 2017-06-06
 */
public class OverviewProducer {
    private static final Logger LOG = LoggerFactory.getLogger(OverviewProducer.class);

    @Inject
    private ContextInfo contextInfo;

    @Produces
    @RequestScoped
    @Named("overviewModel")
    public OverviewModel produceOverviewModel(@New OverviewModel overviewModel) {
        LOG.info("produce overview model ...");
        overviewModel.setName(contextInfo.getUser().getName());
        return overviewModel;
    }
}
