package main.java.com.capstore.app.services;

import java.util.ArrayList;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import main.java.com.capstore.app.models.Product;
import main.java.com.capstore.app.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductServiceInterface {

	@Autowired
	ProductRepository productRepository;

	// Returns all products in database
	@Override
	public List<Product> allProductsList() {
		
		//return productList();
		return productRepository.findAll();
	}

	// Returns products of specific category
	@Override
	public List<Product> specificCategoryProducts(String productCategory) {

		List<Product> products = new ArrayList<Product>();
		if (productCategory.equalsIgnoreCase("All Products")) {
			return productRepository.findAll();
		}
		for (Product p : productRepository.findAll()) {
			if (p.getProductCategory().equals(productCategory)) {
				products.add(p);
			}
		}
		return products;
	}

	// Returns products of specific category and discount
	@Override
	public List<Product> specificDiscountProducts(String category, String discount) {

		List<Product> categoryProducts = specificCategoryProducts(category);
		List<Product> products = new ArrayList<Product>();
		for (Product p : categoryProducts) {
			if (discount.equalsIgnoreCase("Less than 50")) {
				if (p.getDiscount() < 50) {
					products.add(p);
				}
			} else if (discount.equalsIgnoreCase("50-60")) {
				if (p.getDiscount() >= 50 && p.getDiscount() < 60) {
					products.add(p);
				}
			} else if (discount.equalsIgnoreCase("60-70")) {
				if (p.getDiscount() >= 60 && p.getDiscount() < 70) {
					products.add(p);
				}
			} else if (discount.equalsIgnoreCase("Greater than 70")) {
				if (p.getDiscount() >= 70) {
					products.add(p);
				}
			}

		}

		return products;
	}

	@Override
	public List<Product> searchProducts(String category) {
		List<Product> allProducts = productRepository.findAll();
		List<Product> products = new ArrayList<Product>();
		for (Product product : allProducts) {
			if (product.getProductInfo().toLowerCase().contains(category.toLowerCase())) {
				products.add(product);
			}
		}
		return products;
	}

	

	/*
	 * @Override public List<Product> sortAsc() { return
	 * productRepository.findByOrderByProductPriceAsc(); }
	 * 
	 * @Override public List<Product> sortDesc() { return
	 * productRepository.findByOrderByProductPriceDesc(); }
	 * 
	 * @Override public List<Product> sortRasc() { // TODO Auto-generated method
	 * stub return productRepository.findByOrderByProductRatingAsc(); }
	 * 
	 * @Override public List<Product> sortRdesc() { // TODO Auto-generated method
	 * stub return productRepository.findByOrderByProductRatingDesc(); }
	 */
	
	
	
	@Override
	public List<Product> filterAndCategory(String category, String order) {
		
		if(order.equals("Asc"))
			return productRepository.findByProductCategoryOrderByProductPriceAsc(category);
		if(order.equals("Desc"))
			return productRepository.findByProductCategoryOrderByProductPriceDesc(category);
		if(order.equals("Rasc"))
			return productRepository.findByProductCategoryOrderByProductRatingAsc(category);
		if(order.equals("Rdesc"))
			return productRepository.findByProductCategoryOrderByProductRatingDesc(category);
		
		
		return null;
		
		
		
		
	}

	@Override
	public Product getProduct(int id) {
		
		Product product=null;
		for(Product p:productRepository.findAll()) {
			if(p.getProductId()==id) {
				product=p;
			}
		}
		return product;
	}
	
	
	
	
}
