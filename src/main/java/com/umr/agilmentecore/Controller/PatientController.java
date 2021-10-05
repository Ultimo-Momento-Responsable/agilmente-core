package com.umr.agilmentecore.Controller;

import java.util.List;
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
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Services.PatientService;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/patient")
public class PatientController {
	@Autowired
	private PatientService service;
	
	/**
	 * Obtiene todos los resultados de Pacientes.
	 * @return Una lista con todos los pacientes.
	 */
	@GetMapping(value = "/listed")
	public List<Patient> getAllList(Pageable page) {
		return service.getAllList();
	}
	
	/**
	 * Obtiene todos los pacientes.
	 * @param page Contiene las opciones de paginación.
	 * @return Page<Patient> Una página de resultados.
	 */
	@GetMapping
	public Page<Patient> getAll(Pageable page) {
		return service.getAll(page);
	}
	
	/**
	 * Obtiene un Paciente.
	 * @param Long el id del paciente específico.
	 * @return Optional un paciente o nada.
	 */
	@GetMapping(value = "/{id}")
	public Optional<Patient> getOne(@PathVariable(name = "id") Long id) {
		return service.getOne(id);
	}
	
	/**
	 * Obtiene todos los pacientes que concuerden con la cadena de texto provista, usando nombre o apellido en la busqueda.
	 * @param fullName nombre y apellido de paciente.
	 * @param Contiene las opciones de paginación.
	 * @return patientPage Una página de resultados.
	 */
	@GetMapping(value = {"/fn/{fullName}"})
	public Page<Patient> getAll(@PathVariable(name = "fullName") String fullName, Pageable patientPage) {

		return service.findAllPatientsByFirstOrLastName(fullName, patientPage);
	}
	
	/**
	 * Obtiene la lista de Planning activas de un paciente.
	 * @param Long el id del paciente específico.
	 * @return List<Planning> un paciente o nada.
	 */
	@GetMapping(value = "/{id}/current-plannings")
	public List<Planning> getCurrentActivePlannings(@PathVariable(name = "id") Long id) throws Exception {
		return this.service.getCurrentPlanningsFromPatientId(id);
	}
	
	/**
	 * Obtiene un Paciente por su loginCode.
	 * @param value El Login Code del paciente específico.
	 * @return Optional un paciente o nada.
	 */
	@GetMapping(value = "/lc{loginCode}")
	public Optional<Patient> getOne(@PathVariable(name = "loginCode") String loginCode) {
		return service.getOneByLoginCode(loginCode);
	}
	
	/**
	 * Guarda un paciente.
	 * @param p Un paciente.
	 * @return Patient El paciente guardado.
	 */
	@PostMapping
	public Patient save(@RequestBody Patient p) {
		return service.save(p);
	}

	/**
	 * Actualiza un paciente.
	 * @param p El paciente que se actualizará.
	 * @return El paciente guardado.
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public Patient update(@RequestBody Patient p, @PathVariable(name = "id") Long id) {
		return service.update(p);		
	}

	/**
	 * Elimina un paciente, cambiando su estado a deshabilitado
	 * @param p El paciente a deshabilitar
	 * @param id El id del paciente a deshabilitar
	 * @return
	 */
	@RequestMapping(value = "/deletePatient/{id}", method = RequestMethod.PUT)
	public Patient delete(@PathVariable(name = "id") Long id) throws Exception {
		return service.delete(id);
	}
	
	
}
