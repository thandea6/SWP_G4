package Mail;

import java.util.Properties;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;

public class MailSender {

    public static void sendEmail(String email, String subject, String content) throws MessagingException {
        // Assuming you have enabled "Less secure app access" on your Google account or configured OAuth2
        String username = "lytienkhoi1598@gmail.com"; // Your Gmail address
        String password = "vxol ztlw vqdk vhrh"; // Your Gmail password

        // Set up the SMTP properties
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        // Get the Session object
        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        // Create a default MimeMessage object
        Message message = new MimeMessage(session);

        // Set From: header field
        message.setFrom(new InternetAddress(username));

        // Set To: header field
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email));

        // Set Subject: header field
        message.setSubject(subject);

        // Set the actual message
        message.setContent(content, "text/html; charset=UTF-8");

        // Send the message
        Transport.send(message);

        System.out.println("Sent message successfully....");
    }
}
