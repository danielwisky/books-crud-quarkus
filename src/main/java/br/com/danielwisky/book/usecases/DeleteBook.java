package br.com.danielwisky.book.usecases;

import br.com.danielwisky.book.gateways.BookDataGateway;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class DeleteBook {

  @Inject
  private BookDataGateway bookDataGateway;

  public void execute(final String id) {
    bookDataGateway.deleteById(id);
  }
}
