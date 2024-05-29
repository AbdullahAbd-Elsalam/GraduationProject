package com.springjwt.springjwt2.service;

import com.springjwt.springjwt2.dao.MailBody;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendOtpEmail(String email, int otp) throws MessagingException, MessagingException {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");
        mimeMessageHelper.setTo(email);
        mimeMessageHelper.setSubject("Verify OTP");
        mimeMessageHelper.setText(
                String.format(
                        "<div><a href=\"http://localhost:8080/verify-account?email=%s&otp=%s\" target=\"_blank\">click link to verify</a></div>",
                        email, otp
                ),
                true
        );
   javaMailSender.send(mimeMessage);
    }


}
