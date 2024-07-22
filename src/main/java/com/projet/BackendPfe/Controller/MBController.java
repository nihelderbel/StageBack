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
import com.projet.BackendPfe.Entity.UserMB;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import com.projet.BackendPfe.repository.UserMBRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.UserDetailsImpl;
import com.projet.BackendPfe.services.UserMBService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userMB")
public class MBController {
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserMBRepository repository;
	@Autowired	private  UserMBService service  ;
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
	@PostMapping("/addUserMBParAdminSansImage/{id}")
	public long addMBParAdmin( @PathVariable("id") long  idAdmin , @RequestBody UserMB meb ) throws IOException {
		if (repuser.existsByUsername(meb.getUsername()) ) {
            throw new DataIntegrityViolationException("username existe déjà");}
		
		
		if (repuser.existsByEmail(meb.getEmail())) {
            throw new DataIntegrityViolationException("email existe déjà");}
		
		if (repuser.findByCin(meb.getCin())!= null) {
            throw new DataIntegrityViolationException("Cin existe déjà");}
		
		String motDePasse =service.generatePassword(8, meb.getPrenom(), meb.getNom());

		UserMB user = new UserMB( 
									meb.getCin() ,
									meb.getNom() ,
									meb.getPrenom() ,
									meb.getGender() ,
									service.generateUniqueUsername(meb.getPrenom(), meb.getNom()),
									meb.getEmail(),
									encoder.encode(motDePasse), 
									motDePasse ,
									meb.getImage(),
									new Date()
									, "User MB",
									repositoryAdmin.findById(idAdmin) ,
									meb.getTelephone()  ,
									meb.getDate_naissance()) ;
                repository.save(user);
		return   repository.findByUsername(user.getUsername()).get().getId() ;
	}
	
	/************************************************************************************/
	  @PutMapping("/updateUserMb/{id}")
	  public ResponseEntity<?> updateUserAudi(@PathVariable("id") long id, @RequestBody UserMB meb ) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	    Optional<UserMB> UtilisateurInfo = repository.findById(id);
	    UserMB utilisateur = UtilisateurInfo.get();
	    if (repuser.existsByUsername(meb.getUsername())) {
	    	if(repuser.findById(id).get().getId() != (repuser.findByUsername(meb.getUsername()).get().getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}
	    }
		if (repuser.existsByEmail(meb.getEmail())) {
			if(repuser.findById(id).get().getId() != (repuser.findByEmail(meb.getEmail()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));}}
		
		if (repuser.existsByCin(meb.getCin())) {
			if(repuser.findById(id).get().getId() != (repuser.findByCin(meb.getCin()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Cin is already in use!"));}}
		utilisateur.setCin(meb.getCin());
		utilisateur.setNom(meb.getNom());
		utilisateur.setPrenom(meb.getPrenom());
		utilisateur.setGender(meb.getGender());
		utilisateur.setUsername(meb.getUsername());
		utilisateur.setEmail(meb.getEmail());
		utilisateur.setPassword(encoder.encode(meb.getPassword()));
		utilisateur.setReservePassword(meb.getReservePassword());
		utilisateur.setTelephone(meb.getTelephone());
		utilisateur.setDate_naissance(meb.getDate_naissance());
		
	      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);
	    } 
	  
	
  /**********************************************************************************************/
	    @GetMapping( "/getuserMb/{id}" )
			public UserMB getUserAudi(@PathVariable("id") long id) throws IOException {

			  UserMB p = repository.findById(id).get();
				    return p;}
	  /***********************************************************************************************/
		@DeleteMapping("/deletUserMB/{id}")
		public void deleteBMW(@PathVariable("id") long id){

			repository.deleteById(id);
		} 
		/*******************************************************************************************/
		@GetMapping("/all")
		public List<UserMB> getAll(){
			return repository.findAll();}
		
		/***********************************************************************************************/
		 @GetMapping("/homme")
		  public int getUserMBHomme() {
			 
				 List <UserMB> mebs = repository.findAll() ; 
			     List<UserMB> resultat = new ArrayList<UserMB>() ; 

				for(UserMB meb :mebs) {
			       if(meb.getGender().equals("homme")) {
		          	resultat.add(meb) ; 	}

				}
				int nbrUsermeb=resultat.size();
				return nbrUsermeb;
			 }
	/***********************************************************************************************/

		 @GetMapping("/femme")
		  public int getUserAudiFemme() {
			 


			 List <UserMB> mebs = repository.findAll() ; 
		     List<UserMB> resultat = new ArrayList<UserMB>() ; 

			for(UserMB meb :mebs) {
		       if(meb.getGender().equals("femme")) {
	          	resultat.add(meb) ; 	}

			}
			int nbrUsermeb=resultat.size();
			return nbrUsermeb;
			 }
		 
 /***********************************************************************************************/
		 
		 @GetMapping("/nbrAll")
		  public int getAllNbr() {
			 
				 List <UserMB> meb = repository.findAll() ; 

				int nbrUsermeb=meb.size();
				return nbrUsermeb;
			 }
}
