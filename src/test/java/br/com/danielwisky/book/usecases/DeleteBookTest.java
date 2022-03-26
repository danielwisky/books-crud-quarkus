package br.com.danielwisky.book.usecases;

import static org.mockito.Mockito.verify;

import br.com.danielwisky.book.gateways.BookDataGateway;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class DeleteBookTest {

  @Inject
  private DeleteBook deleteBook;

  @InjectMock
  private BookDataGateway bookDataGateway;

  @Test
  void shouldExecute() {
    final String id = "623e11492819b87c3a24884c";
    deleteBook.execute(id);
    verify(bookDataGateway).deleteById(id);
  }
}
