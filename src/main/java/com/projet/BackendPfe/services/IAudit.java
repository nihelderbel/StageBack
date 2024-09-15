package com.projet.BackendPfe.services;

import java.util.List;

import com.projet.BackendPfe.Entity.Audit;



public interface IAudit {
	Audit ajouterAudit(Audit audit);
    List<Audit> getAll();
    Audit modifierAudit(long id ,Audit audit);
    String SupprimerAudit(long id);
   Audit getAudit(long id);
}
