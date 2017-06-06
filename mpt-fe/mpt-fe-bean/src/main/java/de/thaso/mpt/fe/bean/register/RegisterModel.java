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

    private String name;
    private String password;
    private String verify;

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

    public String getVerify() {
        return verify;
    }

    public void setVerify(final String verify) {
        this.verify = verify;
    }
}
