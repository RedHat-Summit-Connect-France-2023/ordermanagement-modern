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
import io.ordermanagement.inventory.model.Pseudo;
import io.ordermanagement.inventory.service.IPseudoService;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;


@Path("/pseudos")
@ApplicationScoped
public class PseudoController {
	private static Logger logger = Logger.getLogger( PseudoController.class.getName() );
	
	@Inject
	IPseudoService pseudoService;
	
	@Inject
	Tracer tracer;
	
	@GET
    @Path("/{id}")
    @Produces({ MediaType.APPLICATION_JSON })
	@Traced
    public Pseudo getById(@PathParam("id") Integer id) {
		Pseudo p;
		logger.debug("Entering PseudoController.getById()");
		p = pseudoService.findById(id);
		if (p == null) {
			logger.error("Pseudo not found");
		}
		return p;    
    }
	
	@GET
    @Path("/{pseudoName}")
    @Produces({ MediaType.APPLICATION_JSON })
	@Traced
    public Pseudo getByName(@PathParam("pseudoName") String pseudoName) {
		Pseudo p;
		logger.debug("Entering PseudoController.getById()");
		p = pseudoService.findByName(pseudoName);
		if (p == null) {
			logger.error("Pseudo not found");
		}
		return p;    
    }

	@GET
	@Produces({ MediaType.APPLICATION_JSON })
	public Response findAll(@QueryParam("sort") String sortString,
            @QueryParam("page") @DefaultValue("0") int pageIndex,
            @QueryParam("size") @DefaultValue("100") int pageSize) {
		Page page = Page.of(pageIndex, pageSize);
        Sort sort = getSortFromQuery(sortString);
        return Response.ok(pseudoService.findAll(page, sort)).build();
	}

	@POST
    @Transactional
    public Response create(Pseudo pseudo) {
        pseudo.persist();
       // return Response.created(URI.create("/pseudos/" + pseudo.getPseudoId())).build();
	   return  Response.status(200).entity(pseudo.getPseudoId().toString()).build();
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
