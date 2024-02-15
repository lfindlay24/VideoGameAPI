package org.openapitools.api;

import org.openapitools.businessLayer.DBMethods;
import org.openapitools.businessLayer.KafkaProducerConfig;
//import org.openapitools.businessLayer.KafkaTopicConfig;
import org.openapitools.model.Game;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.NativeWebRequest;

import javax.annotation.Generated;
import java.util.List;
import java.util.Optional;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-18T17:23:52.154576800-07:00[America/Denver]")
@RestController
@RequestMapping("")
public class GamesApiController implements GamesApi, EnvironmentAware {

    private final NativeWebRequest request;

    private static Environment environment;

    private DBMethods dbMethods;

    @Override
    public void setEnvironment(final Environment environment) {
        GamesApiController.environment = environment;
        this.dbMethods = new DBMethods(environment);
    }

    @Autowired
    public GamesApiController(NativeWebRequest request) {
        this.request = request;
    }

    @Override
    public Optional<NativeWebRequest> getRequest() {
        return Optional.ofNullable(request);
    }

    @Override
    public ResponseEntity<Void> createGame(Game game) {
        dbMethods.createGame(game);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Void> deleteGame(String gameId) {
        dbMethods.deleteGame(gameId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Game> getOneGame(String gameId) {
        return new ResponseEntity<>(dbMethods.getOneGame(gameId), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<Game>> listGames() {
        return new ResponseEntity<>(dbMethods.getAllGames(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Void> updateGame(String gameId, Game game) {
        dbMethods.updateGame(gameId, game);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Override
    public ResponseEntity<Void> updateSomeGame(String gameId, Game game) {
        dbMethods.updateSomeGame(gameId, game);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
