package de.thaso.mpt.fe.bean.register;

import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.be.domain.service.NickNameService;
import de.thaso.mpt.db.api.NickNameDLI;
import de.thaso.mpt.fe.bean.common.ContextInfo;
import de.thaso.mpt.fe.bean.message.MessageModel;
import de.thaso.mpt.fe.bean.navigation.MaskEnum;
import de.thaso.mpt.fe.bean.navigation.TargetBuilder;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * RegisterController
 *
 * @author thaler
 * @since 2017-06-06
 */
@Named
@RequestScoped
public class RegisterController {

    private static final Logger LOG = LoggerFactory.getLogger(RegisterController.class);

    @EJB
    private NickNameService nickNameService;

    @Inject
    private RegisterModel registerModel;

    @Inject
    private ContextInfo contextInfo;

    @Inject
    private MessageModel messageModel;

    public String save() {
        LOG.info("save");

        final NickNameData nickNameData = createNickNameData();

        if (validateInputData()) {
            messageModel.setText("Password nicht identisch");
            return null;
        }

        registerOrUpdate(nickNameData);
        return TargetBuilder.create(MaskEnum.LOGIN).withRedirect().build();
    }

    private NickNameData createNickNameData() {
        final NickNameData nickNameData;
        if (registerModel.isUpdateOnly()) {
            nickNameData = contextInfo.getUser();
        } else {
            nickNameData = new NickNameData();
            nickNameData.setNick(registerModel.getNick());
        }
        nickNameData.setName(registerModel.getName());
        nickNameData.setPassword(registerModel.getPassword());
        return nickNameData;
    }

    boolean validateInputData() {
        return !StringUtils.equals(registerModel.getPassword(), registerModel.getVerify());
    }

    void registerOrUpdate(final NickNameData nickNameData) {
        if(registerModel.isUpdateOnly()) {
            nickNameService.update(nickNameData);
        } else {
            nickNameService.register(nickNameData);
        }
    }

    public String cancel() {
        LOG.info("cancel");
        return TargetBuilder.create(MaskEnum.BACK).withRedirect().build();
    }
}
