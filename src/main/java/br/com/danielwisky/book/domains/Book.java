package br.com.danielwisky.book.domains;

import lombok.Data;

@Data
public class Book {

  private String id;
  private String title;
  private String description;
  private String author;
}
