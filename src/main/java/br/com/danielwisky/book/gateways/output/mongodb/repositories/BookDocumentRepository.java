package br.com.danielwisky.book.gateways.output.mongodb.repositories;

import br.com.danielwisky.book.gateways.output.mongodb.documents.BookDocument;
import io.quarkus.mongodb.panache.PanacheMongoRepository;
import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class BookDocumentRepository implements PanacheMongoRepository<BookDocument> {

}
