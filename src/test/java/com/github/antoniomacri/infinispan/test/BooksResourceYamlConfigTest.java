package com.github.antoniomacri.infinispan.test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

import java.util.Map;

@QuarkusTest
@TestProfile(BooksResourceYamlConfigTest.TestProfile.class)
@QuarkusTestResource(value = InfinispanTestResource.class, restrictToAnnotatedClass = true)
public class BooksResourceYamlConfigTest extends BooksResourceTest {
    public static class TestProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of("quarkus.infinispan-client.cache.books.configuration-uri", "books-cache-config.yaml");
        }
    }
}
