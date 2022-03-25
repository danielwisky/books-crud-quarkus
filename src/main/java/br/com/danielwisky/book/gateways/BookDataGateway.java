package br.com.danielwisky.book.gateways;

import br.com.danielwisky.book.domains.Book;
import java.util.Optional;

public interface BookDataGateway {

  Book save(Book book);

  Optional<Book> findById(String id);

  void deleteById(String id);
}
