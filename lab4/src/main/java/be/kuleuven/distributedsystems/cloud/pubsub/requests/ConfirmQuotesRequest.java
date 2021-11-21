package be.kuleuven.distributedsystems.cloud.pubsub.requests;

import be.kuleuven.distributedsystems.cloud.entities.Quote;
import com.google.protobuf.ByteString;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ConfirmQuotesRequest implements PubSubRequest, Serializable {

    private List<Quote> quotes;
    private String customer;

    public ConfirmQuotesRequest() {

    }

    public ConfirmQuotesRequest(List<Quote> quotes, String customer) {
        this.quotes = quotes;
        this.customer = customer;
    }

    @Override
    public ByteString toByteString() throws IOException {
        // ByteString customerBS = ByteString.copyFromUtf8(customer);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] objBytes = null;
        try(ObjectOutputStream out = new ObjectOutputStream(bos)) {
            System.out.println("Class that I'm converting to byte array: " + this.getClass().toString());
            out.writeObject(this);
            out.flush();
            objBytes = bos.toByteArray();
        } finally {
            bos.close();
        }
        return ByteString.copyFrom(objBytes);
    }

    @Override
    public void fromByteString(ByteString bs) throws IOException{
        ByteArrayInputStream bis = new ByteArrayInputStream(bs.toByteArray());
        ConfirmQuotesRequest confirmQuotesRequest = null;
        try(ObjectInputStream in = new ObjectInputStream(bis)) {
            confirmQuotesRequest = (ConfirmQuotesRequest) in.readObject();
        } catch(ClassNotFoundException ex) {
            System.out.println(ex.toString());
        }
        finally {
            bis.close();
        }
        this.quotes = confirmQuotesRequest.quotes;
        this.customer = confirmQuotesRequest.customer;
    }

    public static void main(String[] args) throws IOException{
        String customer = "Customer1";
        Quote quote1 = new Quote("Company1", UUID.randomUUID(), UUID.randomUUID());
        Quote quote2 = new Quote("Company2", UUID.randomUUID(), UUID.randomUUID());
        List<Quote> quotes = new ArrayList<>();
        quotes.add(quote1);
        quotes.add(quote2);
        ConfirmQuotesRequest confirmQuotesRequest = new ConfirmQuotesRequest(quotes, customer);

        // Convert to byte string
        ByteString bs = confirmQuotesRequest.toByteString();
        System.out.println("ByteString of an object: " + bs.toString());

        // Try to convert back from byte string
        ConfirmQuotesRequest convertedConfirmQuotesRequest = new ConfirmQuotesRequest();
        convertedConfirmQuotesRequest.fromByteString(bs);

        List<Quote> convertedQuotes = convertedConfirmQuotesRequest.quotes;
        System.out.println("Printing quotes...");
        for(Quote quote: convertedQuotes) {
            System.out.println(quote.getCompany());
        }
        System.out.println("Customer: " + confirmQuotesRequest.customer);

    }
}


