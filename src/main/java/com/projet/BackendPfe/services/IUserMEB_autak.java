package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.UserMEB_autak;



public interface IUserMEB_autak {
	public byte[] getImageUserautak(long  id) throws Exception;



	public void addUserUserMEB_autak(UserMEB_autak usermeb);

	public List<UserMEB_autak> getAllUSers();

	public void delete(long id);

	public String generateUniqueUsername(String nom, String prenom);
	public String generatePassword(int length , String nom , String prenom );


}
