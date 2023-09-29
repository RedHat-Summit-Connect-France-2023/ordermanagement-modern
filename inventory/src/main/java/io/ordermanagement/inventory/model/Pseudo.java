package io.ordermanagement.inventory.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.runtime.annotations.RegisterForReflection;

@Entity
@Table(name = "pseudo",uniqueConstraints = @UniqueConstraint(columnNames = "pseudoName"))
@RegisterForReflection
public class Pseudo extends PanacheEntityBase {
	@Id
    @SequenceGenerator(
            name = "pseudoSequence",
            sequenceName = "pseudo_id_seq",
            allocationSize = 1,
            initialValue = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pseudoSequence") 

	private Integer pseudoId;
	
	@Column(length = 60)
	private String pseudoName;
	
	
	public Integer getPseudoId() {
		return pseudoId;
	}
	public void Integer(Integer pseudoId) {
		this.pseudoId = pseudoId;
	}
	public String getPseudoName() {
		return pseudoName;
	}
	public void setPseudoName(String pseudoName) {
		this.pseudoName = pseudoName;
	}
	
	@Override
	public String toString() {
		return "Pseudo [pseudoId=" + pseudoId + ", pseudoName=" + pseudoName +"]";
	}

	
}
