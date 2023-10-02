package io.ordermanagement.inventory.model;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

import org.hibernate.annotations.GenericGenerator;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Entity
@Table(name = "products",uniqueConstraints = @UniqueConstraint(columnNames = "itemId"))
@RegisterForReflection
public class Product extends PanacheEntityBase {
	@Id
    @SequenceGenerator(
            name = "productsSequence",
            sequenceName = "products_id_seq",
            allocationSize = 1,
            initialValue = 7)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "productsSequence") 
	private Integer itemId;
	
	@Column(length = 60)
	private String name;
	
	@Column(length = 255)
	private String description;

	@Column(length = 255)
	private String location;

	@Column
	private Integer pseudoId;
	
	@Column
    private int quantity;
	@Column
    private String link;

	@Column
    private double price;

	@Column
    private String category;
	
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public Integer getItemId() {
		return itemId;
	}

	public Integer getPseudoId() {
		return pseudoId;
	}
	public void setPseudoId(Integer psuedoId) {
		this.pseudoId = psuedoId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	
	@Override
	public String toString() {
		return "Product [itemId=" + itemId + ", name=" + name + ", description=" + description + ", location="
				+ location + ", pseudoId=" + pseudoId + ", quantity=" + quantity + ", link=" + link + ", price=" + price
				+ ", category=" + category + "]";
	}


	
}
