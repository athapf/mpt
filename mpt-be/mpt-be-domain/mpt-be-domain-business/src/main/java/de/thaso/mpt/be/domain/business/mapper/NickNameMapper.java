package de.thaso.mpt.be.domain.business.mapper;

import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.db.api.NickNameEntity;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * NickNameMapper
 *
 * @author thaler
 * @since 22.09.16
 */
@Mapper
public interface NickNameMapper {

    NickNameData nickNameToDO(NickNameEntity nickNameEntity);

    NickNameEntity nickNameToEntity(NickNameData nickNameData);

    List<NickNameData> nickNameListToDOList(List<NickNameEntity> nickNameEntityList);
}
