package com.umr.agilmentecore;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Controller.PatientController;

@SpringBootTest
public class PatientUnitTests {
	
	@Autowired(required=true)
	private PatientController patientController;
	
	private Patient p;
	private Long id;
	
	@BeforeEach
	public void before() throws ParseException {
		String sBD="11-06-1993";  
	    Date bD= new SimpleDateFormat("dd-MM-yyyy").parse(sBD);  
		p = new Patient();
		p.setId((long) 100); 
		id = p.getId();
		p.setFirstName("Julián");
		p.setLastName("Marquez");
		p.setBornDate(bD);
		p.setCity("Villa María");
		p.setDescription("El paciente es una extraña amalgama de Federico Marquez y Julian Polo");
	}
	
	@Test
	public void testNewPatient() throws Exception {
		assertThat(patientController.save(p)).isNotNull();
	}
	
	@Test
	public void testPutPatient() throws Exception {
		p.setCity("Villa Nueva");
		assertThat(patientController.update(p,id)).isNotNull();
	}
	
}
