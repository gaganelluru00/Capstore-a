package test.java.com.capstore.app.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import main.java.com.capstore.app.controller.AppController;
import main.java.com.capstore.app.models.CustomerDetails;
import main.java.com.capstore.app.models.MerchantDetails;
import main.java.com.capstore.app.models.Product;
import main.java.com.capstore.app.services.CustomerServiceImpl;
import main.java.com.capstore.app.services.ProductServiceImpl;

@ExtendWith(MockitoExtension.class)
@RunWith(SpringRunner.class)
public class AppControllerTests {
	
	@InjectMocks
	AppController appController;
	
	@Mock
	CustomerServiceImpl customerServiceImpl;
	@Mock
	ProductServiceImpl productServiceImpl;
	
	@Test
	public void getAllProducts() {
		
		when(productServiceImpl.allProductsList()).thenReturn(productList());
		ResponseEntity<List<Product>> products;
		
		products =appController.getAllProducts();
		assertEquals(products.getBody(), productList());
		assertEquals(products.getStatusCodeValue(),200);
	}
	
	@Test
	public void getCategoryProducts() {
		ResponseEntity<List<Product>> products;
		Product p1=new Product(4, "shoen", "shoe1.jpg", 4500, 4, "nike", 30, "Shoes");
		Product p5=new Product(8, "shoed", "shoe2.jpg", 7500, 5, "adidas", 60, "Shoes");
		List<Product> categoryProducts=new ArrayList<Product>();
		categoryProducts.add(p1);
		categoryProducts.add(p5);
		when(productServiceImpl.specificCategoryProducts("Shoes")).thenReturn(categoryProducts);
		products=appController.getCategory("Shoes");
		assertEquals(products.getBody(),categoryProducts);
		assertEquals(products.getStatusCodeValue(),200);
	}
	
	@Test
	public void discountProducts() {
		ResponseEntity<List<Product>> products;
		List<Product> discountProducts=new ArrayList<Product>();
		Product product=new Product(8, "shoed", "shoe2.jpg", 7500, 5, "adidas", 60, "Shoes");
		discountProducts.add(product);
		
		when(productServiceImpl.specificDiscountProducts("Shoes", "60-70")).thenReturn(discountProducts);
		products=appController.getDiscountProducts("Shoes", "60-70");
		assertEquals(products.getBody(),discountProducts);
		assertEquals(products.getStatusCodeValue(),200);
	}
	
	@Test
	public void productById() {
		ResponseEntity<Product> products;
		Product product=new Product(4, "shoen", "shoe1.jpg", 4500, 4, "nike", 30, "Shoes");
		when(productServiceImpl.getProduct(4)).thenReturn(product);
		products=appController.getSelectedProduct(4);
		assertEquals(products.getBody(),product );
		assertEquals(products.getStatusCodeValue(),200);
		
	}
	
	
	
	public List<Product> productList(){
		List<Product> products=new ArrayList<Product>();
		Product p1=new Product(4, "shoen", "shoe1.jpg", 4500, 4, "nike", 30, "Shoes");
		Product p5=new Product(8, "shoed", "shoe2.jpg", 7500, 5, "adidas", 60, "Shoes");
		Product p2=new Product(5, "mencloth", "menshirt1.jpg", 3500, 8, "Raymond", 50, "Men Clothing");
		products.add(p1);
		products.add(p2);
		products.add(p5);
		return products;
	}
	public List<CustomerDetails> customersList(){
		CustomerDetails cd1=new CustomerDetails();
		cd1.setEmail("gagan@gmail.com");
		cd1.setPassword("password");
		cd1.setRole("Customer");
		CustomerDetails cd2=new CustomerDetails();
		cd2.setEmail("gagan1@gmail.com");
		cd2.setPassword("password");
		cd2.setRole("Customer");
		List<CustomerDetails> customers=new ArrayList<CustomerDetails>();
		customers.add(cd1);
		customers.add(cd2);
		return customers;
	} 

}
