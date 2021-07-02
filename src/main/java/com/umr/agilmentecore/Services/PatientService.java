package com.umr.agilmentecore.Services;

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
	 * Guarda un paciente
	 * @param g Un paciente
	 * @return el paciente guardado
	 */
	public Patient save(Patient p) {
		
		return repository.save(p);
	}
	
	/**
	 *  Obtiene un paciente
	 * @param Long el id del paciente específico
	 * @return Optional un paciente o nada.
	 */
	public Optional<Patient> getOne(Long id) {
		return repository.findById(id);
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
      