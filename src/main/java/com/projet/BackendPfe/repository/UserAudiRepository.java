package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserAudi;


public interface UserAudiRepository extends JpaRepository<UserAudi, Long> {
	Optional<UserAudi> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserAudi> findByImage(String image);
	List<UserAudi> findByRole(String role);
	UserAudi  findByEmail(String email);
	User   findByCin(int cin);
}
