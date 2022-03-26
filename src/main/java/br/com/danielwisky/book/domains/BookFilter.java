package br.com.danielwisky.book.domains;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BookFilter {

  private String title;
  private String author;
  private Integer page;
  private Integer size;
}
