package com.logistics.kk.util;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class MailUtil {

	@Autowired
	private JavaMailSender mailSender;

	/***
	 * This method is used to send email with below details 
	 * @param to
	 * @param cc
	 * @param bcc
	 * @param subject
	 * @param text
	 * @param file
	 * @return
	 */
	public boolean sendEmail(
			String to,
			String[] cc,
			String[] bcc,
			String subject,
			String text,
			MultipartFile file
			) 
	{
		boolean flag = false;
		try {
			//1. create one empty email (mime message)
			MimeMessage message = mailSender.createMimeMessage();

			//2. fill details
			MimeMessageHelper helper = new MimeMessageHelper(message, file!=null);

			helper.setTo(to);
			if(cc!=null && cc.length>0)
				helper.setCc(cc);
			if(bcc!=null && bcc.length>0)
				helper.setBcc(bcc);

			helper.setSubject(subject);
			helper.setText(text,true);

			// add attachment
			if(file!=null)
				helper.addAttachment(file.getOriginalFilename(), file);

			//3. send email
			mailSender.send(message);
			flag = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return flag;
	}

	/***
	 * This method is used to send email with below details 
	 * @param to
	 * @param subject
	 * @param text
	 * @return
	 */
	public boolean sendEmail(
			String to,
			String subject,
			String text) 
	{
		return sendEmail(to, null, null, subject, text, null);
	}
	
}
