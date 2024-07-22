package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserMH2;

public interface UserMH2Repository extends JpaRepository<UserMH2, Long> {
	Optional<UserMH2> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserMH2> findByImage(String image);
	List<UserMH2> findByRole(String role);
	UserMH2  findByEmail(String email);
	User   findByCin(int cin);

}
