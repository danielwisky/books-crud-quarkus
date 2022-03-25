package br.com.danielwisky.book.gateways.inputs.http;

import static javax.ws.rs.core.MediaType.APPLICATION_JSON;

import br.com.danielwisky.book.domains.Book;
import br.com.danielwisky.book.domains.exceptions.ResourceNotFoundException;
import br.com.danielwisky.book.gateways.inputs.http.resources.request.BookRequest;
import br.com.danielwisky.book.gateways.inputs.http.resources.response.BookResponse;
import br.com.danielwisky.book.usecases.CreateBook;
import br.com.danielwisky.book.usecases.DeleteBook;
import br.com.danielwisky.book.usecases.FindBook;
import br.com.danielwisky.book.usecases.UpdateBook;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Path("/api/v1/books")
@Consumes(APPLICATION_JSON)
@Produces(APPLICATION_JSON)
public class BookController {

  @Inject
  private CreateBook createBook;

  @Inject
  private UpdateBook updateBook;

  @Inject
  private DeleteBook deleteBook;

  @Inject
  private FindBook findBook;

  @POST
  public Response create(@Valid final BookRequest bookRequest) {
    final Book bookCreated = createBook.execute(bookRequest.toDomain());
    return Response.ok(new BookResponse(bookCreated)).build();
  }

  @PUT
  @Path("/{id}")
  public Response update(
      @PathParam("id") final String id,
      @Valid final BookRequest bookRequest) {

    try {
      return Response.ok(new BookResponse(updateBook.execute(id, bookRequest.toDomain()))).build();
    } catch (ResourceNotFoundException ex) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }

  @DELETE
  @Path("/{id}")
  public Response delete(@PathParam("id") final String id) {
    deleteBook.execute(id);
    return Response.noContent().build();
  }

  @GET
  public Response list() {

  }

  @GET
  @Path("/{id}")
  public Response get(@PathParam("id") final String id) {
    try {
      return Response.ok(new BookResponse(findBook.execute(id))).build();
    } catch (ResourceNotFoundException ex) {
      return Response.status(Response.Status.NOT_FOUND).build();
    }
  }
}
