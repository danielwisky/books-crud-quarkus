package br.com.danielwisky.book.usecases;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.gateways.BookDataGateway;
import java.lang.module.ResolutionException;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class FindBook {

  @Inject
  private BookDataGateway bookDataGateway;

  public Book execute(final String id) {
    return bookDataGateway.findById(id)
        .orElseThrow(() -> new ResolutionException());
  }
}
