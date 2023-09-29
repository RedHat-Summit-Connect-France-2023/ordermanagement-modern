package io.ordermanagement.entity;



public class Product  {
	
	private Integer itemId;
	
	private String name;
	
	private String description;

	private String location;

	private Integer pseudoId;
	
    private int quantity;
    private String link;

    private double price;

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
	public void setItemId(Integer itemId) {
		this.itemId = itemId;
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
