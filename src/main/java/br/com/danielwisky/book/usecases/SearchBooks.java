package br.com.danielwisky.book.usecases;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.BookFilter;
import br.com.danielwisky.book.domains.Page;
import br.com.danielwisky.book.gateways.BookDataGateway;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class SearchBooks {

  @Inject
  private BookDataGateway bookDataGateway;

  public Page<Book> execute(final BookFilter filter) {
    return bookDataGateway.search(filter);
  }
}
