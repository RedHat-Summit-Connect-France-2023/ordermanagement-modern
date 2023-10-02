package io.ordermanagement.repository;

import org.jboss.logging.Logger;

import com.oracle.svm.core.annotate.Inject;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.ordermanagement.entity.ShoppingCart;
import io.quarkus.hibernate.reactive.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ShoppingCartRepository implements PanacheRepositoryBase<ShoppingCart, Long> {
    private static Logger logger = Logger.getLogger( ShoppingCartRepository.class.getName() );
	
	@Inject
	Tracer tracer;
	
	public ShoppingCart findById(long id) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering OrderRepository.findById()");
		ShoppingCart sc = find("id", id).firstResult();
		childSpan.finish();
		return sc;
	}
}
