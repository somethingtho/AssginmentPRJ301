package util;

import java.nio.charset.Charset;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendEmail {

    
    
    
    public void sendLockAccount(String to, String contendRep) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        System.setProperty("mail.mime.charset", "false");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Lock Account");
            message.setText("Your account has violated our standards please respond to this email to resolve them!");
            Transport.send(message);

            System.out.println("Feedback sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
    

    public void sendFeedBack(String to, String contendRep) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        System.setProperty("mail.mime.charset", "false");
        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Feedback");
            message.setText(contendRep);
            Transport.send(message);

            System.out.println("Feedback sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendVerifiedFeedBack(String to, String text) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("Feedback");
//            message.setText(text);
            message.setText("Hello! Thank you for your feedback. We are sending this letter to confirm that we have received a response from you!"
                    + " We will reply soon! Thank you very much for your contribution! Have a nice day!!!!");
            Transport.send(message);
            System.out.println("Verified feedback sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public void sendOTP(String to, String otp) {
        final String username = "sendotp1234@gmail.com";
        final String password = "ifeahpwziexwuuqo";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("sendotp1234@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(to));
            message.setSubject("OTP for your account");
            message.setText("Your OTP is: " + otp);

            Transport.send(message);

            System.out.println("OTP sent successfully");
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateOTP() {
        // generate an OTP
        // for demonstration purposes, this example generates a random 6-digit number
        return String.format("%06d", (int) (Math.random() * 1000000));
    }
    
    public static void main(String[] args) {
        SendEmail send = new SendEmail();
        String txt ="Xin chào! Cảm ơn bạn đã gửi phản hồi cho chúng tôi."
                + " Chúng tôi gửi thư này để xác nhận rằng chúng tôi đã nhận được phản hồi từ bạn!"
                + " Chúng tôi sẽ sớm hồi âm! Xin chân thành cảm ơn về đóng góp của bạn! Chúc bạn có một ngày mới tốt lành!!";
        send.sendVerifiedFeedBack("daoson03112002@gmail.com",txt);
        
    }

}
