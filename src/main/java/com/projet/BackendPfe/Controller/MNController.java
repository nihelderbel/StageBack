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
import com.projet.BackendPfe.Entity.UserMN;
import com.projet.BackendPfe.config.JwtTokenUtil;
import com.projet.BackendPfe.domaine.JwtResponse;
import com.projet.BackendPfe.domaine.Message;
import com.projet.BackendPfe.repository.AdminMedicalManagerRepository;
import com.projet.BackendPfe.repository.UserMNRepository;
import com.projet.BackendPfe.repository.UserRepository;
import com.projet.BackendPfe.request.LoginRequest;
import com.projet.BackendPfe.services.UserDetailsImpl;
import com.projet.BackendPfe.services.UserMNService;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/userMN")
public class MNController {
	@Autowired 	AuthenticationManager authenticationManager;
	@Autowired	UserMNRepository repository;
	@Autowired	private  UserMNService service  ;
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
	@PostMapping("/addUserMNParAdminSansImage/{id}")
	public long addMEBParAdmin( @PathVariable("id") long  idAdmin , @RequestBody UserMN mH1 ) throws IOException {
		if (repuser.existsByUsername(mH1.getUsername()) ) {
            throw new DataIntegrityViolationException("username existe déjà");}
		
		
		if (repuser.existsByEmail(mH1.getEmail())) {
            throw new DataIntegrityViolationException("email existe déjà");}
		
		if (repuser.findByCin(mH1.getCin())!= null) {
            throw new DataIntegrityViolationException("Cin existe déjà");}
		
		String motDePasse =service.generatePassword(8, mH1.getPrenom(), mH1.getNom());

		UserMN user = new UserMN( 
									mH1.getCin() ,
									mH1.getNom() ,
									mH1.getPrenom() ,
									mH1.getGender() ,
									service.generateUniqueUsername(mH1.getPrenom(), mH1.getNom()),
									mH1.getEmail(),
									encoder.encode(motDePasse), 
									motDePasse ,
									mH1.getImage(),
									new Date()
									, "User MN",
									repositoryAdmin.findById(idAdmin) ,
									mH1.getTelephone()  ,
									mH1.getDate_naissance()) ;
                repository.save(user);
		return   repository.findByUsername(user.getUsername()).get().getId() ;
	}
	
	/************************************************************************************/
	  @PutMapping("/updateUserMN/{id}")
	  public ResponseEntity<?> updateUserAudi(@PathVariable("id") long id, @RequestBody UserMN MH1) {
	    System.out.println("Update Utilisateur with ID = " + id + "...");
	    Optional<UserMN> UtilisateurInfo = repository.findById(id);
	    UserMN utilisateur = UtilisateurInfo.get();
	    if (repuser.existsByUsername(MH1.getUsername())) {
	    	if(repuser.findById(id).get().getId() != (repuser.findByUsername(MH1.getUsername()).get().getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Username is already taken!"));
		}
	    }
		if (repuser.existsByEmail(MH1.getEmail())) {
			if(repuser.findById(id).get().getId() != (repuser.findByEmail(MH1.getEmail()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Email is already in use!"));}}
		
		if (repuser.existsByCin(MH1.getCin())) {
			if(repuser.findById(id).get().getId() != (repuser.findByCin(MH1.getCin()).getId())) {
			return ResponseEntity
					.badRequest()
					.body(new Message("Error: Cin is already in use!"));}}
		utilisateur.setCin(MH1.getCin());
		utilisateur.setNom(MH1.getNom());
		utilisateur.setPrenom(MH1.getPrenom());
		utilisateur.setGender(MH1.getGender());
		utilisateur.setUsername(MH1.getUsername());
		utilisateur.setEmail(MH1.getEmail());
		utilisateur.setPassword(encoder.encode(MH1.getPassword()));
		utilisateur.setReservePassword(MH1.getReservePassword());
		utilisateur.setTelephone(MH1.getTelephone());
		utilisateur.setDate_naissance(MH1.getDate_naissance());
		
	      return new ResponseEntity<>(repository.save(utilisateur), HttpStatus.OK);
	    } 
	  
	
  /**********************************************************************************************/
	    @GetMapping( "/getuserMN/{id}" )
			public UserMN getUserAudi(@PathVariable("id") long id) throws IOException {

			  UserMN p = repository.findById(id).get();
				    return p;}
	  /***********************************************************************************************/
		@DeleteMapping("/deletUserMN/{id}")
		public void deleteBMW(@PathVariable("id") long id){

			repository.deleteById(id);
		} 
		/*******************************************************************************************/
		@GetMapping("/all")
		public List<UserMN> getAll(){
			return repository.findAll();}
		
		/***********************************************************************************************/
		 @GetMapping("/homme")
		  public int getUserMBHomme() {
			 
				 List <UserMN> mebs = repository.findAll() ; 
			     List<UserMN> resultat = new ArrayList<UserMN>() ; 

				for(UserMN meb :mebs) {
			       if(meb.getGender().equals("homme")) {
		          	resultat.add(meb) ; 	}

				}
				int nbrUsermeb=resultat.size();
				return nbrUsermeb;
			 }
	/***********************************************************************************************/

		 @GetMapping("/femme")
		  public int getUserAudiFemme() {
			 


			 List <UserMN> mebs = repository.findAll() ; 
		     List<UserMN> resultat = new ArrayList<UserMN>() ; 

			for(UserMN meb :mebs) {
		       if(meb.getGender().equals("femme")) {
	          	resultat.add(meb) ; 	}

			}
			int nbrUsermeb=resultat.size();
			return nbrUsermeb;
			 }
		 
 /***********************************************************************************************/
		 
		 @GetMapping("/nbrAll")
		  public int getAllNbr() {
			 
				 List <UserMN> meb = repository.findAll() ; 

				int nbrUsermeb=meb.size();
				return nbrUsermeb;
			 }
}
