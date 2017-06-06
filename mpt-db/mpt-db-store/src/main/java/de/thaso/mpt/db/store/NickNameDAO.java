package de.thaso.mpt.db.store;

import de.thaso.mpt.db.api.INickName;
import de.thaso.mpt.db.api.NickNameEntity;
import de.thaso.mpt.db.common.DatabaseError;
import de.thaso.mpt.db.common.DatabaseException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * NickNameDAO
 *
 * @author thaler
 * @since 13.09.16
 */
@Stateless
@Local(INickName.class)
public class NickNameDAO implements INickName {

    private final static Logger LOG = LoggerFactory.getLogger(NickNameDAO.class);

    @Inject
    private EntityManager entityManager;

    public NickNameEntity storeNickName(final NickNameEntity nickNameEntity) {
        LOG.info("storeNickName with id {} in name {}", nickNameEntity.getId(), nickNameEntity.getName());

        entityManager.persist(nickNameEntity);
        return nickNameEntity;
    }

    public NickNameEntity findNickNameById(final Long id) {
        LOG.info("findNickNameById {}", id);

        final NickNameEntity nickNameEntity = entityManager.find(NickNameEntity.class, id);
        return nickNameEntity;
    }

    public NickNameEntity loadNickNameById(final Long id) throws DatabaseException {
        LOG.info("loadNickNameById {}", id);
        
        final NickNameEntity nickNameEntity = entityManager.find(NickNameEntity.class, id);
        if(nickNameEntity == null) {
            throw new DatabaseException(DatabaseError.ENTITY_NOT_FOUND, "NickName with id " + id + " not found!");
        }
        return nickNameEntity;
    }

    public List<NickNameEntity> findByName(final String name) {
        LOG.info("findByName( {} )", name);

        final TypedQuery<NickNameEntity> query
                = entityManager.createNamedQuery(NickNameEntity.FIND_BY_NAMES, NickNameEntity.class);
        query.setParameter("name", name);
        query.setMaxResults(10);
        return query.getResultList();
    }

    public List<NickNameEntity> findByNickName(final String name, final String nick) {
        LOG.info("findByNickName( {}, {} )", name, nick);

        final TypedQuery<NickNameEntity> query
                = entityManager.createNamedQuery(NickNameEntity.FIND_BY_NICK_NAME, NickNameEntity.class);
        query.setParameter("name", name);
        query.setParameter("nick", nick);
        return query.getResultList();
    }
}
