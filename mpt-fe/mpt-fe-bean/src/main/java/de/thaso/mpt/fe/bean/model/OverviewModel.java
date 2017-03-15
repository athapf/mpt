package de.thaso.mpt.fe.bean.model;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.List;

/**
 * OverviewModel
 *
 * @author thaler
 * @since 27.09.16
 */
@Any
public class OverviewModel implements Serializable {

    private String simpleMessage;

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(final String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }
}
