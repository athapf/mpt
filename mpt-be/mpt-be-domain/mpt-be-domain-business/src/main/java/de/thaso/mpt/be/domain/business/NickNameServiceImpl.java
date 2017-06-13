package de.thaso.mpt.be.domain.business;

import de.thaso.mpt.be.domain.business.mapper.NickNameMapper;
import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.be.domain.service.NickNameService;
import de.thaso.mpt.db.api.NickNameDS;
import de.thaso.mpt.db.api.NickNameEntity;
import de.thaso.mpt.db.common.DatabaseException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.Date;
import java.util.List;

/**
 * NickNameImpl
 *
 * @author thaler
 * @since 21.09.16
 */
@Stateless
@Remote(NickNameService.class)
public class NickNameServiceImpl implements NickNameService {

    public static final Logger LOG = LoggerFactory.getLogger(NickNameServiceImpl.class);

    @EJB
    private NickNameDS nickNameDS;

    @Inject
    private NickNameMapper nickNameMapper;

    @Override
    public NickNameData login(final String name, final String passwd) {
        final NickNameEntity nickNameEntity = nickNameDS.findByNick(name);

        if (!isPasswordValid(passwd, nickNameEntity)) {
            throw new RuntimeException("User or Password not valid!");
        }
        return nickNameMapper.nickNameToDO(nickNameEntity);
    }

    boolean isPasswordValid(final String passwd, final NickNameEntity nickNameEntity) {
        return nickNameEntity != null
                && ((StringUtils.isBlank(nickNameEntity.getPassword()) && StringUtils.isBlank(passwd))
                || StringUtils.equals(nickNameEntity.getPassword(), passwd));
    }

    @Override
    public void register(final NickNameData nickNameData) {
        final NickNameEntity nickNameEntity = nickNameMapper.nickNameToEntity(nickNameData);
        nickNameEntity.setId(null);
        nickNameEntity.setSince(new Date());
        
        nickNameDS.storeNickName(nickNameEntity);
    }

    @Override
    public void update(final NickNameData nickNameData) throws DatabaseException {
        final NickNameEntity originalNickName = nickNameDS.loadNickNameById(nickNameData.getId());

        final NickNameEntity nickNameEntity = nickNameMapper.nickNameToEntity(nickNameData);
        nickNameEntity.setSince(originalNickName.getSince());
        
        nickNameDS.updateNickName(nickNameEntity);
    }

    @Override
    public void storeNickName(final NickNameData nickNameData) {
        final NickNameEntity nickNameEntity = nickNameMapper.nickNameToEntity(nickNameData);
        nickNameDS.storeNickName(nickNameEntity);
    }

    @Override
    public List<NickNameData> findByName(final String name) {
        LOG.debug("findByName( {} ) ...", name);
        final List<NickNameEntity> lastNameList = nickNameDS.findByName(name);
        final List<NickNameData> nickNameDataList = nickNameMapper.nickNameListToDOList(lastNameList);
        LOG.debug(" ... found {} messages", nickNameDataList.size());
        return nickNameDataList;
    }

    @Override
    public NickNameData findByNick(final String nick) {
        LOG.debug("findByNick( {} )", nick);
        final NickNameEntity nameEntityList = nickNameDS.findByNick(nick);
        return nickNameMapper.nickNameToDO(nameEntityList);
    }
}
