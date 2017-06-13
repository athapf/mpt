package de.thaso.mpt.fe.bean.login;

import javax.enterprise.inject.Any;
import java.io.Serializable;

/**
 * LoginModel
 *
 * @author thaler
 * @since 2017-06-06
 */
@Any
public class LoginModel implements Serializable {

    private static final long serialVersionUID = -1169586643185691929L;

    private String name;
    private String password;

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = password;
    }
}
