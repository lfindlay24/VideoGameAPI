package luke.findlay.emailservice.Models;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.annotation.processing.Generated;
import java.util.Objects;

/**
 * Game
 */

@Generated(value = "org.openapitools.codegen.languages.SpringCodegen", date = "2024-01-21T13:18:55.670880400-07:00[America/Denver]")
public class Game implements UserGamesInner {

  private Long id;

  private String title;

  private String publisher;

  private String pubishYear;

  private String condition;

  private String console;

  private Integer numOwners;

  public Game() {
    super();
  }

  /**
   * Constructor with only required parameters
   */
  public Game(Long id, String title, String publisher, String condition, String console) {
    this.id = id;
    this.title = title;
    this.publisher = publisher;
    this.condition = condition;
    this.console = console;
  }

  public Game id(Long id) {
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

  public Game title(String title) {
    this.title = title;
    return this;
  }

  /**
   * Get title
   * @return title
  */
  @JsonProperty("title")
  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public Game publisher(String publisher) {
    this.publisher = publisher;
    return this;
  }

  /**
   * Get publisher
   * @return publisher
  */
  @JsonProperty("publisher")
  public String getPublisher() {
    return publisher;
  }

  public void setPublisher(String publisher) {
    this.publisher = publisher;
  }

  public Game pubishYear(String pubishYear) {
    this.pubishYear = pubishYear;
    return this;
  }

  /**
   * Get pubishYear
   * @return pubishYear
  */
  public String getPubishYear() {
    return pubishYear;
  }

  public void setPubishYear(String pubishYear) {
    this.pubishYear = pubishYear;
  }

  public Game condition(String condition) {
    this.condition = condition;
    return this;
  }

  /**
   * Get condition
   * @return condition
  */
  @JsonProperty("condition")
  public String getCondition() {
    return condition;
  }

  public void setCondition(String condition) {
    this.condition = condition;
  }

  public Game console(String console) {
    this.console = console;
    return this;
  }

  /**
   * Get console
   * @return console
  */
  @JsonProperty("console")
  public String getConsole() {
    return console;
  }

  public void setConsole(String console) {
    this.console = console;
  }

  public Game numOwners(Integer numOwners) {
    this.numOwners = numOwners;
    return this;
  }

  /**
   * Get numOwners
   * @return numOwners
  */

  @JsonProperty("numOwners")
  public Integer getNumOwners() {
    return numOwners;
  }

  public void setNumOwners(Integer numOwners) {
    this.numOwners = numOwners;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Game game = (Game) o;
    return Objects.equals(this.id, game.id) &&
        Objects.equals(this.title, game.title) &&
        Objects.equals(this.publisher, game.publisher) &&
        Objects.equals(this.pubishYear, game.pubishYear) &&
        Objects.equals(this.condition, game.condition) &&
        Objects.equals(this.console, game.console) &&
        Objects.equals(this.numOwners, game.numOwners);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, title, publisher, pubishYear, condition, console, numOwners);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class Game {\n");
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    title: ").append(toIndentedString(title)).append("\n");
    sb.append("    publisher: ").append(toIndentedString(publisher)).append("\n");
    sb.append("    pubishYear: ").append(toIndentedString(pubishYear)).append("\n");
    sb.append("    condition: ").append(toIndentedString(condition)).append("\n");
    sb.append("    console: ").append(toIndentedString(console)).append("\n");
    sb.append("    numOwners: ").append(toIndentedString(numOwners)).append("\n");
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

