package com.umr.agilmentecore.Class.IntermediateClasses;

import java.util.List;

import com.umr.agilmentecore.Class.EncuentraAlNuevoResult;
import com.umr.agilmentecore.Class.HayUnoRepetidoResult;
import com.umr.agilmentecore.Class.MemorillaResult;

public class PatientResultsView {
	private PatientResultsHayUnoRepetidoView hayUnoRepetido;
	private PatientResultsEncuentraAlNuevoView encuentraAlNuevo;
	private PatientResultsMemorillaView memorilla;
	
	public PatientResultsView(List<HayUnoRepetidoResult> hayUnoRepetido,
		List<EncuentraAlNuevoResult> encuentraAlNuevo,
		List<MemorillaResult> memorilla) {
			super();
			this.hayUnoRepetido = new PatientResultsHayUnoRepetidoView(hayUnoRepetido);
			this.encuentraAlNuevo = new PatientResultsEncuentraAlNuevoView(encuentraAlNuevo);
			this.memorilla = new PatientResultsMemorillaView(memorilla);
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

	public PatientResultsMemorillaView getMemorilla() {
		return memorilla;
	}

	public void setMemorilla(PatientResultsMemorillaView memorilla) {
		this.memorilla = memorilla;
	}
}
