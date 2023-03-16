package com.github.antoniomacri.infinispan.test;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.infinispan.protostream.annotations.ProtoFactory;
import org.infinispan.protostream.annotations.ProtoField;

import java.util.Objects;
import java.util.Set;

public class Book {
    private final String title;
    private final String description;
    private final int publicationYear;
    private final Set<Author> authors;

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

    @ProtoField(number = 1)
    public String getTitle() {
        return title;
    }

    @ProtoField(number = 2)
    public String getDescription() {
        return description;
    }

    @ProtoField(number = 3, defaultValue = "-1")
    public int getPublicationYear() {
        return publicationYear;
    }

    @ProtoField(number = 4)
    public Set<Author> getAuthors() {
        return authors;
    }
}