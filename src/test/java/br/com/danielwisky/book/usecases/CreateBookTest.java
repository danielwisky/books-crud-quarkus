package br.com.danielwisky.book.usecases;

import static org.mockito.Mockito.verify;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.gateways.BookDataGateway;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class CreateBookTest {

  @Inject
  private CreateBook createBook;

  @InjectMock
  private BookDataGateway bookDataGateway;

  @Test
  void shouldExecute() {
    final Book book = new Book();
    createBook.execute(book);
    verify(bookDataGateway).save(book);
  }
}
