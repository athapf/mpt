package de.thaso.mpt.be.domain.service;

import java.util.List;

/**
 * NickNameService
 *
 * @author thaler
 * @since 21.09.16
 */
public interface NickNameService {

    void storeNickName(NickNameData nickNameData);

    List<NickNameData> findByName(String name);

    List<NickNameData> findByNickName(String name, String nick);
}
