package luke.findlay.emailservice.Models;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * User
 */

public class User implements OfferFromUserInner {

  private Long id;

  private String name;

  private String email;

  private String address;

  private String city;

  private String state;

  private String zip;

  private String password;

  private List<String> games;

  public User() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public User(Long id, String name, String email, String address, String city, String state, String zip, String password) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.address = address;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.password = password;
  }

  public User id(Long id) {
    this.id = id;
    return this;
  }

  /**
   * Get id
   * @return id
  */
  @JsonProperty("id")
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public User name(String name) {
    this.name = name;
    return this;
  }

  /**
   * Get name
   * @return name
  */
  @JsonProperty("name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public User email(String email) {
    this.email = email;
    return this;
  }

  /**
   * Get email
   * @return email
  */
  @JsonProperty("email")
  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public User address(String address) {
    this.address = address;
    return this;
  }

  /**
   * Get address
   * @return address
  */
  @JsonProperty("address")
  public String getAddress() {
    return address;
  }

  public void setAddress(String address) {
    this.address = address;
  }

  public User city(String city) {
    this.city = city;
    return this;
  }

  /**
   * Get city
   * @return city
  */
  @JsonProperty("city")
  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public User state(String state) {
    this.state = state;
    return this;
  }

  /**
   * Get state
   * @return state
  */
  @JsonProperty("state")
  public String getState() {
    return state;
  }

  public void setState(String state) {
    this.state = state;
  }

  public User zip(String zip) {
    this.zip = zip;
    return this;
  }

  /**
   * Get password
   * @return password
   */
  @JsonProperty("password")
  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  /**
   * Get zip
   * @return zip
  */
  @JsonProperty("zip")
  public String getZip() {
    return zip;
  }

  public void setZip(String zip) {
    this.zip = zip;
  }

  public User games(List<String> games) {
    this.games = games;
    return this;
  }

  public User addGamesItem(String gamesItem) {
    if (this.games == null) {
      this.games = new ArrayList<>();
    }
    this.games.add(gamesItem);
    return this;
  }

  /**
   * Get games
   * @return games
  */

  @JsonProperty("games")
  public List<String> getGames() {
    return games;
  }

  public void setGames(List<String> games) {
    this.games = games;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    User user = (User) o;
    return Objects.equals(this.id, user.id) &&
        Objects.equals(this.name, user.name) &&
        Objects.equals(this.email, user.email) &&
        Objects.equals(this.address, user.address) &&
        Objects.equals(this.city, user.city) &&
        Objects.equals(this.state, user.state) &&
        Objects.equals(this.zip, user.zip) &&
        Objects.equals(this.games, user.games);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, email, address, city, state, zip, games);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class User {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("    email: ").append(toIndentedString(email)).append("\n");
    sb.append("    address: ").append(toIndentedString(address)).append("\n");
    sb.append("    city: ").append(toIndentedString(city)).append("\n");
    sb.append("    state: ").append(toIndentedString(state)).append("\n");
    sb.append("    zip: ").append(toIndentedString(zip)).append("\n");
    sb.append("    games: ").append(toIndentedString(games)).append("\n");
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

