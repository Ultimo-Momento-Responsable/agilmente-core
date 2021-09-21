package com.umr.agilmentecore.Class.Params;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.umr.agilmentecore.Interfaces.IParam;

public class VariableSize implements IParam{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "variable_size")
	private boolean variableSize;
	
	@Override
	public String getValue() {
		return Boolean.toString(this.variableSize);
	}

	@Override
	public String getName() {
		return "variableSize";
	}
	
	@Override
	public String getSpanishName() {
		return "Tamaño Variable";
	}
	
	@Override
	public void setValue(String value) throws Exception {
		if (value.equals("true")) {
			this.variableSize = true;
		}else {
			this.variableSize = false;
		}
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
