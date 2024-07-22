package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserOEMS;


public interface IUserOEMS {
	public byte[] getImageUserOEMS(long  id) throws Exception;



	public void addUserOEMS(UserOEMS useroems);

	public List<UserOEMS> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );


}
