package main.java.com.capstore.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import main.java.com.capstore.app.models.User;


public interface Dao extends JpaRepository<User, Integer> {

}
