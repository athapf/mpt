package de.thaso.mpt.fe.bean.register;

import javax.enterprise.inject.Any;
import java.io.Serializable;

/**
 * RegisterModel
 *
 * @author thaler
 * @since 2017-06-06
 */
@Any
public class RegisterModel implements Serializable {

    private static final long serialVersionUID = 3593308552689530699L;

    private String name;
    private String nick;
    private String password;
    private String verify;
    private boolean updateOnly = false;

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

    public String getVerify() {
        return verify;
    }

    public void setVerify(final String verify) {
        this.verify = verify;
    }

    public boolean isUpdateOnly() {
        return updateOnly;
    }

    public void setUpdateOnly(final boolean updateOnly) {
        this.updateOnly = updateOnly;
    }
}
