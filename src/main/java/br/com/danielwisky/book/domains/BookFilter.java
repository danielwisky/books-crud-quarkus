package br.com.danielwisky.book.domains;

import java.util.Objects;
import java.util.Optional;
import lombok.Builder;

@Builder
public class BookFilter {

  private static final String EMPTY = "";
  private static final int DEFAULT_PAGE = 0;
  private static final int DEFAULT_SIZE = 20;

  private String title;
  private String author;
  private Integer page;
  private Integer size;

  public String getTitle() {
    return Objects.isNull(title) ? EMPTY : title;
  }

  public String getAuthor() {
    return Objects.isNull(author) ? EMPTY : author;
  }

  public Integer getPage() {
    return Optional.ofNullable(page).orElse(DEFAULT_PAGE);
  }

  public Integer getSize() {
    return Optional.ofNullable(size).orElse(DEFAULT_SIZE);
  }
}
