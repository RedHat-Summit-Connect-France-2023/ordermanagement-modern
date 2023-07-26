package io.ordermanagement.order.service;

import java.util.List;

import io.ordermanagement.order.model.Order;
import io.quarkus.panache.common.Page;
import io.quarkus.panache.common.Sort;

public interface IOrderService {
	
	public Order findById(String id);
	
	public List<Order> findAll(Page page, Sort sort);
}
