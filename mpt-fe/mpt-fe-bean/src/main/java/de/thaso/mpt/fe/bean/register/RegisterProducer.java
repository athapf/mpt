package de.thaso.mpt.fe.bean.register;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * RegisterProducer
 *
 * @author thaler
 * @since 2017-06-06
 */
public class RegisterProducer {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterProducer.class);

    @Produces
    @RequestScoped
    @Named("registerModel")
    public RegisterModel produceRegisterModel(@New RegisterModel registerModel) {
        LOG.info("produce register model ...");
        return registerModel;
    }
}
