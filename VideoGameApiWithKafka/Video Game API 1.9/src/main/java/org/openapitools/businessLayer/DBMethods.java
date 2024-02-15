package org.openapitools.businessLayer;

import org.openapitools.model.Game;
import org.openapitools.model.Offer;
import org.openapitools.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.format.DateTimeFormatters;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.SimpleDateFormat;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.sql.*;
import java.util.Date;

import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

public class DBMethods {


    private Environment environment;

    String url;
    String userName;
    String password;

    public DBMethods(Environment environment) {
        this.environment = environment;
        url = "jdbc:mysql://" + System.getenv("MYSQL_HOST") + ":3306/" + System.getenv("MYSQL_DB");
        userName = System.getenv("MYSQL_USER");
        password = System.getenv("MYSQL_PASSWORD");
    }

    public DBMethods() {

    }

    public boolean createOffer(Offer offer) {

        String sql = "INSERT INTO games_exchange.offers(fromUser, toUser, timeCreated, state) Values (?, ?, ?, ?)";
        log();

        try {
            Date date = new Date();
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, offer.getFromUser());
            pst.setString(2, offer.getToUser());
            pst.setString(3, date.toString());
            pst.setString(4, offer.getState());
            int count = pst.executeUpdate();
            return (count > 0);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;
    }

    public boolean deleteOffer(String offerId) {

        String sql = "DELETE FROM games_exchange.offers WHERE id = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, offerId);
            int count = pst.executeUpdate();
            return (count > 0);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return false;
    }

