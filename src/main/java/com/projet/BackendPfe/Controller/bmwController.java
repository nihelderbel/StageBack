package com.projet.BackendPfe.Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projet.BackendPfe.EmailSenderService;

import com.projet.BackendPfe.Entity.UserBMW;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import com.projet.BackendPfe.repository.UserBMWRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.UserDetailsImpl;
import com.projet.BackendPfe.services.UserbmwService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userBMw")
public class bmwController {
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserBMWRepository repository;
	@Autowired	private  UserbmwService service  ;
	@Autowired  EmailSenderService senderService;
	@Autowired	AdminMedicalManagerRepository repositoryAdmin;
	@Autowired	PasswordEncoder encoder;
	@Autowired	JwtTokenUtil jwtUtils;
	@Autowired	UserRepository repuser;
	/***********************************************************************************************/
	@PostMapping("/login")
	public ResponseEntity<?> authenticateAdmin(@Valid @RequestBody LoginRequest data) {
		System.out.println(data.getPassword());
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						data.getUsername(),
						data.getPassword()));	
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);
		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();		
		return ResponseEntity.ok(new JwtResponse(jwt, 
												 userDetails.getId(), 
												 userDetails.getUsername(), 
												 userDetails.getEmail()));}
											
	
	/***********************************************************************************************/	
	/***********************************************************************************************/	
	@PostMapping("/addUserBMWParAdminSansImage/{id}")
	public long addPatientParAdmin( @PathVariable("id") long  idAdmin , @RequestBody UserBMW bmw ) throws IOException {
		if (repuser.existsByUsername(bmw.getUsername()) ) {
            throw new DataIntegrityViolationException("username existe déjà");}
		
		
		if (repuser.existsByEmail(bmw.getEmail())) {
            throw new DataIntegrityViolationException("email existe déjà");}
		
		if (repuser.findByCin(bmw.getCin())!= null) {
            throw new DataIntegrityViolationException("Cin existe déjà");}
		
		String motDePasse =service.generatePassword(8, bmw.getPrenom(), bmw.getNom());

		UserBMW user = new UserBMW( 
									bmw.getCin() ,
									bmw.getNom() ,
									bmw.getPrenom() ,
									bmw.getGender() ,
									service.generateUniqueUsername(bmw.getPrenom(), bmw.getNom()),
									bmw.getEmail(),
									encoder.encode(motDePasse), 
									motDePasse ,
									bmw.getImage(),
									new Date()
									, "User BMW",
									repositoryAdmin.findById(idAdmin) ,
									bmw.getTelephone()  ,
									bmw.getDate_naissance()) ;
				
                repository.save(user);
		return   repository.findByUsername(user.getUsername()).get().getId() ;
	}
	
	/************************************************************************************/
	  @PutMapping("/updateUserBMW/{id}")
	  public ResponseEntity<?> updateMedecin(@PathVariable("id") long id, @RequestBody UserBMW bmw ) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	    Optional<UserBMW> UtilisateurInfo = repository.findById(id);
	    UserBMW utilisateur = UtilisateurInfo.get();
	    if (repuser.existsByUsername(bmw.getUsername())) {
	    	if(repuser.findById(id).get().getId() != (repuser.findByUsername(bmw.getUsername()).get().getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}
	    }
		if (repuser.existsByEmail(bmw.getEmail())) {
			if(repuser.findById(id).get().getId() != (repuser.findByEmail(bmw.getEmail()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));}}
		
		if (repuser.existsByCin(bmw.getCin())) {
			if(repuser.findById(id).get().getId() != (repuser.findByCin(bmw.getCin()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Cin is already in use!"));}}
		utilisateur.setCin(bmw.getCin());
		utilisateur.setNom(bmw.getNom());
		utilisateur.setPrenom(bmw.getPrenom());
		utilisateur.setGender(bmw.getGender());
		utilisateur.setUsername(bmw.getUsername());
		utilisateur.setEmail(bmw.getEmail());
		utilisateur.setPassword(encoder.encode(bmw.getPassword()));
		utilisateur.setReservePassword(bmw.getReservePassword());
		utilisateur.setTelephone(bmw.getTelephone());
		utilisateur.setDate_naissance(bmw.getDate_naissance());
		
	      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);
	    } 
	  
	
  /**********************************************************************************************/
	    @GetMapping( "/getuserBMW/{id}" )
			public UserBMW getUserBMW(@PathVariable("id") long id) throws IOException {

			  UserBMW p = repository.findById(id).get();
				    return p;}
	  /***********************************************************************************************/
		@DeleteMapping("/deletUserBMW/{id}")
		public void deleteBMW(@PathVariable("id") long id){

			repository.deleteById(id);
		} 
		/*******************************************************************************************/
		@GetMapping("/all")
		public List<UserBMW> getAll(){
			return repository.findAll();}
		
		/***********************************************************************************************/
		 @GetMapping("/homme")
		  public int getUserBMWHomme() {
			 
				 List <UserBMW> bmws = repository.findAll() ; 
			     List<UserBMW> resultat = new ArrayList<UserBMW>() ; 

				for(UserBMW bmw :bmws) {
			       if(bmw.getGender().equals("homme")) {
		          	resultat.add(bmw) ; 	}

				}
				int nbrUserBmw=resultat.size();
				return nbrUserBmw;
			 }
	/***********************************************************************************************/

		 @GetMapping("/femme")
		  public int getUserBMWFemme() {
			 

			 List <UserBMW> bmws = repository.findAll() ; 
		     List<UserBMW> resultat = new ArrayList<UserBMW>() ; 

			for(UserBMW bmw :bmws) {
		       if(bmw.getGender().equals("femme")) {
	          	resultat.add(bmw) ; 	}

			}
			int nbrUserBmw=resultat.size();
			return nbrUserBmw;
			 }
		 
 /***********************************************************************************************/
		 
		 @GetMapping("/nbrAll")
		  public int getAllNbr() {
			 
				 List <UserBMW> bmws = repository.findAll() ; 

				int nbrUserBMW=bmws.size();
				return nbrUserBMW;
			 }
	
}
