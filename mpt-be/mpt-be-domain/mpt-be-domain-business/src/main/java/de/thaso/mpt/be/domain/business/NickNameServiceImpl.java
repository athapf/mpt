package de.thaso.mpt.be.domain.business;

import de.thaso.mpt.be.domain.business.mapper.NickNameMapper;
import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.be.domain.service.NickNameService;
import de.thaso.mpt.db.api.NickNameDLI;
import de.thaso.mpt.db.api.NickNameEntity;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.EJB;
import javax.ejb.Remote;
import javax.ejb.Stateless;
import javax.inject.Inject;
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
    private NickNameDLI nickNameDLI;

    @Inject
    private NickNameMapper nickNameMapper;

    @Override
    public NickNameData login(final String name, final String passwd) {
        final NickNameEntity nickNameEntity = nickNameDLI.findByNick(name);

        if (nickNameEntity == null
                || !(StringUtils.isBlank(nickNameEntity.getPassword())
                || StringUtils.equals(nickNameEntity.getPassword(), passwd))) {
            throw new RuntimeException("User or Password not valid!");
        }
        return nickNameMapper.nickNameToDO(nickNameEntity);
    }

    @Override
    public void register(final NickNameData nickNameData) {
        final NickNameEntity nickNameEntity = nickNameMapper.nickNameToEntity(nickNameData);
        nickNameEntity.setId(null);
        
        nickNameDLI.storeNickName(nickNameEntity);
    }

    @Override
    public void update(final NickNameData nickNameData) {
        final NickNameEntity originalNickName = nickNameDLI.loadNickNameById(nickNameData.getId());

        final NickNameEntity nickNameEntity = nickNameMapper.nickNameToEntity(nickNameData);
        nickNameEntity.setSince(originalNickName.getSince());
        
        nickNameDLI.storeNickName(nickNameEntity);
    }

    @Override
    public void storeNickName(final NickNameData nickNameData) {
        final NickNameEntity nickNameEntity = nickNameMapper.nickNameToEntity(nickNameData);
        nickNameDLI.storeNickName(nickNameEntity);
    }

    @Override
    public List<NickNameData> findByName(final String name) {
        LOG.debug("findByName( {} ) ...", name);
        final List<NickNameEntity> lastNameList = nickNameDLI.findByName(name);
        final List<NickNameData> nickNameDataList = nickNameMapper.nickNameListToDOList(lastNameList);
        LOG.debug(" ... found {} messages", nickNameDataList.size());
        return nickNameDataList;
    }

    @Override
    public NickNameData findByNick(final String nick) {
        LOG.debug("findByNick( {} )", nick);
        final NickNameEntity nameEntityList = nickNameDLI.findByNick(nick);
        return nickNameMapper.nickNameToDO(nameEntityList);
    }
}
