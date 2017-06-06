package de.thaso.mpt.db.it;

import de.thaso.mpt.db.api.NickNameEntity;
import de.thaso.mpt.db.it.base.DbTestBase;
import de.thaso.mpt.db.it.utils.SecondCauseMatcher;
import de.thaso.mpt.db.store.NickNameDAO;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.postgresql.util.PSQLException;

import javax.persistence.Query;
import javax.persistence.RollbackException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.lessThan;
import static org.hamcrest.core.Is.is;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * SimpleIT
 *
 * @author thaler
 * @since 13.09.16
 */
public class SimpleDatabaseIT extends DbTestBase {

    @InjectMocks
    private NickNameDAO underTest;

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void testStoreNote() throws SQLException {
        // given
        final NickNameEntity nickNameEntity = new NickNameEntity();
        nickNameEntity.setSince(new Date());
        nickNameEntity.setName("test name");
        nickNameEntity.setNick("test nick");
        // when
        entityManager.persist(nickNameEntity);
        // then
        assertThat(nickNameEntity.getId(), is(greaterThan(1000000L)));

        entityManager.flush();
        final ResultSet resultSet = getConnection().prepareStatement("select count(*) from T_NICK_NAME").executeQuery();
        resultSet.next();
        assertThat(resultSet.getInt(1),is(15));
    }

    @Test
    public void testPrimaryKeyViolation() throws SQLException {
        // given
        Query nativeQuery = entityManager.createNativeQuery("INSERT INTO T_NICK_NAME (ID, SINCE, NAME, NICK) VALUES(74, '2014-02-15', 'foo', 'bar')");
        nativeQuery.executeUpdate();

        final NickNameEntity nickNameEntity = new NickNameEntity();
        nickNameEntity.setId(74L);
        nickNameEntity.setSince(new Date());
        nickNameEntity.setName("developer");
        nickNameEntity.setNick("home");

        exception.expect(RollbackException.class);
        exception.expectCause(new SecondCauseMatcher(PSQLException.class, "duplicate key value violates unique constraint"));
        // when
        entityManager.persist(nickNameEntity);
    }

    @Test
    public void testFindByName() throws SQLException {
        // when
        final List<NickNameEntity> result = underTest.findByName("my name A");
        // then
        assertThat(result.size(), is(10));
        Long previousTimestamp = null;
        for (NickNameEntity nickNameEntity : result) {
            if(previousTimestamp != null) {
                assertThat(nickNameEntity.getSince().getTime(),is(lessThan(previousTimestamp)));
            }
            previousTimestamp = nickNameEntity.getSince().getTime();
        }
    }

    @Test
    public void testFindByNickName() throws SQLException, ParseException {
        // given
        final String name = "my name A";
        final String nick = "test B";
        // when
        final List<NickNameEntity> result = underTest.findByNickName(name, nick);
        // then
        assertThat(result.size(), is(5));
        Long previousTimestamp = null;
        for (NickNameEntity nickNameEntity : result) {
            if(previousTimestamp != null) {
                assertThat(nickNameEntity.getSince().getTime(),is(lessThan(previousTimestamp)));
            }
            previousTimestamp = nickNameEntity.getSince().getTime();
        }
    }
}
