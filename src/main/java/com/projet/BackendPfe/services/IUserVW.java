package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserVW;



public interface IUserVW {
	public byte[] getImageUserVW(long  id) throws Exception;



	public void addUserVW(UserVW uservw);

	public List<UserVW> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );


}
