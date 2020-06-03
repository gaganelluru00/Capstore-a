package main.java.com.capstore.app.services;

import javax.mail.MessagingException;

import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import main.java.com.capstore.app.controller.AppController;
import main.java.com.capstore.app.exception.InvalidUserException;
import main.java.com.capstore.app.exception.UserAlreadyExistsException;
import main.java.com.capstore.app.exception.UserNotFoundException;
import main.java.com.capstore.app.models.CustomerDetails;
import main.java.com.capstore.app.models.MerchantDetails;
import main.java.com.capstore.app.repository.ConfirmationTokenRepository;
import main.java.com.capstore.app.repository.UserRepository;
import main.java.com.capstore.app.signup_login.ConfirmationToken;
import main.java.com.capstore.app.signup_login.EmailSenderService;
import main.java.com.capstore.app.signup_login.PasswordProtector;

@Service
public class CustomerServiceImpl implements CustomerService {

	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	EmailSenderService emailSenderService;
	
	@Autowired
	ConfirmationTokenRepository confirmationTokenRepository;
	
	Logger logger=org.slf4j.LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	
	@Override
	public ResponseEntity<?> loginCustomer(String[] userCredentials) throws Exception {
		String email = userCredentials[0];
		String pass = userCredentials[1];
		String role = userCredentials[2];
		if (role.equals("Customer")) {
			CustomerDetails cd = userRepository.findCustomerByEmailIgnoreCase(email);
			if (cd != null && cd.isActive() == true) {
				if (pass.equals(PasswordProtector.decrypt(cd.getPassword()))) {
					return ResponseEntity.ok().body(cd);
				}
			}
		} else {
			MerchantDetails md = userRepository.findMerchantByEmailIgnoreCase(email);
			if (md != null && md.isActive() == true) {
				if (pass.equals(PasswordProtector.decrypt(md.getPassword()))) {
					return ResponseEntity.ok().body(md);
				}
			}
		}
		 
		throw new UserNotFoundException("Invalid Login Details");
}
	
	
	
	
	

	@Override
	public ResponseEntity<?> registerCustomer(CustomerDetails cd) throws Exception {
		CustomerDetails existingCustomer = userRepository.findCustomerByEmailIgnoreCase(cd.getEmail());
		MerchantDetails existingMerchant = userRepository.findMerchantByEmailIgnoreCase(cd.getEmail());
		if (existingCustomer != null && existingMerchant != null) {
           logger.error("Account already exists");
			throw new UserAlreadyExistsException("Account with provided email already exists");
		} else {
			cd.setPassword(PasswordProtector.encrypt(cd.getPassword()));
			userRepository.saveCustomer(cd);
			CustomerDetails cd1 = userRepository.findCustomerByEmailIgnoreCase(cd.getEmail());

			ConfirmationToken confirmationToken = new ConfirmationToken(cd1.getUserId());
			// System.out.println(confirmationToken);

			confirmationTokenRepository.save(confirmationToken);

			SimpleMailMessage mailMessage = new SimpleMailMessage();
			mailMessage.setTo(cd.getEmail());
			mailMessage.setSubject("Complete Registration!");
			mailMessage.setFrom("capstore06@gmail.com");
			mailMessage.setText("To activate your account, please click here : " + "http://localhost:4200/verify?token="
					+ confirmationToken.getConfirmationToken());

			emailSenderService.sendEmail(mailMessage);

			return ResponseEntity.ok(HttpStatus.OK);
		}
	}

	
	
	
	
	


