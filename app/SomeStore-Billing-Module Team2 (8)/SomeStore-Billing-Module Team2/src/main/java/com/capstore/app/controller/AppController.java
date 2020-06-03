package main.java.com.capstore.app.controller;

import java.util.List;



import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;
import main.java.com.capstore.app.exception.InvalidUserException;
import main.java.com.capstore.app.models.CustomerDetails;
import main.java.com.capstore.app.models.MerchantDetails;
import main.java.com.capstore.app.models.Product;
import main.java.com.capstore.app.repository.ConfirmationTokenRepository;
import main.java.com.capstore.app.services.CustomerServiceImpl;
import main.java.com.capstore.app.services.ProductServiceImpl;
import main.java.com.capstore.app.repository.UserRepository;
import main.java.com.capstore.app.signup_login.ConfirmationToken;
import main.java.com.capstore.app.signup_login.EmailSenderService;
import main.java.com.capstore.app.signup_login.PasswordProtector;

@Data

@Transactional
@RestController
@CrossOrigin(origins = "http://localhost:4200", allowedHeaders = "*")
public class AppController {
	@Autowired
	ProductServiceImpl productServiceImpl;

	@Autowired
	UserRepository userRepository;

	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;

	@Autowired
	EmailSenderService emailSenderService;
	
	@Autowired
	CustomerServiceImpl customerServiceImpl;
	
	Logger logger=org.slf4j.LoggerFactory.getLogger(AppController.class);

	@RequestMapping(value = "/registerCustomer", method = RequestMethod.POST)
	public ResponseEntity<?> registerCustomer(@Valid @RequestBody CustomerDetails cd) throws Exception {
		return customerServiceImpl.registerCustomer(cd);
	}

	@RequestMapping(value = "/registerMerchant", method = RequestMethod.POST)
	public ResponseEntity<?> registerMerchant(@Valid @RequestBody MerchantDetails md) throws Exception {
		return customerServiceImpl.registerMerchant(md);
	}

	@RequestMapping(value = "/confirm-account", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<?> confirmUserAccount(@Valid @RequestParam("token") String confirmationToken) {
		return customerServiceImpl.confirmUserAccount(confirmationToken);
	}

	@GetMapping("/generateToken")
	public ResponseEntity<?> generateToken(@Valid @RequestParam("token") String confirmationToken,
			@Valid @RequestParam("action") String action) throws MessagingException {

		return customerServiceImpl.generateToken(confirmationToken, action);
	}

	@RequestMapping(value = "/login", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<?> userLogin(@Valid @RequestBody String[] userCredentials) throws Exception {
		return customerServiceImpl.loginCustomer(userCredentials);
	}
	

	@RequestMapping(value = "/forgotPassword", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<?> forgotPassword(@Valid @RequestBody String email) throws Exception {
		return customerServiceImpl.forgotPassword(email);
	}

	@RequestMapping(value = "/changePassword", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<?> changePassword(@Valid @RequestBody String[] details) throws Exception {
		return customerServiceImpl.changePassword(details);
	}
	
	
	

	@RequestMapping(value = "/getMerchant", method = { RequestMethod.GET, RequestMethod.POST })
	public ResponseEntity<?> userLogin(@Valid @RequestParam("token") String confToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confToken);
		MerchantDetails md = userRepository.findMerchantById(token.getUid());
		return ResponseEntity.ok().body(md);
	}

	// All Products Data
	@GetMapping(value = "/allProducts")
	public ResponseEntity<List<Product>> getAllProducts() {
		return ResponseEntity.ok().body(productServiceImpl.allProductsList());
	}

	// Products data of particular category
	@GetMapping(value = "/productCategory/{category}")
	public ResponseEntity<List<Product>> getCategory(@PathVariable("category") String productCategory) {

		return ResponseEntity.ok().body(productServiceImpl.specificCategoryProducts(productCategory));
	}

	// Product data based on discount
	@GetMapping(value = "/discountCategory/{category}/{discountPercent}")
	public ResponseEntity<List<Product>> getDiscountProducts(@PathVariable("category") String productCategory,
			@PathVariable("discountPercent") String discount) {

		return ResponseEntity.ok().body(productServiceImpl.specificDiscountProducts(productCategory, discount));
	}

	@GetMapping(value = "/searchProducts/{category}")
	public ResponseEntity<List<Product>> getSearchProducts(@PathVariable("category") String productSearch) {
		return ResponseEntity.ok().body(productServiceImpl.searchProducts(productSearch));
	}

	@GetMapping("/{email}")
	public ResponseEntity<MerchantDetails> verifyMerchantDetails(@PathVariable("email") String email) {
		return new ResponseEntity<MerchantDetails>(userRepository.findMerchantByEmailIgnoreCase(email), HttpStatus.OK);
	}

	@GetMapping("/{category}/{order}")
	public ResponseEntity<List<Product>> filterandcategory(@PathVariable String category, @PathVariable String order) {
		return ResponseEntity.ok().body(productServiceImpl.filterAndCategory(category, order));
	}
	@GetMapping("/product/{id}")
	public ResponseEntity<Product> getSelectedProduct(@PathVariable int id) {
		return ResponseEntity.ok().body(productServiceImpl.getProduct(id));
	}

	// getters and setters
	

}
