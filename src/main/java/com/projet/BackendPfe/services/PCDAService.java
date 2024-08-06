package com.projet.BackendPfe.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.BackendPfe.Entity.PCDA;
import com.projet.BackendPfe.repository.PCDArepository;
@Service
public class PCDAService implements IPCDA{
	@Autowired PCDArepository repository;

	@Override
	public PCDA ajouterPDCA(PCDA pcda) {
		// TODO Auto-generated method stub
		return repository.save(pcda);
	}

	@Override
	public List<PCDA> getAll() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

	@Override
	public PCDA modifierPCDA(long id, PCDA pcda) {
		// TODO Auto-generated method stub
		return repository.findById(id)
                .map(d -> {
                   // car date deja automatiquement d.setDate_creation_dossier(dossierMedicale.getDate_creation_dossier());
                    d.setDesignation(pcda.getDesignation());
                    d.setTitre(pcda.getTitre());
                    d.setSujet_de_publication(pcda.getSujet_de_publication());
                    d.setProcessus(pcda.getProcessus());
                    d.setPriorite(pcda.getPriorite());
                    d.setAction(pcda.getAction());
                    d.setO_N(pcda.getO_N());
                    d.setDelaideaction(pcda.getDelaideaction());
                    d.setResponsable(pcda.getResponsable());
                    d.setDelai(pcda.getDelai());
                    d.setStatut(pcda.getStatut());
                    d.setCommentaire(pcda.getCommentaire());
                  
                    return repository.save(d);
                }).orElseThrow(() -> new RuntimeException("PCDA non trové"));
	}

	@Override
	public String SupprimerPCDA(long id) {
		// TODO Auto-generated method stub
		repository.deleteById(id);
		return "PCDA supprimé";
	}

	@Override
	public PCDA getPCDA(long id) {
		// TODO Auto-generated method stub
		return repository.findById(id).get();
	}

}
