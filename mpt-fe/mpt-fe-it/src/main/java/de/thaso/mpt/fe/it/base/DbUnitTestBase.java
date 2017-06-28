package de.thaso.mpt.fe.it.base;

import de.thaso.mpt.db.schema.DatabaseManager;
import de.thaso.mpt.db.schema.PropertiesManager;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.BeforeClass;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * DbUnitTestBase
 *
 * @author thaler
 * @since 01.10.16
 */
public class DbUnitTestBase {

    private static Properties properties;

    private java.sql.Connection connection;
    private IDatabaseConnection databaseConnection;

    public static Properties readProperties() {
        return PropertiesManager.readDevelopProperties();
    }

    @BeforeClass
    public static void prepareDatabase() {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createDatabase(readProperties());
    }

    public static Properties getProperties() {
        if (properties == null) {
            properties = readProperties();
        }
        return properties;
    }

    @Before
    public void setUpConnection() throws SQLException, ClassNotFoundException {
        final Properties properties = getProperties();
        connection = DriverManager.getConnection(
                properties.getProperty("javax.persistence.jdbc.url"),
                properties.getProperty("javax.persistence.jdbc.user"),
                properties.getProperty("javax.persistence.jdbc.password"));
        initializeDatabase();
    }

    private void initializeDatabase() {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        try {
            databaseConnection = new DatabaseConnection(connection);
            final IDataSet dataSet = builder.build(this.getClass().getResourceAsStream("/dbunit/base-setup.xml"));
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
        } catch (SQLException | DatabaseUnitException e) {
            throw new RuntimeException("could not initialize database", e);
        }
    }

    protected void updateDatabase(final String setupFile) {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        try {
            IDataSet dataSet = builder.build(this.getClass().getResourceAsStream("/dbunit/" + setupFile));
            DatabaseOperation.REFRESH.execute(databaseConnection, dataSet);
        } catch (SQLException | DatabaseUnitException e) {
            throw new RuntimeException("could not update database", e);
        }
    }

    protected void updateDatabase() {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        try {
            IDataSet dataSet = builder.build(this.getClass().getResourceAsStream(constructResourcePath()));
            DatabaseOperation.REFRESH.execute(databaseConnection, dataSet);
        } catch (SQLException | DatabaseUnitException e) {
            throw new RuntimeException("could not update database", e);
        }
    }

    private String constructResourcePath() {
        final StackTraceElement stackTraceElement = Thread.currentThread().getStackTrace()[3];
        final String className = this.getClass().getSimpleName();
        final StringBuilder builder = new StringBuilder();
        builder.append("/dbunit/");
        builder.append(className);
        builder.append("/");
        builder.append(stackTraceElement.getMethodName());
        builder.append(".xml");
        return builder.toString();
    }
}
