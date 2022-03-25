package br.com.danielwisky.book.gateways;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.BookFilter;
import br.com.danielwisky.book.domains.Page;
import java.util.Optional;

public interface BookDataGateway {

  Book save(Book book);

  void deleteById(String id);

  Optional<Book> findById(String id);

  Page<Book> search(BookFilter filter);
}
