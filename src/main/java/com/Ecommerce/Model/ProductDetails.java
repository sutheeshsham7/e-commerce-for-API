package com.Ecommerce.Model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "PRODUCT_DETAILS" ,schema = "e-commerce")
@Data
public class ProductDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "PD_ID")
	private Integer productId;
	
	@Column(name = "PD_NAME")
	private String name;
	
	@Column(name = "PD_DESCRIPTION")
	private String description;
	
	@Column(name = "PD_PRICE")
	private Double price;
	
	@Column(name = "PD_ORIGINAL_PRICE")
	private Double originalPrice;
	
	@Column(name = "PD_MODIFIED_TYPE")
	private String modifiedType;
	
	@Column(name = "PD_APPLIED_VALUE")
	private Double appliedValue;
	
	@Column(name = "PD_QUANTITY_AVAILABLE")
	private Integer quantityAvailable;
}
