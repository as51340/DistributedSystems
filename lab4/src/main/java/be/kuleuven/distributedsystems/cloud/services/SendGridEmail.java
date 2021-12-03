package be.kuleuven.distributedsystems.cloud.services;

import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SendGridEmail {

    public void sendEmail(String subject, String mailContent, String customer) {
        // Mail content should differ if we have failure or pub/sub finished successfully
        // You're sending mail to customer
        String emailFrom = System.getenv("MAIL_FROM");
        // System.out.println("Email from: " + emailFrom);
        Email from = new Email(emailFrom);
        Email to = new Email(customer);
        Content content = new Content("text/plain", mailContent);
        Mail mail = new Mail(from, subject, to, content);

        String sendGridAPI = System.getenv("EMAIL_API_KEY");
        // System.out.println("SendGRID api key: " + sendGridAPI);
        SendGrid sg = new SendGrid(sendGridAPI);
        Request request = new Request();
        try {
            request.setMethod(Method.POST);
            request.setEndpoint("mail/send");
            request.setBody(mail.build());
            Response response = sg.api(request);
            System.out.println("Mail's status...");
            System.out.println(response.getStatusCode());
            System.out.println(response.getBody());
            System.out.println(response.getHeaders());
        } catch (IOException ex) {
            System.out.println("Exception catched!!!");
            ex.printStackTrace();
        }
    }
}
