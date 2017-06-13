package de.thaso.mpt.be.domain.business;

import de.thaso.mpt.db.api.NickNameEntity;
import org.apache.commons.lang3.StringUtils;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * NickNameServiceImplPasswordValidTest
 *
 * @author thaler
 * @since 2017-06-12
 */
public class NickNameServiceImplPasswordValidTest {

    private static final String VALID_PASSWORD = "ValidPassword";

    private NickNameEntity nickNameEntity;

    @InjectMocks
    private NickNameServiceImpl underTest;

    @Before
    public void setUp() {
        initMocks(this);

        nickNameEntity = new NickNameEntity();
        nickNameEntity.setPassword(VALID_PASSWORD);
    }

    @Test
    public void testIsPasswordValid() {
        // when
        final boolean result = underTest.isPasswordValid(VALID_PASSWORD, nickNameEntity);
        // then
        assertThat(result, is(true));
    }

    @Test
    public void testIsPasswordValid_WhenEnteredPasswordIsNull() {
        // when
        final boolean result = underTest.isPasswordValid(null, nickNameEntity);
        // then
        assertThat(result, is(false));
    }

    @Test
    public void testIsPasswordValid_WhenEnteredPasswordIsBlank() {
        // when
        final boolean result = underTest.isPasswordValid(StringUtils.EMPTY, nickNameEntity);
        // then
        assertThat(result, is(false));
    }

    @Test
    public void testIsPasswordValid_WhenStoredPasswordIsNull() {
        nickNameEntity.setPassword(null);
        // when
        final boolean result = underTest.isPasswordValid(VALID_PASSWORD, nickNameEntity);
        // then
        assertThat(result, is(false));
    }

    @Test
    public void testIsPasswordValid_WhenStoredPasswordIsBlank() {
        // given
        nickNameEntity.setPassword(StringUtils.EMPTY);
        // when
        final boolean result = underTest.isPasswordValid(VALID_PASSWORD, nickNameEntity);
        // then
        assertThat(result, is(false));
    }

    @Test
    public void testIsPasswordValid_WhenBothPasswordAreNull() {
        // given
        nickNameEntity.setPassword(null);
        // when
        final boolean result = underTest.isPasswordValid(null, nickNameEntity);
        // then
        assertThat(result, is(true));
    }

    @Test
    public void testIsPasswordValid_WhenBothPasswordAreBlank() {
        // given
        nickNameEntity.setPassword(StringUtils.EMPTY);
        // when
        final boolean result = underTest.isPasswordValid(StringUtils.EMPTY, nickNameEntity);
        // then
        assertThat(result, is(true));
    }

    @Test
    public void testIsPasswordValid_WhenPasswordsAreBlankAndNull() {
        // given
        nickNameEntity.setPassword(null);
        // when
        final boolean result = underTest.isPasswordValid(StringUtils.EMPTY, nickNameEntity);
        // then
        assertThat(result, is(true));
    }

    @Test
    public void testIsPasswordValid_WhenEntityIsNull() {
        // when
        final boolean result = underTest.isPasswordValid(VALID_PASSWORD, null);
        // then
        assertThat(result, is(false));
    }
}