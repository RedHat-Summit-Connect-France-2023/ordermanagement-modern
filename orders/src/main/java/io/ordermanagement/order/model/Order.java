package io.ordermanagement.order.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;
@Entity
@Table(name = "orders")
@RegisterForReflection
public class Order extends PanacheEntityBase{


	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long orderId;
	
	@Column(length = 255)
	private String customerName;
	@Column(length = 255)
	private String customerEmail;
	@Column
	private double orderValue;
	@Column
	private double retailPrice;
	@Column
	private double discount;
	@Column
	private double shippingFee;
	@Column
	private double shippingDiscount;

	@Column(name="total_price")

	
	@OneToMany(mappedBy="order", fetch = FetchType.EAGER, orphanRemoval = true)
	public List<OrderItem> itemList;


	public long getOrderId() {
		return orderId;
	}

	public void setOrderId(long orderId) {
		this.orderId = orderId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustomerEmail() {
		return customerEmail;
	}

	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	public double getOrderValue() {
		return orderValue;
	}

	public void setOrderValue(double orderValue) {
		this.orderValue = orderValue;
	}

	public double getRetailPrice() {
		return retailPrice;
	}

	public void setRetailPrice(double retailPrice) {
		this.retailPrice = retailPrice;
	}

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public double getShippingFee() {
		return shippingFee;
	}

	public void setShippingFee(double shippingFee) {
		this.shippingFee = shippingFee;
	}

	public double getShippingDiscount() {
		return shippingDiscount;
	}

	public void setShippingDiscount(double shippingDiscount) {
		this.shippingDiscount = shippingDiscount;
	}

	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	public List<OrderItem> getItemList() {
		return itemList;
	}

	@Override
	public String toString() {
		return "Order [orderId=" + orderId
				+ ", customerName=" + customerName
				+ ", customerEmail=" + customerEmail
				+ ", orderValue=" + orderValue
				+ ", retailPrice=" + retailPrice
				+ ", discount=" + discount
				+ ", shippingFee=" + shippingFee
				+ ", shippingDiscount=" + shippingDiscount
				+ ", itemList=" + itemList 
				+ "]";
	}

}