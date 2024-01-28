package com.Ecommerce.Repository;

import org.springframework.data.repository.CrudRepository;
import com.Ecommerce.Model.ProductDetails;

public interface EcommerceRepository extends CrudRepository<ProductDetails,Integer> {
	
	public ProductDetails findByProductId(Integer productId);
	
	public void deleteByProductId(Integer productId);

}
