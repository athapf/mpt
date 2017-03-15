package de.thaso.mpt.be.domain.service;

import java.io.Serializable;
import java.util.Date;

/**
 * NickNameData
 *
 * @author thaler
 * @since 21.09.16
 */
public class NickNameData implements Serializable {

    private static final long serialVersionUID = -7049489388682590929L;

    private Long id;
    private Date timestamp;
    private String name;
    private String nick;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(final Date timestamp) {
        this.timestamp = timestamp;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(final String nick) {
        this.nick = nick;
    }
}
