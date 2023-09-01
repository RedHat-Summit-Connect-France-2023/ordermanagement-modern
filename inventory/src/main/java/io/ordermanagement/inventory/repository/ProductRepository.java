package io.ordermanagement.inventory.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.ordermanagement.inventory.model.Product;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class ProductRepository implements PanacheRepository<Product> {

	private static Logger logger = Logger.getLogger( ProductRepository.class.getName() );
	
	@Inject
	Tracer tracer;
	
	public Product findById(Integer id) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering ProductRepository.findById()");
		Product p = find("id", id).firstResult();
		childSpan.finish();
		return p;
	}

	public List<Product> findByUserId(Integer userId) {

		List<Product> p = Product.list("userId", userId);
		return p;
	}
	
	public List<Product> findAll(Page page, Sort sort) {
		Span childSpan = tracer.buildSpan("findAll").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering ProductRepository.findAll()");
		List<Product> p = Product.findAll(sort)
				.page(page)
				.list();
		childSpan.finish();
		return p;
	}

}
