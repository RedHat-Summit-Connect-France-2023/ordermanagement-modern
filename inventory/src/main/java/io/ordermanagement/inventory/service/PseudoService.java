package io.ordermanagement.inventory.service;


import java.util.List;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManagerFactory;
import jakarta.transaction.Transactional;

import org.jboss.logging.Logger;

import io.opentracing.Span;
import io.opentracing.Tracer;
import io.ordermanagement.inventory.model.Pseudo;
import io.ordermanagement.inventory.repository.PseudoRepository;
import io.quarkus.hibernate.orm.PersistenceUnit;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

@Transactional
@ApplicationScoped
public class PseudoService implements IPseudoService {
	
	private static Logger logger = Logger.getLogger( PseudoService.class.getName() );
	
	@Inject
	PseudoRepository repository;

	
	@Inject
	Tracer tracer;
	
	/**
	 * Finds a {@link Pseudo} using its {@code id} as search criteria
	 * @param id The {@link Pseudo} {@code id}
	 * @return The {@link Pseudo} with the supplied {@code id}, {@literal null} if no {@link Pseudo} is found. 
	 */
	public Pseudo findById(Integer id) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering ProductRepository.findById()");
		Pseudo p =repository.findById(id);
		childSpan.finish();
		return p;
	}


	public Pseudo findByName(String pseudoName) {
		Span childSpan = tracer.buildSpan("findById").start();
		childSpan.setTag("layer", "Repository");
		logger.debug("Entering Pseudo.findById()");
		pseudoName.toUpperCase();
		Pseudo p = repository.findByName(pseudoName);
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
