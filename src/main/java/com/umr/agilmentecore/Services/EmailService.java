package com.umr.agilmentecore.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.umr.agilmentecore.Class.EmailDetails;

@Service
public class EmailService {
	@Autowired 
	private JavaMailSender javaMailSender;
	 
    @Value("${spring.mail.username}") 
    private String sender;

    /**
     * Método para envío de emails
     * @param details datos de envío de email
     * @return 
     */
    public Boolean sendMail(EmailDetails details) {
        try {
            SimpleMailMessage mailMessage = new SimpleMailMessage();
            mailMessage.setFrom(sender);
            mailMessage.setTo(details.getRecipient());
            mailMessage.setText(details.getMsgBody());
            mailMessage.setSubject(details.getSubject());
            javaMailSender.send(mailMessage);
            return true;
        }
        catch (Exception e) {
            return false;
        }
    }
}
