package io.ordermanagement.order.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.ordermanagement.order.model.Order;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class OrderRepository implements PanacheRepositoryBase<Order, Long> {

	private static Logger logger = Logger.getLogger( OrderRepository.class.getName() );
	
	@Inject
	Tracer tracer;
	
	public Order findById(String id) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering OrderRepository.findById()");
		Order o = find("id", id).firstResult();
		childSpan.finish();
		return o;
	}
	
	public List<Order> findAll(Page page, Sort sort) {
		Span childSpan = tracer.buildSpan("findAll").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering OrderRepository.findAll()");
		List<Order> o = Order.findAll(sort)
				.page(page)
				.list();
		childSpan.finish();
		return o;
	}

}
