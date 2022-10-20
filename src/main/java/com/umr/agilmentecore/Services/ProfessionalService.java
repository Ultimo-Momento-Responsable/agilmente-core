package com.umr.agilmentecore.Services;

import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.EmailDetails;
import com.umr.agilmentecore.Class.Professional;
import com.umr.agilmentecore.Persistence.ProfessionalRepository;

@Service
public class ProfessionalService {
	@Autowired
	private ProfessionalRepository repository;
	
	@Autowired
	private EmailService emailService;

	/**
	 * Obtiene un profesional a partir del id.
	 * @param id Id del profesional.
	 * @return Instancia de Professional.
	 */
	public Optional<Professional> getOne(Long id) {
		return this.repository.findById(id);
	}

	/**
	 * Método para reiniciar la contraseña del profesional, 
	 * enviándole por correo la nueva contraseña generada aleatoriamente
	 * @param email Correo del profesional
	 * @return true o false si se pudo hacer o no.
	 */
	public Boolean resetPassword(String email) {
		Professional professional = this.repository.findByEmail(email);
		if (professional != null) {
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);
			String newPassword = UUID.randomUUID().toString().replace("-", "").substring(0, 8);
			String encryptedPassword = passwordEncoder.encode(newPassword);
			professional.setPassword(encryptedPassword);
			this.repository.save(professional);
			EmailDetails emailDetails = createResetPasswordEmailDetails(professional.getEmail(),newPassword);
			return emailService.sendMail(emailDetails);
		}
		return false;
	}
	
	/**
	 * Crea el email para el reinicio de contraseña
	 * @param email Correo del profesional
	 * @param password Contraseña generada.
	 * @return Email generado con cuerpo, asunto, email.
	 */
	private EmailDetails createResetPasswordEmailDetails(String email, String password) {
		return new EmailDetails(
				email,
				"Usted ha solicitado el reinicio de contraseña. \nSu nueva contraseña es:\n"
					+ password + "\nSi usted no ha solicitado esto por favor "
					+ "comuníquese con los administradores del sistema.",
				"Reinicio de contraseña");
	}
}
