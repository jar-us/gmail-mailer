package com.sebastian_daschner.examples;

public class GMailer {
    public static void main(String[] args) throws Exception {
        new MailSender().sendMail("suraj.sharma3963@gmail.com", "A new message", "This is demo message");
    }
}
