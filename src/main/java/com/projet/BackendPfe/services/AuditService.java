package com.projet.BackendPfe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.BackendPfe.Entity.Audit;
import com.projet.BackendPfe.repository.AuditRepositpry;
@Service
public class AuditService implements IAudit {
	@Autowired AuditRepositpry repository;
	@Override
	public Audit ajouterAudit(Audit audit) {
		// TODO Auto-generated method stub
		return repository
				.save(audit);
	}

	@Override
	public List<Audit> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public Audit modifierAudit(long id, Audit audit) {
		// TODO Auto-generated method stub
		return repository.findById(id)
                .map(d -> {
                   // car date deja automatiquement d.setDate_creation_dossier(dossierMedicale.getDate_creation_dossier());
                    d.setTypedocument(audit.getTypedocument());
                    d.setNumeroinstruction(audit.getNumeroinstruction());
                   d.setTitredocument(audit.getTitredocument());
                   d.setRaison(audit.getRaison());
                   d.setContenu(audit.getContenu());
                   d.setEtatdeinstruction(audit.getEtatdeinstruction());
                   d.setEffectif(audit.getEffectif());
                   d.setApplication(audit.getApplication());
                   d.setMiseajour(audit.getMiseajour());
                   d.setDeviation(audit.getDeviation());
                   d.setAction(audit.getAction());
                   d.setResponsable(audit.getResponsable());
                   d.setDelai(audit.getDelai());
                   d.setControlesuccess(audit.getControlesuccess());
                  
                    return repository.save(d);
                }).orElseThrow(() -> new RuntimeException("Audit non trové"));
	}

	@Override
	public String SupprimerAudit(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		return "Audit supprimée";
	}

	@Override
	public Audit getAudit(long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

}
