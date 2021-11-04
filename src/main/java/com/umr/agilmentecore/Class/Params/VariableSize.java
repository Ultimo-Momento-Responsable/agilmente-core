package com.umr.agilmentecore.Class.Params;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.umr.agilmentecore.Interfaces.IParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "variable_size")
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
	public String getUnit() {
		return null;
	}
	
	@Override
	public String getContextualHelp() {
		return "Conjunto de gráficos a utilizar en las figuras del juego";
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
