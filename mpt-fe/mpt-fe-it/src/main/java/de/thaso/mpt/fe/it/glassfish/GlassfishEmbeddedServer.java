package de.thaso.mpt.fe.it.glassfish;

import de.thaso.mpt.db.schema.PropertiesManager;
import de.thaso.mpt.fe.it.base.ApplicationServerBase;
import de.thaso.mpt.fe.it.utils.CommandParamBuilder;
import org.apache.commons.lang3.StringUtils;
import org.glassfish.embeddable.BootstrapProperties;
import org.glassfish.embeddable.CommandResult;
import org.glassfish.embeddable.Deployer;
import org.glassfish.embeddable.GlassFish;
import org.glassfish.embeddable.GlassFishException;
import org.glassfish.embeddable.GlassFishProperties;
import org.glassfish.embeddable.GlassFishRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Properties;

/**
 * GlassfishEmbeddedServer
 *
 * @author thaler
 * @since 29.09.16
 */
public class GlassfishEmbeddedServer implements ApplicationServerBase {

    private static final Logger LOG = LoggerFactory.getLogger(GlassfishEmbeddedServer.class);

    private static final String EMBEDDED = "embedded";
    private static final String LOCALHOST = "localhost";

    private static Properties properties = PropertiesManager.readDevelopProperties();
    private static GlassFishRuntime glassFishRuntime;
    private static GlassFish glassfish;

    @Override
    public void startEmbeddedServer() throws Exception {
        if(StringUtils.equals(properties.getProperty("app.server.host"), EMBEDDED)) {
            LOG.info("Opening the container: {}", glassfish == null ? "" : glassfish.getStatus().name());

            if (glassfish == null) {
                final BootstrapProperties bootstrapProperties = new BootstrapProperties();
                final GlassFishProperties glassFishProperties = new GlassFishProperties();
                glassFishProperties.setPort("http-listener", Integer.parseInt(properties.getProperty("app.server.http.port")));
                glassFishRuntime = GlassFishRuntime.bootstrap(bootstrapProperties);
                glassfish = glassFishRuntime.newGlassFish(glassFishProperties);
                glassfish.start();
                LOG.info("state: {}", glassfish.getStatus().name());
                addDatabasePool(properties);
            }
            deployApp();
        }
    }

    private void addDatabasePool(final Properties properties) throws GlassFishException {
        final CommandParamBuilder commandParamBuilder
                = CommandParamBuilder.create()
                .withRestype(properties.getProperty("glassfish.resource.type"))
                .withClassname(properties.getProperty("glassfish.datasource.classname"))
                .withUser(properties.getProperty("javax.persistence.jdbc.user"))
                .withPassword(properties.getProperty("javax.persistence.jdbc.password"))
                .withJdbcUrl(properties.getProperty("javax.persistence.jdbc.url"))
                .withPoolname(properties.getProperty("glassfish.pool.name"))
                .withResource(properties.getProperty("glassfish.jdbc.resource"));

        CommandResult res = glassfish.getCommandRunner().run("create-jdbc-connection-pool",
                commandParamBuilder.build(CommandParamBuilder.Type.CONNECTION_POOL));
        LOG.info("=> create-jdbc-connection-pool: {}", res.getOutput());

        res = glassfish.getCommandRunner().run("create-jdbc-resource",
                commandParamBuilder.build(CommandParamBuilder.Type.JDBC_RESOURCE));
        LOG.info("=> create-jdbc-resource: {}", res.getOutput());
    }

    private void deployApp() throws GlassFishException {
        final File ear = new File("./target/ear/mpt-app-ear-1.0-SNAPSHOT.ear");
        final Deployer deployer = glassfish.getDeployer();
        deployer.deploy(ear, "--contextroot=" + properties.getProperty("app.server.contextroot"), "--force=true");
    }

    private void undeployAllApps() throws GlassFishException {
        final Deployer deployer = glassfish.getDeployer();
        for (String app : deployer.getDeployedApplications()) {
            LOG.info("undeploy: {}", app);
            deployer.undeploy(app);
        }
    }

    private String createAppUrl() {
        final String serverHost = properties.getProperty("app.server.host");
        final StringBuilder builder = new StringBuilder();
        builder.append("http://");
        if(StringUtils.equals(serverHost, EMBEDDED)) {
            builder.append(LOCALHOST);
        } else {
            builder.append(serverHost);
        }
        builder.append(":")
                .append(properties.getProperty("app.server.http.port"))
                .append('/')
                .append(properties.getProperty("app.server.contextroot"));
        return builder.toString();
    }

    @Override
    public void stopEmbeddedServer() throws Exception {
        if(StringUtils.equals(properties.getProperty("app.server.host"), EMBEDDED)) {
            LOG.info("Going to close the container ...");
            undeployAllApps();

            // restart of embedded glassfish at the moment not functional, problem with ORB service
            //glassfish.stop();
            //glassfish.dispose();
            //glassFishRuntime.shutdown();
            //glassfish = null;
            //LOG.info("Closing the container: {}", glassfish.getStatus());
        }
    }

    @Override
    public String getApplicationUrl() {
        return createAppUrl();
    }
}
