package com.projet.BackendPfe.services;

import java.io.IOException;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.Entity.UserBMW;


public interface IUserBMW {

public byte[] getImageUserBMW(long  id) throws Exception;



public void addUserBMW(UserBMW userbmw);

public List<UserBMW> getAllPatient();

public void delete(long id);

public String generateUniqueUsername(String nom, String prenom);
public String generatePassword(int length , String nom , String prenom );

}
