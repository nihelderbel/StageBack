package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserMN;



public interface IUserMN {
	public byte[] getImageUserMN(long  id) throws Exception;



	public void addUserMN(UserMN userMN);

	public List<UserMN> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );


}
