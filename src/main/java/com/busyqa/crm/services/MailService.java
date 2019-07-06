package com.busyqa.crm.services;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import com.busyqa.crm.model.Mail;
import com.busyqa.crm.model.user.payment.PaymentMail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MailService.class);

    @Autowired
    private TemplateEngine templateEngine;

    private JavaMailSender javaMailSender;

    @Autowired
    public MailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public void sendEmail(String email) throws MailException {

        /*
         * This JavaMailSender Interface is used to send Mail in Spring Boot. This
         * JavaMailSender extends the MailSender Interface which contains send()
         * function. SimpleMailMessage Object is required because send() function uses
         * object of SimpleMailMessage as a Parameter
         */

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setSubject("Testing Mail API");
        mail.setText("Hurray ! You have done that dude...");

        /*
         * This send() contains an Object of SimpleMailMessage as an Parameter
         */
        javaMailSender.send(mail);
    }

    public void sendEmailWithAttachment(String email) throws MailException, MessagingException {

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();

        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

        String portalUrl = "http://localhost:4200/client/resetPassword/" + email;

        String messageBody = "Hello,\n" +
                "Please set up your profile in the following link: " + portalUrl + "\n" +
                "Firstly, you need to set up a new password for " +
                "the login credentials. The username is the email you have registered. Secondly, you need to " +
                "update your information by filling up the form. Please sign the payment plan agreement, and " +
                "upload it in the form. \n";


        helper.setTo(email);
        helper.setSubject("BusyQA Portal Registration And Payment Plan Agreement");
        helper.setText(messageBody);

        ClassPathResource classPathResource = new ClassPathResource("Attachment.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);

        javaMailSender.send(mimeMessage);
    }

    public ResponseEntity<?> sendPreparedMail(Mail mail){
        sendTemplatedMail(mail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public void sendTemplatedMail(Mail mail) {
        //get and fill the template
        final Context context = new Context();
        context.setVariable("clientName", mail.getClientName());
        context.setVariable("className", mail.getClassName());
        context.setVariable("instructorName", mail.getInstructorName());
        context.setVariable("address", mail.getClassLocation());
        context.setVariable("startDate", mail.getStartDate());
        String body = templateEngine.process("email-template", context);
        //send the html template
        sendPreparedMail(mail.getEmail(), mail.getSubject(), body, true);
    }

    private void sendPreparedMail(String to, String subject, String text, Boolean isHtml) {
        try {
            MimeMessage mail = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mail, true);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, isHtml);
            javaMailSender.send(mail);
        } catch (Exception e) {
            LOGGER.error("Problem with sending email to: {}, error message: {}", to, e.getMessage());
        }
    }

    public void sendTemplatedEmailWithAttachment(String email) throws MailException, MessagingException {
        // the portal link
        String portalUrl = "http://localhost:4200/client/resetPassword/" + email;
        //get and fill the template
        final Context context = new Context();
        context.setVariable("portalUrl", portalUrl);
        String body = templateEngine.process("send-portal-link-email-template", context);
        //send the html template
        MimeMessage mail = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mail, true);
        helper.setTo(email);
        helper.setSubject("BusyQA Portal Registration And Payment Plan Agreement");
        helper.setText(body, true);
        ClassPathResource classPathResource = new ClassPathResource("PaymentPlanAgreement.pdf");
        helper.addAttachment(classPathResource.getFilename(), classPathResource);
        javaMailSender.send(mail);
    }

    public ResponseEntity<?> sendPreparedMailForLatePayment(PaymentMail paymentMail){
        sendMailForLatePayment(paymentMail);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    public void sendMailForLatePayment(PaymentMail paymentMail) {
        //get and fill the template
        final Context context = new Context();
        context.setVariable("message", paymentMail.getMessage());
        context.setVariable("paymentDate", paymentMail.getPaymentDate());
        context.setVariable("unpaidAmount", paymentMail.getUnpaidAmount());
        String body = templateEngine.process("email-late-payment-template", context);
        //send the html template
        sendPreparedMail(paymentMail.getEmail(), paymentMail.getSubject(), body, true);
    }




}
