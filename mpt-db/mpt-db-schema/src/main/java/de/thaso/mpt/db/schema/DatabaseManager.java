package de.thaso.mpt.db.schema;

import org.flywaydb.core.Flyway;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Properties;

/**
 * DatabaseManager
 *
 * @author thaler
 * @since 13.09.16
 */
public class DatabaseManager {

    public static final Logger LOG = LoggerFactory.getLogger(DatabaseManager.class);

    private static final String FLYWAY_SCRIPT_PATH = "db/local";
    private static final String JDBC_URL = "javax.persistence.jdbc.url";
    private static final String JDBC_USER = "javax.persistence.jdbc.user";
    private static final String JDBC_PASSWORD = "javax.persistence.jdbc.password";

    public void createDatabase(final Properties properties) {
        LOG.info("createDatabase {}", properties.getProperty(JDBC_URL));
        Flyway flyway = initFlyway(properties);
        flyway.clean();
        flyway.migrate();
    }

    public void migrateDatabase(final Properties properties) {
        LOG.info("migrateDatabase {}", properties.getProperty(JDBC_URL));
        Flyway flyway = initFlyway(properties);
        flyway.migrate();
    }

    private Flyway initFlyway(final Properties properties) {
        LOG.info("initFlyway {}", properties.getProperty(JDBC_URL));
        Flyway flyway = new Flyway();
        flyway.setDataSource( properties.getProperty(JDBC_URL),
                properties.getProperty(JDBC_USER),
                properties.getProperty(JDBC_PASSWORD));
        flyway.setLocations(FLYWAY_SCRIPT_PATH);
        return flyway;
    }
}
