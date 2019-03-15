package db;

import sample.Moduls.ModuleTables;

import java.sql.*;

/**
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * and will be punished
 * This code is proprietary and confidential of the person stated bellow
 * Created by branislavbily on 14.12.2018
 * If you are confused, feel free to ask me <branislav.bily@gmail.com>
 */
public class DatabaseModule {

    private Connection connection;

    DatabaseModule() {
    }

    void setConnection(Connection connection) {
        this.connection = connection;
        if (!createTableUsers()) {
            System.out.println("ERROR while creating table users");
        }

        if (!createTableUserInfo()) {
            System.out.println("ERROR while crete table user info");
        }
    }

    private boolean createTableUsers() {
        String sql = createUserTableQuery();
        return executeSql(sql);
    }

    private boolean createTableUserInfo() {
        String sql = createUserInfoTableQuery();
        return executeSql(sql);
    }

    private boolean executeSql(String sql) {
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean isDbConnected() {
        try {
            return !connection.isClosed();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

//    boolean queryUpdateOneValue(String query, String value) throws SQLException {
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, value);
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            assert preparedStatement != null;
//            preparedStatement.close();
//        }
//    }
//
//    boolean queryUpdateTwoValues(String query, String value1, String value2) throws SQLException {
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, value1);
//            preparedStatement.setString(2, value2);
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            assert preparedStatement != null;
//            preparedStatement.close();
//        }
//    }
//
//    boolean queryUpdateThreeValues(String query, String value1, String value2, String value3) throws SQLException {
//        PreparedStatement preparedStatement = null;
//        try {
//            preparedStatement = connection.prepareStatement(query);
//            preparedStatement.setString(1, value1);
//            preparedStatement.setString(2, value2);
//            preparedStatement.setString(3, value3);
//            preparedStatement.executeUpdate();
//            return true;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return false;
//        } finally {
//            assert preparedStatement != null;
//            preparedStatement.close();
//        }
//    }

    private String createUserTableQuery() {
        return "create table if not EXISTS " + ModuleTables.USERS + "(" +
                "ID INTEGER primary key autoincrement," +
                "Username varchar(255) not null," +
                "Password varchar(255) not null ," +
                "Status varchar(255) not null, " +
                "Gender varchar(255) not null, " +
                "DateOfBirth varchar(255) not null," +
                "Email varchar(255)," +
                "PhoneNumber varchar(255))";
    }

    private String createUserInfoTableQuery() {
        return "CREATE TABLE IF NOT EXISTS " + ModuleTables.USER_INFO + "(" +
                "ID INTEGER primary key autoincrement," +
                "Weight real not null," +
                "Height real not null," +
                "RecommendedCalories real not null, " +
                "RecommendedProtein real not null, " +
                "RecommendedWater real not null, " +
                "TypeOfTraining varchar(255) not null," +
                "UserID INTEGER not null," +
                "FOREIGN KEY (UserID) REFERENCES Users(ID))";
    }
}
