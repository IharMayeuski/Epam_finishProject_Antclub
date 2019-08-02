package by.epam.club.tool;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * This class is created for sending email from site's mail 'ant_epam@mail.ru' to user's mail when password is forgotten
 *
 * @author Maeuski Igor
 * @version 1.0
 * @see Transport abstract class
 */

public class Sender {
    private final String USERNAME = "ant_epam@mail.ru";
    private final String PASSWORD = "ant61206120";
    private final String TITLE = "AntClub. Your new PASSWORD!";
    private final String MESSAGE = "Dear user the site AntClub, your new PASSWORD is - ";
    private final String AUTHENTIFICATION ="mail.smtp.auth";
    private final String STARTTLS ="mail.smtp.starttls.enable";
    private final String TRUE = "true";
    private final String HOST = "mail.smtp.host";
    private final String MAIL_RU = "smtp.mail.ru";
    private final String PORT = "mail.smtp.port";
    private final String PORT_NUMBER = "587";

    private Properties properties;

    public Sender() {
        properties = new Properties();
        properties.put(AUTHENTIFICATION, TRUE);
        properties.put(STARTTLS, TRUE);
        properties.put(HOST,MAIL_RU);
        properties.put(PORT, PORT_NUMBER);
    }

    /**
     *
     * @param newPassword for user
     * @param toEmail the letter will send on the user's letter
     * @throws MessagingException in the case of the problem with email of the site ant_epam@mail.ru
     */
    public void send(String newPassword, String toEmail) throws MessagingException {
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(USERNAME, PASSWORD);
            }
        });
        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(USERNAME));
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
        message.setSubject(TITLE);
        message.setText(MESSAGE + newPassword);
        Transport.send(message);
    }
}
