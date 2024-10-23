package com.tdc.vlxdonline.Model;

import java.util.Properties;
//import javax.mail.Message;
//import javax.mail.MessagingException;
//import javax.mail.PasswordAuthentication;
//import javax.mail.Session;
//import javax.mail.Transport;
//import javax.mail.internet.InternetAddress;
//import javax.mail.internet.MimeMessage;

public class SendMail {
//
//    public static void sendEmail(String toEmail, String subject, String body) {
//        final String username = "lecongchien843@gmail.com";
//        final String password = "chien122004";
//
//        Properties props = new Properties();
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.host", "smtp.gmail.com");
//        props.put("mail.smtp.port", "587");
//
//        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
//            protected PasswordAuthentication getPasswordAuthentication() {
//                return new PasswordAuthentication(username, password);
//            }
//        });
//
//        try {
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress(username));
//            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
//            message.setSubject(subject);
//            message.setText(body);
//
//            Transport.send(message);
//            System.out.println("Email Sent Successfully");
//
//        } catch (MessagingException e) {
//            throw new RuntimeException(e);
//        }
//    }
}

