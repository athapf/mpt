package de.thaso.mpt.be.domain.business;

import de.thaso.mpt.be.domain.business.mapper.NickNameMapper;
import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.be.domain.service.NickNameService;
import de.thaso.mpt.db.api.NickNameDLI;
import de.thaso.mpt.db.api.NickNameEntity;
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
    private NickNameDLI nickNameDAO;

    @Inject
    private NickNameMapper nickNameMapper;

    @Override
    public void storeNickName(final NickNameData nickNameData) {
        final NickNameEntity nickNameEntity = nickNameMapper.nickNameToEntity(nickNameData);
        nickNameDAO.storeNickName(nickNameEntity);
    }

    @Override
    public List<NickNameData> findByName(final String name) {
        LOG.debug("findByName( {} ) ...", name);
        final List<NickNameEntity> lastNameList = nickNameDAO.findByName(name);
        final List<NickNameData> nickNameDataList = nickNameMapper.nickNameListToDOList(lastNameList);
        LOG.debug(" ... found {} messages", nickNameDataList.size());
        return nickNameDataList;
    }

    @Override
    public NickNameData findByNick(final String nick) {
        LOG.debug("findByNick( {} )", nick);
        final NickNameEntity nameEntityList = nickNameDAO.findByNick(nick);
        return nickNameMapper.nickNameToDO(nameEntityList);
    }
}
