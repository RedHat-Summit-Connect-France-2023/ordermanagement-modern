package io.ordermanagement.inventory.controller;

import java.net.URI;
import java.util.Arrays;
import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.eclipse.microprofile.opentracing.Traced;
import org.jboss.logging.Logger;

import io.opentracing.Tracer;
import io.ordermanagement.inventory.model.Product;
import io.ordermanagement.inventory.service.IProductService;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

import io.micrometer.core.instrument.MeterRegistry;
import io.micrometer.core.instrument.Tags;


@Path("/products")
@ApplicationScoped
public class ProductController {
	private static Logger logger = Logger.getLogger( ProductController.class.getName() );
	
	@Inject
	IProductService productService;

	@Inject
    MeterRegistry registry;
	
	@Inject
	Tracer tracer;
	
	@GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
	@Traced
    public Product getById(@PathParam("id") Integer id) {
		registry.counter("product_getById_counter", Tags.of("id", id+"")).increment();
		Product p;
		logger.debug("Entering ProductController.getById()");
		p = productService.findById(id);
		if (p == null) {
			logger.error("Product not found");
		}
		return p;    
    }
	
	@GET
    @Path("/pseudo/{pseudoId}")
    @Produces({ MediaType.APPLICATION_JSON })
	@Traced
    public List<Product> getByPseudoId(@PathParam("pseudoId") Integer pseudoId) {
		registry.counter("product_getByPseudoId_counter", Tags.of("PseudoId", pseudoId+"")).increment();

		List<Product> p;
		logger.debug("Entering ProductController.getById()");
		p = productService.findByPseudoId(pseudoId);
		if (p == null) {
			logger.error("User not found");
		}
		return p;

    }

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll(@QueryParam("sort") String sortString,
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("20") int pageSize) {
		registry.counter("product_findAllProduct_counter", Tags.of("products", "products")).increment();
		
		Page page = Page.of(pageIndex, pageSize);
        Sort sort = getSortFromQuery(sortString);
        return Response.ok(productService.findAll(page, sort)).build();
	}

	@POST
    @Transactional
    public Response create(Product product) {
		registry.counter("product_createProduct_counter", Tags.of("productName", product.getName())).increment();
		Product p = productService.findById(product.getItemId());
		if (p == null) {
			product.persist();
		} else {
			p.setCategory(product.getCategory());
			p.setDescription(product.getDescription());
			p.setLink(product.getLink());
			p.setLocation(product.getLocation());
			p.setName(product.getName());
			p.setPrice(product.getPrice());
			p.setQuantity(product.getQuantity());
		}
       
        return Response.created(URI.create("/products/" + product.getItemId())).build();
    }

	@PUT
	@Path("{productId}/{quantiy}")
	@Produces({ MediaType.APPLICATION_JSON })
    @Transactional
    public Response updateQuantiy(@QueryParam("productId") Integer productId, @PathParam("quantity") Integer quantity) {
		String message;
		registry.counter("product_updateQuantity_counter", Tags.of("productId", productId+"")).increment();
		Product p = productService.findById(productId);
		if (p.getQuantity() < quantity) {
			 message = "Only "+p.getQuantity()+"items left";
		} else {
        	message = "Quantiy updated for product "+productId;
			p.setQuantity(quantity); 
		}
		return Response.ok(message).build();
    }


	/**
	 * This method tries to mimic the behavior of Spring MVC's @EnableSpringDataWebSupport annotation when it comes to the sort parameter.
	 * @param sortString The string containing the sort query to be used. Must have the "field,asc/desc" format or the second part of the query will be ignored.
	 * @return The {@link Sort} object with the sort criteria to apply.
	 */
	private Sort getSortFromQuery(String sortString) {
		if (sortString != null && !sortString.equals("")) {
			List<String> sortQuery = Arrays.asList(sortString.split(","));
			if (sortQuery == null || sortQuery.size()== 0 || sortQuery.size() >2) {	
				return null;
			}
			else {
				if (sortQuery.size() == 1) {
					return Sort.by(sortQuery.get(0));
				} else {
					if (sortQuery.get(1).equals("asc")) {
						return Sort.ascending(sortQuery.get(0));
					} else {
						if (sortQuery.get(1).equals("desc")) {
							return Sort.descending(sortQuery.get(0));
						} else {
							return Sort.by(sortQuery.get(0));
						}
					}
				}
			}	
		}
		return null;
	}

}
