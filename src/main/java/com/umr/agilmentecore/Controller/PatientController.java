package com.umr.agilmentecore.Controller;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Services.PatientService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {

	@Autowired
	private PatientService service;
	
	/**
	 *  Obtiene todos los resultados de Pacientes
	 * @return Una lista con todos los pacientes
	 */
	@GetMapping(value = "/listed")
	public ArrayList<Patient> getAllList(Pageable page) {
		return service.getAllList();
	}
	
	/**
	 *  Obtiene todos los pacientes
	 * @param page Contiene las opciones de paginación
	 * @return Una página de resultados
	 */
	@GetMapping
	public Page<Patient> getAll(Pageable page) {
		return service.getAll(page);
	}
	
	/**
	 *  Obtiene un Paciente por su id
	 * @param Long el id del paciente específico
	 * @return Optional un paciente o nada.
	 */
	@GetMapping(value = "/{id}")
	public Optional<Patient> getOne(@PathVariable(name = "id") Long id) {
		return service.getOne(id);
	}
	
	/**
	 *  Obtiene un Paciente por su loginCode
	 * @param String el Login Code del paciente específico
	 * @return Optional un paciente o nada.
	 */
	@GetMapping(value = "/lc{loginCode}")
	public Optional<Patient> getOne(@PathVariable(name = "loginCode") String loginCode) {
		return service.getOneByLoginCode(loginCode);
	}
	
	/**
	 * Guarda un paciente
	 * @param p Un paciente
	 * @return el paciente guardado
	 */
	@PostMapping
	public Patient save(@RequestBody Patient p) {
		
		return service.save(p);
	}

	/**
	 *  Actualiza un paciente
	 * @param p El paciente que se actualizará
	 * @return El paciente guardado.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Patient update(@RequestBody Patient p, @PathVariable(name = "id") Long id) {
		return service.update(p);		
	}
	
	/**
	 *  Elimina un paciente
	 * @param id Long el id del paciente a eliminar
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable(name = "id") Long id) {
		service.delete(id);
	}
}
