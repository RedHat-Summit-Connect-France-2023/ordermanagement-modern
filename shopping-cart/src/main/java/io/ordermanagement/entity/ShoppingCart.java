package io.ordermanagement.entity;

import io.quarkus.hibernate.reactive.panache.Panache;
import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.tuples.Tuple4;

import org.hibernate.reactive.mutiny.Mutiny;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.NamedQueries;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;



import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "shopping_cart")
public class ShoppingCart{

	private static final long serialVersionUID = -1108043957592113528L;

    @Id
    private String customerName;

    @Column
	private double cartItemTotal;

    @Column
	private double cartItemPromoSavings;
	
    @Column
	private double shippingTotal;
	
    @Column
	private double shippingPromoSavings;
	
    @Column
	private double cartTotal;
			
	private List<ShoppingCartItem> shoppingCartItemList = new ArrayList<ShoppingCartItem>();

    

	public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public ShoppingCart() {
		
	}
	
	public List<ShoppingCartItem> getShoppingCartItemList() {
		return shoppingCartItemList;
	}

	public void setShoppingCartItemList(List<ShoppingCartItem> shoppingCartItemList) {
		this.shoppingCartItemList = shoppingCartItemList;
	}

	public void resetShoppingCartItemList() {
		shoppingCartItemList = new ArrayList<ShoppingCartItem>();
	}

	public void addShoppingCartItem(ShoppingCartItem sci) {
		
		if ( sci != null ) {
			
			shoppingCartItemList.add(sci);
			
		}
		
	}
	
	public boolean removeShoppingCartItem(ShoppingCartItem sci) {
		
		boolean removed = false;
		
		if ( sci != null ) {
			
			removed = shoppingCartItemList.remove(sci);
			
		}
		
		return removed;
		
	}

	public double getCartItemTotal() {
		return cartItemTotal;
	}

	public void setCartItemTotal(double cartItemTotal) {
		this.cartItemTotal = cartItemTotal;
	}

	public double getShippingTotal() {
		return shippingTotal;
	}

	public void setShippingTotal(double shippingTotal) {
		this.shippingTotal = shippingTotal;
	}

	public double getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(double cartTotal) {
		this.cartTotal = cartTotal;
	}

	public double getCartItemPromoSavings() {
		return cartItemPromoSavings;
	}

	public void setCartItemPromoSavings(double cartItemPromoSavings) {
		this.cartItemPromoSavings = cartItemPromoSavings;
	}

	public double getShippingPromoSavings() {
		return shippingPromoSavings;
	}

	public void setShippingPromoSavings(double shippingPromoSavings) {
		this.shippingPromoSavings = shippingPromoSavings;
	}

    @Override
    public String toString() {
        return "ShoppingCart [cartItemTotal=" + cartItemTotal + ", cartItemPromoSavings=" + cartItemPromoSavings
                + ", shippingTotal=" + shippingTotal + ", shippingPromoSavings=" + shippingPromoSavings + ", cartTotal="
                + cartTotal + ", shoppingCartItemList=" + shoppingCartItemList + ", customerName=" + customerName + "]";
    }


}
