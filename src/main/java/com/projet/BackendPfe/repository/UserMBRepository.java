package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserMB;

public interface UserMBRepository extends JpaRepository<UserMB, Long> {
	Optional<UserMB> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserMB> findByImage(String image);
	List<UserMB> findByRole(String role);
	UserMB  findByEmail(String email);
	User   findByCin(int cin);

}
