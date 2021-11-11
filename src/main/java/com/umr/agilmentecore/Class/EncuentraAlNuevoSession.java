package com.umr.agilmentecore.Class;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnTransformer;

import com.umr.agilmentecore.Class.Params.MaxLevel;
import com.umr.agilmentecore.Class.Params.MaximumTime;
import com.umr.agilmentecore.Class.Params.SpriteSet;
import com.umr.agilmentecore.Class.Params.VariableSize;
import com.umr.agilmentecore.Interfaces.IGameSession;
import com.umr.agilmentecore.Interfaces.IParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "encuentra_al_nuevo_session")
public class EncuentraAlNuevoSession implements IGameSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MaxLevel maxLevel;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MaximumTime maximumTime;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private SpriteSet spriteSet;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private VariableSize variableSize;
	@Column(name = "results")
	@OneToMany(cascade=CascadeType.ALL)
	private List<EncuentraAlNuevoResult> results;
	@ManyToOne
	private Game game;
	
	public void addResult(EncuentraAlNuevoResult result) {
		this.results.add(result);
	}
		
	@Override
	public String toString() {
		return "";
	}
	
	@Override
	public Long getId() {
		return this.id;
	}

	//Devuelve el nombre del juego utilizado en la sesion
	@Override
	public String getName() {
		return this.game.getName();
	}
	
	@Override
	public List<Param> getParams() {
		return this.game.getParams();
	}
	
	@ColumnTransformer
	@Override
	public List<IParam> getSettedParams() {
		List<IParam> params = new ArrayList<IParam>();
		
		if (this.maxLevel != null) {
			params.add(maxLevel);
		}
		
		if (this.maximumTime != null) {
			params.add(maximumTime);
		}
		
		if (this.spriteSet != null) {
			params.add(spriteSet);
		}
		
		if (this.variableSize != null) {
			params.add(variableSize);
		}
		
		return params;
	}
	
	/**
	 * Agrega un parámetro a la sesión.
	 * @param type Puede ser:
	 *   - "MaximumTime": Límite de tiempo para la sesión.
	 *   - "MaxLevel": Límite de figuras para la sesión.
	 * "MaxLevel" y "MaximumTime" son mutuamente exclueyentes. 
	 * @param value Valor del parámetro.
	 */
	@Override
	public void addParam(String type, String value) throws Exception {
		if (this.canAddEndConditionParam()) {
			if (this.isMaximumTimeParam(type)) {
				this.maximumTime = new MaximumTime();
				this.maximumTime.setValue(value);
			} else if(this.isMaxLevelParam(type)) {
				this.maxLevel = new MaxLevel();
				this.maxLevel.setValue(value);
			} 
		}
		
		if (this.isSpriteSetParam(type)) {
			this.spriteSet = new SpriteSet();
			this.spriteSet.setValue(value);
		}
		
		if (this.isVariableSizeParam(type)) {
			this.variableSize = new VariableSize();
			this.variableSize.setValue(value);
		}
	}
	
	/**
	 * Verifica si el tipo de parámetro es "MaximumTime".
	 * @param type Tipo de parámetro.
	 * @return Verdadero si es "MaximumTime".
	 */
	private boolean isMaximumTimeParam(String type) {
		return type.equals("MaximumTime");
	}
	
	/**
	 * Verifica si el tipo de parámetro es "MaxLevel".
	 * @param type Tipo de parámetro.
	 * @return Verdadero si es "MaxLevel".
	 */
	private boolean isMaxLevelParam(String type) {
		return type.equals("MaxLevel");
	}
	
	/**
	 * Verifica si se puede añadir un parámetro "MaxLevel" o
	 * "MaximumTime".
	 * No tiene que existir un valor anterior para ninguna de las dos.
	 * @return Verdadero si puede añadir una condición de parada.
	 */
	private boolean canAddEndConditionParam() {
		return (this.maxLevel == null) && (this.maximumTime == null);
	}

	/**
	 * Obtiene la condición de parada de la instancia de Session.
	 * @return Condición de parada.
	 */
	@Override
	public IParam getEndCondition() {
		IParam param = null;
		
		if (this.maxLevel != null) {
			param = maxLevel;
		} else if (this.maximumTime != null) {
			param = maximumTime;
		}
		
		return param;
	}
	
	/**
	 * Verifica si el tipo de parámetro es "SpriteSet".
	 * @param type Tipo de parámetro.
	 * @return Verdadero si es "SpriteSet".
	 */
	private boolean isSpriteSetParam(String type) {
		return type.equals("SpriteSet");
	}
	
	/**
	 * Verifica si el tipo de parámetro es "VariableSize".
	 * @param type Tipo de parámetro.
	 * @return Verdadero si es "VariableSize".
	 */
	private boolean isVariableSizeParam(String type) {
		return type.equals("VariableSize");
	}
}
