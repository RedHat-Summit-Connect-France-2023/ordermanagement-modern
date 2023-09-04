package io.ordermanagement.inventory.controller;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import io.ordermanagement.inventory.model.Product;
import io.ordermanagement.inventory.service.IProductService;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectMock;


@QuarkusTest
public class ProductControllerTest {
	
	@InjectMock
	IProductService productService;
	
	Product product;
	
	@BeforeEach
	void initProduct() {
		product = new Product();
		product.setItemId(new Integer(1));
		product.setName("Test");
		product.setDescription("Test Product");
		product.setPrice(200);
		product.setLocation("france");
		product.setQuantity(20);
		product.setLink("http://localhost:8080/test");
	}

	@Test
	public void getByIdExisting() {
		
		when(productService.findById(329299)).thenReturn(product);
		
		given()
			.when().get("/products/329299")
			.then()
			.statusCode(00)
			.body("itemId", is(329299))
			.body("name", is("http://maps.google.com/?q=Raleigh"))
			.body("description", is("16 oz. Vortex Tumbler"))
			.body("price",is(100))
			.body("location",is("Raleigh"))
			.body("quantity",is(736))
			.body("link", is("http://maps.google.com/?q=Raleigh"));		
	}

	@Test
	public void getByIdNonExisting() {
		
		when(productService.findById(2)).thenReturn(null);
		
		given()
			.when().get("/products/2")
			.then()
			.statusCode(404);		
	}



}
