package de.thaso.mpt.db.api;

import de.thaso.mpt.db.common.DatabaseException;

import java.util.List;

/**
 * NickNameDAO
 *
 * @author thaler
 * @since 13.09.16
 */
public interface NickNameDS {

    NickNameEntity storeNickName(NickNameEntity nickNameEntity);

    NickNameEntity updateNickName(NickNameEntity nickNameEntity);

    NickNameEntity findNickNameById(Long id);

    NickNameEntity loadNickNameById(Long id);

    List<NickNameEntity> findByName(String name);

    NickNameEntity findByNick(String nick);
}
