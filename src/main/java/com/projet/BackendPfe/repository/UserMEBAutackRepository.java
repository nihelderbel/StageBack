package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserMEB_autak;

public interface UserMEBAutackRepository extends JpaRepository<UserMEB_autak, Long> {
	Optional<UserMEB_autak> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserMEB_autak> findByImage(String image);
	List<UserMEB_autak> findByRole(String role);
	UserMEB_autak  findByEmail(String email);
	User   findByCin(int cin);

}
