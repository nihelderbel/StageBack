package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserMN;

public interface UserMNRepository extends JpaRepository<UserMN, Long> {
	Optional<UserMN> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserMN> findByImage(String image);
	List<UserMN> findByRole(String role);
	UserMN  findByEmail(String email);
	User   findByCin(int cin);

}
