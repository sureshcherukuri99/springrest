package com.hickory.springboot;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductServiceController {
   private static Map<String, Product> productRepo = new HashMap<>();
   static {
      Product honey = new Product();
      honey.setId("1");
      honey.setName("Honey");
      productRepo.put(honey.getId(), honey);
      
      Product almond = new Product();
      almond.setId("2");
      almond.setName("Almond");
      productRepo.put(almond.getId(), almond);
      
      Product cashew = new Product();
      cashew.setId("3");
      cashew.setName("Cashew");
      productRepo.put(cashew.getId(), cashew);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
   public ResponseEntity<Object> delete(@PathVariable("id") String id) { 
      productRepo.remove(id);
      return new ResponseEntity<>("Product is deleted successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products/{id}", method = RequestMethod.PUT)
   public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) { 
      productRepo.remove(id);
      product.setId(id);
      productRepo.put(id, product);
      return new ResponseEntity<>("Product is updated successsfully", HttpStatus.OK);
   }
   
   @RequestMapping(value = "/products", method = RequestMethod.POST)
   public ResponseEntity<Object> createProduct(@RequestBody Product product) {
      productRepo.put(product.getId(), product);
      return new ResponseEntity<>("Product is created successfully", HttpStatus.CREATED);
   }
   
   @RequestMapping(value = "/products")
   public ResponseEntity<Object> getProduct() {
      return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
   }
   
//   @GetMapping(value = "/product/{id}")
//   public ResponseEntity<Object> get(@PathVariable("id") String id) { 
//	   
//      return new ResponseEntity<>(productRepo.get(id), HttpStatus.OK);
//      
//   }
   
   @GetMapping(value = "/products/{id}")
   public Product getProductbyId(@PathVariable("id") String id) { 
	   
      Product product=productRepo.get(id);
      return product;
      
   }
      
     
	@GetMapping(value="/product")
     public List<Product> getProductbyString(@RequestParam(required = false) String id, @RequestParam(required = false) String name)
{ 
    	 List<Product> product = new ArrayList<Product>();
    	 product.add(productRepo.get(id));
    	 product.add(productRepo.get(name));
    	 return product;
    	 
    	 //comment
}
     
   }
  
      
   
