package de.thaso.mpt.db.store.base;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * EntityBaseTest
 *
 * @author thaler
 * @since 13.09.16
 */
public class EntityBaseTest {

    private static final String SOME_VALUE = "some value";
    private static final String OTHER_VALUE = "some other value";

    private TestEntity firstEntity;
    private TestEntity secondEntity;
    private OtherEntity otherEntity;

    @Test
    public void equals_whenBothIdAreIdentical() {
        // given
        firstEntity = new TestEntity(1L, SOME_VALUE);
        secondEntity = new TestEntity(1L, OTHER_VALUE);
        // then
        assertThat(firstEntity.equals(secondEntity), is(true));
    }

    @Test
    public void equals_whenBothIdNotSet() {
        // given
        firstEntity = new TestEntity(null, SOME_VALUE);
        secondEntity = new TestEntity(null, SOME_VALUE);
        // then
        assertThat(firstEntity.equals(secondEntity), is(false));
    }

    @Test
    public void equals_whenOnlyFirstIdIsSet() {
        // given
        firstEntity = new TestEntity(1L, SOME_VALUE);
        secondEntity = new TestEntity(null, SOME_VALUE);
        // then
        assertThat(firstEntity.equals(secondEntity), is(false));
    }

    @Test
    public void equals_whenOnlySecondIdIsSet() {
        // given
        firstEntity = new TestEntity(null, SOME_VALUE);
        secondEntity = new TestEntity(1L, SOME_VALUE);
        // then
        assertThat(firstEntity.equals(secondEntity), is(false));
    }

    @Test
    public void equals_whenSecondEntityIsNull() {
        // given
        firstEntity = new TestEntity(1L, SOME_VALUE);
        // then
        assertThat(firstEntity.equals(null), is(false));
    }

    @Test
    public void equals_whenSecondEntityIsOtherEntity() {
        // given
        firstEntity = new TestEntity(1L, SOME_VALUE);
        otherEntity = new OtherEntity(1L, SOME_VALUE);
        // then
        assertThat(firstEntity.equals(otherEntity), is(false));
    }

    private class TestEntity extends EntityBase {
        private Long id;
        private String name;

        public TestEntity(final Long id, final String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Long getId() {
            return id;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }
    }

    private class OtherEntity extends EntityBase {
        private Long id;
        private String name;

        public OtherEntity(final Long id, final String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public Long getId() {
            return id;
        }

        public void setId(final Long id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(final String name) {
            this.name = name;
        }
    }
}