package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

import com.esprit.examen.entities.Contrat;
import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.Poste;
import com.esprit.examen.entities.Session;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.repositories.SessionRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
@RunWith(SpringRunner.class)
@SpringBootTest
public class SessionServiceTest {

    @Autowired
    SessionRepository  sr;
    @Autowired
    ICoursService  cs;
    @Autowired
    IFormateurService  fs;
    @Autowired
    ISessionService  ss;
    @Test
    public void testAddSession() {

        Session session = new Session(null, new Date(20141212), new Date(20201212), 50L, "description");
        Long dataPreTest = sr.count();
        Session savedSession =sr.save(session);
		Long dataAfterTest = sr.count();
		assertThat(dataPreTest).isEqualTo(dataAfterTest -1);
        assertThat(savedSession.getId()).isEqualTo(session.getId());
        ss.supprimerSession(session.getId());

    }

    @Test
    public void testAffecterFormateurASession() {


        Session session = new Session(null, new Date(20141212), new Date(20201212), 50L, "description");
        Formateur formateur = new Formateur(null,"ayari","sami",Poste.Ingénieur,Contrat.CDI,"h@gmail.com","psdpsd");
        Session savedSession = sr.save(session);
        ss.affecterFormateurASession(formateur.getId(), session.getId());
        ss.supprimerSession(session.getId());
        assertThat(session.getFormateur().getNom()).isEqualTo("sami");

    }

    @Test
    public void testModifierSession() {
        Session session = new Session(null, new Date(20141212), new Date(20201212), 50L, "description");
        Formateur formateur = new Formateur(null,"ayari","sami",Poste.Ingénieur,Contrat.CDI,"h@gmail.com","psdpsd");
        Session savedSession = sr.save(session);
        session.setDescription("modified");
        ss.modifierSession(session);
		assertThat(session.getDescription()).isEqualTo("modified");
    }

    @Test
    public void testSupprimerSession() {
        Session session = new Session(null, new Date(20151212), new Date(20201010), 50L, "description");
        Session savedSession =sr.save(session);
        Long dataPreTest = sr.count();
        ss.supprimerSession(savedSession.getId());
        Long dataAfterTest = sr.count();
		assertThat(dataPreTest).isEqualTo(dataAfterTest +1);

    }
}
