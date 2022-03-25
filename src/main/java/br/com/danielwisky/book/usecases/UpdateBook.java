package br.com.danielwisky.book.usecases;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.book.gateways.BookDataGateway;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class UpdateBook {

  @Inject
  private BookDataGateway bookDataGateway;

  public Book execute(final String id, final Book book) {
    final Book bookData = bookDataGateway.findById(id)
        .orElseThrow(() -> new ResourceNotFoundException());

    book.setId(book.getId());
    return bookDataGateway.save(book);
  }
}
