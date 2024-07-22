package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserMH1;

public interface UserMH1Repository extends JpaRepository<UserMH1, Long> {
	Optional<UserMH1> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserMH1> findByImage(String image);
	List<UserMH1> findByRole(String role);
	UserMH1  findByEmail(String email);
	User   findByCin(int cin);

}
