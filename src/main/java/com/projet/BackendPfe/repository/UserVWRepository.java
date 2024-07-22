package com.projet.BackendPfe.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.BackendPfe.Entity.User;
import com.projet.BackendPfe.Entity.UserVW;

public interface UserVWRepository extends JpaRepository<UserVW, Long> {
	Optional<UserVW> findByUsername(String username);
	Boolean existsByUsername(String username);
	Boolean existsByEmail(String email);
	Boolean existsByCin(int cin);
	Optional<UserVW> findByImage(String image);
	List<UserVW> findByRole(String role);
	UserVW  findByEmail(String email);
	User   findByCin(int cin);

}
