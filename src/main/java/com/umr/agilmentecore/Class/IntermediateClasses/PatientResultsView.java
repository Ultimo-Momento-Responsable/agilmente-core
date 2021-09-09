package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.List;

import com.umr.agilmentecore.Class.HayUnoRepetidoResult;

public class PatientResultsView {
	private PatientResultsHayUnoRepetidoView hayUnoRepetido;
	private PatientResultsEncuentraAlNuevoView encuentraAlNuevo;
	
	public PatientResultsView(List<HayUnoRepetidoResult> hayUnoRepetido,
			PatientResultsEncuentraAlNuevoView encuentraAlNuevo) {
		super();
		this.hayUnoRepetido = new PatientResultsHayUnoRepetidoView(hayUnoRepetido);
		this.encuentraAlNuevo = encuentraAlNuevo;
	}
	
	public PatientResultsHayUnoRepetidoView getHayUnoRepetido() {
		return hayUnoRepetido;
	}
	public void setHayUnoRepetido(PatientResultsHayUnoRepetidoView hayUnoRepetido) {
		this.hayUnoRepetido = hayUnoRepetido;
	}
	public PatientResultsEncuentraAlNuevoView getEncuentraAlNuevo() {
		return encuentraAlNuevo;
	}
	public void setEncuentraAlNuevo(PatientResultsEncuentraAlNuevoView encuentraAlNuevo) {
		this.encuentraAlNuevo = encuentraAlNuevo;
	}
}