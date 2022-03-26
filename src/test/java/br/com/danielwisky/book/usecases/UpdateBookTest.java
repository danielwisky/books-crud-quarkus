package br.com.danielwisky.book.usecases;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.book.gateways.BookDataGateway;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class UpdateBookTest {

  @Inject
  private UpdateBook updateBook;

  @InjectMock
  private BookDataGateway bookDataGateway;

  @Test
  void shouldExecute() {
    final Book book = new Book();
    final String id = "623e11492819b87c3a24884c";
    when(bookDataGateway.findById(id)).thenReturn(of(book));
    updateBook.execute(id, book);
    verify(bookDataGateway).save(book);
  }

  @Test
  void shouldThrowResourceNotFoundExceptionWhenBookNotFound() {
    final Book book = new Book();
    final String id = "623e11492819b87c3a24884c";
    when(bookDataGateway.findById(id)).thenReturn(empty());
    assertThrows(ResourceNotFoundException.class, () -> updateBook.execute(id, book));
  }
}
