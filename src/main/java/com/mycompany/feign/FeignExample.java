package com.mycompany.feign;

import feign.Feign;
import feign.Logger;
import feign.gson.GsonDecoder;
import feign.gson.GsonEncoder;
import feign.okhttp.OkHttpClient;
import feign.slf4j.Slf4jLogger;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Slf4j
public class FeignExample {

    public static void main( String[] args ) {
        BookClient bookClient = Feign.builder()
            .client(new OkHttpClient())
            .encoder(new GsonEncoder())
            .decoder(new GsonDecoder())
            .logger(new Slf4jLogger(BookClient.class))
            .logLevel(Logger.Level.FULL)
            .target(BookClient.class, Constants.BOOK_SERVICE_URL);

        // GET list of books
        List<Book> books = bookClient.findAll().stream()
            .map(BookResource::getBook)
            .collect(Collectors.toList());

        log.info("Books: {}", books);

        // GET one book
        Book book = bookClient.findByIsbn("01234556").getBook();
        log.info("Book: {}", book);

        // POST book
        Book bookToCreate = new Book();
        bookToCreate.setIsbn(UUID.randomUUID().toString());
        bookToCreate.setAuthor("New Author");
        bookToCreate.setLanguage("EN");
        bookToCreate.setTitle("Book Title");
        bookToCreate.setSypnosis("Book Sypnosis");

        bookClient.create(book);

    }
}
