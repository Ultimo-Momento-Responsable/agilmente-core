package com.umr.agilmentecore.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Persistence.PatientRepository;


@Service
public class PatientService {
	@Autowired
	private PatientRepository repository;
	@Autowired
	private PlanningService planningService;
	
	/**
	 * Obtiene todos los resultados de Pacientes.
	 * @param page Contiene las opciones de paginación.
	 * @return Una página de resultados.
	 */
	public Page<Patient> getAll(Pageable page) {
		return repository.findAll(page);
	}
	
	/**
	 * Obtiene todos los resultados de Pacientes.
	 * @return Una lista con todos los pacientes.
	 */
	public List<Patient> getAllList() {
		return repository.findAll();
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
	 * Elimina un paciente.
	 * @param id Long el id del paciente a eliminar.
	 */
	public void delete(Long id) {
		repository.deleteById(id);
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
	 * Obtiene todas las planificaciones actualmente activas o
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
      