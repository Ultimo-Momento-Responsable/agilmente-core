package com.umr.agilmentecore;

import static org.assertj.core.api.Assertions.assertThat;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.umr.agilmentecore.Class.HayUnoRepetido;
import com.umr.agilmentecore.Controller.HayUnoRepetidoController;

@SpringBootTest
public class HayUnoRepetidoUnitTests {
	
	@Autowired(required=true)
	private HayUnoRepetidoController hayUnoRepeController;
	
	private HayUnoRepetido hayUnoRepe;
	
	
	@BeforeEach
	public void before() throws ParseException {
		float[] tBS = new float[2];
		tBS[0] = (float) 1.428417;
		tBS[1] = (float) 0.8234483;
		
		String sDT="11-06-2021 11:55:27";  
	    Date dT= new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").parse(sDT);  
		hayUnoRepe = new HayUnoRepetido();
		hayUnoRepe.setName("Hay Uno Repetido");
		hayUnoRepe.setTotalTime((float)3.054781);
		hayUnoRepe.setMistakes(1);
		hayUnoRepe.setSuccesses(2);
		hayUnoRepe.setTimeBetweenSuccesses(tBS);
		hayUnoRepe.setCanceled(true);
		hayUnoRepe.setDateTime(dT);
	}
	
	@Test
	public void testNewHayUnoRepetido() throws Exception {
		assertThat(hayUnoRepeController.saveGame(hayUnoRepe)).isNotNull();
	}
	
}
