package com.arc.helper;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import jakarta.activation.DataHandler;
import jakarta.activation.DataSource;
import jakarta.activation.FileDataSource;
import jakarta.mail.*;
import jakarta.mail.internet.*;

public class EmailSender {

    public static void sendMailWithAttachments(String from, String user, String pass, String[] to, String subject,
                                               String sentMessage, List<String> body, String[] attachmentPaths, String anyOtherDetails) {
        Properties props = System.getProperties();
        String host = "smtp.sendgrid.net";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            message.setSentDate(new Date());
            StringBuilder b = new StringBuilder();
            for (String carrier : body) {
                b.append(carrier).append("\n");
            }
            sentMessage += "\n" + b.toString() + "\n" + anyOtherDetails + "\n" + "Automation Team";

            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(sentMessage);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            for (String attachmentPath : attachmentPaths) {
                MimeBodyPart attachPart = new MimeBodyPart();
                DataSource source = new FileDataSource(attachmentPath);
                attachPart.setDataHandler(new DataHandler(source));
                try {
                    File file = new File(attachmentPath);
                    attachPart.setFileName(file.getName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                multipart.addBodyPart(attachPart);
            }

            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }

    public static void sendMailWithoutAttachments(String from, String user, String pass, String[] to, String subject,
                                                  String sentMessage, List<String> body, String anyOtherDetails) {
        Properties props = System.getProperties();
        String host = "smtp.sendgrid.net";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");

        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);

        try {
            message.setFrom(new InternetAddress(from));
            InternetAddress[] toAddress = new InternetAddress[to.length];
            for (int i = 0; i < to.length; i++) {
                toAddress[i] = new InternetAddress(to[i]);
            }
            for (int i = 0; i < toAddress.length; i++) {
                message.addRecipient(Message.RecipientType.TO, toAddress[i]);
            }
            message.setSubject(subject);
            message.setSentDate(new Date());
            StringBuilder b = new StringBuilder();
            for (String carrier : body)
                b.append(carrier).append("\n");
            sentMessage += "\n" + b.toString() + "\n" + anyOtherDetails + "\n" + "Automation Team";

            BodyPart bodyPart = new MimeBodyPart();
            bodyPart.setText(sentMessage);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(bodyPart);
            message.setContent(multipart);

            Transport transport = session.getTransport("smtp");
            transport.connect(host, user, pass);
            transport.sendMessage(message, message.getAllRecipients());
            transport.close();
        } catch (AddressException ae) {
            ae.printStackTrace();
        } catch (MessagingException me) {
            me.printStackTrace();
        }
    }
}

