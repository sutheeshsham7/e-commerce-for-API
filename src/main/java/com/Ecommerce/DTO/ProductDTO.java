package com.Ecommerce.DTO;

import lombok.Data;

@Data
public class ProductDTO {
	
	private String name;
	
	private String description;
	
	private double price;
	
	private int quantityAvailable;

}
