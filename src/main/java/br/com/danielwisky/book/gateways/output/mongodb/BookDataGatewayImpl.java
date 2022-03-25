package br.com.danielwisky.book.gateways.output.mongodb;

import static java.util.stream.Collectors.toList;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.BookFilter;
import br.com.danielwisky.book.domains.Page;
import br.com.danielwisky.book.gateways.BookDataGateway;
import br.com.danielwisky.book.gateways.output.mongodb.documents.BookDocument;
import br.com.danielwisky.book.gateways.output.mongodb.repositories.BookDocumentRepository;
import java.util.List;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.bson.types.ObjectId;

@ApplicationScoped
public class BookDataGatewayImpl implements BookDataGateway {

  private static final String QUERY_SEARCH = "{ 'title': new RegExp(?1), 'author': new RegExp(?2) } }";

  @Inject
  private BookDocumentRepository bookDocumentRepository;

  @Override
  public Book save(final Book book) {
    final BookDocument bookDocument = new BookDocument(book);
    bookDocumentRepository.persistOrUpdate(bookDocument);
    return bookDocument.toDomain();
  }

  @Override
  public void deleteById(final String id) {
    bookDocumentRepository.deleteById(new ObjectId(id));
  }

  @Override
  public Optional<Book> findById(final String id) {
    return bookDocumentRepository.findByIdOptional(new ObjectId(id)).map(BookDocument::toDomain);
  }

  @Override
  public Page<Book> search(final BookFilter filter) {

    long totalBooks = bookDocumentRepository.count(
        QUERY_SEARCH, filter.getTitle(), filter.getAuthor());
    int totalPages = totalBooks == 0 ? 0 : (int) Math.ceil(totalBooks / filter.getSize());

    final List<Book> books =
        bookDocumentRepository.find(QUERY_SEARCH, filter.getTitle(), filter.getAuthor())
            .page(filter.getPage(), filter.getSize())
            .stream()
            .map(BookDocument::toDomain)
            .collect(toList());

    return new Page(books, filter.getPage(), filter.getSize(), totalBooks, totalPages);
  }

}