package com.github.antoniomacri.infinispan.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;

public record Author(String name, String surname) {
    @JsonCreator
    @ProtoFactory
    public Author(
            @JsonProperty("name") String name,
            @JsonProperty("surname") String surname
    ) {
        this.name = Objects.requireNonNull(name);
        this.surname = Objects.requireNonNull(surname);
    }

    @Override
    @ProtoField(number = 1)
    public String name() {
        return name;
    }

    @Override
    @ProtoField(number = 2)
    public String surname() {
        return surname;
    }
}
