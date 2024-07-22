package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserMS;



public interface IUserMS {
	public byte[] getImageUserMS(long  id) throws Exception;



	public void addUserMS(UserMS userMS);

	public List<UserMS> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );


}
