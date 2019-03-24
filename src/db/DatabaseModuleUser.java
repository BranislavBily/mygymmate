package db;


import sample.Modules.ModuleTables;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * and will be punished
 * This code is proprietary and confidential of the person stated bellow
 * Created by branislavbily on 11.01.2019
 * If you are confused, feel free to ask me <branislav.bily@gmail.com>
 */
public class DatabaseModuleUser extends DatabaseModule {

    //Module for querying User related data from the database

    private Connection connection;

    public DatabaseModuleUser() {
        super();
        connection = SqliteConnection.connector();
        if(connection == null) System.exit(1);
        super.setConnection(connection);
    }

    public boolean isUser(String user, String pass) {
        ResultSet resultSet;
        String query = "select * from "+ ModuleTables.USERS+" where Username = ? and Password = ? ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, user);
            preparedStatement.setString(2, pass);
            resultSet = preparedStatement.executeQuery();
            return resultSet.next();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
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
