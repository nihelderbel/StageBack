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

import com.projet.BackendPfe.Entity.Audit;
import com.projet.BackendPfe.services.AuditService;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/Audit")
public class AuditController {
	@Autowired AuditService service;
	@PostMapping("/addAudit")
	public Audit create(@RequestBody Audit audit) {
		System.out.println(audit);
		return service.ajouterAudit(audit);
	}
	@GetMapping("/all")
	public List<Audit> get(){
	    return service.getAll();
	}
	@PutMapping("/update/{id}")
	public Audit update(@PathVariable int id ,@RequestBody Audit audit){
		System.out.println(audit);
	    return service.modifierAudit(id, audit);
	}
	@DeleteMapping("{id}")
	public String delet(@PathVariable int id){
	    return service.SupprimerAudit(id);
	}

	@GetMapping("/get/{id}")
	public Audit getById(@PathVariable int id){
	    return service.getAudit(id);
	}

}
