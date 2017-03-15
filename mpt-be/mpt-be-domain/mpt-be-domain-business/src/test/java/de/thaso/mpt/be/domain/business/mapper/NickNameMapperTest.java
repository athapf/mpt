package de.thaso.mpt.be.domain.business.mapper;

import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.db.store.NickNameEntity;
import org.junit.Before;
import org.junit.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * NickNameMapperTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class NickNameMapperTest {

    @InjectMocks
    private NickNameMapper underTest = Mappers.getMapper(NickNameMapper.class);

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void convertToEntity_WhenDOIsNull() {
        // when
        final NickNameEntity result = underTest.nickNameToEntity(null);
        // then
        assertThat(result,is(nullValue()));
    }

    @Test
    public void convertListToDO() {
        // given
        final ArrayList<NickNameEntity> nickNameEntityList = new ArrayList<>();
        final NickNameEntity firstMessageEntity = new NickNameEntity();
        firstMessageEntity.setId(743L);
        firstMessageEntity.setName("Hello");
        nickNameEntityList.add(firstMessageEntity);
        final NickNameEntity secondMessageEntity = new NickNameEntity();
        secondMessageEntity.setId(234L);
        secondMessageEntity.setName("World");
        nickNameEntityList.add(secondMessageEntity);
        // when
        final List<NickNameData> nickNameDataList = underTest.nickNameListToDOList(nickNameEntityList);
        // then
        assertThat(nickNameDataList.size(),is(nickNameEntityList.size()));

        NickNameData firstNickNameData = nickNameDataList.get(0);
        assertThat(firstNickNameData.getId(),is(firstMessageEntity.getId()));
        assertThat(firstNickNameData.getName(),is(firstMessageEntity.getName()));
        NickNameData secondNickNameData = nickNameDataList.get(1);
        assertThat(secondNickNameData.getId(),is(secondMessageEntity.getId()));
        assertThat(secondNickNameData.getName(),is(secondMessageEntity.getName()));
    }
}
