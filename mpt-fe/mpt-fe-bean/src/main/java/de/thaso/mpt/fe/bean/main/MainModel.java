package de.thaso.mpt.fe.bean.main;

import javax.enterprise.inject.Any;
import java.io.Serializable;
import java.util.List;

/**
 * MainModel
 *
 * @author thaler
 * @since 27.09.16
 */
@Any
public class MainModel implements Serializable {

    private static final long serialVersionUID = -2443249629199409358L;

    private String simpleMessage;

    public String getSimpleMessage() {
        return simpleMessage;
    }

    public void setSimpleMessage(final String simpleMessage) {
        this.simpleMessage = simpleMessage;
    }
}
