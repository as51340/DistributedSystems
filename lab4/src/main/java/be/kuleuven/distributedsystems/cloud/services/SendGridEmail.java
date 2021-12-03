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

    @Autowired
    private String sendGridAPI;

    @Autowired
    private String emailFrom;

    public void sendEmail(String subject, String mailContent, String customer) {
        // Mail content should differ if we have failure or pub/sub finished successfully
        // You're sending mail to customer
        System.out.println("Email from: " + emailFrom);
        System.out.println("SendGRID api key: " + sendGridAPI);
        Email from = new Email(emailFrom);
        Email to = new Email(customer);
        Content content = new Content("text/plain", mailContent);
        Mail mail = new Mail(from, subject, to, content);

        // SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
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
