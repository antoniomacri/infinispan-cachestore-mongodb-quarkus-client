package com.github.antoniomacri.infinispan.test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.ResourceArg;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

import java.util.Map;

@QuarkusTest
@TestProfile(BooksResourceXmlConfigWithMongoTest.TestProfile.class)
@QuarkusTestResource(value = InfinispanTestResource.class, restrictToAnnotatedClass = true,
        initArgs = @ResourceArg(name = InfinispanTestResource.LOCAL_ARTIFACTS_ARG, value = "../infinispan-cachestore-mongodb/target/infinispan-cachestore-mongodb-14.0.8-SNAPSHOT.jar")
)
public class BooksResourceXmlConfigWithMongoTest extends BooksResourceTest {
    public static class TestProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of("quarkus.infinispan-client.cache.books.configuration-uri", "books-cache-config-with-store.xml");
        }
    }
}