    public boolean addGameOffer(String offerId, String gameId, boolean isSent) {


        String sql = "INSERT INTO games_exchange.offers_games(offerId, gameid, isSent) Values (?, ?, ?)";
        String sql2 = "SELECT * FROM games_exchange.offers_games";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                if (String.valueOf(rs.getLong("gameid")).equals(gameId))
                    return false;
            }
            pst.setString(1, offerId);
            pst.setString(2, gameId);
            pst.setBoolean(3, isSent);
            int count = pst.executeUpdate();
            return (count > 0);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;

    }

    public boolean deleteGameOffer(String offerId, String gameId, boolean isSent) {


        String sql = "DELETE FROM games_exchange.offers_games WHERE offerid = ? AND gameid = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, offerId);
            pst.setString(2, gameId);
            int count = pst.executeUpdate();
            return (count > 0);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;


    }


    public Offer getOneOffer(String offerId) {

        String sql = "SELECT * FROM games_exchange.offers WHERE id = ?";
        String gameSql = "SELECT * FROM games_exchange.offers_games WHERE offerid = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst2 = con.prepareStatement(gameSql);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, offerId);
            pst2.setString(1, offerId);
            ResultSet rs = pst.executeQuery();
            ResultSet rs2 = pst2.executeQuery();
            Offer offer = null;
            while (rs.next()) {
                offer = new Offer(rs.getLong("id"));
                offer.setFromUser("http://localhost:8080/users/" + rs.getLong("fromUser"));
                offer.setToUser("http://localhost:8080/users/" + rs.getLong("toUser"));
                offer.setTimeCreated(rs.getString("timeCreated"));
                offer.setState(rs.getString("state"));
            }
            while (rs2.next()) {
                byte isSent = rs2.getByte("isSent");
                assert offer != null;
                if (isSent == 1) {
                    offer.addSentGamesItem(("http://localhost:8080/games/") + rs2.getLong("gameid"));
                } else {
                    offer.addReceivedGamesItem(("http://localhost:8080/games/") + rs2.getLong("gameid"));
                }
            }
            return offer;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public List<Offer> listOffers() {

        String sql = "SELECT * FROM games_exchange.offers";
        String gameSql = "SELECT * FROM games_exchange.offers_games";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst2 = con.prepareStatement(gameSql);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSet rs2 = pst2.executeQuery();
            List<Offer> offers = new ArrayList<>();
            Offer offer = null;
            while (rs.next()) {
                offer = new Offer(rs.getLong("id"));
                offer.setFromUser("http://localhost:8080/users/" + rs.getLong("fromUser"));
                offer.setToUser("http://localhost:8080/users/" + rs.getLong("toUser"));
                offer.setTimeCreated(rs.getString("timeCreated"));
                offer.setState(rs.getString("state"));
                while (rs2.next()) {
                    if (rs2.getLong("offerId") == offer.getId()) {
                        byte isSent = rs2.getByte("isSent");
                        if (isSent == 1) {
                            offer.addSentGamesItem(("http://localhost:8080/games/") + rs2.getLong("gameid"));
                        } else {
                            offer.addReceivedGamesItem(("http://localhost:8080/games/") + rs2.getLong("gameid"));
                        }
                    }
                }
                offers.add(offer);
            }
            return offers;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public boolean updateOffer(String offerId, Offer offer) {


        String sql = "UPDATE games_exchange.offers SET fromUser = ?, toUser = ?, timeCreated = ? , state = ? WHERE id = ?";
        log();

        try {
            Date date = new Date();
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, offer.getFromUser());
            pst.setString(2, offer.getToUser());
            pst.setString(3, date.toString());
            pst.setString(4, offer.getState());
            pst.setString(5, offerId);
            int count = pst.executeUpdate();
            return (count > 0);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;

    }

    public boolean updateSomeOffer(String offerId, Offer offer) {

        String sql = "UPDATE games_exchange.offers SET ";
        log();
        Date date = new Date();

        Map<String, Object> offerAttributes = new HashMap<>();
        if (!offer.getFromUser().isEmpty())
            offerAttributes.put("fromUser", offer.getFromUser());
        if (!offer.getToUser().isEmpty())
            offerAttributes.put("email", offer.getToUser());
        offerAttributes.put("timeCreated", date.toString());
        if (!offer.getState().isEmpty())
            offerAttributes.put("state", offer.getState());

        int index = 1;

        for (Map.Entry<String, Object> entry : offerAttributes.entrySet()) {
            if (offerAttributes.size() == index)
                sql = sql + entry.getKey() + " = '" + entry.getValue() + "' ";
            else
                sql = sql + entry.getKey() + " = '" + entry.getValue() + "', ";

            index++;
        }

        sql = sql + "WHERE id = " + offerId;

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            int count = pst.executeUpdate();
            return (count > 0);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return false;
    }

    public boolean addGameUser(String userId, String gameId) {

        String sql = "INSERT INTO games_exchange.users_games(userid, gameid) Values (?, ?)";
        String sql2 = "SELECT * FROM games_exchange.users_games";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            PreparedStatement pst2 = con.prepareStatement(sql2);
            ResultSet rs = pst2.executeQuery();
            while (rs.next()) {
                if (String.valueOf(rs.getLong("gameid")).equals(gameId))
                    return false;
            }
            pst.setString(1, userId);
            pst.setString(2, gameId);
            int count = pst.executeUpdate();
            return (count > 0);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;
    }

    public boolean deleteGameUser(String userId, String gameId) {


        String sql = "DELETE FROM games_exchange.users_games WHERE userid = ? AND gameid = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, userId);
            pst.setString(2, gameId);
            int count = pst.executeUpdate();
            return (count > 0);
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
        return false;


    }

    public void createUser(User user) {

        String sql = "INSERT INTO games_exchange.users(name, email, address, city, state, zip, password) Values (?, ?, ?, ?, ?, ?, ?)";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getAddress());
            pst.setString(4, user.getCity());
            pst.setString(5, user.getState());
            pst.setString(6, user.getZip());
            pst.setString(7, user.getPassword());
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public void deleteUser(String id) {


        String sql = "DELETE FROM games_exchange.users WHERE id = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }


    }

    public User getOneUser(String id) {

        String sql = "SELECT * FROM games_exchange.users WHERE id = ?";
        String gameSql = "SELECT * FROM games_exchange.users_games WHERE userid = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst2 = con.prepareStatement(gameSql);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst2.setString(1, id);
            ResultSet rs = pst.executeQuery();
            ResultSet rs2 = pst2.executeQuery();
            User user = null;
            while (rs.next()) {
                user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("password"));
            }
            while (rs2.next()) {
                user.addGamesItem(("http://localhost:8080/games/") + String.valueOf(rs2.getLong("gameid")));
            }
            return user;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public List<User> getAllUsers() {

        String sql = "SELECT * FROM games_exchange.users";
        String gameSql = "SELECT * FROM games_exchange.users_games WHERE userid = ?";
        log();

        try {
            List<User> users = new ArrayList<>();
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ;
            while (rs.next()) {
                User user = new User(rs.getLong("id"), rs.getString("name"), rs.getString("email"), rs.getString("address"), rs.getString("city"), rs.getString("state"), rs.getString("zip"), rs.getString("password"));
                PreparedStatement pst2 = con.prepareStatement(gameSql);
                pst2.setString(1, String.valueOf(user.getId()));
                ResultSet rs2 = pst2.executeQuery();
                while (rs2.next()) {
                    user.addGamesItem(("http://localhost:8080/games/") + rs2.getLong("gameid"));
                }
                users.add(user);
            }
            return users;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public void updateSomeUser(String id, User user) {
        String sql = "UPDATE games_exchange.users SET ";
        log();

        Map<String, Object> userAttributes = new HashMap<>();
        if (!user.getName().isEmpty())
            userAttributes.put("name", user.getName());
        if (!user.getEmail().isEmpty())
            userAttributes.put("email", user.getEmail());
        if (!user.getAddress().isEmpty())
            userAttributes.put("address", user.getAddress());
        if (!user.getCity().isEmpty())
            userAttributes.put("city", user.getCity());
        if (!user.getState().isEmpty())
            userAttributes.put("state", user.getState());
        if (!user.getZip().isEmpty())
            userAttributes.put("zip", user.getZip());
        if (!user.getPassword().isEmpty())
            userAttributes.put("password", user.getPassword());

        int index = 1;

        for (Map.Entry<String, Object> entry : userAttributes.entrySet()) {
            if (userAttributes.size() == index)
                sql = sql + entry.getKey() + " = '" + entry.getValue() + "' ";
            else
                sql = sql + entry.getKey() + " = '" + entry.getValue() + "', ";

            index++;
        }

        sql = sql + "WHERE id = " + id;

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }


    }

    public void updateUser(String id, User user) {


        String sql = "UPDATE games_exchange.users SET name = ?, email = ?, address = ?, city = ?, state = ?, zip = ?, password = ? WHERE id = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, user.getName());
            pst.setString(2, user.getEmail());
            pst.setString(3, user.getAddress());
            pst.setString(4, user.getCity());
            pst.setString(5, user.getState());
            pst.setString(6, user.getZip());
            pst.setString(7, user.getPassword());
            pst.setString(8, id);
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }

    public void createGame(Game game) {

        String sql = "INSERT INTO games_exchange.games(title, publisher, publishYear, `condition`, numOwners, console) Values (?, ?, ?, ?, ?, ?)";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, game.getTitle());
            pst.setString(2, game.getPublisher());
            pst.setString(3, game.getPubishYear());
            pst.setString(4, game.getCondition());
            pst.setString(5, String.valueOf(game.getNumOwners()));
            pst.setString(6, game.getConsole());
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }
    }

    public void deleteGame(String id) {


        String sql = "DELETE FROM games_exchange.games WHERE id = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }

    public Game getOneGame(String id) {

        String sql = "SELECT * FROM games_exchange.games WHERE id = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Game game = new Game(rs.getLong("id"), rs.getString("title"), rs.getString("publisher"), rs.getString("condition"), rs.getString("console"));
                game.setPubishYear(rs.getString("publishYear"));
                game.setNumOwners(rs.getInt("numOwners"));
                return game;
            }
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public List<Game> getAllGames() {

        String sql = "SELECT * FROM games_exchange.games";
        log();

        try {
            List<Game> games = new ArrayList<>();
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                Game game = new Game(rs.getLong("id"), rs.getString("title"), rs.getString("publisher"), rs.getString("condition"), rs.getString("console"));
                game.setPubishYear(rs.getString("publishYear"));
                game.setNumOwners(rs.getInt("numOwners"));
                games.add(game);
            }
            return games;
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

        return null;
    }

    public void updateGame(String id, Game game) {


        String sql = "UPDATE games_exchange.games SET title = ?, publisher = ?, publishYear = ?, `condition` = ?, numOwners = ?, console = ? WHERE id = ?";
        log();

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, game.getTitle());
            pst.setString(2, game.getPublisher());
            pst.setString(3, game.getPubishYear());
            pst.setString(4, game.getCondition());
            pst.setString(5, String.valueOf(game.getNumOwners()));
            pst.setString(6, game.getConsole());
            pst.setString(7, id);
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }

    }

    public void updateSomeGame(String id, Game game) {
        String sql = "UPDATE games_exchange.games SET ";
        log();

        Map<String, Object> gameAttributes = new HashMap<>();
        if (!game.getTitle().isEmpty())
            gameAttributes.put("title", game.getTitle());
        if (!game.getPublisher().isEmpty())
            gameAttributes.put("publisher", game.getPublisher());
        if (!game.getPubishYear().isEmpty())
            gameAttributes.put("publishYear", game.getPubishYear());
        if (!game.getCondition().isEmpty())
            gameAttributes.put("`condition`", game.getCondition());
        if (!(game.getNumOwners() == 0))
            gameAttributes.put("numOwners", game.getNumOwners());
        if (!game.getConsole().isEmpty())
            gameAttributes.put("console", game.getConsole());

        int index = 1;

        for (Map.Entry<String, Object> entry : gameAttributes.entrySet()) {
            if (gameAttributes.size() == index)
                sql = sql + entry.getKey() + " = '" + entry.getValue() + "' ";
            else
                sql = sql + entry.getKey() + " = '" + entry.getValue() + "', ";

            index++;
        }

        sql = sql + "WHERE id = " + id;

        try {
            Connection con = DriverManager.getConnection(url, userName, password);
            PreparedStatement pst = con.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException sqlException) {
            System.out.println(sqlException.getMessage());
        }


    }

    private void log() {
        System.out.println("Responding");
    }

}
