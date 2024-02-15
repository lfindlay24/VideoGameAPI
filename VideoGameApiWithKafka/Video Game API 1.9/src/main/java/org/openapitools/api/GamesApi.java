/**
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech) (7.2.0).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */
package org.openapitools.api;

import org.openapitools.model.Game;
import io.swagger.v3.oas.annotations.ExternalDocumentation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import javax.annotation.Generated;

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-21T13:18:55.670880400-07:00[America/Denver]")
@Validated
@Tag(name = "games", description = "the games API")
public interface GamesApi {

    default Optional<NativeWebRequest> getRequest() {
        return Optional.empty();
    }

    /**
     * POST /games : Create a Game
     *
     * @param game  (required)
     * @return Null response (status code 201)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "createGame",
        summary = "Create a Game",
        tags = { "games" },
        responses = {
            @ApiResponse(responseCode = "201", description = "Null response"),
            @ApiResponse(responseCode = "default", description = "unexpected error")
        }
    )
    @RequestMapping(
        method = RequestMethod.POST,
        value = "/games",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> createGame(
        @Parameter(name = "Game", description = "", required = true) @Valid @RequestBody Game game
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * DELETE /games/{gameId} : Delete one Game
     *
     * @param gameId The id of the game to delete (required)
     * @return Null response (status code 204)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "deleteGame",
        summary = "Delete one Game",
        tags = { "games" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Null response"),
            @ApiResponse(responseCode = "default", description = "unexpected error")
        }
    )
    @RequestMapping(
        method = RequestMethod.DELETE,
        value = "/games/{gameId}"
    )
    
    default ResponseEntity<Void> deleteGame(
        @Parameter(name = "gameId", description = "The id of the game to delete", required = true, in = ParameterIn.PATH) @PathVariable("gameId") String gameId
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /games/{gameId} : Get One Game
     *
     * @param gameId The id of the game to get (required)
     * @return One User Object (status code 200)
     */
    @Operation(
        operationId = "getOneGame",
        summary = "Get One Game",
        tags = { "games" },
        responses = {
            @ApiResponse(responseCode = "200", description = "One User Object", content = {
                @Content(mediaType = "application/json", schema = @Schema(implementation = Game.class))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/games/{gameId}",
        produces = { "application/json" }
    )
    
    default ResponseEntity<Game> getOneGame(
        @Parameter(name = "gameId", description = "The id of the game to get", required = true, in = ParameterIn.PATH) @PathVariable("gameId") String gameId
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "{ \"console\" : \"console\", \"numOwners\" : 6, \"condition\" : \"condition\", \"pubishYear\" : \"pubishYear\", \"publisher\" : \"publisher\", \"id\" : 0, \"title\" : \"title\" }";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * GET /games : List All Games
     *
     * @return A paged array of Games (status code 200)
     */
    @Operation(
        operationId = "listGames",
        summary = "List All Games",
        tags = { "games" },
        responses = {
            @ApiResponse(responseCode = "200", description = "A paged array of Games", content = {
                @Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Game.class)))
            })
        }
    )
    @RequestMapping(
        method = RequestMethod.GET,
        value = "/games",
        produces = { "application/json" }
    )
    
    default ResponseEntity<List<Game>> listGames(
        
    ) {
        getRequest().ifPresent(request -> {
            for (MediaType mediaType: MediaType.parseMediaTypes(request.getHeader("Accept"))) {
                if (mediaType.isCompatibleWith(MediaType.valueOf("application/json"))) {
                    String exampleString = "[ { \"console\" : \"console\", \"numOwners\" : 6, \"condition\" : \"condition\", \"pubishYear\" : \"pubishYear\", \"publisher\" : \"publisher\", \"id\" : 0, \"title\" : \"title\" }, { \"console\" : \"console\", \"numOwners\" : 6, \"condition\" : \"condition\", \"pubishYear\" : \"pubishYear\", \"publisher\" : \"publisher\", \"id\" : 0, \"title\" : \"title\" } ]";
                    ApiUtil.setExampleResponse(request, "application/json", exampleString);
                    break;
                }
            }
        });
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PUT /games/{gameId} : Update all Game Attributes
     *
     * @param gameId The id of the game to update (required)
     * @param game  (required)
     * @return Null response (status code 204)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "updateGame",
        summary = "Update all Game Attributes",
        tags = { "games" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Null response"),
            @ApiResponse(responseCode = "default", description = "unexpected error")
        }
    )
    @RequestMapping(
        method = RequestMethod.PUT,
        value = "/games/{gameId}",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> updateGame(
        @Parameter(name = "gameId", description = "The id of the game to update", required = true, in = ParameterIn.PATH) @PathVariable("gameId") String gameId,
        @Parameter(name = "Game", description = "", required = true) @Valid @RequestBody Game game
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }


    /**
     * PATCH /games/{gameId} : Update Some Game Attributes
     *
     * @param gameId The id of the game to update (required)
     * @param game  (required)
     * @return Null response (status code 204)
     *         or unexpected error (status code 200)
     */
    @Operation(
        operationId = "updateSomeGame",
        summary = "Update Some Game Attributes",
        tags = { "games" },
        responses = {
            @ApiResponse(responseCode = "204", description = "Null response"),
            @ApiResponse(responseCode = "default", description = "unexpected error")
        }
    )
    @RequestMapping(
        method = RequestMethod.PATCH,
        value = "/games/{gameId}",
        consumes = { "application/json" }
    )
    
    default ResponseEntity<Void> updateSomeGame(
        @Parameter(name = "gameId", description = "The id of the game to update", required = true, in = ParameterIn.PATH) @PathVariable("gameId") String gameId,
        @Parameter(name = "Game", description = "", required = true) @Valid @RequestBody Game game
    ) {
        return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);

    }

}
