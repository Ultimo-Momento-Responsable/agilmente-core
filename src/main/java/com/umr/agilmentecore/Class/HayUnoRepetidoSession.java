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

import com.umr.agilmentecore.Class.IntermediateClasses.ResultsData;
import com.umr.agilmentecore.Class.Params.FigureQuantity;
import com.umr.agilmentecore.Class.Params.MaximumTime;
import com.umr.agilmentecore.Interfaces.IGameSession;
import com.umr.agilmentecore.Interfaces.IParam;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "hay_uno_repetido_session")
public class HayUnoRepetidoSession implements IGameSession {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private FigureQuantity figureQuantity;
	@OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
	private MaximumTime maximumTime;
	@Column(name = "results")
	@OneToMany
	private List<HayUnoRepetidoResult> results;
	@ManyToOne
	private Game game;
	
	@Override
	public String toString() {
		return "";
	}
	

	//Devuelve el nombre del juego utilizado en la sesion
	@Override
	public String getName() {
		return this.game.getName();
	}
	
	/*
	 * Pide a ResultsData los datos especificos de los resultados de
	 * Hay Uno Repetido y los devuelve como una lista
	 */
	@Override
	public List<ResultsData> getResults() {
		List<ResultsData> finalResult = new ArrayList<ResultsData>();
		
		for (HayUnoRepetidoResult resultsData : this.results) {
			ResultsData result = new ResultsData(resultsData.getId(), resultsData.getCompleteDatetime(), resultsData.isCanceled(),
												resultsData.getMistakes(),	resultsData.getSuccesses(), resultsData.getTimeBetweenSuccesses(),
												resultsData.getTotalTime());	
			finalResult.add(result);
		}
		
		return finalResult;
	}
	
	@Override
	public List<Param> getParams() {
		return this.game.getParam();
	}
	
	@Override
	public List<IParam> getSettedParams() {
		List<IParam> params = new ArrayList<IParam>();
		params.add(figureQuantity);
		params.add(maximumTime);
		return params;
	}
	
	/**
	 * Agrega un parámetro a la sesión.
	 * @param type Puede ser:
	 *   - "MaximumTime": Límite de tiempo para la sesión.
	 *   - "FigureQuantity": Límite de figuras para la sesión.
	 * "FigureQuantity" y "MaximumTime" son mutuamente exclueyentes. 
	 * @param value Valor del parámetro.
	 */
	@Override
	public void addParam(String type, String value) throws Exception {
		if (this.canAddEndConditionParam()) {
			if (this.isMaximumTimeParam(type)) {
				this.maximumTime = new MaximumTime();
				this.maximumTime.setValue(value);
			} else if(this.isFigureQuantityParam(type)) {
				this.figureQuantity = new FigureQuantity();
				this.figureQuantity.setValue(value);
			} 
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
	 * Verifica si el tipo de parámetro es "FigureQuantity".
	 * @param type Tipo de parámetro.
	 * @return Verdadero si es "FigureQuantity".
	 */
	private boolean isFigureQuantityParam(String type) {
		return type.equals("FigureQuantity");
	}
	
	/**
	 * Verifica si se puede añadir un parámetro "FigureQuantity" o
	 * "MaximumTime".
	 * No tiene que existir un valor anterior para ninguna de las dos.
	 * @return Verdadero si puede añadir una condición de parada.
	 */
	private boolean canAddEndConditionParam() {
		return (this.figureQuantity == null) && (this.maximumTime == null);
	}
}
