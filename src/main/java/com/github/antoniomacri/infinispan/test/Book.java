package com.github.antoniomacri.infinispan.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;
import java.util.Set;

public record Book(String title, String description, int publicationYear, Set<Author> authors) {
    @JsonCreator
    @ProtoFactory
    public Book(
            @JsonProperty("title") String title,
            @JsonProperty("description") String description,
            @JsonProperty("publicationYear") int publicationYear,
            @JsonProperty("authors") Set<Author> authors
    ) {
        this.title = Objects.requireNonNull(title);
        this.description = description;
        this.publicationYear = publicationYear;
        this.authors = Objects.requireNonNull(authors);
    }

    @Override
    @ProtoField(number = 1)
    public String title() {
        return title;
    }

    @Override
    @ProtoField(number = 2)
    public String description() {
        return description;
    }

    @Override
    @ProtoField(number = 3, defaultValue = "-1")
    public int publicationYear() {
        return publicationYear;
    }

    @Override
    @ProtoField(number = 4)
    public Set<Author> authors() {
        return authors;
    }
}