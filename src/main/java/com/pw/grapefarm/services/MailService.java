package com.pw.grapefarm.services;

import javax.mail.MessagingException;

public interface MailService {

     void sendSimpleMail(String to, String subject, String content);

     void sendHtmlMail(String to, String subject, String[] cc, String content) throws MessagingException;
}