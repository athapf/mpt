package de.thaso.mpt.fe.bean.message;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import java.io.Serializable;

/**
 * MessageModel
 *
 * @author thaler
 * @since 2017-06-11
 */
@Named
@RequestScoped
public class MessageModel implements Serializable {

    private static final long serialVersionUID = 1252809568874858193L;

    private String text;

    public String getText() {
        return text;
    }

    public void setText(final String text) {
        this.text = text;
    }
}
