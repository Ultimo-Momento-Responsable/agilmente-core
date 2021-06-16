package com.umr.agilmentecore;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.umr.agilmentecore.Class.CognitiveDomain;
import com.umr.agilmentecore.Controller.CognitiveDomainController;

@SpringBootTest
public class CognitiveDomainUnitTests {
	
	@Autowired(required=true)
	private CognitiveDomainController cognitiveDomainController;
	
	private CognitiveDomain cognitiveDomain;
	
	@BeforeEach
	public void before() throws ParseException {
		float[] tBS = new float[2];
		tBS[0] = (float) 1.428417;
		tBS[1] = (float) 0.8234483;
		
	    cognitiveDomain = new CognitiveDomain();
	    cognitiveDomain.setName("Memoria");
	}
	
	@Test
	public void testNewCognitiveDomain() throws Exception {
		assertThat(cognitiveDomainController.saveGame(cognitiveDomain)).isNotNull();
	}
}
