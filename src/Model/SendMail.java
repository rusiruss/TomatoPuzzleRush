package Model;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {
    public  void sendMail(String recepient) throws Exception{
        System.out.println("preparing send mail");
        Properties  properties =  new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
         
         String myAccountEmail ="tomatopuzzlerush@gmail.com";
         String password="evdmmqpvbwkpvnbw";
         
         Session session = Session.getInstance(properties, new Authenticator(){
             @Override
             protected PasswordAuthentication getPasswordAuthentication(){
                 return new PasswordAuthentication(myAccountEmail,password);
             }
         });
         Message message  = preparemessage(session,myAccountEmail,recepient);
         Transport.send(message);
         System.out.println("send successfully");
        
     }
    
    private  Message preparemessage(Session session,String myAccountEmail,String recepient){
        try {
            Message message  = new MimeMessage(session);
            message.setFrom(new InternetAddress(myAccountEmail));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recepient));
            message.setSubject("Registration confirmation");
            message.setText("Welcome to TomatoPuzzleRush Game. Thank You Join with us.  Instructions to play the game - Here you have 3 minutes to play the game. Within that time you can score maximum points and Level up.Enjoy the Game Bye !!!!");
             return message;
        } catch (Exception e) {
            Logger.getLogger(SendMail.class.getName()).log(Level.SEVERE,null,e);
            System.out.println(e);
        }
        return null;
    }}