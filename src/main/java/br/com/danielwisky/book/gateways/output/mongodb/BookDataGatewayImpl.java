package br.com.danielwisky.book.gateways.output.mongodb;

import static java.util.Optional.ofNullable;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.gateways.BookDataGateway;
import br.com.danielwisky.book.gateways.output.mongodb.documents.BookDocument;
import br.com.danielwisky.book.gateways.output.mongodb.repositories.BookDocumentRepository;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.bson.types.ObjectId;

@ApplicationScoped
public class BookDataGatewayImpl implements BookDataGateway {

  @Inject
  private BookDocumentRepository bookDocumentRepository;

  @Override
  public Book save(final Book book) {
    final BookDocument bookDocument = new BookDocument(book);
    bookDocumentRepository.persistOrUpdate(bookDocument);
    return bookDocument.toDomain();
  }

  @Override
  public Optional<Book> findById(final String id) {
    return ofNullable(bookDocumentRepository.findById(new ObjectId(id)))
        .map(BookDocument::toDomain);
  }

  @Override
  public void deleteById(final String id) {
    bookDocumentRepository.deleteById(new ObjectId(id));
  }
}