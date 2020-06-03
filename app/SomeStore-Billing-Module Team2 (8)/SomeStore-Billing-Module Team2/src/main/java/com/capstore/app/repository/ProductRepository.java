package main.java.com.capstore.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import main.java.com.capstore.app.models.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {
	/*
	 * List<Product> findByOrderByProductPriceAsc();
	 * 
	 * List<Product> findByOrderByProductPriceDesc();
	 * 
	 * List<Product> findByOrderByProductRatingAsc();
	 * 
	 * List<Product> findByOrderByProductRatingDesc();
	 */

	List<Product> findByProductCategoryOrderByProductPriceAsc(String category);

	List<Product> findByProductCategoryOrderByProductPriceDesc(String category);

	List<Product> findByProductCategoryOrderByProductRatingAsc(String category);

	List<Product> findByProductCategoryOrderByProductRatingDesc(String category);
}
