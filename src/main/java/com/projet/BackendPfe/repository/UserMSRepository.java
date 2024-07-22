package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserMS;

public interface UserMSRepository extends JpaRepository<UserMS, Long> {
	Optional<UserMS> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserMS> findByImage(String image);
	List<UserMS> findByRole(String role);
	UserMS  findByEmail(String email);
	User   findByCin(int cin);
}
