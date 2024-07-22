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
import com.projet.BackendPfe.Entity.UserVW;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;

import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.repository.UserVWRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.UserDetailsImpl;
import com.projet.BackendPfe.services.UserVWService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userVW")
public class VWController {
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserVWRepository repository;
	@Autowired	private  UserVWService service  ;
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
	@PostMapping("/addUserVWParAdminSansImage/{id}")
	public long addPatientParAdmin( @PathVariable("id") long  idAdmin , @RequestBody UserVW vw ) throws IOException {
		if (repuser.existsByUsername(vw.getUsername()) ) {
            throw new DataIntegrityViolationException("username existe déjà");}
		
		
		if (repuser.existsByEmail(vw.getEmail())) {
            throw new DataIntegrityViolationException("email existe déjà");}
		
		if (repuser.findByCin(vw.getCin())!= null) {
            throw new DataIntegrityViolationException("Cin existe déjà");}
		
		String motDePasse =service.generatePassword(8, vw.getPrenom(), vw.getNom());

		UserVW user = new UserVW( 
									vw.getCin() ,
									vw.getNom() ,
									vw.getPrenom() ,
									vw.getGender() ,
									service.generateUniqueUsername(vw.getPrenom(), vw.getNom()),
									vw.getEmail(),
									encoder.encode(motDePasse), 
									motDePasse ,
									vw.getImage(),
									new Date()
									, "User VW",
									repositoryAdmin.findById(idAdmin) ,
									vw.getTelephone()  ,
									vw.getDate_naissance()) ;
				
                repository.save(user);
		return   repository.findByUsername(user.getUsername()).get().getId() ;
	}
	
	/************************************************************************************/
	  @PutMapping("/updateUserVW/{id}")
	  public ResponseEntity<?> updateMedecin(@PathVariable("id") long id, @RequestBody UserVW vw ) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	    Optional<UserVW> UtilisateurInfo = repository.findById(id);
	    UserVW utilisateur = UtilisateurInfo.get();
	    if (repuser.existsByUsername(vw.getUsername())) {
	    	if(repuser.findById(id).get().getId() != (repuser.findByUsername(vw.getUsername()).get().getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}
	    }
		if (repuser.existsByEmail(vw.getEmail())) {
			if(repuser.findById(id).get().getId() != (repuser.findByEmail(vw.getEmail()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));}}
		
		if (repuser.existsByCin(vw.getCin())) {
			if(repuser.findById(id).get().getId() != (repuser.findByCin(vw.getCin()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Cin is already in use!"));}}
		utilisateur.setCin(vw.getCin());
		utilisateur.setNom(vw.getNom());
		utilisateur.setPrenom(vw.getPrenom());
		utilisateur.setGender(vw.getGender());
		utilisateur.setUsername(vw.getUsername());
		utilisateur.setEmail(vw.getEmail());
		utilisateur.setPassword(encoder.encode(vw.getPassword()));
		utilisateur.setReservePassword(vw.getReservePassword());
		utilisateur.setTelephone(vw.getTelephone());
		utilisateur.setDate_naissance(vw.getDate_naissance());
		
	      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);
	    } 
	  
	
  /**********************************************************************************************/
	    @GetMapping( "/getuserVW/{id}" )
			public UserVW getUserBMW(@PathVariable("id") long id) throws IOException {

			  UserVW p = repository.findById(id).get();
				    return p;}
	  /***********************************************************************************************/
		@DeleteMapping("/deletUserVW/{id}")
		public void deleteVW(@PathVariable("id") long id){

			repository.deleteById(id);
		} 
		/*******************************************************************************************/
		@GetMapping("/all")
		public List<UserVW> getAll(){
			return repository.findAll();}
		
		/***********************************************************************************************/
		 @GetMapping("/homme")
		  public int getUserBMWHomme() {
			 
				 List <UserVW> vws = repository.findAll() ; 
			     List<UserVW> resultat = new ArrayList<UserVW>() ; 

				for(UserVW vw :vws) {
			       if(vw.getGender().equals("homme")) {
		          	resultat.add(vw) ; 	}

				}
				int nbrUservw=resultat.size();
				return nbrUservw;
			 }
	/***********************************************************************************************/

		 @GetMapping("/femme")
		  public int getUserBMWFemme() {
			 
			 List <UserVW> vws = repository.findAll() ; 
		     List<UserVW> resultat = new ArrayList<UserVW>() ; 

			for(UserVW vw :vws) {
		       if(vw.getGender().equals("femme")) {
	          	resultat.add(vw) ; 	}

			}
			int nbrUservw=resultat.size();
			return nbrUservw;
			 }
		 
 /***********************************************************************************************/
		 
		 @GetMapping("/nbrAll")
		  public int getAllNbr() {
			 
				 List <UserVW> vws = repository.findAll() ; 

				int nbrUserVW=vws.size();
				return nbrUserVW;
			 }
	
}
