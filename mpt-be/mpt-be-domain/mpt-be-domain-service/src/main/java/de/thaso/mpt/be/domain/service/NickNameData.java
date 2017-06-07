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
    private Date since;
    private String name;
    private String nick;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public Date getSince() {
        return since;
    }

    public void setSince(final Date since) {
        this.since = since;
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

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
