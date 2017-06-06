package de.thaso.mpt.fe.bean.overview;

import javax.enterprise.inject.Any;
import java.io.Serializable;

/**
 * OverviewModel
 *
 * @author thaler
 * @since 2017-06-06
 */
@Any
public class OverviewModel implements Serializable {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
