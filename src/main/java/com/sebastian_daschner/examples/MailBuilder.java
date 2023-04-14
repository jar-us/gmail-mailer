package com.sebastian_daschner.examples;

import com.google.api.services.gmail.model.Message;
import org.apache.commons.codec.binary.Base64;

import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.ByteArrayOutputStream;
import java.util.Properties;

import static javax.mail.Message.RecipientType.TO;

public class MailBuilder {

    public static Message prepareMail(String sendTo, String subject, String message) throws Exception {
        Properties props = new Properties();
        Session session = Session.getDefaultInstance(props, null);
        MimeMessage email = new MimeMessage(session);
        email.setFrom(new InternetAddress(sendTo));
        email.addRecipient(TO, new InternetAddress(sendTo));
        email.setSubject(subject);
        email.setText(message);
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        email.writeTo(buffer);
        byte[] rawMessageBytes = buffer.toByteArray();
        String encodedEmail = Base64.encodeBase64URLSafeString(rawMessageBytes);
        Message msg = new Message();
        msg.setRaw(encodedEmail);
        return msg;
    }

}
