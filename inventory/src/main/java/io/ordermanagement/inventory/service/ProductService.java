package io.ordermanagement.inventory.service;


import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;

import org.jboss.logging.Logger;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.ordermanagement.inventory.model.Product;
import io.ordermanagement.inventory.repository.ProductRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@Transactional
@ApplicationScoped
public class ProductService implements IProductService {
	
	private static Logger logger = Logger.getLogger( ProductService.class.getName() );
	
	@Inject
	ProductRepository repository;
	
	@Inject
	Tracer tracer;
	
	/**
	 * Finds a {@link Product} using its {@code id} as search criteria
	 * @param id The {@link Product} {@code id}
	 * @return The {@link Product} with the supplied {@code id}, {@literal null} if no {@link Product} is found. 
	 */
	public Product findById(Integer id) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Service");
		logger.debug("Entering ProductService.findById()");
		Product p = repository.findById(id);
		childSpan.finish();
		return p;
	}

		@Override
	public List<Product> findByPseudoId(Integer pseudoId) {
		Span childSpan = tracer.buildSpan("findAll").start();
		childSpan.setTag("layer", "Service");
		logger.debug("Entering ProductService.findAll()");
		List<Product> p = repository.findByPseudoId(pseudoId);
		childSpan.finish();
		return p;
	}

	@Override
	public List<Product> findAll(Page page, Sort sort) {
		Span childSpan = tracer.buildSpan("findAll").start();
		childSpan.setTag("layer", "Service");
		logger.debug("Entering ProductService.findAll()");
		List<Product> p = repository.findAll(page, sort);
		childSpan.finish();
		return p;
	}
	
	
}
