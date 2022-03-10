package com.esprit.examen.services;

import org.junit.Test;
import static org.assertj.core.api.Assertions.assertThat;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import com.esprit.examen.entities.Contrat;
import com.esprit.examen.entities.Formateur;
import com.esprit.examen.entities.Poste;
import com.esprit.examen.repositories.FormateurRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.event.TransactionalEventListener;
@RunWith(SpringRunner.class)
@SpringBootTest
public class FormateurServiceTest {

	    @Autowired
	    IFormateurService  fs;
	    @Autowired
	    FormateurRepository  fr;
	    private static final Logger l = LogManager.getLogger(SessionServiceTest.class);
	    
        Formateur formateur = new Formateur(null,"HBIBI","Amin",Poste.Ingénieur,Contrat.CDI,"hbibi@gmail.com","psdpsd");
        Formateur formateur1 = new Formateur(null,"Amira","Amira",Poste.Ingénieur ,Contrat.CIVP ,"Amira@gmail.com","psdpsd");
        Formateur formateur2 = new Formateur(null,"Ali","Ali",Poste.Ingénieur,Contrat.CDI,"ali@gmail.com","psdpeffesd");
        
	    @TransactionalEventListener 
    @Test
    public void testAddFormateur() {
	    	
	        Long dataPreTest = fr.count();
	        Formateur savedFormateur =fr.save(formateur); 
			Long dataAfterTest = fr.count();
			assertThat(dataPreTest).isEqualTo(dataAfterTest -1);
	        assertThat(savedFormateur.getId()).isEqualTo(formateur.getId());
	        l.info("add sformateur : "+ formateur);
	        fs.supprimerFormateur(formateur.getId());

    }

    @Test
    public void testListFormateurs() {

    }

    @Test
    public void testmodifierFormateur(){
       fs.addFormateur(formateur);
       formateur.setNom("med amin");
       System.out.print(formateur.getNom());
       formateur.setPassword("aminamin");
       fs.modifierFormateur(formateur);
       Formateur formateurModifier = (Formateur) fs.listFormateurs().get(0);
       assertThat("med amin").isEqualTo(formateurModifier.getNom());
       assertThat("new password").isEqualTo(formateurModifier.getPassword());
        l.info(" formateur : "+ formateur + "modified");
    }
    	

    
    @Test
    public void testNombreFormateursImpliquesDansUnCours() {

    }

    @Test
    public void testSupprimerFormateur() {
    	
        Formateur savedFormateur =fr.save(formateur1);
        Long dataPreTest = fr.count();
        fs.supprimerFormateur(savedFormateur.getId());
        Long dataAfterTest = fr.count();
		assertThat(dataPreTest).isEqualTo(dataAfterTest -1);
        l.info(" this formateur has been deleted : "+ formateur1);

    }



@Test
public void testCountFormateur(){
   List<Formateur> listFormateur = (List<Formateur>) new ArrayList<Formateur>(); 
   assertThat(0).isEqualTo(listFormateur.size());
   listFormateur.add(formateur);
   listFormateur.add(formateur1);
   listFormateur.add(formateur2);
   for ( Formateur f : (ArrayList<Formateur>) listFormateur ){
	   fs.addFormateur(f);
   }
   List<Formateur> listFormateurSaved = (List<Formateur>) fs.listFormateurs(); 
       assertThat(3).isEqualTo(listFormateurSaved.size());
  
       l.info(" the number of trainers is : "+ fs.countFormateur() );

}}