package de.thaso.mpt.be.domain.service;

import java.util.List;

/**
 * NickNameService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface NickNameService {

    NickNameData login(String name, String passwd);

    void register(NickNameData nickNameData);

    void update(NickNameData nickNameData);

    @Deprecated
    void storeNickName(NickNameData nickNameData);

    @Deprecated
    List<NickNameData> findByName(String name);

    @Deprecated
    NickNameData findByNick(String nick);
}
