package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserMH1;



public interface IUserMH1 {
	public byte[] getImageUserMH1(long  id) throws Exception;



	public void addUserMH1(UserMH1 userMH1);

	public List<UserMH1> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );


}
