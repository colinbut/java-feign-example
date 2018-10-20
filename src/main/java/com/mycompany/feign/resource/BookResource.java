/*
 * |-------------------------------------------------
 * | Copyright © 2018 Colin But. All rights reserved.
 * |-------------------------------------------------
 */
package com.mycompany.feign.resource;

import com.mycompany.feign.model.Book;
import lombok.Data;

@Data
public class BookResource {
    private Book book;
}
