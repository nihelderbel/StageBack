package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserBMW;



public interface UserBMWRepository extends JpaRepository<UserBMW, Long> {
	Optional<UserBMW> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserBMW> findByImage(String image);
	List<UserBMW> findByRole(String role);
	UserBMW  findByEmail(String email);
	User   findByCin(int cin);

}
