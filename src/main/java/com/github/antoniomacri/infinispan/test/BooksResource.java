package com.github.antoniomacri.infinispan.test;

import io.quarkus.infinispan.client.Remote;
import org.infinispan.client.hotrod.RemoteCache;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/books")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class BooksResource {

    @Inject
    @Remote("books")
    RemoteCache<String, Book> booksCache;


    @GET
    @Path("/{isbn}")
    public Book getBook(@PathParam("isbn") String isbn) {
        Book book = booksCache.get(isbn);
        if (book == null) {
            throw new NotFoundException();
        }
        return book;
    }

    @PUT
    @Path("/{isbn}")
    public Book putBook(@PathParam("isbn") String isbn, Book book) {
        booksCache.put(isbn, book);
        return book;
    }

    @DELETE
    @Path("/{isbn}")
    public Book deleteBook(@PathParam("isbn") String isbn) {
        return booksCache.remove(isbn);
    }
}
