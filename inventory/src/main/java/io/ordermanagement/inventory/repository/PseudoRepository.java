package io.ordermanagement.inventory.repository;

import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import org.jboss.logging.Logger;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.ordermanagement.inventory.model.Pseudo;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@ApplicationScoped
public class PseudoRepository implements PanacheRepository<Pseudo> {

	private static Logger logger = Logger.getLogger( PseudoRepository.class.getName() );
	
	@Inject
	Tracer tracer;
	
	public Pseudo findById(Integer id) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering ProductRepository.findById()");
		Pseudo p = find("id", id).firstResult();
		childSpan.finish();
		return p;
	}

	public Pseudo findByName(String pseudoName) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering Pseudo.findById()");
		pseudoName.toUpperCase();
		Pseudo p = find("pseudoName", pseudoName).firstResult();
		childSpan.finish();
		return p;
	}
	
	
	public List<Pseudo> findAll(Page page, Sort sort) {
		Span childSpan = tracer.buildSpan("findAll").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering ProductRepository.findAll()");
		List<Pseudo> p = Pseudo.findAll(sort)
				.page(page)
				.list();
		childSpan.finish();
		return p;
	}

}
