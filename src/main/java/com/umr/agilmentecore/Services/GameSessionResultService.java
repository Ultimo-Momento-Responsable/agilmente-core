package com.umr.agilmentecore.Services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.EncuentraAlNuevoResult;
import com.umr.agilmentecore.Class.EncuentraAlNuevoSession;
import com.umr.agilmentecore.Class.IntermediateClasses.EncuentraAlNuevoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.HayUnoRepetidoResultDetailView;
import com.umr.agilmentecore.Class.IntermediateClasses.PlanningMobileData;
import com.umr.agilmentecore.Class.IntermediateClasses.ResultsListView;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoResultRepository;
import com.umr.agilmentecore.Persistence.EncuentraAlNuevoSessionRepository;
import com.umr.agilmentecore.Persistence.HayUnoRepetidoResultRepository;

@Service
public class GameSessionResultService {
	@Autowired
	private HayUnoRepetidoResultRepository hayUnoRepetidoResultRepository;
	@Autowired
	private EncuentraAlNuevoSessionRepository encuentraAlNuevoSessionRepository;
	
	/**
	 * Obtiene una página de resultados de todos los juegos.
	 * @param page Opciones de paginación.
	 * @return Página de resultados.
	 */
	public Page<ResultsListView> getAllResultsOrdered(Pageable page) {
		return this.hayUnoRepetidoResultRepository.findAllResultsListView(page);
	}	
	
	/**
	 * Obtiene un resultado de HayUnoRepetido.
	 * @param Long el id del juego específico.
	 * @return Optional un resultado de un juego o nada.
	 */
	public Optional<HayUnoRepetidoResultDetailView> getOneHayUnoRepetido(Long id) {
		return this.hayUnoRepetidoResultRepository.findHayUnoRepetidoResultDetailById(id);
	}

	public void saveEncuentraAlNuevo(EncuentraAlNuevoResultDetailView result) {
		EncuentraAlNuevoSession eANS = this.encuentraAlNuevoSessionRepository.getOne(result.getEncuentraAlNuevoSessionId());
		EncuentraAlNuevoResult eANR = new EncuentraAlNuevoResult();
		eANR.setMistakes(result.getMistakes());
		eANR.setCanceled(result.isCanceled());
		eANR.setCompleteDatetime(result.getCompleteDatetime());
		eANR.setTimeBetweenSuccesses(result.getTimeBetweenSuccesses());
		eANR.setTotalTime(result.getTotalTime());
		eANS.addResult(eANR);
		encuentraAlNuevoSessionRepository.save(eANS);
	}
}
