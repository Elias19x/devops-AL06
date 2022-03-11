package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.util.Date;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;

import com.esprit.examen.entities.Contrat;
import com.esprit.examen.entities.Cours;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.Poste;
import com.esprit.examen.entities.Session;
import com.esprit.examen.entities.TypeCours;
import com.esprit.examen.repositories.CoursRepository;

class CoursServiceTest {
    @Autowired
    CoursService coursService;
    @Autowired
    SessionService sessionService;
    @Autowired
    FormateurService formateurService;
    @Autowired
    CoursRepository coursRepository;
    private static final Logger l = LogManager.getLogger(CoursServiceTest.class);
    private static String DESCRIPTION = "Angular est un framework côté client, open source, basé sur TypeScript,";
    private static TypeCours TYPECOUR = TypeCours.Informatique;
    private static String INTITULE = "Angular";
    private Set<Session> sessions;
    private Cours cours1 = new Cours( DESCRIPTION, TYPECOUR, INTITULE ,sessions);
    Date dateDebut = new Date(2021, 7,2 );
    Date datefin = new Date(2021, 8,2 );
    private Formateur formateur= new Formateur("nour", "laabbidi", Poste.Ingénieur, Contrat.CIVP, "nour@gmail.com", "nour");

   // private Session session= new Session(dateDebut,datefin,13L,"test" , formateur);




    @BeforeEach
    void clearDataBase() {
        coursRepository.deleteAll();
    }
    @Test
    void testAddCours() throws ParseException{
        l.debug("save formateur into data base");
        formateurService.addFormateur(formateur);
        Formateur f1=  (Formateur)formateurService.listFormateurs().get(0);
        Session session= new Session(dateDebut,datefin,13L,"test" , f1);
        Session S1=  (Session)sessionService.listSession().get(0);
        l.debug("save sesssion into data base");
        sessionService.addSession(S1);
       l.debug("Save course with sessions");
        coursService.addCours(cours1);
        Cours c1=  (Cours)coursService.getCours().get(0);
        assertThat(c1.equals(cours1)).isTrue();
    }

   @Test
    void testDeleteCours() throws ParseException{
        l.debug("entring TestDeleteCours methode");
        coursService.addCours(cours1);
        Cours c1=  (Cours)coursService.getCours().get(0);
        assertThat(c1.equals(cours1)).isTrue();
        coursService.supprimerCours(c1.getId());
        int size = coursService.getCours().size();
        assertThat(size==0).isTrue();
        assertThat(!coursService.getCours().contains(c1)).isTrue();
    }
    @Test
    void testModifierCours() throws ParseException{
        l.debug("entring TestModifierCours methode");
        coursService.addCours(cours1);
        cours1.setIntitule("React");
        cours1.setDescription("React est un framework côté client, open source, basé sur TypeScript,");
        cours1.setTypeCours(TypeCours.Scrum);
        coursService.addCours(cours1);
        Cours c1=  (Cours)coursService.getCours().get(0);
        assertThat(!c1.equals(cours1)).isFalse();
    }



    @AfterEach
    void setUpDataBase() {
        coursRepository.deleteAll();
    }
}
