package com.Ecommerce.Service;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import com.Ecommerce.response.Response;
import com.Ecommerce.DTO.ProductDTO;
import com.Ecommerce.Model.ProductDetails;
import com.Ecommerce.Repository.EcommerceRepository;


@Service
public class EcommerceService {
	
	@Autowired
	private EcommerceRepository eCommerceRepository;

	public Response<ProductDetails> createProduct(ProductDTO productDTO) {

		Response<ProductDetails> response = new Response<ProductDetails>();
		ProductDetails productDetails = new ProductDetails();
		try {
		productDetails.setName(productDTO.getName());
		productDetails.setDescription(productDTO.getDescription());
		productDetails.setOriginalPrice(productDTO.getPrice());
		productDetails.setQuantityAvailable(productDTO.getQuantityAvailable());
		eCommerceRepository.save(productDetails);
		response.setStatus(HttpStatus.OK.value());
		response.setData(productDetails);
		response.setMessage("Product Created Successfully");
		
		}catch(Exception e) {
			response.setStatus(HttpStatus.NOT_IMPLEMENTED.value());
			response.setMessage("Please Enter Valid Product Details");
		}
		return response;

	}

	public Response<ProductDetails> getAllProduct(int productId) {
		
		Response<ProductDetails> response = new Response<ProductDetails>();
		ProductDetails finfindByProductId = eCommerceRepository.findByProductId(productId);
		if(finfindByProductId != null) {
			response.setData(finfindByProductId);
			response.setStatus(HttpStatus.OK.value());
		}else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.setMessage("ProductId : "+ productId +" is not available");
		}
		return response;
	}

	public Response<ProductDetails> updateProduct(ProductDTO productDTO, int productId) {
		
		Response<ProductDetails> response = new Response<ProductDetails>();
		ProductDetails findByProductId = eCommerceRepository.findByProductId(productId);
		if(findByProductId != null) {
			findByProductId.setName(productDTO.getName());
			findByProductId.setDescription(productDTO.getDescription());
			findByProductId.setOriginalPrice(productDTO.getPrice());
			findByProductId.setQuantityAvailable(productDTO.getQuantityAvailable());
			response.setData(findByProductId);
			response.setStatus(HttpStatus.OK.value());
			response.setMessage("Product Updated Successfully");
		}else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.setMessage("Please Enter Valid Prouct Details");
		}

		return response;
	}

	public String deleteProduct(int productId) {
		
		eCommerceRepository.deleteByProductId(productId);
		return "Product Deleted Successfully";
	}


	public Response<ProductDetails> modifyProduct(int productId, String modifyType, double value) {
		
		Response<ProductDetails> response = new Response<ProductDetails>();
		ProductDetails findByProductId = eCommerceRepository.findByProductId(productId);
		double price = findByProductId.getOriginalPrice();
		if(findByProductId != null) {
			double calculateDiscountOrTax = calculateDiscountOrTax(productId, modifyType, price, value);
			findByProductId.setPrice(calculateDiscountOrTax);
			findByProductId.setModifiedType(modifyType);
			findByProductId.setAppliedValue(value);
			eCommerceRepository.save(findByProductId);
			response.setData(findByProductId);
			response.setStatus(HttpStatus.OK.value());
		}else {
			response.setStatus(HttpStatus.NOT_FOUND.value());
			response.setMessage("Product Not Found");
		}
		
		return response;
	}
	
	
	private static double calculateDiscountOrTax(int productId, String modifyType, double originalPrice, double value) {
			if(modifyType.equalsIgnoreCase("discount")) {
				 return originalPrice - (originalPrice * value / 100);
			}else if (modifyType.equalsIgnoreCase("tax")) {
				return originalPrice + (originalPrice * value / 100);
			}
		
		return originalPrice;
		
	}
	
	}  