package com.esprit.examen.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

import com.esprit.examen.entities.Contrat;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.Session;
import com.esprit.examen.repositories.FormateurRepository;
import com.esprit.examen.repositories.SessionRepository;

@Service
public class SessionService implements ISessionService{

	@Autowired
	SessionRepository sessionRepository;
	@Override
	public Long addSession(Session session) {
		sessionRepository.save(session);
		return session.getId();
	}

	@Override
	public Long modifierSession(Session session) {
		sessionRepository.save(session);
		return session.getId();
	}

	@Override
	public void supprimerSession(Long sessionId) {
		sessionRepository.deleteById(sessionId);
	}

	@Override
	public void affecterFormateurASession(Long formateurId, Long sessionId) {
			/*todo*/
		
	}
	@Override
	public Session plusLongSession() {
		List<Session> list = sessionRepository.findAll();
		return  list.stream().sorted(Comparator.comparing(Session::getDuree).reversed())
		.findFirst().get();


		
	}
}
