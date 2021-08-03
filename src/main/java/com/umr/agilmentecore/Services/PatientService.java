package com.umr.agilmentecore.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Persistence.PatientRepository;


@Service
public class PatientService {
	
	@Autowired
	private PatientRepository repository;
	
	/**
	 *  Obtiene todos los resultados de Pacientes
	 * @param page Contiene las opciones de paginación
	 * @return Una página de resultados
	 */
	public Page<Patient> getAll(Pageable page) {
		return repository.findAll(page);
	}
	
	/**
	 *  Obtiene todos los resultados de Pacientes
	 * @return Una lista con todos los pacientes
	 */
	public ArrayList<Patient> getAllList() {
		return repository.findAll();
	}
	
	/**
	 * Guarda un paciente
	 * @param p Un paciente
	 * @return el paciente guardado
	 */
	public Patient save(Patient p) {
		
		return repository.save(p);
	}
	
	/**
	 *  Obtiene un paciente por el id
	 * @param Long el id del paciente específico
	 * @return Optional un paciente o nada.
	 */
	public Optional<Patient> getOne(Long id) {
		return repository.findById(id);
	}
	
	/**
	 *  Obtiene un paciente por el loginCode
	 * @param String el código de Logueo del paciente específico
	 * @return Optional un paciente o nada.
	 */
	public Optional<Patient> getOneByLoginCode(String loginCode) {
		return repository.findByLoginCode(loginCode);
	}
	
	/**
	 *  Elimina un paciente
	 * @param id Long el id del paciente a eliminar
	 */
	public void delete(Long id) {
		repository.deleteById(id);
	}
	
	/**
	 *  Actualiza un paciente
	 * @param p El paciente que se actualizará
	 * @return El paciente guardado.
	 */
	public Patient update(Patient p) {
		if (p.getId()==null) {
			throw new RuntimeException("Error el objeto NO tiene id");
		}
		return repository.save(p);
	}
	
	
	
}
      