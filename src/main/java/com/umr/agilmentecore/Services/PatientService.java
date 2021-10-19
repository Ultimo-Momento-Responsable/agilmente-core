package com.umr.agilmentecore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningState;
import com.umr.agilmentecore.Persistence.PatientRepository;
import com.umr.agilmentecore.Persistence.PlanningRepository;
import com.umr.agilmentecore.Persistence.PlanningStateRepository;


@Service
public class PatientService {
	@Autowired
	private PatientRepository repository;
	@Autowired
	private PlanningService planningService;
	@Autowired
	private PlanningStateRepository planningStateRepository;
	@Autowired
	private PlanningRepository planningRepository;
	
	/**
	 * Obtiene todos los resultados de Pacientes.
	 * @param page Contiene las opciones de paginación.
	 * @return Una página de resultados.
	 */
	public Page<Patient> getAll(Pageable page) {
		return repository.findAll(page);
	}
	
	/**
	 * Obtiene todos los resultados de pacientes que tengan el atributo isEnabled = true
	 * @param page Contiene las opciones de paginación.
	 * @return Una pagina de resultados filtrada por el valor isEnabled
	 */
	public Page<Patient> getAllActive(Pageable page) {
		return repository.findAllByIsEnabledTrue(page);
	}
	
	/**
	 * Obtiene todos los resultados de Pacientes, usado en filtros de busqueda.
	 * @return Una lista con todos los pacientes.
	 */
	public List<Patient> getAllList() {
		return repository.findAll();
	}
	
	/**
	 * Obtiene todos los resultados de Pacientes habilitados, usado en filtros de busqueda
	 * @return Una lista con todos los pacientes habilitados.
	 */
	public List<Patient> getAllActiveList() {
		return repository.findAllByIsEnabledTrue();
	}
	
	/**
	 * Obtiene todos los pacientes que concuerden con la cadena de texto provista, usando nombre o apellido en la busqueda, que se encuentren habilitados.
	 * @param fullName nombre y apellido de paciente.
	 * @param page Contiene las opciones de paginación.
	 * @return Una pagina de resultados.
	 */
	public Page<Patient> findAllActivePatientsByFirstOrLastName(String fullName, Pageable page) {
		fullName = fullName.toLowerCase();
		return repository.findByFullNameContainingIgnoreCaseActive(fullName, page);
	}
	
	/**
	 * Obtiene todos los pacientes que concuerden con la cadena de texto provista, usando nombre o apellido en la busqueda.
	 * @param fullName nombre y apellido de paciente.
	 * @param page Contiene las opciones de paginación.
	 * @return Una pagina de resultados.
	 */
	public Page<Patient> findAllPatientsByFirstOrLastName(String fullName, Pageable page) {
		fullName = fullName.toLowerCase();
		return repository.findByFullNameContainingIgnoreCase(fullName, page);
	}
	
	/**
	 * Guarda un paciente.
	 * @param p Un paciente.
	 * @return el paciente guardado.
	 */
	public Patient save(Patient p) {
		return repository.save(p);
	}
	
	/**
	 * Obtiene un paciente.
	 * @param Long el id del paciente específico.
	 * @return Optional Un paciente o nada.
	 */
	public Optional<Patient> getOne(Long id) {
		return repository.findById(id);
	}
	
	/**
	 * Obtiene un paciente por el loginCode.
	 * @param String el código de Logueo del paciente específico.
	 * @return Optional un paciente o nada.
	 */
	public Optional<Patient> getOneByLoginCode(String loginCode) {
		return repository.findByLoginCode(loginCode);
	}
	
	/**
	 * Elimina un paciente, cambiando su estado a "Deshabilitado", y cancelando todas sus planificaciones pendientes o vigentes
	 * @param id Id del paciente
	 * @return El paciente guardado
	 * @throws Exception Control de errores en caso de que no exista el paciente o ya se encuentre deshabilitado
	 */
	public Patient delete(Long id) throws Exception {
		Patient p = repository.getOne(id);
		PlanningState cancel = planningStateRepository.findByName("Cancelada");
		if (p.getId() == null) {
			throw new RuntimeException("Patient id is not defined.");
		}
		if (p.isEnabled() == true) {
			p.setEnabled(false);
			p.setLogged(false);
			p.setLoginCode(null);
			List<Planning> patientPlannings = this.planningService.getCurrentAndPendingPlanningsFromPatient(p.getId());
			patientPlannings.forEach(
					(planning) -> {
						planning.setState(cancel);
						planningRepository.save(planning);
						}
					);	
		} else {
			throw new RuntimeException("Patient is already deleted.");
		}		
		return repository.save(p);
	}
	
	/**
	 * Actualiza un paciente.
	 * @param p El paciente que se actualizará.
	 * @return El paciente guardado.
	 */
	public Patient update(Patient p) {
		if (p.getId() == null) {
			throw new RuntimeException("Patient id is not defined.");
		}
		
		return repository.save(p);
	}

	/**
	 * Obtiene todas las planificaciones actualmente
	 * vigentes del paciente a partir de su id.
	 * @param id Id del paciente.
	 * @return Lista de planificaciones.
	 */
	public List<Planning> getCurrentPlanningsFromPatientId(Long id) throws Exception {
		Optional<Patient> patient = this.getOne(id);
		
		if(patient.isEmpty()) {
			throw new Exception("Patient not found.");
		}
		
		return this.planningService.getCurrentPlanningsFromPatient(patient.get().getId());
	}

	
}
      