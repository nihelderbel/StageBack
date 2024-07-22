package com.projet.BackendPfe.services;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.BackendPfe.Entity.UserBMW;
import com.projet.BackendPfe.repository.UserBMWRepository;

@Service
public class UserbmwService implements IUserBMW{
@Autowired UserBMWRepository  bmw;
	
	private List<UserBMW> liste_bmws=new ArrayList<UserBMW>();

	@Override
	public byte[] getImageUserBMW(long id) throws Exception {
		// TODO Auto-generated method stub
		String image =bmw.findById(id).get().getImage() ; 
		Path p =Paths.get(System.getProperty("user.home")+"/Desktop/images projet web/",image);
		return Files.readAllBytes(p);
	}

	@Override
	public void addUserBMW(com.projet.BackendPfe.Entity.UserBMW userbmw) {
		// TODO Auto-generated method stub
		bmw.save(userbmw);
		
	}

	@Override
	public List<com.projet.BackendPfe.Entity.UserBMW> getAllPatient() {
		// TODO Auto-generated method stub
		liste_bmws=bmw.findAll();
		return liste_bmws;
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		bmw.deleteById(id);
	}

	@Override
	public String generateUniqueUsername(String nom, String prenom) {
		 // Convertir le nom et le prénom en minuscules pour assurer la cohérence
        nom = nom.toLowerCase();
        prenom = prenom.toLowerCase();

        // Concaténer le nom et le prénom pour former un nom d'utilisateur de base
        String baseUsername = nom + "_" + prenom;

        // Vérifier si le nom d'utilisateur est déjà pris
        if (!(bmw.existsByUsername(baseUsername))) {
            // Si le nom d'utilisateur est disponible, le retourner tel quel
            return baseUsername;
        } else {
            // Si le nom d'utilisateur est pris, ajouter des caractères aléatoires pour éviter les conflits
            Random random = new Random();
            StringBuilder uniqueUsernameBuilder = new StringBuilder(baseUsername);
            // Ajouter des caractères aléatoires jusqu'à ce que le nom d'utilisateur soit unique
            while (bmw.existsByUsername(uniqueUsernameBuilder.toString())) {
                char randomChar = (char) (random.nextInt(26) + 'a'); // Générer un caractère minuscule aléatoire
                uniqueUsernameBuilder.append(randomChar);
            }
            return uniqueUsernameBuilder.toString();
        }
	}

	@Override
	public String generatePassword(int length, String nom, String prenom) {
        List<Character> characters = new ArrayList<>();

        // Ajouter les caractères du nom et du prénom à la liste
        for (char c : (nom + prenom).toCharArray()) {
            characters.add(c);
        }

        // Mélanger les caractères pour plus de variété
        Random random = new Random();
        StringBuilder password = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (!characters.isEmpty()) {
                int randomIndex = random.nextInt(characters.size());
                password.append(characters.remove(randomIndex));
            } else {
                // Si la liste est vide, réinitialiser en ajoutant à nouveau les caractères
                for (char c : (nom + prenom).toCharArray()) {
                    characters.add(c);
                }
            }
        }

        return password.toString();
	}
}