	@Override
	public ResponseEntity<?> forgotPassword(String email) throws Exception {
		
		if (userRepository.findCustomerByEmailIgnoreCase(email) != null) {
			CustomerDetails cd = userRepository.findCustomerByEmailIgnoreCase(email);
			if (cd.isActive()) {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(cd.getEmail());
				mailMessage.setSubject("Forgot Password");
				mailMessage.setFrom("capstore06@gmail.com");
				mailMessage.setText("Hi, your password is : " + PasswordProtector.decrypt(cd.getPassword()) + "\n"
						+ "Note: Confidential Information. Do Not Share it with Others.");
				emailSenderService.sendEmail(mailMessage);
               logger.info("Password sent to mail");
				return ResponseEntity.ok(HttpStatus.OK);
			}
		} else if (userRepository.findMerchantByEmailIgnoreCase(email) != null) {
			MerchantDetails md = userRepository.findMerchantByEmailIgnoreCase(email);
			if (md.isActive()) {
				SimpleMailMessage mailMessage = new SimpleMailMessage();
				mailMessage.setTo(md.getEmail());
				mailMessage.setSubject("Forgot Password");
				mailMessage.setFrom("capstore06@gmail.com");
				mailMessage.setText("Hi, your password is : " + PasswordProtector.decrypt(md.getPassword()) + "\n"
						+ "Note: Confidential Information. Do Not Share it with Others.");
				emailSenderService.sendEmail(mailMessage);
				logger.info("Password sent to mail");
				return ResponseEntity.ok(HttpStatus.OK);
			}
		}
              logger.error("No account with the provided email");
              throw new InvalidUserException("No account with the provided email");
		
	}



	
	
	
	
	
	@Override
	public ResponseEntity<?> changePassword(String[] details) throws Exception {
		String currPass = details[0];
		String newPass = details[1];
		String email = details[2];
		if (userRepository.findCustomerByEmailIgnoreCase(email) != null) {
			CustomerDetails cd = userRepository.findCustomerByEmailIgnoreCase(email);
			if (cd.isActive() && currPass.equals(PasswordProtector.decrypt(cd.getPassword()))) {
				cd.setPassword(PasswordProtector.encrypt(newPass));
				logger.info("Password Changed");
				return ResponseEntity.ok(HttpStatus.OK);
			}
		} else if (userRepository.findMerchantByEmailIgnoreCase(email) != null) {
			MerchantDetails md = userRepository.findMerchantByEmailIgnoreCase(email);
			if (md.isActive() && currPass.equals(PasswordProtector.decrypt(md.getPassword()))) {
				md.setPassword(PasswordProtector.encrypt(newPass));
				logger.info("Password Changed");
				return ResponseEntity.ok(HttpStatus.OK);
			}
		}
		 logger.error("No account with the provided email");
		throw new InvalidUserException("No account with the provided email");
		
	}






	@Override
	public ResponseEntity<?> registerMerchant(MerchantDetails md) throws Exception {
		CustomerDetails existingCustomer = userRepository.findCustomerByEmailIgnoreCase(md.getEmail());
		MerchantDetails existingMerchant = userRepository.findMerchantByEmailIgnoreCase(md.getEmail());
		if (existingMerchant != null && existingCustomer != null) {
			  logger.error("Account already exists");
			  throw new UserAlreadyExistsException("Account with provided email already exists");
		} else {
			md.setPassword(PasswordProtector.encrypt(md.getPassword()));
			userRepository.saveMerchant(md);
			MerchantDetails md1 = userRepository.findMerchantByEmailIgnoreCase(md.getEmail());

			ConfirmationToken confirmationToken = new ConfirmationToken(md1.getUserId());

			confirmationTokenRepository.save(confirmationToken);

			MimeMessage mailMessage = emailSenderService.createMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
			String url = "http://localhost:4200/verifyMerchant?token=" + confirmationToken.getConfirmationToken();
			helper.setTo("gagan.elluru@gmail.com");
			helper.setSubject("Merchant Requesting Approval!");
			helper.setFrom("capstore06@gmail.com");
			helper.setText("<html><body><h1>Merchant Registration!</h1><br>" + md + "<br><button type='submit'>"
					+ "<a href=" + url + ">Show Details</a></button>", true);

			emailSenderService.sendEmail(mailMessage);

			return ResponseEntity.ok(HttpStatus.OK);
		}
	}






	@Override
	public ResponseEntity<?> confirmUserAccount(String confirmationToken) {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		if (token != null) {
			if (userRepository.findCustomerById(token.getUid()) != null) {
				CustomerDetails cd = userRepository.findCustomerById(token.getUid());
				cd.setActive(true);
				userRepository.saveCustomer(cd);
			}
			return ResponseEntity.ok(HttpStatus.OK);
		} else {
			throw new UserNotFoundException("Account not created");
			
		}
	}






	@Override
	public ResponseEntity<?> generateToken(String confirmationToken, String action) throws MessagingException {
		ConfirmationToken token = confirmationTokenRepository.findByConfirmationToken(confirmationToken);

		MerchantDetails md = userRepository.findMerchantById(token.getUid());
		if (action.equals("Accept")) {
			md.setActive(true);
			md.setApproved(true);
		} else {
			md.setActive(false);
			md.setApproved(false);
		}

		userRepository.saveMerchant(md);

		MimeMessage mailMessage = emailSenderService.createMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mailMessage, true);
		helper.setTo(md.getEmail());
		helper.setSubject("Account Activated!");
		helper.setFrom("capstore06@gmail.com");
		helper.setText("Admin approved your account.\nTo login and access your account, please click here : "
				+ "http://localhost:4200");

		emailSenderService.sendEmail(mailMessage);

		return ResponseEntity.ok().body(md);
	}
	
	
	
	
	
	
	

}
