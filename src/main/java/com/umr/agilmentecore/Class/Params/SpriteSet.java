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
@Table(name = "sprite_set")
public class SpriteSet implements IParam {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(name = "sprite_set")
	private int spriteSet;
	
	@Override
	public String getValue() {
		return Integer.toString(this.spriteSet);
	}
	
	@Override
	public void setValue(String value) throws Exception {
		int parsed = Integer.parseInt(value);
		this.spriteSet = parsed;
	}
	
	@Override
	public String toString() {
		return this.getValue();
	}
	
	@Override
	public String getName() {
		return "spriteSet";
	}
	
	@Override
	public String getSpanishName() {
		return "Conjunto de figuras";
	}
	
}
