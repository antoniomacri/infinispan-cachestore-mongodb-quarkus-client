package com.github.antoniomacri.infinispan.test;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.common.ResourceArg;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.QuarkusTestProfile;
import io.quarkus.test.junit.TestProfile;

import java.util.Map;

@QuarkusTest
@TestProfile(BooksResourceXmlConfigWithMongoTest.TestProfile.class)
@QuarkusTestResource(value = InfinispanTestResource.class, restrictToAnnotatedClass = true, initArgs = {
        @ResourceArg(name = InfinispanTestResource.LOCAL_ARTIFACTS_ARG, value = "../infinispan-cachestore-mongodb/target/infinispan-cachestore-mongodb-14.0.8-SNAPSHOT.jar"),
        @ResourceArg(name = InfinispanTestResource.ARTIFACTS_ARG, value = "org.mongodb:mongodb-driver-reactivestreams:4.9.0,org.mongodb:mongodb-driver-core:4.9.0"),
})
public class BooksResourceXmlConfigWithMongoTest extends BooksResourceTest {
    public static class TestProfile implements QuarkusTestProfile {
        @Override
        public Map<String, String> getConfigOverrides() {
            return Map.of("quarkus.infinispan-client.cache.books.configuration-uri", "books-cache-config-with-store.xml");
        }
    }
}
