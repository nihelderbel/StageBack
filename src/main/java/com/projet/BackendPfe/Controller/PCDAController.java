package com.projet.BackendPfe.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.projet.BackendPfe.Entity.PCDA;
import com.projet.BackendPfe.services.PCDAService;



@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/PCDA")
public class PCDAController {
	@Autowired PCDAService  service ;
	
@PostMapping("/addPcda")
public PCDA create(@RequestBody PCDA pcda) {
	//System.out.println(pcda);
	return service.ajouterPDCA(pcda);
}
@GetMapping("/all")
public List<PCDA> get(){
    return service.getAll();
}
@PutMapping("/update/{id}")
public PCDA update(@PathVariable int id ,@RequestBody PCDA pcda){
	System.out.println(pcda);
    return service.modifierPCDA(id,pcda);
}
@DeleteMapping("{id}")
public String delet(@PathVariable int id){
    return service.SupprimerPCDA(id);
}

@GetMapping("/get/{id}")
public PCDA getById(@PathVariable int id){
    return service.getPCDA(id);
}
}
