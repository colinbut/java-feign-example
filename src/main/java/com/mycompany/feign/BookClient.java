/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.feign;

import feign.Headers;
import feign.Param;
import feign.RequestLine;

import java.util.List;

public interface BookClient {

    @RequestLine("GET /{isbn}")
    BookResource findByIsbn(@Param("isbn") String isbn);

    @RequestLine("GET")
    List<BookResource> findAll();

    @RequestLine("POST")
    @Headers("Content-Type: application/json")
    void create(Book book);
}
