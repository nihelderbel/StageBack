package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserMB;



public interface IUserMB {
	public byte[] getImageMB(long  id) throws Exception;



	public void addUserMB(UserMB usermb);

	public List<UserMB> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );


}
