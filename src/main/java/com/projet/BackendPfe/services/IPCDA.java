package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.PCDA;



public interface IPCDA {
	 PCDA ajouterDossier(PCDA pcda);
     List<PCDA> getAll();
     PCDA modifierPCDA(long id ,PCDA pcda);
     String SupprimerPCDA(long id);
    PCDA getPCDA(long id);


}
