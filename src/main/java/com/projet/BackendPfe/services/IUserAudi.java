package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserAudi;



public interface IUserAudi {
	public byte[] getImageUserAudi(long  id) throws Exception;



	public void addUserAudi(UserAudi useraudi);

	public List<UserAudi> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );

}
