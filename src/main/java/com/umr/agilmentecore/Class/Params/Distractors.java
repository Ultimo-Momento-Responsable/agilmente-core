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
@Table(name = "distractors")
public class Distractors implements IParam{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "distractors")
	private boolean distractors;
	
	@Override
	public String getValue() {
		return Boolean.toString(this.distractors);
	}

	@Override
	public String getName() {
		return "distractors";
	}
	
	@Override
	public String getSpanishName() {
		return "Distractores";
	}
	
	@Override
	public String getUnit() {
		return null;
	}
	
	@Override
	public String getContextualHelp() {
		return "Agrega aleatoriamente figuras que no corresponden al conjunto de figuras a utilizar";
	}
	
	@Override
	public void setValue(String value) throws Exception {
		if (value.equals("true")) {
			this.distractors = true;
		}else {
			this.distractors = false;
		}
	}

	@Override
	public String toString() {
		return this.getValue();
	}
}
