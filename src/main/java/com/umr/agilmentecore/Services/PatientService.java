package com.umr.agilmentecore.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.Comment;
import com.umr.agilmentecore.Class.Patient;
import com.umr.agilmentecore.Class.Planning;
import com.umr.agilmentecore.Class.PlanningState;
import com.umr.agilmentecore.Class.Professional;
import com.umr.agilmentecore.Class.IntermediateClasses.MedalsAndTrophies;
import com.umr.agilmentecore.Class.IntermediateClasses.PatientComment;
import com.umr.agilmentecore.Persistence.CommentRepository;
import com.umr.agilmentecore.Persistence.PatientRepository;
import com.umr.agilmentecore.Persistence.PlanningRepository;
import com.umr.agilmentecore.Persistence.PlanningStateRepository;
import com.umr.agilmentecore.Persistence.ProfessionalRepository;

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
	@Autowired
	private ProfessionalRepository professionalRepository;
	@Autowired
	private CommentRepository commentRepository;

	/**
	 * Obtiene todos los resultados de Pacientes, usado en filtros de busqueda.
	 * 
	 * @return Una lista con todos los pacientes.
	 */
	public List<Patient> getAll() {
		return repository.findAll();
	}

	/**
	 * Obtiene todos los resultados de Pacientes habilitados, usado en filtros de
	 * busqueda
	 * 
	 * @return Una lista con todos los pacientes habilitados.
	 */
	public List<Patient> getAllActive() {
		return repository.findAllByIsEnabledTrue();
	}

	/**
	 * Obtiene todos los pacientes que concuerden con la cadena de texto provista,
	 * usando nombre o apellido en la busqueda, que se encuentren habilitados.
	 * 
	 * @param fullName nombre y apellido de paciente.
	 * @return Una lista de resultados.
	 */
	public List<Patient> findAllActivePatientsByFirstOrLastName(String fullName, boolean all) {
		fullName = fullName.toLowerCase();
		if (!all) {
			return repository.findByFullNameContainingIgnoreCaseActive(fullName);
		}
		return repository.findAllByFullNameContainingIgnoreCaseActive(fullName);

	}

	/**
	 * Obtiene todos los pacientes que concuerden con la cadena de texto provista,
	 * usando nombre o apellido en la busqueda.
	 * 
	 * @param fullName nombre y apellido de paciente.
	 * @return Una lista de resultados.
	 */
	public List<Patient> findAllPatientsByFirstOrLastName(String fullName) {
		fullName = fullName.toLowerCase();
		return repository.findByFullNameContainingIgnoreCase(fullName);
	}

	/**
	 * Guarda un paciente.
	 * 
	 * @param p Un paciente.
	 * @return el paciente guardado.
	 */
	public Patient save(Patient p) {
		return repository.save(p);
	}

	/**
	 * Obtiene un paciente.
	 * 
	 * @param Long el id del paciente específico.
	 * @return Optional Un paciente o nada.
	 */
	public Optional<Patient> getOne(Long id) {
		return repository.findById(id);
	}

	/**
	 * Obtiene un paciente por el loginCode.
	 * 
	 * @param String el código de Logueo del paciente específico.
	 * @return Optional un paciente o nada.
	 */
	public Optional<Patient> getOneByLoginCode(String loginCode) {
		return repository.findByLoginCode(loginCode);
	}

	/**
	 * Elimina un paciente, cambiando su estado a "Deshabilitado", y cancelando
	 * todas sus planificaciones pendientes o vigentes
	 * 
	 * @param id Id del paciente
	 * @return El paciente guardado
	 * @throws Exception Control de errores en caso de que no exista el paciente o
	 *                   ya se encuentre deshabilitado
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
			patientPlannings.forEach((planning) -> {
				planning.setState(cancel);
				planningRepository.save(planning);
			});
		} else {
			throw new RuntimeException("Patient is already deleted.");
		}
		return repository.save(p);
	}

	/**
	 * Actualiza un paciente.
	 * 
	 * @param p El paciente que se actualizará.
	 * @return El paciente guardado.
	 */
	public Patient update(Patient p) {
		if (p.getId() == null) {
			throw new RuntimeException("Patient id is not defined.");
		}
		Patient prevPat = repository.getOne(p.getId());
		if (p.getComments() == null && prevPat.getComments() != null) {
			p.setComments(prevPat.getComments());
		}
		if (p.getEmail() == null && prevPat.getEmail() != null) {
			p.setEmail(prevPat.getEmail());
		}
		if (p.getTelephone() == null && prevPat.getTelephone() != null) {
			p.setTelephone(prevPat.getTelephone());
		}
		return repository.save(p);
	}

	/**
	 * Agrega un comentario al comment box del paciente.
	 * 
	 * @param pc instancia de objeto PatientComment con los Datos del comentario,
	 *           del paciente y del profesional
	 * @return true si todo sale bien
	 */
	public boolean addComment(PatientComment pc) {
		Professional professional = professionalRepository.findByFirstNameAndLastName(pc.getProfessionalFirstName(),
				pc.getProfessionalLastName());
		Patient patient = repository.getOne(pc.getPatientId());
		List<Comment> comments = patient.getComments();
		Comment comment = new Comment();
		comment.setAuthor(professional);
		comment.setDatetime(new Date());
		comment.setComment(pc.getComment());
		comments.add(comment);
		patient.setComments(comments);
		repository.save(patient);
		return true;
	}

	/**
	 * Elimina un comentario del comment box del paciente.
	 * 
	 * @param pc instancia de objeto PatientComment con el id del comentario y del
	 *           paciente
	 * @return true si todo sale bien
	 */
	public boolean deleteComment(PatientComment pc) {
		Patient patient = repository.getOne(pc.getPatientId());
		List<Comment> comments = patient.getComments();
		Comment comment = commentRepository.getOne(pc.getCommentId());
		comments.remove(comment);
		patient.setComments(comments);
		repository.save(patient);
		return true;
	}

	/**
	 * Edita un comentario del comment box del paciente.
	 * 
	 * @param pc instancia de objeto PatientComment con los Datos del comentario y
	 *           del paciente
	 * @return true si todo sale bien
	 */
	public boolean editComment(PatientComment pc) {
		Patient patient = repository.getOne(pc.getPatientId());
		List<Comment> comments = patient.getComments();
		Comment comment = commentRepository.getOne(pc.getCommentId());
		int indexComment = comments.indexOf(comment);
		comment.setComment(pc.getComment());
		comment.setEdited(true);
		comments.set(indexComment, comment);
		patient.setComments(comments);
		repository.save(patient);
		return true;
	}

	/**
	 * Obtiene los trofeos y medallas del paciente
	 * @param patientId Id del paciente
	 * @return Medallas y Trofeos.
	 */
	public MedalsAndTrophies getMedalsAndTrophies(Long patientId) {
		Patient patient = repository.getOne(patientId);
		MedalsAndTrophies medalsAndTrophies = new MedalsAndTrophies();
		medalsAndTrophies.setMedals(patient.getMedals());
		medalsAndTrophies.setTrophies(patient.getTrophies());
		return medalsAndTrophies;
	}

}
