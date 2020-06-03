package test.java.com.capstore.app.repository;

import static org.junit.jupiter.api.Assertions.*;





import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import main.java.com.capstore.app.BillingApplication;
import main.java.com.capstore.app.models.Product;

import main.java.com.capstore.app.repository.ProductRepository;
import main.java.com.capstore.app.services.ProductServiceImpl;
import test.java.com.capstore.app.BillingApplicationTests;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@SpringBootTest(classes=BillingApplication.class)
public class ProductServiceTest {

	
	@Autowired
	ProductServiceImpl productServiceImpl;
	
	@MockBean
	ProductRepository productRepository;
	
	@Test
	public void allProductsTest() {
		Mockito.when(productRepository.findAll()).thenReturn(productList());
		assertEquals(productServiceImpl.allProductsList(),productList());
	}
	@Test
	public void categoryProducts() {
		
		Mockito.when(productRepository.findAll()).thenReturn(productList());
		assertNotNull(productServiceImpl.specificCategoryProducts("Shoes"));
	}
	@Test
	public void discountProducts() {
		Mockito.when(productRepository.findAll()).thenReturn(productList());
		assertNotNull(productServiceImpl.specificDiscountProducts("Shoes", "Less than 50"));
		
	}
	@Test
	public void getProduct() {
		Mockito.when(productRepository.findAll()).thenReturn(productList());
		Product p1=new Product(4, "shoen", "shoe1.jpg", 4500, 4, "nike", 30, "Shoes");
		assertEquals(productServiceImpl.getProduct(4),p1);
	}
	
	@Test
	public void filterProducts() {
		Mockito.when(productRepository.findAll()).thenReturn(productList());
		assertNotNull(productServiceImpl.filterAndCategory("Shoes", "Asc"));
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
}
