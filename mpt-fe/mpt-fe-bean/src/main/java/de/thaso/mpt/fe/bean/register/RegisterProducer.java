package de.thaso.mpt.fe.bean.register;

import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.fe.bean.common.ContextInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.New;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * RegisterProducer
 *
 * @author thaler
 * @since 2017-06-06
 */
public class RegisterProducer {
    private static final Logger LOG = LoggerFactory.getLogger(RegisterProducer.class);

    @Inject
    private ContextInfo contextInfo;

    @Produces
    @RequestScoped
    @Named("registerModel")
    public RegisterModel produceRegisterModel(@New RegisterModel registerModel) {
        LOG.info("produce register model ...");
        if (contextInfo != null && contextInfo.getUser() != null) {
            final NickNameData user = contextInfo.getUser();
            registerModel.setName(user.getName());
            registerModel.setNick(user.getNick());
            registerModel.setUpdateOnly(true);
        }
        return registerModel;
    }
}
