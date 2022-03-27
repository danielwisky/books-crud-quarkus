package br.com.danielwisky.book.gateways.output.mongodb;

import static java.util.stream.Collectors.toList;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.BookFilter;
import br.com.danielwisky.book.domains.Page;
import br.com.danielwisky.book.gateways.BookDataGateway;
import br.com.danielwisky.book.gateways.output.mongodb.documents.BookDocument;
import br.com.danielwisky.book.gateways.output.mongodb.repositories.BookDocumentRepository;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.model.Filters;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import org.bson.BsonDocument;
import org.bson.BsonDocumentReader;
import org.bson.Document;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.DocumentCodec;
import org.bson.conversions.Bson;
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
  public void deleteById(final String id) {
    bookDocumentRepository.deleteById(new ObjectId(id));
  }

  @Override
  public Optional<Book> findById(final String id) {
    return bookDocumentRepository.findByIdOptional(new ObjectId(id)).map(BookDocument::toDomain);
  }

  @Override
  public Page<Book> search(final BookFilter filter) {

    final List<Bson> filters = new ArrayList<>();
    filters.add(Filters.ne("_id", null));
    addRegexIfNotNull(filters, "title", filter.getTitle());
    addRegexIfNotNull(filters, "author", filter.getAuthor());

    final Document query = toDocument(Filters.and(filters.toArray(new Bson[0]))
        .toBsonDocument(BsonDocument.class, MongoClientSettings.getDefaultCodecRegistry()));

    long totalBooks = bookDocumentRepository.count(query);
    int totalPages = totalBooks == 0 ? 0 : (int) Math.ceil(totalBooks / filter.getSize());

    final List<Book> books =
        bookDocumentRepository.find(query)
            .page(filter.getPage(), filter.getSize())
            .stream()
            .map(BookDocument::toDomain)
            .collect(toList());

    return new Page(books, filter.getPage(), filter.getSize(), totalBooks, totalPages);
  }

  private Document toDocument(final BsonDocument bsonDocument) {
    final DocumentCodec codec = new DocumentCodec();
    final DecoderContext decoderContext = DecoderContext.builder().build();
    return codec.decode(new BsonDocumentReader(bsonDocument), decoderContext);
  }

  private void addRegexIfNotNull(final List<Bson> filters, final String field, final String value) {
    if (Objects.nonNull(value)) {
      filters.add(Filters.regex(field, value));
    }
  }
}