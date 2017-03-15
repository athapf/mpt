package de.thaso.mpt.fe.it.glassfish.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Glassfish4DatabasePropertiesBuilder
 *
 * @author thaler
 * @since 29.09.16
 */
public class CommandParamBuilder {

    public enum Type { CONNECTION_POOL, JDBC_RESOURCE }

    private String jdbcUrl;
    private String user;
    private String password;
    private String restype;
    private String classname;
    private String poolname;
    private String resource;

    public static CommandParamBuilder create() {
        return new CommandParamBuilder();
    }

    private CommandParamBuilder() {}

    public CommandParamBuilder withJdbcUrl(final String jdbcUrl) {
        this.jdbcUrl = jdbcUrl;
        return this;
    }

    public CommandParamBuilder withUser(final String user) {
        this.user = user;
        return this;
    }

    public CommandParamBuilder withPassword(final String password) {
        this.password = password;
        return this;
    }

    public CommandParamBuilder withRestype(final String restype) {
        this.restype = restype;
        return this;
    }

    public CommandParamBuilder withClassname(final String classname) {
        this.classname = classname;
        return this;
    }

    public CommandParamBuilder withPoolname(final String poolname) {
        this.poolname = poolname;
        return this;
    }

    public CommandParamBuilder withResource(final String resource) {
        this.resource = resource;
        return this;
    }

    public String[] build(final Type type) {
        final List<String> result = new ArrayList<>();

        switch (type) {
            case CONNECTION_POOL:
                buildConnectionPool(result);
                break;
            case JDBC_RESOURCE:
                buildJdbcResource(result);
                break;
            default:
        }
        return result.toArray(new String[result.size()]);
    }

    private void buildJdbcResource(final List<String> paramList) {
        paramList.add("--connectionpoolid");
        paramList.add(poolname);
        paramList.add(resource);
    }

    private void buildConnectionPool(final List<String> paramList) {
        paramList.add("--ping");
        paramList.add("--restype=" + restype);
        paramList.add("--datasourceClassname");
        paramList.add(classname);
        paramList.add("--property");

        final StringBuilder builder = new StringBuilder();
        if(StringUtils.isNotEmpty(user)) {
            builder.append("user=").append(user).append(':');
        }
        if(StringUtils.isNotEmpty(password)) {
            builder.append("password=").append(password).append(':');
        }
        builder.append("url=").append(jdbcUrl.replaceAll(":","\\\\:").replaceAll("=","\\\\="));

        paramList.add(builder.toString());
        paramList.add(poolname);
    }
}
