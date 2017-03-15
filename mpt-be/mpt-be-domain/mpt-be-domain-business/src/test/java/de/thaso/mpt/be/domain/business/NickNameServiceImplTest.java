package de.thaso.mpt.be.domain.business;

import de.thaso.mpt.be.domain.business.mapper.NickNameMapper;
import de.thaso.mpt.be.domain.service.NickNameData;
import de.thaso.mpt.db.store.NickNameDAO;
import de.thaso.mpt.db.store.NickNameEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * NickNameServiceImplTest
 *
 * @author thaler
 * @since 22.09.16
 */
public class NickNameServiceImplTest {

    @InjectMocks
    private NickNameServiceImpl underTest;

    @Mock
    private NickNameDAO nickNameDAO;

    @Mock
    private NickNameMapper nickNameMapper;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testName() {
        // given
        final NickNameData nickNameData = new NickNameData();
        final NickNameEntity nickNameEntity = new NickNameEntity();
        when(nickNameMapper.nickNameToEntity(nickNameData)).thenReturn(nickNameEntity);
        // when
        underTest.storeNickName(nickNameData);
        // then
        verify(nickNameDAO).storeNickName(nickNameEntity);
    }

    @Test
    public void testFindByName() {
        // given
        final String name = "Hugo";
        final List<NickNameEntity> nickNameEntityList = new ArrayList<>();
        when(nickNameDAO.findByName(name)).thenReturn(nickNameEntityList);
        final List<NickNameData> nickNameDataList = new ArrayList<>();
        when(nickNameMapper.nickNameListToDOList(nickNameEntityList)).thenReturn(nickNameDataList);
        // when
        final List<NickNameData> result = underTest.findByName(name);
        // then
        assertThat(result, is(nickNameDataList));
    }
}
