package br.com.danielwisky.book.gateways.inputs.http.resources.response;

import br.com.danielwisky.book.domains.Book;
import lombok.Data;

@Data
public class BookResponse {

  private String id;
  private String title;
  private String description;
  private String author;

  public BookResponse(final Book book) {
    this.id = book.getId();
    this.title = book.getTitle();
    this.description = book.getDescription();
    this.author = book.getAuthor();
  }
}
