package com.github.antoniomacri.infinispan.test;

import io.quarkus.test.common.QuarkusTestResourceLifecycleManager;
import org.infinispan.client.hotrod.impl.ConfigurationProperties;
import org.infinispan.commons.util.Version;
import org.infinispan.server.test.core.InfinispanContainer;
import org.jboss.logging.Logger;
import org.testcontainers.containers.BindMode;

import java.nio.file.Path;
import java.util.Collections;
import java.util.Map;
import java.util.Optional;

/**
 * Taken from {@link io.quarkus.test.infinispan.client.InfinispanTestResource}
 * and extended to mount local artifacts under server/lib.
 */
public class InfinispanTestResource implements QuarkusTestResourceLifecycleManager {
    private static final Logger LOGGER = Logger.getLogger(InfinispanTestResource.class);
    public static final String PORT_ARG = "port";
    public static final String USER_ARG = "user";
    public static final String PASSWORD_ARG = "password";
    public static final String ARTIFACTS_ARG = "artifacts";
    public static final String LOCAL_ARTIFACTS_ARG = "local-artifacts";
    private static final int DEFAULT_PORT = ConfigurationProperties.DEFAULT_HOTROD_PORT;
    private static final String DEFAULT_USER = "admin";
    private static final String DEFAULT_PASSWORD = "password";
    private static InfinispanContainer INFINISPAN;
    private String USER;
    private String PASSWORD;
    private String[] ARTIFACTS;
    private String[] LOCAL_ARTIFACTS;
    private Integer HOTROD_PORT;

    @Override
    public void init(Map<String, String> initArgs) {
        HOTROD_PORT = Optional.ofNullable(initArgs.get(PORT_ARG)).map(Integer::parseInt).orElse(DEFAULT_PORT);
        USER = Optional.ofNullable(initArgs.get(USER_ARG)).orElse(DEFAULT_USER);
        PASSWORD = Optional.ofNullable(initArgs.get(PASSWORD_ARG)).orElse(DEFAULT_PASSWORD);
        String artifacts = initArgs.get(ARTIFACTS_ARG);
        if (artifacts == null) {
            ARTIFACTS = new String[0];
        } else {
            ARTIFACTS = artifacts.split(",");
        }
        String localArtifacts = initArgs.get(LOCAL_ARTIFACTS_ARG);
        if (localArtifacts == null) {
            LOCAL_ARTIFACTS = new String[0];
        } else {
            LOCAL_ARTIFACTS = localArtifacts.split(",");
        }
    }

    @Override
    public Map<String, String> start() {
        INFINISPAN = new InfinispanContainer();
        INFINISPAN.withUser(USER).withPassword(PASSWORD).withArtifacts(ARTIFACTS);
        for (String localArtifact : LOCAL_ARTIFACTS) {
            String filename = Path.of(localArtifact).getFileName().toString();
            INFINISPAN.withFileSystemBind(localArtifact, "/opt/infinispan/server/lib/" + filename, BindMode.READ_ONLY);
        }

        LOGGER.infof("Starting Infinispan Server %s on port %s with user %s and password %s", Version.getMajorMinor(),
                HOTROD_PORT, USER, PASSWORD);
        INFINISPAN.start();

        final String hosts = INFINISPAN.getHost() + ":" + INFINISPAN.getMappedPort(HOTROD_PORT);
        return Collections.singletonMap("quarkus.infinispan-client.hosts", hosts);
    }

    @Override
    public void stop() {
        if (INFINISPAN != null) {
            INFINISPAN.stop();
        }
    }
}
