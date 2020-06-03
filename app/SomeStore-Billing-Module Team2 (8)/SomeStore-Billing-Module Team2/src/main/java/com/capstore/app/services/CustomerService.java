package main.java.com.capstore.app.services;

import javax.mail.MessagingException;
import javax.validation.Valid;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import main.java.com.capstore.app.models.CustomerDetails;
import main.java.com.capstore.app.models.MerchantDetails;

@Service
public interface CustomerService {
	ResponseEntity<?> loginCustomer(String[] userCredentials) throws Exception;
	ResponseEntity<?> registerCustomer(CustomerDetails cd) throws Exception;
	ResponseEntity<?> registerMerchant(MerchantDetails cd) throws Exception;
	ResponseEntity<?> forgotPassword(String email) throws Exception;
	ResponseEntity<?> changePassword(String[] details) throws Exception;
	
	ResponseEntity<?> confirmUserAccount(String confirmationToken);
	ResponseEntity<?> generateToken(String confirmationToken,String action) throws MessagingException;
	
}
