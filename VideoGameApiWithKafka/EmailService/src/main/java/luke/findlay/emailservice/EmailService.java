package luke.findlay.emailservice;

import jakarta.annotation.PostConstruct;
//import luke.findlay.emailservice.Models.User;
import org.openapitools.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.DependsOn;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    /*
    Email Service sends emails to users using information from kafka message stream
    javaMailSender connects to Ethereal Email service to send fake emails to users
    greetingListener listens to "users" topic and sends an email that their password has been changed
    listenOfferCreated listens to the offersCreated topic and sends an email to both the toUser and fromUser
    listenOfferAccepted and listenOfferDeclined do the same thing but only for when an offer is accepted or declined
     */

  @Autowired
  private JavaMailSender javaMailSender;

  DBMethods dbMethods = new DBMethods();

  public void run() {
      System.out.println("Please WORK!!!");
  }


    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("sibyl.bednar@ethereal.email");
        message.setTo(to);
        message.setSubject(subject);
        message.setText(body);
        javaMailSender.send(message);
    }

    @KafkaListener(
            topics = "users",
            groupId = "user-email-consumer")
    public void greetingListener(User user) {
        System.out.println("GOT A MESSAGE!!!");
        // process greeting message
        try {
            sendEmail(user.getEmail(), "Password Updated", ("Your password has been changed to: " + user.getPassword()));
        } catch (Exception e) {
            System.out.println("Invalid Email Address");
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "offersCreated", groupId = "user-email-consumer")
    public void listenOfferCreated(Offer offer) {
      User toUser = dbMethods.getOneUser(offer.getToUser());
      User fromUser = dbMethods.getOneUser(offer.getFromUser());
      try {
          sendEmail(toUser.getEmail(), "Offer Created", fromUser.getName() + " has made an offer to trade!!!");
          sendEmail(fromUser.getEmail(), "Offer Created", "Successfully made an offer to " + toUser.getName());
      } catch (Exception e) {
          System.out.println("Invalid Email Address");
          System.out.println(e.getMessage());
      }
    }

    @KafkaListener(topics = "offersAccepted", groupId = "user-email-consumer")
    public void listenOfferAccepted(Offer offer) {
        System.out.println(offer.toString());
        User toUser = dbMethods.getOneUser(offer.getToUser().substring(offer.getToUser().length() - 1));
        User fromUser = dbMethods.getOneUser(offer.getFromUser().substring(offer.getFromUser().length() - 1));
        try {
            sendEmail(toUser.getEmail(), "Offer Accepted", "You have accepted " + fromUser.getName() + "'s offer");
            sendEmail(fromUser.getEmail(), "Offer Accepted", toUser.getName() + " has accepted your offer");
        } catch (Exception e) {
            System.out.println("Invalid Email Address");
            System.out.println(e.getMessage());
        }
    }

    @KafkaListener(topics = "offersDeclined", groupId = "user-email-consumer")
    public void listenOfferDeclined(Offer offer) {
        User toUser = dbMethods.getOneUser(offer.getToUser().substring(offer.getToUser().length() - 1));
        User fromUser = dbMethods.getOneUser(offer.getFromUser().substring(offer.getFromUser().length() - 1));
        try {
            sendEmail(toUser.getEmail(), "Offer Declined", "You have declined " + fromUser.getName() + "'s offer");
            sendEmail(fromUser.getEmail(), "Offer Declined", toUser.getName() + " has declined your offer");
        } catch (Exception e) {
            System.out.println("Invalid Email Address");
            System.out.println(e.getMessage());
        }
    }
}
