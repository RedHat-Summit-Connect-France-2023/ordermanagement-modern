package io.ordermanagement.inventory.service;


import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.when;

import jakarta.inject.Inject;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.ordermanagement.inventory.model.Product;
import io.ordermanagement.inventory.repository.ProductRepository;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;


@QuarkusTest
public class ProductServiceTest {

	@Inject
	ProductService service;
	
	@InjectMock
	ProductRepository productRepository;
	
	Product product;
	
	@BeforeEach
	void initProduct() {
		product = new Product();
		product.setItemId("2334");
		product.setName("Test");
		product.setDescription("Test Product");
		product.setPrice(200);
		product.setLocation("france");
		product.setQuantity(20);
		product.setLink("http://localhost:8080/test");
	}
	
	@Test
	public void findByIdExistingTest() {
		
		when(productRepository.findById("1")).thenReturn(product);
		
		Product p = service.findById("1");
		assertThat(p.getItemId(), equalTo("1"));
		assertThat(p.getName(), equalTo("Test"));
		assertThat(p.getDescription(), equalTo("Test Product"));
		assertThat(p.getPrice(), equalTo(200));
		assertThat(p.getLocation(), equalTo("france"));
		assertThat(p.getQuantity(), equalTo(20));
		assertThat(p.getLink(), equalTo("http://localhost:8080/test"));




	}
	
	@Test
	public void findByIdNonExistingTest() {
		
		when(productRepository.findById("2")).thenReturn(null);
		
		Product p = service.findById("2");
		assertThat(p, is(nullValue()));
	}
	
}
