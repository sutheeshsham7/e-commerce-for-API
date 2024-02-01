package com.Ecommerce.Controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.Ecommerce.DTO.ProductDTO;
import com.Ecommerce.Model.ProductDetails;
import com.Ecommerce.Service.EcommerceService;
import com.Ecommerce.response.Response;


@RestController
@RequestMapping("/product")
public class EcommerceController {

	@Autowired
	private EcommerceService eCommerceService;
	
	
	@PostMapping("/create")
	public Response<ProductDetails> createProduct(@RequestBody ProductDTO productdto) {
		
		return eCommerceService.createProduct(productdto);
	}
	
	@GetMapping("/read/{ProductId}")
	public Response<ProductDetails> getAllProduct(@PathVariable int ProductId) {
		
		return eCommerceService.getAllProduct(ProductId);
	}
	
	@PutMapping("/update/{productId}")
	public Response<ProductDetails> updateProduct(@RequestBody ProductDTO productdto,
												  @PathVariable int productId){
		
		return eCommerceService.updateProduct(productdto, productId);
	}
	
	@DeleteMapping("/delete/{ProductId}")
	public String deleteProduct(@PathVariable int ProductId) {
		
		return eCommerceService.deleteProduct(ProductId);
		
	}
	
	@PutMapping("/modify/{productId}")
	public Response<ProductDetails> modifyProduct(@PathVariable int productId,
												  @RequestHeader String modifyType,
												  @RequestHeader double value){
		
		return eCommerceService.modifyProduct(productId, modifyType, value);
	}

	
	@GetMapping("/get-all")
	public List<ProductDetails> getAllProduct(){
		
		return eCommerceService.getAllProduct();
		
	}
	
	}  
