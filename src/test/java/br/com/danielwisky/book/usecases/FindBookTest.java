package br.com.danielwisky.book.usecases;

import static java.util.Optional.empty;
import static java.util.Optional.of;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.book.gateways.BookDataGateway;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class FindBookTest {

  @Inject
  private FindBook findBook;

  @InjectMock
  private BookDataGateway bookDataGateway;

  @Test
  void shouldExecute() {
    final String id = "623e11492819b87c3a24884c";
    when(bookDataGateway.findById(id)).thenReturn(of(new Book()));
    final Book book = findBook.execute(id);
    assertNotNull(book);
  }

  @Test
  void shouldThrowResourceNotFoundExceptionWhenBookNotFound() {
    final String id = "623e11492819b87c3a24884c";
    when(bookDataGateway.findById(id)).thenReturn(empty());
    assertThrows(ResourceNotFoundException.class, () -> findBook.execute(id));
  }
}
