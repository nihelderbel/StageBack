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
import org.springframework.web.bind.annotation.RestController;

import com.projet.BackendPfe.EmailSenderService;
import com.projet.BackendPfe.Entity.UserAudi;
import com.projet.BackendPfe.Entity.UserBMW;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import com.projet.BackendPfe.repository.UserAudiRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.UserAudiService;
import com.projet.BackendPfe.services.UserDetailsImpl;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userAudi")
public class AudiController {
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserAudiRepository repository;
	@Autowired	private  UserAudiService service  ;
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
	@PostMapping("/addUserAudiParAdminSansImage/{id}")
	public long addAudiParAdmin( @PathVariable("id") long  idAdmin , @RequestBody UserAudi audi ) throws IOException {
		if (repuser.existsByUsername(audi.getUsername()) ) {
            throw new DataIntegrityViolationException("username existe déjà");}
		
		
		if (repuser.existsByEmail(audi.getEmail())) {
            throw new DataIntegrityViolationException("email existe déjà");}
		
		if (repuser.findByCin(audi.getCin())!= null) {
            throw new DataIntegrityViolationException("Cin existe déjà");}
		
		String motDePasse =service.generatePassword(8, audi.getPrenom(), audi.getNom());

		UserAudi user = new UserAudi( 
									audi.getCin() ,
									audi.getNom() ,
									audi.getPrenom() ,
									audi.getGender() ,
									service.generateUniqueUsername(audi.getPrenom(), audi.getNom()),
									audi.getEmail(),
									encoder.encode(motDePasse), 
									motDePasse ,
									audi.getImage(),
									new Date()
									, "User Audi",
									repositoryAdmin.findById(idAdmin) ,
									audi.getTelephone()  ,
									audi.getDate_naissance()) ;
                repository.save(user);
		return   repository.findByUsername(user.getUsername()).get().getId() ;
	}
	
	/************************************************************************************/
	  @PutMapping("/updateUserAudi/{id}")
	  public ResponseEntity<?> updateUserAudi(@PathVariable("id") long id, @RequestBody UserAudi audi ) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	    Optional<UserAudi> UtilisateurInfo = repository.findById(id);
	    UserAudi utilisateur = UtilisateurInfo.get();
	    if (repuser.existsByUsername(audi.getUsername())) {
	    	if(repuser.findById(id).get().getId() != (repuser.findByUsername(audi.getUsername()).get().getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}
	    }
		if (repuser.existsByEmail(audi.getEmail())) {
			if(repuser.findById(id).get().getId() != (repuser.findByEmail(audi.getEmail()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));}}
		
		if (repuser.existsByCin(audi.getCin())) {
			if(repuser.findById(id).get().getId() != (repuser.findByCin(audi.getCin()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Cin is already in use!"));}}
		utilisateur.setCin(audi.getCin());
		utilisateur.setNom(audi.getNom());
		utilisateur.setPrenom(audi.getPrenom());
		utilisateur.setGender(audi.getGender());
		utilisateur.setUsername(audi.getUsername());
		utilisateur.setEmail(audi.getEmail());
		utilisateur.setPassword(encoder.encode(audi.getPassword()));
		utilisateur.setReservePassword(audi.getReservePassword());
		utilisateur.setTelephone(audi.getTelephone());
		utilisateur.setDate_naissance(audi.getDate_naissance());
		
	      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);
	    } 
	  
	
  /**********************************************************************************************/
	    @GetMapping( "/getuserAudi/{id}" )
			public UserAudi getUserAudi(@PathVariable("id") long id) throws IOException {

			  UserAudi p = (UserAudi) repository.findById(id).get();
				    return p;}
	  /***********************************************************************************************/
		@DeleteMapping("/deletUserAudi/{id}")
		public void deleteBMW(@PathVariable("id") long id){

			repository.deleteById(id);
		} 
		/*******************************************************************************************/
		@GetMapping("/all")
		public List<UserAudi> getAll(){
			return repository.findAll();}
		
		/***********************************************************************************************/
		 @GetMapping("/homme")
		  public int getUserAudiHomme() {
			 
				 List <UserAudi> audis = repository.findAll() ; 
			     List<UserAudi> resultat = new ArrayList<UserAudi>() ; 

				for(UserAudi audi :audis) {
			       if(audi.getGender().equals("homme")) {
		          	resultat.add(audi) ; 	}

				}
				int nbrUseraudi=resultat.size();
				return nbrUseraudi;
			 }
	/***********************************************************************************************/

		 @GetMapping("/femme")
		  public int getUserAudiFemme() {
			 

			 List <UserAudi> audis = repository.findAll() ; 
		     List<UserAudi> resultat = new ArrayList<UserAudi>() ; 

			for(UserAudi audi :audis) {
		       if(audi.getGender().equals("femme")) {
	          	resultat.add(audi) ; 	}

			}
			int nbrUseraudi=resultat.size();
			return nbrUseraudi;
			 }
		 
 /***********************************************************************************************/
		 
		 @GetMapping("/nbrAll")
		  public int getAllNbr() {
			 
				 List <UserAudi> audis = repository.findAll() ; 

				int nbrUseraudi=audis.size();
				return nbrUseraudi;
			 }
	
}
