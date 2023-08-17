package io.ordermanagement.entity;


import io.quarkus.hibernate.reactive.panache.PanacheEntityBase;
import io.smallrye.common.constraint.NotNull;
import io.smallrye.mutiny.Multi;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.json.bind.annotation.JsonbTransient;
import jakarta.persistence.Cacheable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import java.math.BigDecimal;

import jakarta.persistence.NoArgsConstructor;
import jakarta.persistence.AllArgsConstructor;
import jakarta.persistence.Builder;
import jakarta.persistence.NamedQueries;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Cacheable
@Getter
@Table(indexes = {
        @Index(name = "shopping_cart_item_cart_product_index", columnList = "cart_id, product_id"),
})
public class ShoppingCartItem extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonbTransient
    public Long id;

    @Column(name = "total_price", precision = 21, scale = 2)
    public BigDecimal totalPrice;

    @Column(name = "quantity")
    public Integer quantity;
   
    @Column(name = "promo_savings")
    public double promoSavings;


    @ManyToOne(fetch = FetchType.LAZY)
    @NotNull
    @JsonbTransient
    public ShoppingCart cart;

    @ManyToOne(fetch = FetchType.LAZY)
    @OnDelete(action = OnDeleteAction.CASCADE)
    public Product product;


    public static Multi<ShoppingCartItem> findByCartIdByProductId(Long cartId, Long productId) {
        return stream("cart.id = ?1 and product.id = ?2", cartId, productId);
    }

    
    public String toString() {
        return this.getClass().getSimpleName() + "<" + this.id + ">";
    }
    
}
