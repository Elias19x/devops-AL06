package com.esprit.examen.services;

import java.util.List;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.TypeCours;

public interface IFormateurService {
	Long addFormateur(Formateur formateur);

	Long modifierFormateur(Formateur formateur);

	void supprimerFormateur(Long formateurId);
	
		
	List<Formateur> listFormateurs();
	public Integer countFormateur();
}
