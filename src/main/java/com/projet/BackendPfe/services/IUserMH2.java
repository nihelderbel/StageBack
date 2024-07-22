package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserMH2;



public interface IUserMH2 {
	public byte[] getImageUserMH2(long  id) throws Exception;



	public void addUserMH2(UserMH2 userMH2);

	public List<UserMH2> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );


}
