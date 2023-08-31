package io.ordermanagement.inventory.service;

import java.util.List;

import io.ordermanagement.inventory.model.Pseudo;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

public interface IPseudoService {
	
	public Pseudo findById(Integer id);
	
	public Pseudo findByName(String pseudoName);
	
	public List<Pseudo> findAll(Page page, Sort sort);
}
