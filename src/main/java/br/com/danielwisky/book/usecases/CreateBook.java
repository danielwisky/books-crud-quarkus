package br.com.danielwisky.book.usecases;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.gateways.BookDataGateway;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class CreateBook {

  @Inject
  private BookDataGateway bookDataGateway;

  public Book execute(final Book book) {
    return bookDataGateway.save(book);
  }
}
