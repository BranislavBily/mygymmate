package db;


import sample.Modules.ModuleTables;
import db.DTO.RegisteredUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * and will be punished
 * This code is proprietary and confidential of the person stated bellow
 * Created by branislavbily on 11.01.2019
 * If you are confused, feel free to ask me <branislav.bily@gmail.com>
 */
public class DatabaseModuleUser {

    //Module for querying User related data from the database

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
        String query = "select ID from "+ ModuleTables.USERS+" where Username = ? and Password = ?";
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
        String query = "select status from " + ModuleTables.USERS + " where ID = ?";
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

    public String getUsername(int userID) {
        ResultSet resultSet;
        String query = "select username from " + ModuleTables.USERS + " where ID = ?";
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
        String query = "select * from "+ ModuleTables.USERS+" where Username = ?";
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
        String query = "insert into " + ModuleTables.USERS + " (username, password, status, firstName, lastName, weight, height, gender, typeOfTraining) values(?, ?, ?, ?, ?, ?, ?, ?, ?)";
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
//
//    public boolean registerUser(String name, String pass) throws SQLException {
//        String sql = "insert into users(username, password, status) values(?, ?, ?)";
//        return super.queryUpdateThreeValues(sql, name, pass, "user");
//    }

//    public User getUserFromName(String name) throws SQLException {
//        String sql = "select * from users where username =?";
//        PreparedStatement preparedStatement = null;
//        ResultSet resultSet;
//        try {
//            preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setString(1, name);
//            resultSet = preparedStatement.executeQuery();
//            if(resultSet.next()) {
//                return new User(resultSet.getInt("id"), resultSet.getString("username"), resultSet.getString("status"));
//            }
//            return null;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        } finally {
//            assert preparedStatement != null;
//            preparedStatement.close();
//        }
//    }
//
//    public boolean deleteUser(String name) throws SQLException {
//        String sql = "delete from users where username = ?";
//        return queryUpdateOneValue(sql, name);
//    }
//
//    public boolean updateUsername(String previousNameString, String name) throws SQLException {
//        String sql = "update users set username = ? where username = ?";
//        return queryUpdateTwoValues(sql, previousNameString, name);
//    }
//
//    public boolean updateUserPassword(String name, String password) throws SQLException {
//        String sql = "update users set password = ? where username = ?";
//        return queryUpdateTwoValues(sql, name, password);
//    }
}
