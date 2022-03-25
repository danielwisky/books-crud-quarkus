package br.com.danielwisky.book.gateways.output.mongodb.documents;

import static java.util.Optional.ofNullable;

import br.com.danielwisky.book.domains.Book;
import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;

@Data
@NoArgsConstructor
@AllArgsConstructor
@MongoEntity(collection = "books")
public class BookDocument extends PanacheMongoEntity {

  private ObjectId id;
  private String title;
  private String description;
  private String author;

  public BookDocument(final Book book) {
    this.id = ofNullable(book.getId())
        .map(ObjectId::new)
        .orElse(null);
    this.title = book.getTitle();
    this.description = book.getDescription();
    this.author = book.getAuthor();
  }

  public Book toDomain() {
    final Book book = new Book();
    book.setId(ofNullable(this.id)
        .map(ObjectId::toString)
        .orElse(null));
    book.setTitle(this.title);
    book.setDescription(this.description);
    book.setAuthor(this.author);
    return book;
  }
}
