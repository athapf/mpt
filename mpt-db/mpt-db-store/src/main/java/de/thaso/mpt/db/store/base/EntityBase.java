package de.thaso.mpt.db.store.base;

import java.io.Serializable;

/**
 * EntityBase
 *
 * @author thaler
 * @since 13.09.16
 */
public abstract class EntityBase implements Serializable {

    public abstract Long getId();

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof EntityBase)) return false;
        if (o == null || getClass() != o.getClass()) return false;

        EntityBase that = (EntityBase) o;

        return !(getId() != null ? !getId().equals(that.getId()) : true);
    }

    @Override
    public final int hashCode() {
        return getId() != null ? getId().hashCode() : 0;
    }
}
