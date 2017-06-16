package de.thaso.mpt.db.it.base;

import de.thaso.mpt.db.it.utils.PersistenceStoreHelper;
import de.thaso.mpt.db.schema.DatabaseManager;
import de.thaso.mpt.db.schema.PropertiesManager;
import org.dbunit.DatabaseUnitException;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.mockito.Spy;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * DbTestBase
 *
 * @author thaler
 * @since 13.09.16
 */
public class DbTestBase {

    @Spy
    protected EntityManager entityManager = PersistenceStoreHelper.getEntityManager();

    private java.sql.Connection connection;

    @BeforeClass
    public static void prepareDatabase() {
        DatabaseManager databaseManager = new DatabaseManager();
        databaseManager.createDatabase(PropertiesManager.readDevelopProperties());
    }

    @Before
    public void setUpConnection() {
        entityManager.getTransaction().begin();
        connection = entityManager.unwrap(java.sql.Connection.class);

        initializeDatabase();
    }

    @After
    public void tearDownConnection() {
        entityManager.getTransaction().commit();
    }

    public Connection getConnection() {
        return connection;
    }

    private void initializeDatabase() {
        FlatXmlDataSetBuilder builder = new FlatXmlDataSetBuilder();
        builder.setColumnSensing(true);
        try {
            IDatabaseConnection databaseConnection = new DatabaseConnection(connection);
            IDataSet dataSet = builder.build(this.getClass().getResourceAsStream("/dbunit/base-setup.xml"));
            DatabaseOperation.CLEAN_INSERT.execute(databaseConnection, dataSet);
        } catch (SQLException | DatabaseUnitException e) {
            throw new RuntimeException("could not initialize database", e);
        }
    }
}
