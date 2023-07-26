package io.ordermanagement.order.service;


import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

import org.jboss.logging.Logger;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.ordermanagement.order.model.Order;
import io.ordermanagement.order.repository.OrderRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@Transactional
@ApplicationScoped
public class OrderService implements IOrderService {
	
	private static Logger logger = Logger.getLogger( OrderService.class.getName() );
	
	@Inject
	OrderRepository repository;
	
	@Inject
	Tracer tracer;
	
	/**
	 * Finds a {@link Product} using its {@code id} as search criteria
	 * @param id The {@link Product} {@code id}
	 * @return The {@link Product} with the supplied {@code id}, {@literal null} if no {@link Product} is found. 
	 */
	public Order findById(String id) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Service");
		logger.debug("Entering ProductService.findById()");
		Order o = repository.findById(id);
		childSpan.finish();
		return o;
	}

	@Override
	public List<Order> findAll(Page page, Sort sort) {
		Span childSpan = tracer.buildSpan("findAll").start();
		childSpan.setTag("layer", "Service");
		logger.debug("Entering ProductService.findAll()");
		List<Order> o = repository.findAll(page, sort);
		childSpan.finish();
		return o;
	}
	
	
}
