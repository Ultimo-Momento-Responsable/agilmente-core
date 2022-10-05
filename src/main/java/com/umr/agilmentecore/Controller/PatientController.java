package com.umr.agilmentecore.Controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientComment;
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
	@GetMapping(value = "/active")
	public List<Patient> getAllActive() {
		return service.getAllActive();
	}
	
	/**
	 * Obtiene todos los resultados de Pacientes.
	 * @return Una lista con todos los pacientes.
	 */
	@GetMapping
	public List<Patient> getAll() {
		return service.getAll();
	}
	
	/**
	 * Obtiene un Paciente.
	 * @param Long el id del paciente específico.
	 * @return Optional un paciente o nada.
	 */
	@GetMapping(value = "/frontend/{id}")
	public Optional<Patient> getOneFront(@PathVariable(name = "id") Long id) {
		return service.getOne(id);
	}
	
	/**
	 * Obtiene un Paciente.
	 * @param Long el id del paciente específico.
	 * @return Optional un paciente o nada.
	 */
	@GetMapping(value = "/{id}")
	public Optional<Patient> getOneUnity(@PathVariable(name = "id") Long id) {
		return service.getOne(id);
	}
	
	/**
	 * Obtiene todos los pacientes que concuerden con la cadena de texto provista, usando nombre o apellido en la busqueda.
	 * @param fullName nombre y apellido de paciente.
	 * @return patientPage Una página de resultados.
	 */
	@GetMapping(params = {"fullName","all"})
	public List<Patient> getAll(String fullName, boolean all) {
		return service.findAllActivePatientsByFirstOrLastName(fullName, all);
	}
	
	/**
	 * Obtiene un Paciente por su loginCode.
	 * @param value El Login Code del paciente específico.
	 * @return Optional un paciente o nada.
	 */
	@GetMapping(value = "/loginCode/{loginCode}")
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
	 * Actualiza un paciente.
	 * @param p El paciente que se actualizará.
	 * @return El paciente guardado.
	 */
	@RequestMapping(value = "/frontend/{id}", method = RequestMethod.PUT)
	public Patient updateFrontend(@RequestBody Patient p, @PathVariable(name = "id") Long id) {
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
	
	/**
	 * Guarda un comentario
	 * @param pc Comentario
	 * @return true o false
	 */
	@PostMapping(value = "/comment")
	public boolean addComment(@RequestBody PatientComment pc) {
		return service.addComment(pc);
	}
	
	/**
	 * Borra un comentario
	 * @param id del comentario
	 * @return true o false
	 */
	@PostMapping(value = "/deleteComment")
	public boolean deleteComment(@RequestBody PatientComment pc) {
		return service.deleteComment(pc);
	}
	
	/**
	 * Edita un comentario
	 * @param id del comentario
	 * @return true o false
	 */
	@PostMapping(value = "/editComment")
	public boolean editComment(@RequestBody PatientComment pc) {
		return service.editComment(pc);
	}
	
}
