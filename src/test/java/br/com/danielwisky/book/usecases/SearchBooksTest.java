package br.com.danielwisky.book.usecases;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.BookFilter;
import br.com.danielwisky.book.domains.Page;
import br.com.danielwisky.book.gateways.BookDataGateway;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;
import javax.inject.Inject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

@QuarkusTest
public class SearchBooksTest {

  @Inject
  private SearchBooks searchBooks;

  @InjectMock
  private BookDataGateway bookDataGateway;

  @Test
  void shouldExecute() {
    final BookFilter bookFilter = new BookFilter();
    when(bookDataGateway.search(bookFilter)).thenReturn(new Page<>());
    final Page<Book> pageResult = searchBooks.execute(bookFilter);
    assertNotNull(pageResult);
  }
}
