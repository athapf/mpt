package de.thaso.mpt.fe.it.base;

/**
 * EmbeddedServerBase
 *
 * @author thaler
 * @since 29.09.16
 */
public interface ApplicationServerBase {

    void startEmbeddedServer() throws Exception;

    void stopEmbeddedServer() throws Exception;

    String getApplicationUrl();
}
