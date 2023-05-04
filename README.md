# Quarkus Infinispan Client

This example showcases how to use the Infinispan client with Quarkus.

I use it to test the Infinispan MongoDB Cache Store.


## Tests

Just run tests with:
```bash
./mvnw clean package
```


## Running

Start the docker-compose:
```bash
docker-compose rm -f && docker-compose up -d
```

Build the Quarkus app with java 17 and start it in JVM mode:
```bash
./mvnw -DskipTests=true clean package
java -jar ./target/quarkus-app/quarkus-run.jar
```

Use the `client.http` to send sample requests.
