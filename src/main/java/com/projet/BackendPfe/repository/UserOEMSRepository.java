package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserOEMS;

public interface UserOEMSRepository extends JpaRepository<UserOEMS, Long> {
	Optional<UserOEMS> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserOEMS> findByImage(String image);
	List<UserOEMS> findByRole(String role);
	UserOEMS  findByEmail(String email);
	User   findByCin(int cin);
}
