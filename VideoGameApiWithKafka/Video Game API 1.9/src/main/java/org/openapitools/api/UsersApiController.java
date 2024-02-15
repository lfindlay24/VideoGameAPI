package org.openapitools.api;

import org.openapitools.businessLayer.DBMethods;
import org.openapitools.model.Offer;
import org.openapitools.model.User;
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
@RequestMapping("${openapi.videoGameExchange.base-path:}")
public class UsersApiController implements UsersApi, EnvironmentAware {

    private final NativeWebRequest request;

    private Environment environment;

    DBMethods dbMethods;

    @Autowired
    private KafkaTemplate<String, User> userKafkaTemplate;

    public void sendMessage(String topic, User msg) {
        userKafkaTemplate.send(topic, msg);
    }

    @Autowired
    public UsersApiController(NativeWebRequest request, Environment environment) {
        this.request = request;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
        this.dbMethods = new DBMethods(environment);
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createUser(User user) {
        dbMethods.createUser(user);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) {
        dbMethods.deleteUser(userId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<User> getOneUser(String userId) {
        return new ResponseEntity<>(dbMethods.getOneUser(userId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<User>> listUsers() {
        return new ResponseEntity<>(dbMethods.getAllUsers(), HttpStatus.OK);
    }

    //Email is only sent if password has changed
    @Override
    public ResponseEntity<Void> updateSomeUser(String userId, User user) {
        User userToUpdate = dbMethods.getOneUser(userId);
        dbMethods.updateSomeUser(userId, user);
        if (!userToUpdate.getPassword().equals(user.getPassword()))
            sendMessage("users", user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    //Email is only sent if password has changed
    @Override
    public ResponseEntity<Void> updateUser(String userId, User user) {
        User userToUpdate = dbMethods.getOneUser(userId);
        dbMethods.updateUser(userId, user);
        if (!userToUpdate.getPassword().equals(user.getPassword()))
            sendMessage("users", user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> addGameUser(String userId, String gameId) {
        if (dbMethods.addGameUser(userId, gameId))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public ResponseEntity<Void> deleteGameUser(String userId, String gameId) {
        if (dbMethods.deleteGameUser(userId, gameId))
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        else
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
