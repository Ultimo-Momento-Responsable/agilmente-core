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

import com.umr.agilmentecore.Class.Params.FigureQuantity;
import com.umr.agilmentecore.Class.Params.MaxLevel;
import com.umr.agilmentecore.Class.Params.NumberOfColumns;
import com.umr.agilmentecore.Class.Params.NumberOfRows;
import com.umr.agilmentecore.Interfaces.IGameSession;
import com.umr.agilmentecore.Interfaces.IParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "memorilla_session")
public class MemorillaSession implements IGameSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MaxLevel maxLevel;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private NumberOfColumns numberOfColumns;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private NumberOfRows numberOfRows;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private FigureQuantity figureQuantity;
	@Column(name = "results")
	@OneToMany(cascade=CascadeType.ALL)
	private List<MemorillaResult> results;
	@ManyToOne
	private Game game;
	
	public void addResult(MemorillaResult result) {
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
		
				
		if (this.numberOfRows != null) {
			params.add(numberOfRows);
		}
		
		if (this.numberOfColumns != null) {
			params.add(numberOfColumns);
		}
		
		if (this.figureQuantity != null) {
			params.add(figureQuantity);
		}
		
		return params;
	}
	
	/**
	 * Agrega un parámetro a la sesión.
	 * @param type Puede ser:
	 *   - "MaxLevel"
	 *   - "NumberOfRows"
	 *   - "NumberOfColumns"
	 *   - "FigureQuantity"
	 * @param value Valor del parámetro.
	 */
	@Override
	public void addParam(String type, String value) throws Exception {
		if (this.canAddEndConditionParam()) {
			if (this.isMaxLevelParam(type)) {
				this.maxLevel = new MaxLevel();
				this.maxLevel.setValue(value);
			}
		}
		
		if (this.isNumberOfRowsParam(type)) {
			this.numberOfRows = new NumberOfRows();
			this.numberOfRows.setValue(value);
		}
		
		if (this.isNumberOfColumnsParam(type)) {
			this.numberOfColumns = new NumberOfColumns();
			this.numberOfColumns.setValue(value);
		}
		
		if (this.isFigureQuantityParam(type)) {
			this.figureQuantity = new FigureQuantity();
			this.figureQuantity.setValue(value);
		}
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
	 * Verifica si se puede añadir un parámetro "MaxLevel"
	 * No tiene que existir un valor anterior.
	 * @return Verdadero si puede añadir una condición de parada.
	 */
	private boolean canAddEndConditionParam() {
		return (this.maxLevel == null);
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
		}
		
		return param;
	}
	
	/**
	 * Verifica si el tipo de parámetro es "NumberOfRows".
	 * @param type Tipo de parámetro.
	 * @return Verdadero si es "NumberOfRows".
	 */
	private boolean isNumberOfRowsParam(String type) {
		return type.equals("NumberOfRows");
	}
	
	/**
	 * Verifica si el tipo de parámetro es "NumberOfColumns".
	 * @param type Tipo de parámetro.
	 * @return Verdadero si es "NumberOfColumns".
	 */
	private boolean isNumberOfColumnsParam(String type) {
		return type.equals("NumberOfColumns");
	}
	
	/**
	 * Verifica si el tipo de parámetro es "FigureQuantity".
	 * @param type Tipo de parámetro.
	 * @return Verdadero si es "FigureQuantity".
	 */
	private boolean isFigureQuantityParam(String type) {
		return type.equals("FigureQuantity");
	}
}
