package io.ordermanagement.order.model;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.JoinColumn;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
@Entity
@Table(name = "order_items")
@RegisterForReflection
public class OrderItem  extends PanacheEntityBase{
	private static final long serialVersionUID = 64565445665456666L;

	@Id
	@Column(name="ID")
	@GeneratedValue
	private long id;

	@ManyToOne(targetEntity = Order.class)
    @JoinColumn(name = "order_id")
	private Order order;


	@Column
	private int quantity;

	@Column(name = "product_itemId")
	private String productId;
	@Column
	private double price;

	

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public OrderItem() {}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return "OrderItem [id=" + id + ", quantity=" + quantity + ", productId=" + productId + ", price=" + price + "]";
	}

	

}