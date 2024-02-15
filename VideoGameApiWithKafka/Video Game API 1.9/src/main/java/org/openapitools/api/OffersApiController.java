package org.openapitools.api;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openapitools.businessLayer.DBMethods;
import org.openapitools.model.Offer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-20T15:30:39.262604100-07:00[America/Denver]")
@Controller
@RequestMapping("")
public class OffersApiController implements OffersApi, EnvironmentAware {

    private final NativeWebRequest request;

    private Environment environment;

    DBMethods dbMethods;


    @Autowired
    private KafkaTemplate<String, Offer> offerKafkaTemplate;

    public void sendMessage(String topic, Offer msg) {
        offerKafkaTemplate.send(topic, msg);
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.dbMethods = new DBMethods(environment);
    }

    @Autowired
    public OffersApiController(NativeWebRequest request, Environment environment) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createOffer(Offer offer){
        ObjectMapper objectMapper = new ObjectMapper();
        if (dbMethods.createOffer(offer)) {
            try {
                sendMessage("offersCreated", offer);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> deleteOffer(String offerId) {
        if (dbMethods.deleteOffer(offerId))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> addGameOffer(String offerId, String gameId, Boolean isSent) {
        if (dbMethods.addGameOffer(offerId, gameId, isSent))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> deleteGameOffer(String offerId, String gameId, Boolean isSent) {
        if (dbMethods.deleteGameOffer(offerId, gameId, isSent))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Offer> getOneOffer(String offerId) {
        return new ResponseEntity<>(dbMethods.getOneOffer(offerId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Offer>> listOffers() {
        return new ResponseEntity<>(dbMethods.listOffers(), HttpStatus.OK);
    }

    //Email is sent to users if offer is accepted or declined
    @Override
    public ResponseEntity<Void> updateOffer(String offerId, Offer offer) {
        if (dbMethods.updateOffer(offerId, offer)) {
            if (offer.getState().equalsIgnoreCase("accepted"))
                sendMessage("offersAccepted", dbMethods.getOneOffer(offerId));
            else if (offer.getState().equalsIgnoreCase("declined"))
                sendMessage("offersDeclined", dbMethods.getOneOffer(offerId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    //Email is sent to users if offer is accepted or declined
    @Override
    public ResponseEntity<Void> updateSomeOffer(String offerId, Offer offer) {
        if (dbMethods.updateSomeOffer(offerId, offer)) {
            if (offer.getState().equalsIgnoreCase("accepted"))
                sendMessage("offersAccepted", dbMethods.getOneOffer(offerId));
            else if (offer.getState().equalsIgnoreCase("declined"))
                sendMessage("offersDeclined", dbMethods.getOneOffer(offerId));
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
