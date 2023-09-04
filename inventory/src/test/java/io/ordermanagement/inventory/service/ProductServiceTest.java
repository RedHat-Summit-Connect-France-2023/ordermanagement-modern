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
		product.setItemId(new Integer(2334));
		product.setName("Test");
		product.setDescription("Test Product");
		product.setPrice(200);
		product.setLocation("france");
		product.setQuantity(20);
		product.setLink("http://localhost:8080/test");
	}
	
	@Test
	public void findByIdExistingTest() {
		
		when(productRepository.findById(329299)).thenReturn(product);
		
		Product p = service.findById(329299);
		assertThat(p.getItemId(), equalTo(329299));
		assertThat(p.getName(), equalTo("16 oz. Vortex Tumbler"));
		assertThat(p.getDescription(), equalTo("Test Product"));
		assertThat(p.getPrice(), equalTo(100));
		assertThat(p.getLocation(), equalTo("Raleigh"));
		assertThat(p.getQuantity(), equalTo(736));
		assertThat(p.getLink(), equalTo("http://maps.google.com/?q=Raleigh"));

	}

	@Test
	public void findByIdNonExistingTest() {
		
		when(productRepository.findById(2)).thenReturn(null);
		
		Product p = service.findById(2);
		assertThat(p, is(nullValue()));
	}

	
	
}
