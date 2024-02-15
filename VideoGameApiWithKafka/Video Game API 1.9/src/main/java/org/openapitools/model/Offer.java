package org.openapitools.model;

import java.net.URI;
import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import org.openapitools.jackson.nullable.JsonNullable;
import java.time.OffsetDateTime;
import javax.validation.Valid;
import javax.validation.constraints.*;
import io.swagger.v3.oas.annotations.media.Schema;


import java.util.*;
import javax.annotation.Generated;

/**
 * Offer
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-21T13:18:55.670880400-07:00[America/Denver]")
public class Offer {

  private Long id;

  private String fromUser;

  @Valid
  private List<String> sentGames = new ArrayList<>();

  @Valid
  private List<String> receivedGames = new ArrayList<>();

  private String toUser;

  private String timeCreated;

  private String state;

  public Offer() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Offer(Long id, String fromUser, List<String> sentGames, List<String> receivedGames, String toUser, String timeCreated, String state) {
    this.id = id;
    this.fromUser = fromUser;
    this.sentGames = sentGames;
    this.receivedGames = receivedGames;
    this.toUser = toUser;
    this.timeCreated = timeCreated;
    this.state = state;
  }

  public Offer(long id) {
    this.id = id;
  }

  public Offer id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @NotNull 
  @Schema(name = "id", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public Offer fromUser(String fromUser) {
    this.fromUser = fromUser;
    return this;
  }

  /**
   * Get fromUser
   * @return fromUser
  */
  @NotNull 
  @Schema(name = "fromUser", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("fromUser")
  public String getFromUser() {
    return fromUser;
  }

  public void setFromUser(String fromUser) {
    this.fromUser = fromUser;
  }

  public Offer sentGames(List<String> sentGames) {
    this.sentGames = sentGames;
    return this;
  }

  public Offer addSentGamesItem(String sentGamesItem) {
    if (this.sentGames == null) {
      this.sentGames = new ArrayList<>();
    }
    this.sentGames.add(sentGamesItem);
    return this;
  }

  /**
   * Get sentGames
   * @return sentGames
  */
  @NotNull 
  @Schema(name = "sentGames", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("sentGames")
  public List<String> getSentGames() {
    return sentGames;
  }

  public void setSentGames(List<String> sentGames) {
    this.sentGames = sentGames;
  }

  public Offer receivedGames(List<String> receivedGames) {
    this.receivedGames = receivedGames;
    return this;
  }

  public Offer addReceivedGamesItem(String receivedGamesItem) {
    if (this.receivedGames == null) {
      this.receivedGames = new ArrayList<>();
    }
    this.receivedGames.add(receivedGamesItem);
    return this;
  }

  /**
   * Get receivedGames
   * @return receivedGames
  */
  @NotNull 
  @Schema(name = "receivedGames", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("receivedGames")
  public List<String> getReceivedGames() {
    return receivedGames;
  }

  public void setReceivedGames(List<String> receivedGames) {
    this.receivedGames = receivedGames;
  }

  public Offer toUser(String toUser) {
    this.toUser = toUser;
    return this;
  }

  /**
   * Get toUser
   * @return toUser
  */
  @NotNull 
  @Schema(name = "toUser", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("toUser")
  public String getToUser() {
    return toUser;
  }

  public void setToUser(String toUser) {
    this.toUser = toUser;
  }

  public Offer timeCreated(String timeCreated) {
    this.timeCreated = timeCreated;
    return this;
  }

  /**
   * Get timeCreated
   * @return timeCreated
  */
  @NotNull @Valid 
  @Schema(name = "timeCreated", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("timeCreated")
  public String getTimeCreated() {
    return timeCreated;
  }

  public void setTimeCreated(String timeCreated) {
    this.timeCreated = timeCreated;
  }

  public Offer state(String state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @NotNull 
  @Schema(name = "state", requiredMode = Schema.RequiredMode.REQUIRED)
  @JsonProperty("state")
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Offer offer = (Offer) o;
    return Objects.equals(this.id, offer.id) &&
        Objects.equals(this.fromUser, offer.fromUser) &&
        Objects.equals(this.sentGames, offer.sentGames) &&
        Objects.equals(this.receivedGames, offer.receivedGames) &&
        Objects.equals(this.toUser, offer.toUser) &&
        Objects.equals(this.timeCreated, offer.timeCreated) &&
        Objects.equals(this.state, offer.state);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, fromUser, sentGames, receivedGames, toUser, timeCreated, state);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Offer {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    fromUser: ").append(toIndentedString(fromUser)).append("\n");
    sb.append("    sentGames: ").append(toIndentedString(sentGames)).append("\n");
    sb.append("    receivedGames: ").append(toIndentedString(receivedGames)).append("\n");
    sb.append("    toUser: ").append(toIndentedString(toUser)).append("\n");
    sb.append("    timeCreated: ").append(toIndentedString(timeCreated)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

