package main.java.com.capstore.app;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import main.java.com.capstore.app.models.Product;
import main.java.com.capstore.app.repository.ProductRepository;

@SpringBootApplication(scanBasePackages = {"main.java.com.capstore.app.controller","main.java.com.capstore.app.models","main.java.com.capstore.app.repository","main.java.com.capstore.app.signup_login","main.java.com.capstore.app.exceptions","main.java.com.capstore.app.services"})
public class BillingApplication implements CommandLineRunner{

	@Autowired
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(BillingApplication.class, args);
		
		
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		Product p1=new Product(4, "shoen", "shoe1.jpg", 4500, 4, "nike", 30, "Shoes");
		Product p5=new Product(8, "shoed", "shoe2.jpg", 7500, 5, "adidas", 70, "Shoes");
		Product p2=new Product(5, "mencloth", "menshirt1.jpg", 3500, 8, "Raymond", 50, "Men Clothing");
		Product p3=new Product(6, "Women Dress", "womendress.jpg", 7500, 9, "Levis", 60, "Women Clothing");
		Product p4=new Product(7, "Java Book", "book.jpg", 1500, 9, "Core Java", 20, "Books");
		Product p6=new Product(9, "Java Script", "book1.jpg", 2500, 8, "JS", 70, "Books");
		Product p7=new Product(10, "R programming", "book2.jpg", 3500, 7, "R", 60, "Books");
		Product p8=new Product(11, "C++ programming", "book3.jpg", 500, 3, "C++", 20, "Books");
		Product p9=new Product(12, "mencloth1", "menshirt2.jpg", 8500, 2, "Raymond", 30, "Men Clothing");
		Product p10=new Product(13, "mencloth2", "menshirt3.jpg", 4500, 6, "Raymond", 10, "Men Clothing");
		Product p11=new Product(14, "mencloth3", "menshirt4.jpg", 1500, 4, "Raymond", 80, "Men Clothing");
		Product p12=new Product(15, "Women Dress1", "womendress1.jpg", 12500, 3, "Levis", 90, "Women Clothing");
		Product p13=new Product(16, "Women Dress2", "womendress2.jpg", 7500, 6, "Levis", 20, "Women Clothing");
		Product p14=new Product(17, "Women Dress3", "womendress3.jpg", 21500, 1, "Levis", 60, "Women Clothing");
		productRepository.save(p1);
		productRepository.save(p2);
		productRepository.save(p3);
		productRepository.save(p4);
		productRepository.save(p5);
		productRepository.save(p6);productRepository.save(p13);productRepository.save(p14);
		productRepository.save(p7);productRepository.save(p11);productRepository.save(p12);
		productRepository.save(p8);productRepository.save(p9);productRepository.save(p10);
		
	}
}
