package br.com.danielwisky.book.gateways.inputs.http.resources.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse<T> {

  private List<T> content;
  private Integer page;
  private Integer size;
  private Long totalElements;
  private Integer totalPages;
}
