package de.thaso.mpt.db.store;

import de.thaso.mpt.db.store.utils.DatabaseExceptionCodeMatcher;
import de.thaso.mpt.db.common.DatabaseError;
import de.thaso.mpt.db.common.DatabaseException;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.nullValue;
import static org.hamcrest.core.StringContains.containsString;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * NickNameDAOTest
 *
 * @author thaler
 * @since 13.09.16
 */
public class NickNameDAOTest {

    public static final DatabaseExceptionCodeMatcher EXCEPTION_MATCHER_ENTITY_NOT_FOUND
            = new DatabaseExceptionCodeMatcher(DatabaseError.ENTITY_NOT_FOUND);

    @InjectMocks
    private NickNameDAO underTest;

    @Mock
    private EntityManager entityManager;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    private Long primaryKey;
    private NickNameEntity nickNameEntity;

    @Before
    public void setUp() {
        initMocks(this);

        primaryKey = 1L;
        nickNameEntity = new NickNameEntity();
        when(entityManager.find(NickNameEntity.class, primaryKey)).thenReturn(nickNameEntity);
    }

    @Test
    public void storeNickName() {
        // when
        final NickNameEntity result = underTest.storeNickName(nickNameEntity);
        // then
        verify(entityManager).persist(nickNameEntity);
        assertThat(result, is(nickNameEntity));
    }

    @Test
    public void findNickName() {
        // when
        final NickNameEntity result = underTest.findNickNameById(primaryKey);
        // then
        assertThat(result, is(nickNameEntity));
    }

    @Test
    public void findNickName_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(NickNameEntity.class, primaryKey)).thenReturn(null);
        // when
        final NickNameEntity result = underTest.findNickNameById(primaryKey);
        // then
        assertThat(result, is(nullValue()));
    }

    @Test
    public void loadNickName() {
        // when
        final NickNameEntity result = underTest.loadNickNameById(primaryKey);
        // then
        assertThat(result, is(nickNameEntity));
    }

    @Test
    public void loadNickName_whenPrimaryKeyNotFound() {
        // given
        when(entityManager.find(NickNameEntity.class, primaryKey)).thenReturn(null);
        exception.expect(DatabaseException.class);
        exception.expectMessage(containsString(" " + primaryKey.toString() + " "));
        exception.expect(EXCEPTION_MATCHER_ENTITY_NOT_FOUND);
        // when
        final NickNameEntity result = underTest.loadNickNameById(primaryKey);
    }

    @Test
    public void findByName() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(NickNameEntity.FIND_BY_NAMES,NickNameEntity.class)).thenReturn(query);
        final List<NickNameEntity> messageEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(messageEntityList);
        // when
        final List<NickNameEntity> result = underTest.findByName("Hugo");
        // then
        assertThat(result,is(messageEntityList));
        verify(query).setParameter("name","Hugo");
        verify(query).setMaxResults(10);
    }

    @Test
    public void findByNickName() {
        // given
        final TypedQuery query = mock(TypedQuery.class);
        when(entityManager.createNamedQuery(NickNameEntity.FIND_BY_NICK_NAME,NickNameEntity.class)).thenReturn(query);
        final List<NickNameEntity> messageEntityList = new ArrayList<>();
        when(query.getResultList()).thenReturn(messageEntityList);
        // when
        final List<NickNameEntity> result = underTest.findByNickName("Hugo", "Big H");
        // then
        assertThat(result,is(messageEntityList));
        verify(query).setParameter("name","Hugo");
        verify(query).setParameter("nick","Big H");
    }
}