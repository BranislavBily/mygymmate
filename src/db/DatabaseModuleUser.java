package db;


import db.DTO.ProfileData;
import db.DTO.RegisteredUser;
import sample.Resources.Tables;
import sample.Session;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Module for handling data from Users table
 */
public class DatabaseModuleUser {

    private Connection connection;

    public DatabaseModuleUser() {

        connection = SqliteConnection.connector();
        if(connection == null) System.exit(1);
    }

    /**
     * Method that returns user ID from the database if logging credentials
     * where correct, <code>null</code> if they were not
     *
     * @param username of the user trying to log in
     * @param password of the user trying to log in
     * @return <code>null</code> if logging credential where wrong, ID of user if credentials were found
     */
    //TODO Javadoc
    public Integer isUser(String username, String password) {
        ResultSet resultSet;
        String query = "select ID from "+ Tables.USERS+" where Username = ? and Password = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getInt("ID");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getUserStatus(int userID) {
        ResultSet resultSet;
        String query = "select status from " + Tables.USERS + " where ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString("status");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Method used for loading username into Labels
     * @param userID ID of user in database
     * @return String with username of user, <code>null</code> if no user is found
     */
    public String getUsername(int userID) {
        ResultSet resultSet;
        String query = "select username from " + Tables.USERS + " where ID = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                return resultSet.getString("username");
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean isUsernameTaken(String username) {
        ResultSet resultSet;
        String query = "select * from "+ Tables.USERS+" where Username = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean insertUserIntoDatabase(RegisteredUser registerUser) {
        String query = "insert into " + Tables.USERS + " (username, password, status, firstName, lastName, weight, height, gender, typeOfTraining) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, registerUser.getUsername());
            preparedStatement.setString(2, registerUser.getPassword());
            preparedStatement.setString(3, registerUser.getStatus());
            preparedStatement.setString(4, registerUser.getFirstName());
            preparedStatement.setString(5, registerUser.getLastName());
            preparedStatement.setDouble(6, registerUser.getWeight());
            preparedStatement.setDouble(7, registerUser.getHeight());
            preparedStatement.setString(8, registerUser.getGender());
            preparedStatement.setString(9, registerUser.getTypeOfTraining());
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public ProfileData loadUserProfileData(int userID) {
        ResultSet resultSet;
        ProfileData profileData = new ProfileData();
        String query = "select * from " + Tables.USERS + " where ID = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            resultSet = preparedStatement.executeQuery();
            if(resultSet.next()) {
                profileData.setRealName(resultSet.getString("FirstName") + " " + resultSet.getString("LastName"));
                profileData.setGender(resultSet.getString("gender"));
                profileData.setStatus(resultSet.getString("status"));
                profileData.setTypeOfTraining(resultSet.getString("typeOfTraining"));
                profileData.setUsername(resultSet.getString("username"));
                return profileData;
            }
            return null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean updateUser(ProfileData profileData) {
        int userID = Session.getUserID();
        String [] name = profileData.getRealName().split(" ");
        String query = "update " + Tables.USERS + " set username = ?, status = ?, firstName = ?, lastName = ?, " +
                "gender = ?, typeOfTraining = ? where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, profileData.getUsername());
            preparedStatement.setString(2,profileData.getStatus());
            preparedStatement.setString(3, name[0]);
            preparedStatement.setString(4,name[1]);
            preparedStatement.setString(5,profileData.getGender());
            preparedStatement.setString(6,profileData.getTypeOfTraining());
            preparedStatement.setInt(7, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteLoggedInUser() {
        int userID = Session.getUserID();
        String query = "delete from " + Tables.USERS + " where id = ?";
        try(PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setInt(1, userID);
            return preparedStatement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

}
