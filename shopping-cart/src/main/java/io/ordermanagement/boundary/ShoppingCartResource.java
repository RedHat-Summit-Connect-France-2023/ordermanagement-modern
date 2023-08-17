package io.ordermanagement.boundary;

import io.ordermanagement.entity.ShoppingCart;
import io.smallrye.mutiny.Uni;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

@Path("/carts")
public class ShoppingCartResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getCarts() {
        return ShoppingCart.getAllShoppingCarts()
                .onItem().transform(shoppingcarts -> Response.ok(shoppingcarts))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getSingleCart(@PathParam("id") Long id) {
        return ShoppingCart.findByShoppingCartId(id)
                .onItem().ifNotNull().transform(cart -> Response.ok(cart).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);

    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Uni<Response> createShoppingCart(ShoppingCart shoppingCart) {
        if (shoppingCart == null || shoppingCart.name == null) {
            throw new WebApplicationException("ShoppingCart name was not set on request.", 422);
        }
        return ShoppingCart.createShoppingCart(shoppingCart)
                .onItem().transform(id -> URI.create("/v1/carts/" + id.id))
                .onItem().transform(uri -> Response.created(uri))
                .onItem().transform(Response.ResponseBuilder::build);
    }

    @PUT
    @Path("{cartid}/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> update(@PathParam("cartid") Long id, @PathParam("productid") Long product) {
        return ShoppingCart.addProductToShoppingCart(id, product)
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);

    }

    @DELETE
    @Path("{cartid}/{productid}")
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> delete(@PathParam("cartid") Long id, @PathParam("productid") Long product) {
        return ShoppingCart.deleteProductFromShoppingCart(id, product)
                .onItem().ifNotNull().transform(entity -> Response.ok(entity).build())
                .onItem().ifNull().continueWith(Response.ok().status(NOT_FOUND)::build);
    }
}
