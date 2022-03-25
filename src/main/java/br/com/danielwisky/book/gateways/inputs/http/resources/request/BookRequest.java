package br.com.danielwisky.book.gateways.inputs.http.resources.request;

import br.com.danielwisky.book.domains.Book;
import javax.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookRequest {

  @NotBlank
  private String title;
  @NotBlank
  private String description;
  @NotBlank
  private String author;

  public Book toDomain() {
    final Book book = new Book();
    book.setTitle(this.title);
    book.setDescription(this.description);
    book.setAuthor(this.author);
    return book;
  }
}
