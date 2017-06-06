package de.thaso.mpt.fe.bean.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

/**
 * LoginProducer
 *
 * @author thaler
 * @since 2017-06-06
 */
public class LoginProducer {
    private static final Logger LOG = LoggerFactory.getLogger(LoginProducer.class);

    @Produces
    @RequestScoped
    @Named("loginModel")
    public LoginModel produceOverviewModel(@New LoginModel loginModel) {
        LOG.info("produce login model ...");
        return loginModel;
    }
}
